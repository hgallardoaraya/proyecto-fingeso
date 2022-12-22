
<template>
    <div>

        <div class="d-flex justify-start flex-column" v-if="show === 'solicitudes'">
            <h2 class="my-8 accent--text">
                Mis Evaluaciones - <span class="blue--text">{{ this.user.comite[0] }}</span>
            </h2>
            <v-data-table
            style="width: 80%;"
            :headers="headers"
            :items="items"
            :items-per-page="5"
            item-key="name"
            class="elevation-1"
            :footer-props="{
                showFirstLastPage: true,
                firstIcon: 'mdi-arrow-collapse-left',
                lastIcon: 'mdi-arrow-collapse-right',
                prevIcon: 'mdi-minus',
                nextIcon: 'mdi-plus'
            }"
        >
    
            <template v-slot:item.accion="{ item }">
                <v-icon                                
                class="mr-2"              
                @click="getSolicitud(item)"  
                >
                mdi-arrow-right-bold
                </v-icon>            
            </template>
        </v-data-table>
    </div>
    <Solicitud v-if="show === 'solicitud'" :solicitud="solicitud"></Solicitud>
</div>
</template>


<script>
    import axios from 'axios';
    import isAuthenticated from '../../utils/isAuthenticated';
    import Solicitud from '../../components/Solicitud.vue'
    
    export default {
        data(){
            return{
                headers: [
                    {
                        text: 'Número',
                        value: 'numero'
                    },
                    {
                        text: 'Semestre',
                        value: 'semestre'
                    },                    
                    {
                        text: 'Resultado',
                        value: 'resultado'
                    },
                    {
                        text: 'Acción',
                        value: 'accion',
                        align: 'center'
                    },
                    
                ],
                items: [],
                solicitudes: [],
                solicitud: [],
                show: "solicitudes",
                components: {
                    Solicitud
                },
                user: {}
            }
        },
        created(){                
            if(!isAuthenticated()){
                this.$router.push({ path: "/login" })
            }

            this.user = JSON.parse(localStorage.getItem('user'));
            
            const fetchUsuario = async () => {                                            
                const response = await axios.get('http://localhost:3000/api/usuario/evaluaciones', {                
                    headers: {
                        'username': JSON.parse(localStorage.getItem('user')).username
                    }
                })                    
                response.data.map((solicitud, i) => {
                    const row = {
                        numero: i,
                        semestre: solicitud.periodo,                        
                        resultado: solicitud.resultado_final,
                        accion: "Click",
                        id: solicitud.id
                    }
                    this.solicitudes = response.data;
                    this.items.push(row);
                })

                console.log(this.solicitudes);
                            
            }
            fetchUsuario();
        },
        methods: {
            getSolicitud(item){
                this.solicitud = this.solicitudes[item.numero];                                                
                this.$router.push({ path: "/evaluaciones/evaluacion", query: this.solicitud})
                // this.show = "solicitud";                
            }
        }
    }
</script>