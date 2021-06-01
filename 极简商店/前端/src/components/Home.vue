<template>
  <el-row>
    <el-col :span="4">
      <el-menu @open="click(index)" :router="true">
        <el-menu-item index="show">
          <span slot="title">商品</span>
        </el-menu-item>
        <el-menu-item index="car">
          <span slot="title">购物车</span>
        </el-menu-item>
        <el-menu-item index="me">
          <span slot="title">我的</span>
        </el-menu-item>
      </el-menu>
      <el-button @click="get()">token</el-button>
    </el-col>
    <el-col :span="20" style="overflow: auto;">
      <router-view></router-view>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    data() {
      return {
        cookie: document.cookie,
      }
    },
    methods: {
      click() {
        console.log('a');
      },
      get() {
        this.$notify.info({
          title: 'cookie',
          message: this.getCookie('token')
        });
      },
      getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
          var c = ca[i].trim();
          if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
      }
    },
    mounted() {
      if (this.getCookie('token') != "") {} else {
        this.$router.push('/login')
      }
      // document.cookie = "token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzYWx0IiwiYXVkIjoiIiwiaWF0IjoxNjIyNDU0OTIzLCJuYmYiOjE2MjI0NTQ5MjMsImV4cCI6MTYyMjU0MTMyMywiZGF0YSI6eyJpZCI6MX19.4DokM6wy1lwql7kid9BdeO6UX1YupvrFbbm2joNeUHs;path=/"
      // document.cookie = "token=0;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/"
    }
  }
</script>

<style>
  .el-row,
  .el-col {
    height: 100%;
  }
</style>
