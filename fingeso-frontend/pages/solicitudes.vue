
<template>
    <div class="d-flex justify-start flex-column">
        <h2 class="my-8 accent--text">
            Mis Solicitudes
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
</template>


<script>
    import axios from 'axios';
    import parseJwt from '../utils/parseJwt';
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
                        text: 'Estado',
                        value: 'estado'
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
                solicitudes: []
            }
        },
        created(){            
            const fetchUsuario = async () => {
                const token = this.$auth.strategy.token.get();
                if(token){
                    const credentials = parseJwt(token);
                    console.log(credentials);
                    const response = await axios.get('http://localhost:3000/api/usuario/solicitudes', {                
                        headers: {
                            'username': credentials.sub
                        }
                    })                    
                    response.data.map((solicitud, i) => {
                        const row = {
                            numero: i,
                            semestre: solicitud.periodo,
                            estado: solicitud.estado,
                            resultado: solicitud.resultado_final,
                            accion: "Click",
                            id: solicitud.id
                        }
                        this.solicitudes = response.data;
                        this.items.push(row);
                    })
                }else{                    
                    this.$router.push("/login");
                }                
            }
            fetchUsuario();
        },
        methods: {
            getSolicitud(item){
                console.log(this.solicitudes, item);
                console.log(this.solicitudes[item.numero]);
                this.$router.params = this.solicitudes[item.numero];
                console.log(this.$router);
                this.$router.push("/solicitud")
                // this.editedItem = Object.assign({}, item)
            }
        }
    }
</script>