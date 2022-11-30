<template>
    <v-container>
        <v-alert      
            v-if="success ===  false"
            dense
            dismissible 
            outlined
            type="error"
            
        >Usuario o contraseña incorrectos.</v-alert>
        <v-alert
            v-if="success === true"
            dense
            dismissible
            outlined
            type="success"
        >Usuario y contraseña correctos!</v-alert>
        <h1>Login</h1>
        <UserAuthForm buttonText="Login" :submitForm="loginUser" />
    </v-container>
</template>

<script>
    import UserAuthForm from '../components/UserAuthForm';
    import axios from 'axios';

    export default {
        data(){
            return{
                dialog: false,
                success: null
            }
        },
        components:{
            UserAuthForm
        },
        methods: {
            async loginUser(userInfo){  
                this.success = null;              
                const params = new URLSearchParams();
                params.append('username', userInfo.email);
                params.append('password', userInfo.password);                
                try{
                    const response = await this.$auth.loginWith('local', {
                        data: params
                    });                    
                    const user = response.data['user'];
                    console.log(this.$auth);
                    this.$auth.setUser({
                        username: userInfo.email,
                        password: userInfo.password
                    })                                                           
                    this.success = true;
                    this.$auth.strategy.token.set(response.data['access_token']);
                    // this.$router.push("/perfil");
                }catch(e){
                    this.success = false;
                }                
            }
        }
    }
</script>

