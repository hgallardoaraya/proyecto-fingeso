<template>
    <v-expansion-panels inset>
        <v-expansion-panel>
            <v-expansion-panel-header>{{ items.text }}</v-expansion-panel-header>
            <v-expansion-panel-content  >
                <template v-if="items.input">
                    <div class="d-flex justify-end align-center">
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
                    </template>
                    <ExpansionPanel v-else v-for="(item, i) in subItems" :key="i" v-bind:items="item" v-bind:subItems="item.subItems" v-bind:solicitud="solicitud"/>
                </v-expansion-panel-content>
        </v-expansion-panel>
        </v-expansion-panels>
</template>

<script>
    import Solicitudes from '../pages/solicitudes.vue';
import ExpansionPanel from './ExpansionPanel.vue'
    export default{
        name: 'ExpansionPanel',
        props: ['items', 'subItems', 'solicitud'],
        data(){
            return{
                file: null,
                components: {
                    ExpansionPanel
                }
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
                console.log(documento);                

                const formData = new FormData();
                formData.append('file', this.file[0]);
                formData.append('id_documento', documento?.id || -1);
                formData.append('id_tipo', items.id);
                formData.append('id_solicitud', this.solicitud.id)
                formData.append('tipo_respaldo', items.tipo);
                const headers = {
                    'Content-Type': 'multipart/form-data',                        
                };                                   
                
                const response = await this.$axios.post("/uploadFile", formData, { headers });
                console.log(response);
            }
        },
        created(){
            console.log("Items", this.items);
            console.log("Solicitud", this.solicitud);
        }   
    }
</script>