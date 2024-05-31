import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css' 
import axios from 'axios'

Vue.config.productionTip = false
Vue.use(ElementUI);



// 访问后端地址 
Vue.prototype.$httpAddress = "http://127.0.0.1:8081"
// 用户地址
Vue.prototype.$userAddress = "/zero/user"
// 商品地址
Vue.prototype.$productAddress = "/zero/product"
// 上传图片地址
Vue.prototype.$uploadAddress = "/images/upload"
// 商品类别地址
Vue.prototype.$categoryAddress = "/zero/category"
// 购物车地址
Vue.prototype.$shoppingCart = "/zero/cart"
// 第三方地址: 通过关键字查询地址
Vue.prototype.$keywordAddress = "/address/getAddressDetails"


// 从本地存储获取token
const token = window.localStorage.getItem("token");
console.log("token值:"+token)
// 用户id
Vue.prototype.$userId = window.localStorage.getItem("id");
// 用户状态
Vue.prototype.$userStatus = window.localStorage.getItem("status");

// axios统一发送请求头
Vue.prototype.$axios = axios.create({
  headers:{
    Authorization:token
  }
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
