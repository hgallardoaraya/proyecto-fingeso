<template>
    <div>
        <h1>Actividades</h1>
        <!-- {{ solicitud }} -->
        <!-- {{ items }} -->
        <ExpansionPanel v-for="(item, i) in items" :key="i" v-bind:items="item" v-bind:subItems="item.subItems" v-bind:solicitud="solicitud"/>
    </div>
</template>

<script>
    import ExpansionPanel from '../components/ExpansionPanel.vue'
    export default{
        name: 'Actividades',
        props: ['items', 'solicitud'],   
        data(){
            return{
                fileInputs: [],
                fileDownload: new Set(),
                index: 0,
                components: {
                    ExpansionPanel
                },
            }
        },
        created(){
            // console.log("subcategorias", this.subcategorias);
            
            console.log("Solicitud", this.solicitud);
        }
    }
</script>