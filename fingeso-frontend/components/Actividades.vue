<template>
    <div>
        <h1>Actividades</h1>
        {{ solicitud }}
        
        <template>
        <v-expansion-panels>
            <v-expansion-panel
            v-for="(subcategoria, i) in subcategorias"
            :key="i"
            >
                <v-expansion-panel-header>
                    {{ subcategoria.text }}
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-expansion-panels>
                    <v-expansion-panel
                    v-for="(grupoActividades, j) in subcategoria.grupoActividades"
                    :key="j"
                    >
                            <v-container v-if="(grupoActividades.input === false)">
                                <v-expansion-panel-header>
                                    {{ grupoActividades.text }}
                                </v-expansion-panel-header>
                                    <v-expansion-panel-content>
                                        <v-row justify="center">
                                            <v-expansion-panels inset>
                                            <v-expansion-panel
                                                v-for="(actividad, k) in grupoActividades.actividades"
                                                :key="k"
                                            >
                                                <v-expansion-panel-header>{{ actividad.text }}</v-expansion-panel-header>
                                                <v-expansion-panel-content>
                                                    <template>
                                                        <div class="d-flex justify-end align-center">
                                                            <v-file-input
                                                                show-size
                                                                counter
                                                                multiple
                                                                label="File input"
                                                                v-model="fileInputs[k]"
                                                                class="mr-8" 
                                                            ></v-file-input>
                                                            
                                                            <v-btn @click="uploadFile(k, grupoActividades.actividades.id)" class="mx-16 blue white--text">
                                                                    Subir
                                                                </v-btn>
                                                                
                                                        </div>
                                                        </template>
                                                </v-expansion-panel-content>
                                            </v-expansion-panel>
                                            </v-expansion-panels>
                                        </v-row>
                                    </v-expansion-panel-content>
                            </v-container>
                            <v-container v-if="(grupoActividades.input === true)">
                                <v-expansion-panel-header>
                                    {{ grupoActividades.text }}
                                </v-expansion-panel-header>
                                <v-expansion-panel-content>
                                    <template>
                                        <div class="d-flex justify-end align-center">
                                            <v-file-input
                                                show-size
                                                counter
                                                multiple
                                                label="File input"
                                                v-model="fileInputs[j]"
                                                class="mr-8" 
                                            ></v-file-input>
                                            
                                            <v-btn @click="uploadFile(j, grupoActividades.id)" class="mx-16 blue white--text">
                                                    Subir
                                                </v-btn>
                                                
                                        </div>
                                        </template>
                                    </v-expansion-panel-content>
                            </v-container>
                        </v-expansion-panel>
                    </v-expansion-panels>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-expansion-panels>
</template>

    </div>
</template>

<script>
    import axios from 'axios';
    export default{
        name: 'Actividades',
        props: ['subcategorias', 'solicitud'],    
        data(){
            return{
                fileInputs: [],
                fileDownload: new Set(),
                index: 0
            }
        },
        methods: {
            uploadFile(i, id){
                console.log(this.fileInputs[i]);
                this.fileInputs[i].forEach(async (file, j) => {
                    console.log(file);    
                    console.log(id);         
                    const formData = new FormData();
                    formData.append('file', file);
                    formData.append('id', id);
                    const headers = {
                        'Content-Type': 'multipart/form-data',                        
                    };
                    await axios.post('http://localhost:3000/api/uploadFile', formData, { headers }).then((res) => {
                        console.log(res.data);
                        
                        
                    });    
                    console.log(this.fileDownload);
                })
            }
        }
    }
</script>