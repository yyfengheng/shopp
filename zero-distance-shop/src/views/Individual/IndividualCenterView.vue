<template>
    <div class="individualCenterBase">
        <base-layout class="baseLayout" v-bind:activeIndex="4"></base-layout>
        <div class="individualCenterBody">
            <div class="header">
                <span style="font-size: 20px;">我的个人资料</span>
            </div>

            <div class="individualCenter-side">
                <div class="individualCenter-side-nav">
                    <ul>
                        <li @click="changeFlag(0)" v-bind:class="{active1: 0==flag}"><img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/%E4%B8%AA%E4%BA%BA.png"><span>个人信息</span></li>
                        <li @click="changeFlag(1)" v-bind:class="{active1: 1==flag}"><img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/%E5%AE%9A%E4%BD%8D-%E4%BD%8D%E7%BD%AE.png"><span>收货地址</span></li>
                        <li @click="changeFlag(2)" v-bind:class="{active1: 2==flag}"><img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/%E5%AF%86%E7%A0%81.png"><span>修改密码</span></li>
                    </ul>
                </div>

                <!-- 个人信息 -->
                <div class="individualCenter-side-body" v-if="flag == 0">
                    <div class="individualCenter-side-body-header">
                        <span style="font-size: 20px; margin-left: 20px">个人信息</span>
                    </div>
                    <hr style="width: 1000px;">
                    <div class="individualCenter-side-body-body">
                        <div style="width:980px;height:120px;background-color: white;display: flex;align-items: center;">
                            <img v-bind:src="echoImg" alt="" style="width:90px;height:100px;margin-left: 140px;">
                            <div style="width: 300px;height: 120px;text-align: left;margin-left: 30px;">
                                <p style="margin-top: 30px;">只支持jpg，png等格式的图片文件</p>
                                <label class="file-button" for="fileInput">更改图片</label>
                                <input type="file" id="fileInput" style="display:none;" @change="uploadImage">
                            </div>
                        </div>
                        <div style="width:900px;height:260px;margin:0 auto;margin-left:0px;display: flex;justify-content: flex-end;align-items: center">
                            <div style="width:450px;height:250px;text-align: left;">
                                <p style="margin-bottom: 5px;">姓名</p>
                                <input v-model="userVO.name" type="text" style="height: 25px;width: 180px;"> <br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ userPrompt.name }}</span>

                                <p style="margin-bottom: 5px;">手机号</p>
                                <input v-model="userVO.phone" type="text" style="height: 25px;width: 180px;"><br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ userPrompt.phone }}</span>

                                <p style="margin-bottom: 5px;">性别</p>
                                <label><input type="radio" value="1" v-model="userVO.sex">男</label>
                                <label style="margin-left: 30px;"><input type="radio" value="2" v-model="userVO.sex">女</label>
                            </div>
                            <div style="width:300px;height:250px;text-align: left;">
                                <p style="margin-bottom: 5px;">用户名</p>
                                <input v-model="userVO.username" type="text" style="height: 25px;width: 180px;"><br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ userPrompt.username }}</span>

                                <p style="margin-bottom: 5px;">Email</p>
                                <input v-model="userVO.email" type="text" style="height: 25px;width: 180px;"><br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ userPrompt.email }}</span>

                                <p style="margin-bottom: 5px;">年龄</p>
                                <input v-model="userVO.age" type="number" min="18" max="100" style="height: 25px;width: 180px;"><br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{userPrompt.age}}</span>
                            </div>
                        </div>
                        <div style="width: 850px;height: 50px;line-height:50px;margin: 0 auto;display: flex;justify-content: flex-end">
                            <button v-bind:disabled="isDisabled" @click="clicKUpdateUser()" style="margin-right: 40px;width: 80px;height: 30px;color: white;background-color: #769CF2;border: 0px;border-radius: 10px;">保存</button>
                        </div>
                    </div>
                </div>

                <!-- 收货地址 -->
                <div class="individualCenter-side-body" v-if="flag == 1">
                    <div class="individualCenter-side-body-header">
                        <span style="font-size: 20px; margin-left: 20px">收货地址</span>
                    </div>
                    <hr style="width: 1000px;">
                    <div class="individualCenter-side-body-body">
                        <div style="width:900px;height:350px;margin:0 auto;margin-left:0px;display: flex;justify-content: center;align-items: center">
                            <div style="width:350px;height:350px;text-align: left;">
                                <p style="margin-bottom: 5px; font-weight: normal;">姓名</p>
                                <input v-model="address.name" type="text" style="height: 35px;width: 350px;" placeholder="请输入姓名"> <br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ userPrompt.name }}</span>

                                <p style="margin-bottom: 5px;font-weight: normal;">手机号</p>
                                <input v-model="address.phone" type="text" style="height: 35px;width: 350px;" placeholder="请输入手机号"><br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ userPrompt.phone }}</span>


                                <p style="margin-bottom: 5px;font-weight: normal;">详细地址</p>
                                <el-autocomplete
                                    class="inline-input"
                                    v-model="address.address"
                                    :fetch-suggestions="querySearch"
                                    placeholder="请输入内容"
                                    :trigger-on-focus="false"
                                    style="width:350px;border: 1px solid black;,padding: 0px;"
                                    @select="handleSelect"
                                    ></el-autocomplete>
                                <br>
                                
                            </div>
                        </div>
                        <div style="width: 850px;height: 50px;line-height:50px;margin: 0 auto;display: flex;justify-content: flex-end">
                            <button v-bind:disabled="isDisabled2" @click="clicKAddAddress()" style="margin-right: 40px;width: 90px;height: 30px;color: white;background-color: #769CF2;border: 0px;border-radius: 10px;">保存</button>
                        </div>
                    </div>
                </div>


                <!-- 修改密码 -->
                <div class="individualCenter-side-body" v-if="flag == 2">
                    <div class="individualCenter-side-body-header">
                        <span style="font-size: 20px; margin-left: 20px">修改密码</span>
                    </div>
                    <hr style="width: 1000px;">
                    <div class="individualCenter-side-body-body">
                        <div style="width:900px;height:350px;margin:0 auto;margin-left:0px;display: flex;justify-content: center;align-items: center">
                            <div style="width:350px;height:250px;text-align: left;">
                                <p style="margin-bottom: 5px; font-weight: normal;">原密码</p>
                                <input v-model="password.oldPassword" type="text" style="height: 30px;width: 250px;" placeholder="请输入"> <br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ passwordPrompt.oldPassword }}</span>

                                <p style="margin-bottom: 5px;font-weight: normal;">新密码</p>
                                <input v-model="password.newPassword1" type="text" style="height: 30px;width: 250px;" placeholder="请输入"><br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ passwordPrompt.newPassword1 }}</span>

                                <p style="margin-bottom: 5px;font-weight: normal;">请再次输入新密码</p>
                                <input v-model="password.newPassword2" type="text" style="height: 30px;width: 250px;" placeholder="请输入"><br>
                                <!-- 标记 -->
                                <span style="font-size: 10px;font-weight: normal;">{{ passwordPrompt.newPassword2 }}</span>
                            </div>
                        </div>
                        <div style="width: 850px;height: 50px;line-height:50px;margin: 0 auto;display: flex;justify-content: flex-end">
                            <button v-bind:disabled="isDisabled3" @click="clicKUpdatePassword()" style="margin-right: 40px;width: 90px;height: 30px;color: white;background-color: #769CF2;border: 0px;border-radius: 10px;">保存</button>
                        </div>
                    </div>
                </div>


            </div> 
        </div>
    </div>

</template>

<script>
    
import BaseLayout from '@/components/BaseLayout'

export default {
    name:"IndividualCenterView",
    components: {
        BaseLayout
    },
    data:function(){
        return {
            echoImg:"",
            // 用户信息
            userVO:{
                id:0,
                username:"",
                name:'',
                phone:"",
                email:"",
                status:"",
                sex:1,
                age:0,
                image:"",
            },
            userPrompt:{
                username:"",
                name:'',
                phone:"",
                email:"",
                status:"",
                sex:'',
                age:'',
            },
            passwordPrompt:{
                oldPassword:"",
                newPassword1:"",
                newPassword2:""
            },
            address:{
                name:"",
                phone:"",
                address:""
            },
            // 密码
            password:{
                oldPassword:"",
                newPassword1:"",
                newPassword2:""
            },
            isDisabled:false,
            isDisabled2:false,
            isDisabled3:false,
            flag:0,

            // 详细地址
            keyword: ""
            
        }
    },
    methods:{
        // 更改标记点
        changeFlag(flag1){
            this.flag = flag1;
        },
        // 上传图片
        uploadImage(event) {
            console.log(666);
            const formData = new FormData();
            const imageFile = event.target.files[0];
            formData.append('file', imageFile);
            
            let url = this.$httpAddress + this.$uploadAddress;

            this.$axios.post(url,formData,{
                headers:{
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then(response=>{
                console.log(response)
                if(response.data.msg=="success"){
                    this.echoImg = response.data.data
                    console.log(this.echoImg)
                }else{
                    alert(response.data.msg)
                }
                
            })
            .catch(error=>{
                console.log(error)
            })
        },
        // 点击更新用户信息
        clicKUpdateUser(){
            this.userVO.image = this.echoImg;
            this.updateUser(this.userVO);
        },
        // 点击新增收货地址
        clicKAddAddress(){
            if(this.address.address == null || this.address.name == null || this.address.phone == null || this.address.address == "" || this.address.name == "" || this.address.phone == ""){
                alert("信息不能为空")
                return;
            }
            console.log(this.address)
        },
        // 点击更新密码
        clicKUpdatePassword(){
            this.updatePassword();
        },


        /* 访问后端方法 */
        /* 获取用户信息 */
        getUser(){
            let url = this.$httpAddress + this.$userAddress + '?id=' + this.$userId;
            this.$axios(url)
            .then(response=>{
                console.log(response.data);
                if(response.data.msg=="success"){
                    console.log(response.data.data);
                    this.userVO = response.data.data;
                    this.echoImg = this.userVO.image;
                }else{
                    alert(response.data.msg);
                }
            })
            .catch(error=>{
                console.log(error)
            })
        },
        /* 更新用户信息 */
        updateUser(userVO){
            let url = this.$httpAddress + this.$userAddress;
            this.$axios.put(url,JSON.stringify(userVO),{
                headers:{
                    "Content-Type": "application/json"
                }
            })
            .then(response=>{
                if(response.data.msg == "success"){
                    alert("更新成功");
                    window.location.reload();
                }else{
                    alert(response.data.msg);
                }
            })
            .catch(error=>{
                console.log(error);
            })
        },
        /* 修改密码 */
        updatePassword(){
            let url = this.$httpAddress + this.$userAddress + "/password";
            let userDTO = {
                id:0,
                password:"",
                newPassword:""
            }
            userDTO.id = this.$userId
            userDTO.password = this.password.oldPassword
            userDTO.newPassword = this.password.newPassword2
            this.$axios.put(url,JSON.stringify(userDTO),{
                headers:{
                    "Content-Type": "application/json"
                }
            })
            .then(response=>{
                if(response.data.msg == "success"){
                    alert("更新成功");
                    window.location.reload();
                }else{
                    alert(response.data.msg);
                }
            })
            .catch(error=>{
                console.log(error);
            })
        },
        
       
        /* 搜索查找地址 */
        querySearch(queryString,cb){
            let url = this.$httpAddress + this.$keywordAddress + "?keyword=" + queryString
            this.$axios.get(url)
            .then(response=>{
                console.log(response.data.data)
                let result = []
                for(let i of response.data.data){
                    result.push({
                        value:i.address + i.title,
                        address:i.address
                    })
                }
                console.log(result)
                cb(result)
            })
            .catch(error=>{
                console.log(error)
            })
        },

        /*  */
        handleSelect(item){
            console.log(item.value)
            this.address.address = item.value

        }

    },
    // 监听对象改变
    watch:{
        // 监听姓名
        'userVO.name': function(newVal){
            console.log(newVal)
            this.userPrompt.name = "";
            this.isDisabled = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.name = "姓名不能为空";
                this.isDisabled = true;
            }
        },
        'address.name': function(newVal){
            console.log(newVal)
            this.userPrompt.name = "";
            this.isDisabled = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.name = "姓名不能为空";
                this.isDisabled = true;
            }
        },
        // 监听手机号
        'userVO.phone': function(newVal){
            console.log(newVal);
            this.userPrompt.phone = "";
            this.isDisabled = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.phone = "手机号不能为空";
                this.isDisabled = true;
            }else if(!/^(?:(?:\+|00)86)?1\d{10}$/.test(newVal)){
                this.userPrompt.phone = "手机号格式错误";
                this.isDisabled = true;
            }
        },
        'address.phone': function(newVal){
            console.log(newVal);
            this.userPrompt.phone = "";
            this.isDisabled = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.phone = "手机号不能为空";
                this.isDisabled = true;
            }else if(!/^(?:(?:\+|00)86)?1\d{10}$/.test(newVal)){
                this.userPrompt.phone = "手机号格式错误";
                this.isDisabled = true;
            }
        },
        // 监听用户名
        'userVO.username': function(newVal){
            console.log(newVal);
            this.userPrompt.username = "";
            this.isDisabled = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.username = "用户名不能为空";
                this.isDisabled = true;
            }else if(!/^[\w-]{4,16}$/.test(newVal)){
                this.userPrompt.username = "用户名格式错误,需要4位字母或数字";
                this.isDisabled = true;
            }
        },
        // 监听邮箱
        'userVO.email': function(newVal){
            console.log(newVal);
            this.userPrompt.email = "";
            this.isDisabled = false;
            if(newVal.trim() == '' || newVal == null){
                this.userPrompt.email = "邮箱不能为空";
                this.isDisabled = true;
            }else if(!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(newVal)){
                this.userPrompt.email = "邮箱格式错误";
                this.isDisabled = true;
            }
        },
        // 监听年龄
        'userVO.age': function(newVal,oldVal) {
            // 当userVO对象的age属性发生变化时执行的回调函数
            console.log('userVO对象的age属性发生了变化' + newVal +","+oldVal);
            if((newVal<18 || newVal>100) || !(/^(?:0|(?:-?[1-9]\d*))$/.test(newVal))){
                this.userPrompt.age = "无法直接输入年龄";
                this.userVO.age = oldVal;
            }
        },

        // 监听旧密码
        'password.oldPassword': function(newVal){
            console.log(newVal)
            this.passwordPrompt.oldPassword = "";
            this.isDisabled3 = false;
            if(newVal.trim() == '' || newVal == null){
                this.passwordPrompt.oldPassword = "密码不能为空";
                this.isDisabled3 = true;
            }
        },

        // 监听新密码
        'password.newPassword1': function(newVal){
            console.log(newVal)
            this.passwordPrompt.newPassword1 = "";
            this.isDisabled3 = false;
            if(newVal.trim() == '' || newVal == null){
                this.passwordPrompt.newPassword1 = "密码不能为空";
                this.isDisabled3 = true;
            }
        },

        // 监听新密码2
        'password.newPassword2': function(newVal){
            console.log(newVal)
            console.log(this.password.newPassword1)
            this.passwordPrompt.newPassword2 = "";
            this.isDisabled3 = false;
            if(newVal.trim() == '' || newVal == null){
                this.passwordPrompt.newPassword2 = "密码不能为空";
                this.isDisabled3 = true;
            }
            if(!(newVal === this.password.newPassword1)){
                this.passwordPrompt.newPassword2 = "密码需要一致";
                this.isDisabled3 = true;
            }
        },

        // 监听选择的省份 , 调用获取城市的方法
        'address.province': function(newVal){
            console.log(newVal);
            this.$getCity(newVal)
        }

    },
    mounted(){
        this.getUser();
        this.getProvince();
    }
}

</script>

<style>
    .individualCenterBase{
        width: 1380px;
        height: 540px;
        margin: 0 auto;
        margin-top: 30px;
        position: relative;
    }
    /* 设置基础布局组件 */
    .baseLayout {
        position: absolute;
        margin-top: -30px;
        top: 0; /* 距离容器顶部的距离 */
        left: 0; /* 距离容器左侧的距离 */
    }

    /* 设置主体内容 */
    .individualCenterBody {
        position: absolute;
        top: 0; /* 距离容器顶部的距离 */
        left: 180px; /* 距离容器左侧的距离 */
        width: 1200px;
        height: 620px;
        text-align: center;
        font-weight: 1000;
    }
    .individualCenterBody>.header{
        width: 1200px;
        height: 50px;
        line-height: 50px;
        margin-top: 0px;
        background-color: white;
    }
    .individualCenterBody>.header>span{
        margin-left: 30px;
    }

    /* 个人资料 */
    .individualCenter-side{
        width: 1200px;
        height: 570px;
        display: flex;
        background-color: rgba(193, 192, 192, 0.2);
    }
    /* 个人资料侧边导航栏 */
    .individualCenter-side-nav{
        width: 150px;
        height: 570px;
        background-color: white;
    }
    .individualCenter-side-nav>ul{
        margin-top: 0px;
        padding-left: 0px;
        list-style: none;
        font-size: 13px;
        font-weight:bold;
        cursor: pointer;
    }
    .individualCenter-side-nav>ul>li{
        height: 40px;
        line-height: 40px;
        text-align: left;
    }
    .individualCenter-side-nav>ul>li:hover{
        background-color: rgba(193, 192, 192, 0.2);
    }
    .individualCenter-side-nav>ul>li>img{
        height: 15px;
        margin-left: 15px;
    }
    .individualCenter-side-nav>ul>li>span{
        margin-left: 15px;
    }

    /* 设置侧边导航的主体内容 */
    .individualCenter-side-body{
        width: 1030px;
        height: 550px;
        margin-top: auto;
        margin-left: auto;
        background-color: white;
    }
    .individualCenter-side-body-header{
        height: 50px;
        width: 1030px;
        line-height: 50px;
        margin-top: 0px;
        text-align: left;
    }
    .individualCenter-side-body-body{
        width: 980px;
        height: 480px;
        margin: 0 auto;
    }

    /* 上传按钮 */
    .file-button {
        width: 80px;
        height: 30px;
        line-height: 30px;
        color: white;
        background-color: #769CF2;
        border: 0px;
        font-size: 13px;
        border-radius: 10px;
        padding: 0px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        cursor: pointer;
    }

    /* 选中li */
    .active1{
        background-color: #F2F2F2;
    }

</style>