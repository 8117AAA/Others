<template>
  <div>
    <el-form v-model="form" label-width="80px">
      <el-form-item label="phone">
        <el-input v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="password">
        <el-button @click="hide()">hide</el-button>
        <el-input v-model="form.password" :type="type"></el-input>
      </el-form-item>
      <el-button @click="login()">login</el-button>
      <el-button @click="register()">register</el-button>
    </el-form>
    <el-button plain @click="get()">
      显示token
    </el-button>
    <el-button plain @click="del()">
      删除token
    </el-button>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        form: {
          phone: '19000000000',
          password: '123qwe'
        },
        type: 'text'
      }
    },
    methods: {
      hide() {
        this.type = this.type == 'password' ? 'text' : 'password';
      },
      login() {
        var self = this;
        axios.post('http://localhost:8000/login', {
          phone: this.form.phone,
          password: this.form.password
        }).then(function(response) {
          console.log(response.data);
          if (response.data.token != undefined){
            document.cookie = "token=" + response.data.token + ";path=/";
            self.$router.push('/')
          }
          else
            self.$message.error(response.data.msg);
        })
      },
      register() {
        var self = this;
        axios.post('http://localhost:8000/register', {
          phone: this.form.phone,
          password: this.form.password
        }).then(function(response) {
          console.log(response.data);
          if (response.data.msg == 1) {
            self.login();
          } else {
            self.$message.error(response.data.msg.password);
          }
        })
      },
      del() {
        document.cookie = "token=0;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/"
        this.$notify.error({
          title: '删除token',
          message: document.cookie
        });
      },
      get() {
        this.$notify.info({
          title: 'cookie',
          message: document.cookie
        });
      },
    },
  }
</script>

<style>
</style>
