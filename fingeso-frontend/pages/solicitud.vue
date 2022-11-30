<template>
    <div>

        <h1 class="accent--text">Solicitud {{ solicitud.periodo }}</h1>
        {{ this.solicitud }}
    </div>
</template>

<script>
    export default{
        data(){
            return{       
                solicitud: this.$router.params   
            }
        },
        created(){
            // console.log(this.$router);
        }
    }
</script>