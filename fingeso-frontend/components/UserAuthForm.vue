<template>
  <v-form v-model="valid">
    <v-text-field v-model="userInfo.name" 
                  label="Name"                   
                  v-if="name" />

    <v-text-field v-model="userInfo.username" 
                  label="Email" 
                  />

    <v-text-field v-model="userInfo.password"
                  label="Password"
                  :type="showPassword ? 'text' : 'password'" 
                  :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                  @click:append="showPassword = !showPassword"
                  counter=true
                  
                  />

    <v-btn @click="submitForm(userInfo)" :disabled="!valid">{{ buttonText }}</v-btn>
  </v-form>
</template>

<script>
  import validations from "@/utils/validations";

  export default {
    data() {
      return {
        valid: false,
        showPassword: false,
        name: false,
        userInfo: {
          username: "",
          password: ""
        },
        ...validations
      }
    },
    props: ["submitForm", "buttonText", "hasName"]
  }
</script>

<style lang="scss" scoped>

</style>