<template>
  <el-row>
    <el-card v-for="(o,i) in car" :key="i" :body-style="{ padding: '0px' }" style="margin: 5px 0;">
      <img src="../assets/logo.png" style="float: left;width: 100px;height: 100px;">
      <div style="padding: 14px;">
        <span>{{o.name}}</span>
      </div>
      <div style="float: right;">
        <el-input-number v-model="o.num" @change="handleChange(o.num,o.com_id)" size="mini" :min="1" :max="9999">
        </el-input-number>
        <el-button size="mini" type="danger" plain @click="del(o.com_id)">del</el-button>
      </div>
    </el-card>
    <el-button v-show='car.length' @click="createOrder()">create order</el-button>
  </el-row>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        car: 0
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
      getCar() {
        var self = this;
        axios.post('http://localhost:8000/cars', {
          token: this.getCookie('token')
        }).then(function(response) {
          self.car = response.data;
        })
      },
      handleChange(num, id) {
        var self = this;
        axios.post('http://localhost:8000/setnum', {
          token: this.getCookie('token'),
          id: id,
          num: num
        }).then(function(response) {
          console.log('updated');
        })
      },
      del(id) {
        var self = this;
        axios.post('http://localhost:8000/delCar', {
          token: this.getCookie('token'),
          id: id
        }).then(function(response) {
          console.log(response.data);
          self.getCar();
        })
      },
      createOrder() {
        var self = this;
        axios.post('http://localhost:8000/carOrder', {
          token: this.getCookie('token')
        }).then(function(response) {
          console.log(response.data);
          self.getCar();
        })
      }
    },
    mounted() {
      this.getCar();
    }
  }
</script>

<style>
</style>
