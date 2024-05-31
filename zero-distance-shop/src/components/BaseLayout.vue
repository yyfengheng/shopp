<template>
    <div class="base">
        <div class="body">
            <div class="nav">
                <ul class="child-nav">
                    <li class="child-nav-first"><img @click="backIndex()" src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/logo2.png" alt=""></li>
                    <li v-for="(item,index) in navItems" :key="index"  v-on:click="setActive(index)"><router-link v-bind:class="{active:activeIndex == index}" class="router-link" :to="'/'+item.to">{{item.name}}</router-link></li>
                    <li class="child-nav-second" @click="setSearch" style="cursor: pointer;">搜索</li>
                </ul>
            </div>
            <div class="main">
                <div class="search" v-bind:style="{display:search}">
                    <input type="text" placeholder="搜索" v-model="searchText" name="searchText">
                    <img src="https://caihongjia-web-tlias.oss-cn-beijing.aliyuncs.com/%E6%90%9C%E7%B4%A2.png" alt="" @click="clickSearch">
                </div>
            </div>
            <footer>
                版权所有 &copy; caihongjia
            </footer>
        </div>
        
    </div>
</template>

<script>
export default {
    name: 'BaseLayout',
    props:['activeIndex'],
    data:function(){
        return {
            navItems:[
                {
                    'name':'首页',
                    'to':'index'
                },
                {
                    'name':'商品类别',
                    'to':'productCategory'
                },
                {
                    'name':'购物管理',
                    'to':'shoppingManagement'
                },
                {
                    'name':'商品管理',
                    'to':'productMerchant'
                },
                {
                    'name':'个人中心',
                    'to':'individualCenter'
                }
            ],
            search:"none",
            searchText:''
        }
    },
    methods:{
        setActive:function(index){
            this.props.activeIndex=index;
        },
        setSearch(){
            if(this.search=="none"){
                this.search = "inline-block"
            }else{
                this.search = "none"
            }
        },
        clickSearch(){
            console.log(this.searchText)
            window.location.href = "http://localhost:8080/search?searchText=" + this.searchText
        },
        // 回到首页
        backIndex(){
            window.location.href = "http://localhost:8080/"
        },
    }
}
</script>

<style>
    /* 设置页面背景颜色 */
    body {
        background-color: #F2F2F2;
    }

    /* 设置整个页面布局 */
    .body {
        width: 1380px;
        height: 540px;
        display: flex;
        margin: 0 auto;
        margin-top: 30px;
        flex-wrap: wrap;
    }

    /* 设置侧面导航栏 */
    .nav{
        background-color: #769CF2;
        width: 180px;
        height: 620px;
    }
    .nav ul{
        list-style-type: none; /* 取消点号 */
        padding: 0; /* 去除默认的内边距 */
        margin-left: 5px;
    }


    /* 设置子导航logo */
    .child-nav-first{
        margin-left: 0;
        margin-bottom: 10px;
    }
    .child-nav-first img{
        padding-left: 5px;
        width: 140px;
    }
    
     /* 设置子导航 */
    .child-nav-second{
        background-color: #769CF2;
        width: 130px;
        height: 35px;
        line-height: 35px;
        font-size: 15px;
        color: white;
        margin-left: 10px;
        text-align:left;
        padding-left: 20px;
        border-radius: 10px;
    }
    .router-link:hover {
        background-color: rgba(29, 97, 255, 0.5);
    }
    .router-link{
        text-decoration: none;
        background-color: #769CF2;
        display: block;
        width: 130px;
        height: 35px;
        line-height: 35px;
        font-size: 15px;
        color: white;
        margin-left: 10px;
        text-align:left;
        padding-left: 20px;
        border-radius: 10px;
    }


    /* 已选中 */
    .active{
        background-color: rgba(29, 97, 255, 0.5);
        color: rgba(255, 255, 255, 1);
    }

    /* 设置右侧主体 */
    .main{
        background-color: white;
        width: 1200px;
        height: 620px;
    }

    /* 搜索框 */
    .main>.search{
        width: 1185px;
        height: 50px;
        background-color: rgba(118, 156, 242, 0.5);
        line-height: 50px;
        position: fixed; 
        top: 30px; 
        left: 248px; 
        z-index: 9999; 
    }
    .search>input{
        width: 300px;
        height: 20px;
        margin-left: 30px;
        border-radius: 20px;
        border: 0;
    }
    .search>img{
        position: relative;
        left: -25px;
        top: 4px;
        z-index: 99999;
    }

    /* 底部备案号 */
    footer{
        width: 1380px;
        height: 40px;
        line-height: 40px;
        text-align: center;
        background-color: rgba(211, 211, 211, 0.5);
    }
   
    
</style>