<template>
    <div class="login">
        <div class="login-left">

        </div>
        <div class="login-right">
            <div class="login-right-header">
                <div class="login-right-header-left" v-bind:class="{active3:isActive==0}" @click="isActive=0">
                    登录
                </div>
                <div class="login-right-header-right" v-bind:class="{active3:isActive==1}" @click="isActive=1">
                    注册
                </div>
            </div>
            
            <div class="login-form" v-if="isActive==0">
                <span>账&emsp;号</span>：<input type="text" placeholder="用户名/手机号/邮箱" v-model="login.userIdentifier">
                <span>密&emsp;码</span>：<input type="text" placeholder="请输入密码" v-model="login.password">
                <button @click="loginUp()"><span>登&emsp;录</span></button>
            </div>
            <div class="login-form" v-if="isActive==1">
                <span>用户名</span>：<input type="text" placeholder="请输入用户名" v-model="register.username"><span style="font-size: 10px;margin-left: 70px;" v-html="userPrompt.username"></span>
                <span>姓&emsp;名</span>：<input type="text" placeholder="请输入姓名" v-model="register.name"><span style="font-size: 10px;margin-left: 70px;" v-html="userPrompt.name"></span>
                <span>手&emsp;机</span>：<input type="text" placeholder="请输入手机" v-model="register.phone"><span style="font-size: 10px;margin-left: 70px;" v-html="userPrompt.phone"></span>
                <span>邮&emsp;箱</span>：<input type="text" placeholder="请输入邮箱" v-model="register.email"><span style="font-size: 10px;margin-left: 70px;" v-html="userPrompt.email"></span>
                <span>密&emsp;码</span>：<input type="text" placeholder="请输入密码" v-model="register.password"><span style="font-size: 10px;margin-left: 70px;" v-html="userPrompt.password"></span>
                <span>年&emsp;龄</span>：<input type="number" placeholder="请输入年龄" min="18" max="100" v-model="register.age"><span style="font-size: 10px;margin-left: 70px;" v-html="userPrompt.password"></span>
                <span>性&emsp;别</span>：<label><input type="radio" name="sex" value="1" v-model="register.sex"> 男</label><label style="margin-left: 10px;"><input type="radio" name="sex" value="2" v-model="register.sex"> 女</label><br>
                <span>状&emsp;态</span>：<label><input type="radio" name="status" value="1" v-model="register.status"> 商家</label><label style="margin-left: 10px;"><input type="radio" name="status" value="2" v-model="register.status"> 用户</label><br>
                <button v-bind:disabled="isDisabledRegister" @click="registerUser()"><span>注&emsp;册</span></button>
            </div>
        </div>
    </div>
</template>

<script>

export default{
    name: "LoginView",
    data(){
        return {
            isActive:0,
            // 注册类
            register:{
                username: "",
                name: "",
                phone: "",
                email: "",
                password: "",
                age: 18,
                sex: 1,
                status: 1
            },
            isDisabledRegister: true,
            // 登录类
            login:{
                userIdentifier: "",
                password: ""
            },
            // 用户提示类
            userPrompt:{
                username: "",
                name: "",
                phone: "",
                email: "",
                password: ""
            },
        }
    },
    watch: {
        'register.username':function(newVal){
            console.log(newVal);
            this.userPrompt.username = "";
            this.isDisabledRegister = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.username = "用户名不能为空<br>";
                this.isDisabledRegister = true;
            }else if(!/^[\w-]{4,16}$/.test(newVal)){
                this.userPrompt.username = "用户名格式错误,需要4位字母或数字<br>";
                this.isDisabledRegister = true;
            }
        },
        'register.name': function(newVal){
            console.log(newVal)
            this.userPrompt.name = "";
            this.isDisabledRegister = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.name = "姓名不能为空<br>";
                this.isDisabledRegister = true;
            }
        },
        'register.phone': function(newVal){
            console.log(newVal);
            this.userPrompt.phone = "";
            this.isDisabledRegister = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.phone = "手机号不能为空<br>";
                this.isDisabledRegister = true;
            }else if(!/^(?:(?:\+|00)86)?1\d{10}$/.test(newVal)){
                this.userPrompt.phone = "手机号格式错误<br>";
                this.isDisabledRegister = true;
            }
        },
        'register.email': function(newVal){
            console.log(newVal);
            this.userPrompt.email = "";
            this.isDisabledRegister = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.email = "邮箱不能为空<br>";
                this.isDisabledRegister = true;
            }else if(!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(newVal)){
                this.userPrompt.email = "邮箱格式错误<br>";
                this.isDisabledRegister = true;
            }
        },
        'register.password': function(newVal){
            console.log(newVal)
            this.userPrompt.password = "";
            this.isDisabledRegister = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.password = "密码不能为空<br>";
                this.isDisabledRegister = true;
            }
        },
    },
    methods: {
        // 注册方法
        registerUser(){
            console.log(this.register);
            // 判断用户为空
            if(this.register.username.trim() == '' || this.register.name.trim() == '' || this.register.phone.trim() == '' || this.register.email.trim() == '' || this.register.password.trim() == ''){
                alert("请填写完整信息")
            }else{
                // 请求接口
                this.insertRegister(this.register);  
            }
            
        },
        // 登录方法
        loginUp(){
            console.log(this.login);
            // 判断用户为空
            if(this.login.userIdentifier.trim() == '' || this.login.password.trim() == ''){
                alert("请填写完整信息");
            }else{
                this.loginUser(this.login);
            }
        },

        /* 访问后端方法 */
        insertRegister(register){
            let url = this.$httpAddress + this.$userAddress + "/register";
            this.$axios.post(url, JSON.stringify(register),{
                headers: {
                    'Content-Type': 'application/json'
                }
            })
               .then(response => {
                    console.log(response);
                    if(response.data.code == 200){
                        alert("注册成功");
                        window.location.reload();
                    }else{
                        alert("注册失败," + response.data.msg);
                    }
               })
               .catch(error => {
                    console.log(error); 
               });
        },
        loginUser(login){
            let url = this.$httpAddress + this.$userAddress + "/login";
            this.$axios.post(url, JSON.stringify(login),{
                headers: {
                    'Content-Type': 'application/json'
                }
            })
               .then(response => {
                    console.log(response);
                    if(response.data.code == 200){
                        console.log(response.data.data)
                        window.localStorage.clear();
                        window.localStorage.setItem("token", response.data.data.token);
                        window.localStorage.setItem("id", response.data.data.id);
                        window.localStorage.setItem("status", response.data.data.status);
                        
                        window.location.href = "http://127.0.0.1:8080/";
                    }else{
                        alert("登录失败," + response.data.msg);
                    }
               })
               .catch(error => {
                    console.log(error); 
               });
        }
    }
}
</script>

<style>
    .login{
        width: 800px;
        height: 500px;
        margin: 0 auto;
        margin-top: 80px;
        border-radius: 30px;
        display: flex;
        background-color: aquamarine;
    }
    .login-left{
        width: 300px;
        height: 500px;
        border-radius: 30px 0 0 30px;
        background-color: #769CF2; 
        background-image: linear-gradient(to bottom right, #769CF2 , #dce6ff);
    }
    .login-right{
        width: 500px;
        height: 500px;
        border-radius: 0 30px 30px 0;
        background-color: white
    }
    .login-right-header{
        width:160px;
        display: flex;
        margin: 0 auto;
        margin-top: 50px;
        text-align: center;
    }
    .login-right-header-left,.login-right-header-right{
        text-align: center;
        font-size: 20px;
        font-weight: bold;
        margin: 0 auto;
        width: 80px;
        height: 30px;
        cursor: pointer;
        color: rgba(0, 0 , 0, 0.5);
        border-bottom: 2px solid rgba(0, 0 , 0, 0.5);
    }
    .active3{
        color: rgba(0, 0 , 0, 1);
        background-color: white;
        border-bottom: 2px solid rgba(0, 0 , 0, 1);
    }
    
    .login-form{
        width: 252px;
        height: 260px;
        margin: 0 auto;
        text-align: left;
    }
    .login-form>span{
        margin-top: 10px;
    }
    .login-form>input{
        height: 30px;
        width: 180px;
        margin-top: 10px;
    }
    .login-form>button{
        margin-top: 20px;
        height: 30px;
        width: 250px;
        border: 0px;
        background-color: rgba(118, 156, 242, 0.5);
    }

</style>