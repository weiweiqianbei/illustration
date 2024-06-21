import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '@/views/home.vue';
import RegisterPage from '@/views/register.vue';

// 配置路由规则
const constRoutes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage,
    children: [
      {
        path: '/',
        name: 'cs',
        component: () => import('@/views/index.vue'),
        meta: {
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: '/signup',
    name: 'Register',
    component: RegisterPage,
    meta: {
      title: '注册账号'
    }
  }
]

// 创建路由
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // 使用路由规则常量
  routes: constRoutes
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title || '插画分享网站';
  // if (to.meta.requiresAuth && !sessionStorage.getItem('isAuthenticated')) {
  //   // 如果需要登录并且未登录，则重定向到登录页面
  //   next('/');
  // } else {
  //   // 允许访问路由
    next();
  // }
});

export default router
