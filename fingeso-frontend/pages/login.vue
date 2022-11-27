<template>
    <v-container>
        <h1>Login</h1>
        <UserAuthForm buttonText="Login" :submitForm="loginUser" />
    </v-container>
</template>

<script>
    import UserAuthForm from '../components/UserAuthForm';
    import axios from 'axios';

    export default {
        components:{
            UserAuthForm
        },
        methods: {
            async loginUser(userInfo){
                const userData = {
                    email: userInfo.email,
                    password: userInfo.password
                }

                const response = await axios.post("http://localhost:3000/login", userData,
                {withCredentials: true});

                console.log(response);  

                // try{
                //     this.$auth.loginWith('local', {
                //         data: userData,
                //     }).then((res) => console.log(res));
                // }catch(e){
                //     console.log(e);
                // }   
            }
        }
    }
</script>