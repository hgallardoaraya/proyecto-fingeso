<template>
    <v-expansion-panels inset>
        <v-expansion-panel>
            <v-expansion-panel-header>{{ items.text }}</v-expansion-panel-header>
            <v-expansion-panel-content  >
                <template v-if="items.input">
                    <div class="d-flex justify-end align-center">
                        <v-file-input
                            show-size
                            counter
                            multiple
                            label="File input"
                            
                            class="mr-8" 
                        ></v-file-input>
                        
                        <v-btn class="mx-16 blue white--text">
                            Subir
                        </v-btn>                            
                    </div>
                    </template>
                    <RecursiveExpansionPanel v-else v-for="(item, i) in subItems" :key="i" v-bind:items="item" v-bind:subItems="item.subItems"/>
                </v-expansion-panel-content>
        </v-expansion-panel>
        </v-expansion-panels>
</template>

<script>
    import ExpansionPanel from './ExpansionPanel.vue'
    export default{
        name: 'RecursiveExpansionPanel',
        props: ['items', 'subItems'],
        data(){
            return{
                components: {
                    ExpansionPanel
                }
            }
        },
        created(){
            console.log("Items", this.items);
            
        }
    }
</script>