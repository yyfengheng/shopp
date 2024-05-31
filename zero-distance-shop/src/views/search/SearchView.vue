<template>
    <div class="search-body">
        <div class="search-header">
            <img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/logo2.png" @click="backIndex" alt="">
            <div class="search">
                <input type="text" placeholder="搜索" v-model="searchText" name="searchText">
                <img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/%E6%90%9C%E7%B4%A2.png" alt="" @click="clickSearch">
            </div>
        </div>
        <div class="search-main">
            <div class="header">
                <span style="font-size: 20px;font-weight: bold;">全部商品</span>
            </div>
            <hr style="width:950px"/>
            <div class="search-nav">
                <ul>
                    <!-- <li style="font-size: 15px;color: #BBBBBB;" @click="clickSortByPrice(2)">价格从高到低</li> -->
                    <!-- <li style="font-size: 15px;color: #BBBBBB;" @click="clickSortByPrice(1)">价格从低到高</li> -->
                </ul>
            </div>

            <div class="child-productSearch">
                <div class="child-product" v-for="(item,index) in productVO" :key="index">
                    <img :src="item.image" alt="">
                    <div class="product-name">
                        {{ item.name }}
                    </div>
                    <div class="product-operations">
                        <span><img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/%E4%BB%B7%E9%92%B1.png" alt="价钱">{{ item.price }}</span>
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
export default {
    name:"SearchView",

    data:function(){
        return {
            // 分页组件参数 
            total:40, //总数
            pageSize:10, //每页记录数
            pageSizes:[10, 20, 30, 40], 
            page: 1,//第几页,页码

            searchText:"",
            productVO:[
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
            ],
            shoppingCartDTO:{
                productId:0,
                productName:'',
                productImage:'',
                quantity:0,
                price:0.0
            }


        }
    },
    methods:{
        //设置每页记录数
        handleSizeChange(val) {
            console.log("每条记录数"+val);
            console.log("页码"+this.page);
            this.pageSize = val;
            this.getByProductName(this.searchText,this.page,this.pageSize)
        },
        //设置页码
        handleCurrentChange(val) {
            console.log("页码"+val);
            console.log("每页记录数"+this.pageSize);
            this.page = val;
            this.getByProductName(this.searchText,this.page,this.pageSize)
        },
        // 搜索商品
        clickSearch(){
            console.log(this.searchText)
            this.getByProductName(this.searchText,this.page,this.pageSize)
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
        // 回到首页
        backIndex(){
            window.location.href = "http://localhost:8080/"
        },


        /* 访问后端方法 */
        /* 根据商品名称查询商品 */
        getByProductName(productName,page,pageSize){
            let url = this.$httpAddress + this.$productAddress + "/search";
            this.$axios.get(url,{
                params:{
                    productName:productName,
                    page:page,
                    pageSize:pageSize
                }
            })
            .then(response=>{
                if(response.data.msg == "success"){
                    console.log(response.data.data.records)
                    this.productVO = response.data.data.records
                    this.total = response.data.data.total
                }else{
                    alert(response.data.msg)
                }
                
            })
            .catch(error=>{
                console.log(error)
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
    mounted(){
        const queryString = window.location.search;
        this.searchText = queryString.split("=")[1]
        this.getByProductName(this.searchText,this.page,this.pageSize);
    }
}
</script>

<style>
    .search-body{
        width: 1380px;
        height: 660px;
        display: flex;
        margin: 0 auto;
        margin-top: 30px;
        flex-wrap: wrap;
    }
    .search-header{
        width: 1380px;
        height: 50px;
        background-color: rgba(118, 156, 242, 1);
        line-height: 50px;
        display: flex;
        align-items: center
    }
    .search-header>img{
        width: 130px;
        height: 40px;
    }
    .search-main{
        width: 1380px;
        height: 610px;
        overflow-y: auto;
        background-color: white;
    }
    /* 设置导航栏 */
    .search-nav{
        width: 950px;
        height: 40px;
        line-height: 40px;
        margin: 0 auto;
        padding-left: 0px;
    }
    .search-nav>ul{
        margin: 0;
        padding: 0;
        text-align: left;
        list-style-type: none;
    }
    .search-nav>ul>li{
        display: inline-block;
        margin-left: 10px;
        font-size: 15px;
        font-weight: 450;
        cursor: pointer;
    }

    /* 商品信息 */
    .child-productSearch{
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