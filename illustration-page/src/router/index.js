import { createRouter, createWebHistory } from 'vue-router';

import HomePage from '@/views/home.vue';
import RegisterPage from '@/views/register.vue';
import CreateIllustrationPage from '@/views/illustration/create/create.vue';

// 配置路由规则
const constRoutes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage,
    children: [
      {
        path: '/',
        name: 'index',
        component: () => import('@/views/index.vue'),
        meta: {
          requiresAuth: true
        }
      },
      {
        path: '/illustration/create',
        name: 'CreateIllustration',
        component: CreateIllustrationPage,
        meta: {
          title: '插画投稿'
        }
      },
      {
        path: '/tags/:findkey/artworks',
        name: 'Artworks',
        component: () => import('@/views/artworks.vue'),
        meta: {
          title: route => `#${decodeURIComponent(route.params.findkey)} | 作品`
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
  history: createWebHistory(),
  // 使用路由规则常量
  routes: constRoutes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = typeof to.meta.title === 'function' ? to.meta.title(to) : to.meta.title;
  } else {
    document.title = '插画分享网站'; // 设置默认标题
  }
  // if (to.meta.requiresAuth && !sessionStorage.getItem('isAuthenticated')) {
  //   // 如果需要登录并且未登录，则重定向到登录页面
  //   next('/');
  // } else {
  //   // 允许访问路由
    next();
  // }
});

export default router
