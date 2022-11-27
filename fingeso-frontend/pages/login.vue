<template>
    <v-container>
        <h1>Login</h1>
        <UserAuthForm buttonText="Login" :submitForm="loginUser" />
    </v-container>
</template>

<script>
    import UserAuthForm from '../components/UserAuthForm';

    export default {
        components:{
            UserAuthForm
        },
        methods: {
            loginUser(userInfo){
                this.$auth.loginWith('local', {
                    data: {                        
                        ...userInfo
                    }
                })
                alert('You pressed login');
            }
        }
    }
</script>