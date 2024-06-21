// 封装axios
import axios from 'axios';
// 引入router
import router from '@/router/index.js';
// 获取touken
import { getToken } from './token/index.js';

// 创建axios实例
const request = axios.create({
    baseURL: 'http://localhost:8080',
    withCredentials: false,  // 不需要跨域凭证时设为false
    timeout: 30000  // 请求超时时间
})

// 配置请求头的参数类型和编码格式
request.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';

// 配置请求的拦截器
request.interceptors.request.use((config) => {
    // 添加token到请求头部
    const token = getToken("myToken");
    if (token) {
        config.headers['Authorization'] = token;
    }
    return config;
}, (error) => {
    console.error('请求异常==========》', error);
    return Promise.reject(error);
})

// 配置相应拦截器
request.interceptors.response.use((response) => {
    const { code, msg } = response.data;
    if (code === 200) {
        return response;
    } else if (code === 400) {
        ElMessage.error(msg);
    } else if (code === 401) {
        ElMessage.warning('请重新登录.');
        window.sessionStorage.clear();  // 清除本地存储
        router.push('/');  // 跳转到登录页面
        return Promise.reject('需要重新登录');
    } else if (code === 403) {
        ElMessage.error('权限不足.');
    } else if (code === 500) {
        ElMessage.error('服务端异常，请联系管理员.');
    } else if (code === 432) {
        ElMessage.error(msg);
    }
    return Promise.reject(msg);
}, (error) => {
    if (error.response) {
        const { message, code } = error.response.data;
        if (code === 401) {
            ElMessage.warning('请重新登录.');
            window.sessionStorage.clear();  // 清除本地存储
            router.push('/');  // 跳转到登录页面
        }
        ElMessage.error(message);
    } else {
        ElMessage.error('网络错误，请稍后再试.');
    }
    return Promise.reject(error);
})

// 导出
export default request;