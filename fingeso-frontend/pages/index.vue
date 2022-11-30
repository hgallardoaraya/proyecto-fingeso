<template>
    <div class="d-flex flex-column">
        <div class="d-flex justify-center align-center py-n16">
            <img class="mr-3" :src="require('../assets/img/file1.svg')" width="500px" height="500px"/>
            <div style="width: 500px;">
                <h2 class="accent--text">Proceso de Jerarquización Académica</h2>
                <p class="grey--text">Hola {{ nombre }}, te damos la bienvenida a la nueva plataforma web para realizar el proceso de jerarquización académica.</p>
                    <v-btn
                    color="blue"
                    elevation="2"
                    large
                    class="white--text mt-8"
                    >
                    Saber más
                </v-btn>

            </div>
        </div>
        <div class="d-flex justify-space-around align-center">
            <div class="d-flex flex-column justify-center align-center" style="maxWidth: 300px;">
                <v-avatar class="mx-16 my-8" color="primary" size="100" >
                    <v-icon dark size="60">
                        mdi-attachment-check
                    </v-icon>            
                </v-avatar>                        
                <h3 class="accent--text">Envía tu solicitud</h3>
                <p class="accent--text text-center">Envia tu solicitud adjuntando los archivos de respaldo correspondientes a cada actividad para evaluar tu jerarquía académica.</p>            
            </div>
            <div class="d-flex flex-column justify-center align-center" style="maxWidth: 300px;">
                <v-avatar class="mx-16 my-8" color="primary" size="100" >
                    <v-icon dark size="60">
                        mdi-star-box
                    </v-icon>            
                </v-avatar>                        
                <h3 class="accent--text">Puntúa a otros profesores</h3>
                <p class="accent--text text-center">Sé parte del proceso puntuando las actividades respaldadas por otros profesores.</p>            
            </div>
            <div class="d-flex flex-column justify-center align-center" style="maxWidth: 300px;">
                <v-avatar class="mx-16 my-8" color="primary" size="100" >
                    <v-icon dark size="60">
                        mdi-clipboard-check
                    </v-icon>            
                </v-avatar>                        
                <h3 class="accent--text">Realiza la evaluación</h3>
                <p class="accent--text text-center">Evalúa en base a los criterios del manual de jerarquización académica las solicitudes de otros profesores.</p>            
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        data(){
            return{
                nombre: "default"
            }
        },
        created(){
            console.log(this.$auth.user.username);
            const fetchUsuario = async () => {
                const response = await axios.get('http://localhost:3000/api/usuario', {                
                    headers: {
                        'username': this.$auth.user.username
                    }
                })
                this.nombre = response.data.nombre;
            }

            fetchUsuario();
        }
    }
</script>