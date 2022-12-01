<template>
  <h1>
    <v-btn
      @click="downloadFile()"
    >
      Download
    </v-btn>
  </h1>
</template>

<script>
import axios from 'axios';

export default{
  data(){
    return{
      document: null
    }
  },
  methods: {
    async downloadFile(){
      const res = await axios.get("http://localhost:3000/api/downloadFile/yzJSiKkx", { responseType: 'blob' }, );
      console.log(res);
      const url = window.URL.createObjectURL(new Blob([res.data]));
      const link = document.createElement('a');
      const file_name = `${new Date().toISOString().slice(0,10)}${res.headers['fileextension']}`; // RENAME HERE
      link.href = url;
      link.setAttribute('download', file_name);
      document.body.appendChild(link);
      link.click();



    }
  }
}
</script>