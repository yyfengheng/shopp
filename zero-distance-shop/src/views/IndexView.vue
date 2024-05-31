<template>
    <div class="indexBase">
        
        <base-layout class="baseLayout" v-bind:activeIndex="0"></base-layout>
        <div class="indexBody">
            <p style="font-size: 20px;">全部商品</p>
            <hr>
            <div class="product">
                <div class="child-product" v-for="(item,index) in data.data.records" :key="index">
                    <img :src="item.image" alt="">
                    <div class="product-name">
                        {{item.name}}
                    </div>
                    <div class="product-operations">
                        <span><img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/%E4%BB%B7%E9%92%B1.png" alt="价钱">{{item.price}}</span>
                        <button @click="clickAddShoppingCart(item.id,item.name,item.image,item.price)">加入购物车</button>
                    </div>
                </div>

                
                
            </div>
            <div class="page">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="page"
                    :page-sizes="pageSizes"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
            </div>
        </div>
    </div>
    
</template>

<script>

import BaseLayout from '../components/BaseLayout.vue'

export default {
    name: "IndexView",
    components:{
        BaseLayout
    },
    methods: {
        //设置每页记录数
        handleSizeChange(val) {
            console.log("每条记录数"+val);
            console.log("页码"+this.page);
            this.pageSize = val;
            this.getByCategoryId(null,this.page,this.pageSize);
        },
        //设置页码
        handleCurrentChange(val) {
            console.log("页码"+val);
            console.log("每页记录数"+this.pageSize);
            this.page = val;
            this.getByCategoryId(null,this.page,this.pageSize);
        },
        // 新增购物车
        clickAddShoppingCart(productId,productName,productImage,price){
            this.shoppingCartDTO.productId = productId;
            this.shoppingCartDTO.productName = productName;
            this.shoppingCartDTO.productImage = productImage;
            this.shoppingCartDTO.quantity = 1;
            this.shoppingCartDTO.price = price;
            this.addShoppingCart(this.shoppingCartDTO);
        },
        //根据分类ID查询商品信息或所有信息
        getByCategoryId(categoryId,page,pageSize){
            console.log(this.$httpAddress + this.$productAddress + "/list")
            let url = this.$httpAddress + this.$productAddress + "/list"
            // 变量没有值
            this.$axios.get(url,{
                params: {
                    page: page,
                    pageSize: pageSize
                }
            })
            .then(response => {
                // 后端返回的参数;
                console.log(response.data)
                console.log(this.data)
                if(response.data.msg=="success"){
                    this.data = response.data;
                    this.total = this.data.data.total;
                }else{
                    alert(response.data.msg)
                }
                
            })
            .catch(error=>{
                console.log(error);
                return;
            })
            
        },
        /* 新增购物车商品 */
        addShoppingCart(shoppingCartDTO){
            let url = this.$httpAddress + this.$shoppingCart;
            this.$axios.post(url,JSON.stringify(shoppingCartDTO),{
                headers:{
                    'Content-Type': 'application/json'
                }
            })
            .then(response=>{
                if(response.data.msg=="success"){
                    alert("新增商品成功")
                }else{
                    alert(response.data.msg)
                }
            })
            .catch(error=>{
                console.log(error)
            })
        }
    },
    data() {
      return {
        // 分页组件参数 
        total:40, //总数
        pageSize:10, //每页记录数
        pageSizes:[10, 20, 30, 40], 
        page: 1,//第几页,页码

        // 后端全部商品数据
        data:{
            "code": "200",
            "msg": "success",
            "data": {
                "total": 1,
                "records": [
                    {
                        "id": 61,
                        "name": "现代简约奶油风小户型齐边1米5双人真皮床",
                        "image": "https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/3bff6349-eac2-4ba4-a498-b6a2ac379af9.png",
                        "categoryId": "7",
                        "price": 523.5,
                        "quantity": 55,
                        "merchantId": 74,
                        "createTime": "2011-05-27 09:27:50",
                        "updateTime": "1990-01-05 19:26:11"
                    },
                    {
                        "id": 61,
                        "name": "现代简约奶油风小户型齐边1米5双人真皮床",
                        "image": "https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/3bff6349-eac2-4ba4-a498-b6a2ac379af9.png",
                        "categoryId": "7",
                        "price": 523.5,
                        "quantity": 55,
                        "merchantId": 74,
                        "createTime": "2011-05-27 09:27:50",
                        "updateTime": "1990-01-05 19:26:11"
                    }
                ]
            }
        },
        shoppingCartDTO:{
            productId:0,
            productName:'',
            productImage:'',
            quantity:0,
            price:0.0
        }

      };
    },
    // 挂载完成钩子
    mounted(){
        this.getByCategoryId(null,this.page,this.pageSize);
    }
}
</script>

<style>
    .indexBase{
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
    .indexBody {
        position: absolute;
        top: 0; /* 距离容器顶部的距离 */
        left: 180px; /* 距离容器左侧的距离 */
        width: 1200px;
        height: 600px;
        overflow-y: auto;
        text-align: center;
        font-weight: 1000;
        margin-top: 20px;
    }
    .indexBody>hr{
        width: 950px;
    }

    /* 商品信息 */
    .product{
        width: 960px;
        height: 100%;
        margin: 0 auto;
        display: flex;
        flex-wrap: wrap;
    }

    /* 各个商品信息 */
    .child-product{
        width: 160px;
        height: 280px;
        margin: 15px; /* 设置间距 */
        display: flex;
        justify-content: center;
        align-items: flex-start;
        flex-wrap: wrap;
        border: 1px solid #BBBBBB;
    }
    .child-product>img{
        width: 160px;
        height: 210px;
    }
    .product-name{
        width: 140px;
        height: 30px;
        font-size: 10px;
        text-align: left;
        color: black;
    }
    .product-operations{
        width: 160px;
        height: 30px;
        line-height: 30px;
        font-size: 10px;
        display: flex;
        justify-content: space-around;
        align-items: center;
    }
    .product-operations>span{
        height: 20px;
        display: flex;
        align-items: center;
        color: #FF6700;
        
    }
    .product-operations>span>img{
        height: 10px;
        opacity:0.8;
    }
    .product-operations>button{
        font-size: 12px;
        width: 90px;
        height: 20px;
        border-radius: 20px;
        border: 0ch;
        color: white;
        background-color: #FF6700;
    }

    /* 分页 */
    .page{
        width: 950px;
        height: 40px;
        line-height: 40px;
        text-align: right;
        margin: 0 auto;
    }

    
    
</style>