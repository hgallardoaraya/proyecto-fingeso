<template>
  <v-app>
      <v-app-bar      
        elevation="2"
        rounded
        extended
        extended-height="100%"
        class="px-4 py-5"
        app
      >     
        <v-app-bar-nav-icon class="d-sm-none"></v-app-bar-nav-icon>
        <img class="mr-3" :src="require('../assets/img/usach_logo.svg')" height="60"/>
        <v-tabs
          right
          
          color="primary"
          slider-color="primary"
          class="d-none d-sm-block"
        >
          <v-tab
            v-for="(item, i) in items"
            :key="i"
            :to="item.to"          
          >
            {{item.title}}
          </v-tab>
        </v-tabs>
      </v-app-bar>
    <v-main>
      <v-container>
        <Nuxt/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import axios from 'axios';
export default {
  name: 'DefaultLayout',
  data () {
    return {
      items: [
        {
          title: 'INICIO',
          to: '/'
        },           
        {         
          title: 'SOLICITUDES',
          to: '/solicitudes'
        },
        {         
          title: 'EVALUACIONES',
          to: '/evaluaciones'
        },
        {
          title: 'PERFIL',
          to: '/perfil'
        },             
      ],
    }
  }
}
</script>
