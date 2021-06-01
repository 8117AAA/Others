<template>
  <div>
    <el-col :span="8" v-for="(commondity, index) in commondities" :key="index">
      <a @click="addcar(commondity.id)">
        <el-card :body-style="{ padding: '0px' }">
          <img src="../assets/logo.png">
          <div style="padding: 14px;">
            <span>{{commondity.name}}</span>
          </div>
        </el-card>
      </a>
    </el-col>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        commondities: ''
      }
    },
    methods: {
      getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
          var c = ca[i].trim();
          if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
      },
      addcar(id){
        axios.post('http://localhost:8000/addcar', {
          token: this.getCookie('token'),
          id: id
        }).then(function(response) {
        })
      }
    },
    mounted() {
      axios.get("http://localhost:8000/show").then((response) => {
        this.commondities = response.data;
      });
    }
  }
</script>

<style>
</style>
