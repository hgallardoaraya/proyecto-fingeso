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
    import parseJwt from '../utils/parseJwt';
    import isAuthenticated from '../utils/isAuthenticated'

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
                console.log(userInfo);
                this.success = null;              
                const params = new URLSearchParams();
                params.append('username', userInfo.username);
                params.append('password', userInfo.password);                
                try{
                    const response = await this.$axios.post("/login", params, { withCredentials: true  });                    
                    const responseAccessToken = response.data['access_token'];
                    const responseRefreshToken = response.data['refresh_token'];

                    const { comite, exp, _, sub } = parseJwt(responseAccessToken);                    
                    const tokenItem = { value: responseAccessToken, expiry: exp };
                    const loginItem = { username: sub, comite };

                    localStorage.setItem('access_token', JSON.stringify(tokenItem));
                    localStorage.setItem('refresh_token', responseRefreshToken)
                    localStorage.setItem('user', JSON.stringify(loginItem));

                    this.success = true;                    
                    this.$router.push({ path: "/" });
                }catch(e){
                    this.success = false;
                }                
            }
        },
        created(){
            console.log(localStorage.getItem('access_token'));
            if(isAuthenticated()){
                this.$router.push({ path: "/" })
            }
        }
    }
</script>

