import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

// 配置路由规则
const constRoutes = [
  {
    path: '/',
    name: 'login',
    component: Login
  }
]

// 创建路由
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // 使用路由规则常量
  routes: constRoutes
})

export default router
