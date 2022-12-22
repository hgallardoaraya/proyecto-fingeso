<template>
    <div>
        <div v-if="show === 'solicitud'">
            <h2 class="my-8 accent--text">Evaluación Periodo {{ solicitud.periodo }}</h2>            
            <v-container class="d-flex">
                <v-card
                    elevation="2"
                    width="300"
                    class="mr-10"
                    v-for="(categoria, i) in categorias"
                    :key="i"
                >       
                    <div class="d-flex justify-center">
                        <v-img
                        v-bind:src="require('~/assets/img/' + categoria.img)"
                        style="width: 100%; height: 230px;"
                        >
                        </v-img>                  
                    </div>
                    <v-divider></v-divider>
                    <div class="d-flex flex-column justify-space-around">
                        <v-card-title class="mb-1 font-weight-bold">
                            {{ categoria.text}}
                        </v-card-title>
                        <v-card-text class="accent--text">
                            <p class="mb-1 accent--text font-weight-medium">
                                Puntaje: 
                                <span class="primary--text">
                                    500/600
                                </span>
                            </p>                  
                        </v-card-text>
                    </div>
                    <v-divider></v-divider>
                    <v-card-actions class="d-flex justify-end align-end">
                        <v-btn
                            right   
                            color="blue"
                            text
                            @click="getSubCategorias(categoria)"
                            >
                            Ver Actividades
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-container>
            
            <v-btn 
                v-if="type==='evaluacion'"
                color="primary"
                class="mt-8"
                @click="sendPuntuacion()"
            >
                Finalizar Evaluacion
            </v-btn>
        </div>
        <Actividades v-if="show === 'actividades'" v-bind:items="subcategorias" v-bind:solicitud="solicitud" v-bind:type="type"/>
    </div>
</template>

<script>
    import axios from 'axios';
    import Actividades from '~/components/Actividades.vue'
    import isAuthenticated from '~/utils/isAuthenticated';

    export default{
        data(){
            return{
                categorias: [],
                subcategorias: [],
                show: 'solicitud',
                type: 'evaluacion',
                components: {
                    Actividades
                },
                solicitud: {}
            }
        },
        created(){
            if(!isAuthenticated()){
                this.$router.push({ path: "/login" })
            }

            this.solicitud = this.$route.query;

            const getCategorias = async () => {
                const response = await axios.get('http://localhost:3000/api/categorias');
                this.categorias = response.data;
                console.log("categorias", this.categorias);
            }

            getCategorias();
        },
        methods: {
            async getSubCategorias(categoria){
                this.subcategorias = categoria.subItems; 
                console.log("categoria sub items", categoria.subItems);
                this.show = 'actividades';                
            },
            async sendPuntuacion(){
                const params = new URLSearchParams();
                params.append('id_solicitud', this.solicitud.id);
                const response = await this.$axios.post("/evaluacion/finalizarPuntuacion", params);                    
                alert("Evaluación realizada con éxito!");
                console.log(response);
            }
        }
    }
</script>