<template>
  
    <div>
      <input type="file" @change="uploadFile" ref="file">
      <button @click="submitFile">Upload!</button>
    </div>

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
    uploadFile(){
      this.document = this.$refs.file.files[0];
    },
    submitFile(){
      const formData = new FormData();
      formData.append('file', this.document);
      const headers = {'Content-Type': 'multipart/form-data'};
      axios.post('http://localhost:3000/api/uploadFile', formData, { headers }).then((res) => {
          console.log(res.data);
        });
    }
  }

}
</script>