import Vue from 'vue'
import VueRouter from 'vue-router'
import IndexView from '../views/IndexView.vue'
import ProductMerchantView from '@/views/product/ProductMerchantView'
import ProductCategoryView from '@/views/product/ProductCategoryView'
import ShoppingManagementView from '@/views/cart/ShoppingManagementView.vue'
import IndividualCenterView from '@/views/Individual/IndividualCenterView'
import SearchView from '@/views/search/SearchView'
import LoginView from '@/views/login/LoginView.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/index',
    name: 'index',
    component: IndexView
  },
  {
    path: '/productMerchant',
    name: 'productMerchant',
    component: ProductMerchantView
  },
  {
    path: '/productCategory',
    name: 'productCategory',
    component: ProductCategoryView
  },
  {
    path: '/shoppingManagement',
    name: 'shoppingManagement',
    component: ShoppingManagementView
  },
  {
    path: '/individualCenter',
    name: 'individualCenter',
    component: IndividualCenterView
  },
  {
    path: '/search',
    name: 'search',
    component: SearchView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/',
    redirect: '/index'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
