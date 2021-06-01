<template>
  <el-row>
    <div style="background-color: #42B983;margin: 0;">
      <b>
        <el-upload style="float: left;" name="image" class="avatar-uploader"
          action="http://127.0.0.1:8000/uploadprofilephoto" :show-file-list="false" :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="user.profile_photo" :src="'http://127.0.0.1:8000/file/'+user.profile_photo" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        电话号码：{{user.phone}}<br />
        名字：{{user.name}}<br />
        地址：{{user.address}}<br />
      </b>
      <el-button @click="changeAddress()">修改地址</el-button>
    </div>
    <br />
    <el-card v-for="(o,i) in orders" :key="i" :body-style="{ padding: '0px' }" style="margin: 5px 0;">
      <img src="../assets/logo.png" style="float: left;width: 100px;height: 100px;">
      <div style="padding: 14px;">
        <span>
          商品id-数量 : {{o.comm_id}} <br />
        总价 : {{o.price}}</span>
        <div style="float: right;">
          <el-button v-show="o.pay_time==null" @click="pay(o.id)">pay</el-button>
          <el-button @click="del(o.id)">delete</el-button>
        </div>
      </div>
    </el-card>
  </el-row>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        user: '',
        orders: ''
      }
    },
    methods: {
      handleAvatarSuccess(res, file) {
        var self = this;
        axios.post('http://localhost:8000/updateprofilephoto', {
          token: this.getCookie('token'),
          'image': res.url
        }).then(function(response) {
          if (response.data.status == 1) {
            self.userinfo();
          }
        }).catch(() => {});
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG && !isPNG) {
          this.$message.error('只能上传图片!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG || isPNG && isLt2M;
      },
      userinfo() {
        var self = this;
        axios.post('http://localhost:8000/userinfo', {
          token: this.getCookie('token')
        }).then(function(response) {
          // console.log(response.data);
          self.user = response.data;
        })
      },
      getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
          var c = ca[i].trim();
          if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
      },
      changeAddress() {
        var self = this;
        this.$prompt('请输入地址', '修改地址', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(({
          value
        }) => {
          axios.post('http://localhost:8000/changeaddress', {
            token: this.getCookie('token'),
            address: value
          }).then(function(response) {
            if (response.data.status == 1) {
              self.userinfo();
            }
          })
        }).catch(() => {});
      },
      getOrders() {
        var self = this;
        axios.post('http://localhost:8000/getorders', {
          token: this.getCookie('token')
        }).then(function(response) {
          self.orders = response.data;
        }).catch(() => {});
      },
      pay(id) {
        var self = this;
        axios.post('http://localhost:8000/pay', {
          token: this.getCookie('token'),
          order: id
        }).then(function(response) {
          if(response.data.status==true){
            self.getOrders();
          }
        }).catch(() => {});
      },
      del(id){
        var self = this;
        axios.post('http://localhost:8000/delorder', {
          token: this.getCookie('token'),
          order: id
        }).then(function(response) {
          if(response.data.status==true){
            self.getOrders();
          }
        }).catch(() => {});
      }
    },
    mounted() {
      if (this.getCookie('token') != "") {
        // get data
        this.userinfo()
        this.getOrders()
      } else {
        this.$router.push('/login')
      }
    }
  }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }

  .avatar {
    width: 100px;
    height: 100px;
    display: block;
  }
</style>
