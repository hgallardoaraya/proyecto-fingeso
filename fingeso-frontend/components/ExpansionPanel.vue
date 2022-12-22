<template>
    <v-expansion-panels inset>        
        <v-expansion-panel>
            <v-expansion-panel-header :class="this.documentoSubido && items.input ? '' : (!this.documentoSubido && items.input && type==='evaluacion' ? 'red lighten-3' : '')">{{ items.text }}</v-expansion-panel-header>
            <v-expansion-panel-content  >
                <template v-if="items.input">
                    <div class="d-flex justify-end align-center" v-if="type === 'solicitud'">
                        <v-file-input
                            show-size
                            counter
                            multiple                            
                            label="File input"                            
                            v-model="file"
                            class="mr-8" 
                        ></v-file-input>
                        <v-btn 
                            class="mx-16 blue white--text" 
                            @click="uploadFile(items)"
                        >
                            Subir
                        </v-btn>                            
                    </div>
                    <a class="blue--text " v-if="documentoSubido" :href="'http://localhost:3000/api' + documentoSubido?.downloadUri">{{ documentoSubido?.fileName }}</a>
                    <div class="d-flex align-center mt-4 justify-space-between" v-if="type === 'evaluacion'">
                        <div class="d-flex align-end">
                            <v-text-field                            
                                :label="this.documentoSubido ? ('0 - ' + items.puntaje_maximo + ' puntos') : 'No existen respaldos'"
                                solo       
                                class="d-flex shrink mr-12"
                                type="number"
                                :max="items.puntaje_maximo"                             
                                v-model="puntaje"         
                                :rules="[puntaje => puntaje <= items.puntaje_maximo || `Estás superando el puntaje máximo (${items.puntaje_maximo})`]"                   
                                :disabled="this.documentoSubido ? false : true"                
                                hide-details="auto"
                            >                                                                     
                            </v-text-field>
                            <v-checkbox
                                v-model="valido"
                                :class="this.documentoSubido ? 'd-flex flex-column' : 'd-none'"
                                label="El archivo es válido"
                                color="success"                                                        
                                hide-details
                            ></v-checkbox>                    
                        </div>
                    <v-btn 
                            class="mx-16 blue white--text" 
                            @click="addPuntaje()"
                        >
                            Puntuar
                        </v-btn>   
                    </div>
                
                    </template>
                    <ExpansionPanel v-else v-for="(item, i) in subItems" :key="i" v-bind:items="item" v-bind:subItems="item.subItems" v-bind:solicitud="solicitud" v-bind:comite="comite" v-bind:type="type"/>
                </v-expansion-panel-content>
        </v-expansion-panel>
        </v-expansion-panels>
</template>

<script>
import ExpansionPanel from './ExpansionPanel.vue'

    export default {
        name: 'ExpansionPanel',
        props: ['items', 'subItems', 'solicitud', 'comite', 'type'],
        data(){
            return{
                file: null,
                components: {
                    ExpansionPanel
                },
                documentoSubido: null,
                puntaje: null,
                valido: null
            }
        },
        methods: {
            async uploadFile(items){
                if(this.file === null || this.file[0] === null) return false;

                console.log("Archivo", this.file);
                console.log("Items", items);
                console.log("Solicitud", this.solicitud);    
                
                
                const documentos = this.solicitud.documentos.filter(doc => doc.tipoRespaldo === items.tipo);                                
                let documento = null;   
                documentos.forEach(doc => {
                    if(doc[doc.tipoRespaldo].id === items.id){
                        documento = doc;
                        return;
                    }
                })   
                                   
                const formData = new FormData();
                formData.append('file', this.file[0]);
                formData.append('id_documento', documento?.id || -1);
                formData.append('id_tipo', items.id);
                formData.append('id_solicitud', this.solicitud.id)
                formData.append('tipo_respaldo', items.tipo);
                const headers = {
                    'Content-Type': 'multipart/form-data',                        
                };                                   
                try{
                    const response = await this.$axios.post("/uploadFile", formData, { headers });
                
                    this.documentoSubido = response.data;
                    alert("Archivo subido con exito");
                }catch{
                    alert("Error al subir el archivo")
                }
            },
            async addPuntaje(){
                console.log(this.puntaje, this.valido);

                if(this.puntaje != null && this.puntaje <= this.items.puntaje_maximo){                    
                    const params = new URLSearchParams();
                    params.append('id_documento', this.documentoSubido.id);
                    params.append('puntaje', this.puntaje);                
                    params.append('valido', this.valido);
                    const response = await this.$axios.post("/documento/addPuntaje", params, { withCredentials: true  });                    
                    console.log(response);
                }else{
                    alert("No has ingresado un puntaje válido.");
                }                
            }
        },
        mounted(){
            const loadFiles = async () => {
                console.log("Items", this.items);
                console.log("Solicitud", this.solicitud);                
                if(this.items?.input || !this.documentoSubido){
                    try{
                        const response = await this.$axios.get(`/documento/${this.items?.tipo}/${this.items?.id}`);                        
                        if(response.data.fileName !== "null"){                        
                            this.documentoSubido = response.data;
                        }                        
                    }catch(e){
                        return e;
                    }
                }
            }
            loadFiles();

        }   
    }
</script>