<template>
    <div>
        <h1>Actividades</h1>        
        <ExpansionPanel v-for="(item, i) in items" :key="i" v-bind:items="item" v-bind:subItems="item.subItems" v-bind:solicitud="solicitud" v-bind:comite="comite" v-bind:type="type"/>
        <div class="d-flex justify-center">
            <v-btn 
                v-if="type==='evaluacion'"
                color="primary"
                class="mt-8"
                @click="volver()"
            >
                Volver
            </v-btn>
        </div>
    </div>
</template>

<script>
    import ExpansionPanel from '../components/ExpansionPanel.vue'
    export default{
        name: 'Actividades',
        props: ['items', 'solicitud', 'type'],   
        data(){
            return{
                fileInputs: [],
                fileDownload: new Set(),
                index: 0,
                comite: "",
                components: {
                    ExpansionPanel
                },
            }
        },
        created(){
            // console.log("subcategorias", this.subcategorias);
            this.comite = JSON.parse(localStorage.getItem('user')).comite[0];
            console.log(this.comite)
        },
        methods: {
            volver: function (){
                location.reload();
            }
        }
    }
</script>