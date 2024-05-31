<template>
    <div class="shoppingManagementBase">
        <base-layout class="baseLayout" v-bind:activeIndex="2"></base-layout>
        <div class="shoppingManagementBody">
            <div class="header">
                <span style="font-size: 20px;">购物车</span>
            </div>
            <hr/>
            <div class="child-shoppingManagement">
                <ul class="child-shoppingManagement-header">
                    <li><label><input type="checkbox" v-model="isAllSelected" @change="checkAll()"/>全选</label></li>
                    <li>商品信息</li>
                    <li>单价</li>
                    <li>数量</li>
                    <li>金额</li>
                    <li>操作</li>
                </ul>
                    
                <div class="child-shoppingManagement-body" v-for="item in shoppingCartVO" :key="item.id">
                    <div><input type="checkbox" v-model="ids" :value="item.id" @change="checkBox()"/><img :src="item.productImage" alt=""><span style="width: 170px;word-wrap: break-word;margin-top: 10px;margin-left: 10px;">{{item.productName}}</span></div>
                    <div><span>{{item.price}}</span></div>
                    <div><span><el-input-number size="small" style="width:100px" v-model="item.quantity" @change="handleChange(item)" :min="1" :max="50" label="描述文字"></el-input-number></span></div>
                    <div><span>{{item.price * item.quantity}}</span></div>
                    <div><span style="cursor: pointer;" @click="delById(item.id)">删除</span></div>
                </div>

                <div class="child-shoppingManagement-footer">
                    <div><span @click="delByIds()">删除</span></div>
                    <div><span>合计：{{ totalAmount }}</span><button v-bind:disabled="isDisabled">结算</button></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import BaseLayout from '@/components/BaseLayout.vue';

export default {
    name:"ProductMerchantView",
    components:{
        BaseLayout
    },
    data:function(){
        return {
            // 是否点击全选框
            isAllSelected:false,
            // 按钮是否禁用
            isDisabled:true,
            // 多选框id
            ids:[],
            // 购物车类
            shoppingCartVO:[
                {
                    id:1,
                    productId:1,
                    productName:"123",
                    productImage:"http://dummyimage.com/400x400",
                    quantity:6,
                    price:5.25
                }
            ],
            // 购物车id
            shoppingCartId:0

        }
    },
    methods:{
        // 全选多选框
        checkAll(){
            console.log(this.ids);
            if(this.ids.length == 0){
                for(let i = 0; i < this.shoppingCartVO.length; i++){
                    this.ids.push(this.shoppingCartVO[i].id)
                    
                }
                this.isDisabled = false;
                console.log(this.ids)
            }else if(this.ids.length > 0 && this.ids.length < this.shoppingCartVO.length){
                this.ids = []
                for(let i = 0; i < this.shoppingCartVO.length; i++){
                    this.ids.push(this.ids.push(this.shoppingCartVO[i].id))
                }
                this.isDisabled = false;
            }else{
                this.ids = []
                this.isDisabled = true;
            }
        },
        // 各个单选框
        checkBox(){
            if(this.ids.length == 0){
                this.isDisabled = true;
            }else{
                this.isDisabled = false;
            }
        },
        // 根据id删除购物车信息
        delById(id){
            console.log(id);
            this.ids = [];
            this.ids.push(id);
            this.deleteShoppingCart();
        },
        // 根据ids批量删除购物车信息
        delByIds(){
            this.deleteShoppingCart();
        },
        
        // 计数器,用于改变购物车商品数量
        handleChange(value) {
            this.updateShoppingCart(value);
        },

        /* 访问后端的方法 */
        /* 根据用户ID查询购物车商品信息 */
        getByUserId(){
            let url = this.$httpAddress + this.$shoppingCart;
            this.$axios.get(url)
            .then(response=>{
                if(response.data.msg=="success"){
                    console.log(response.data);
                    this.shoppingCartVO = response.data.data;
                }else{
                    alert(response.data.msg);
                }
            })
            .catch(error=>{
                console.log(error);
            })
        },
        /* 根据id修改购物车商品数量 */
        updateShoppingCart(value){
            let url = this.$httpAddress + this.$shoppingCart;
            this.$axios.put(url,JSON.stringify(value),{
                headers:{
                    "Content-Type": "application/json"
                }
            })
            .then(response=>{
                if(response.data.msg=="success"){
                    console.log(1)
                }else{
                    alert(response.data.msg)
                }
            })
            .catch(error=>{
                console.log(error)
            })
        },
        /* 根据id批量删除购物车商品 */
        deleteShoppingCart(){
            let url = this.$httpAddress + this.$shoppingCart;
            let queryParams = this.ids.map(function(item){
                return "ids=" + encodeURIComponent(item)
            }).join('&')
            console.log(queryParams)
            this.$axios.delete(url+"?"+queryParams)
            .then(response=>{
                if(response.data.msg == "success"){
                    console.log(response.data)
                    window.location.reload();
                }else{
                    console.log(response.data.msg);
                }
            })
            .catch(error=>{
                console.log(error)
            })
        }

    },
    // 计算属性
    computed: {
        totalAmount() {
            let total = 0;
            for(let i of this.shoppingCartVO){
                total += i.price * i.quantity;
            }
            return total;
        }
    },
    mounted(){
        this.getByUserId();
    }
}
</script>

<style>
    .shoppingManagementBase{
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
    .shoppingManagementBody {
        position: absolute;
        top: 0; /* 距离容器顶部的距离 */
        left: 180px; /* 距离容器左侧的距离 */
        width: 1200px;
        height: 620px;
        overflow-y: auto;
        font-weight: 1000;
    }
    .shoppingManagementBody>hr{
        width: 950px;
    }

    /* 购物车商品信息显示 */
    .child-shoppingManagement{
        width: 950px;
        height: 100%;
        margin: 0 auto;
        margin-top: 20px;
    }
    .child-shoppingManagement-header{
        width: 800px;
        height: 50px;
        line-height: 50px;
        box-sizing: border-box;
        margin: 0 auto;
        padding: 0;
        display: flex;
        justify-content: space-between;
        list-style-type: none;
    }
    .child-shoppingManagement-header>li{
        display: inline-block;
        width: 80px;
        margin-left: 10px;
        text-align: center;
        font-size: 15px;
        font-weight: bold;
    }
    .child-shoppingManagement-header>li:nth-child(2){
        width: 250px;
    }
    .child-shoppingManagement-body{
        width: 800px;
        height: 120px;
        margin: 0 auto;
        margin-top: 10px;
        border: 1px solid #BBBBBB;
        display: flex;
        justify-content: space-between;
    }
    .child-shoppingManagement-body>div{
        width: 80px;
        height: 120px;
        line-height: 120px;
        text-align: center;
        font-size: 12px;
        font-weight: 400;
    }
    .child-shoppingManagement-body>div input{
        margin-right: 20px;
    }
    .child-shoppingManagement-body>div:nth-child(1){
        width: 330px;
        line-height: 20px;
        margin-left: 25px;
        text-align: left;
        display: flex;
    }
    .child-shoppingManagement-body>div>img{
        display: inline;
        width: 80px;
        height: 120px;
    }
    /* 底部信息 */
    .child-shoppingManagement-footer{
        width: 800px;
        height: 40px;
        line-height: 40px;
        margin: 0 auto;
        margin-top: 10px;
        border: 1px solid #BBBBBB;
        display: flex;
        justify-content: space-between;
    }
    .child-shoppingManagement-footer>div:nth-child(1){
        margin-left: 50px;
    }
    .child-shoppingManagement-footer>div:nth-child(1)>span{
        font-size: 12px;
        font-weight: 400;
        cursor: pointer;
    }
    .child-shoppingManagement-footer>div:nth-child(2){
        margin-right: 50px;
    }
    .child-shoppingManagement-footer>div:nth-child(2)>span{
        margin-right: 30px;
    }
    .child-shoppingManagement-footer>div:nth-child(2)>button{
        width: 75px;
        height: 25px;
        color: white;
        background-color: black;
        border: 0px;
        border-radius: 15px;
    }
    .child-shoppingManagement-footer>div:nth-child(2)>button:disabled {
        background-color: #ccc;
        color: #fff;
    }
</style>