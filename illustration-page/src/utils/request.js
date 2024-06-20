// 封装axios
import axios from 'axios';
// 引入router
import router from '@/router/index.js';
// 获取touken
import { getToken } from './token/index.js';

// 创建axios
const request = axios.create({
    // 根请求地址
    baseURL: 'http://localhost:8080',
    withCredentials: false,  // 用于配置请求接口跨域时是否需要凭证
    timeout: 30000  // 超时时间
})

// 配置请求头的参数类型和编码格式
axios.defaults.headers['Content-Type'] = 'application/json?charset=urf-8'

// 配置请求的拦截器
request.interceptors.request.use((config) => {
    // 在请求头添加token
    // TODO token应该从pinia中获取
    if (getToken("loveToken")) {
        config.headers['Authorization'] = getToken("loveToken");
    }
    return config;
}, (error) => {
    // 发生异常
    console.log('请求异常==========》', error);
    return Promise.reject(error);
})

// 配置相应拦截器
request.interceptors.response.use((response) => {
    let {msg, code} = response.data;
    if (code == 200) {
        return response;
    } else if (code == 400) {
        ElMessage.error(msg);
    } else if (code == 401) {
        ElMessage({
            message: '请重新登录.',
            type: 'warning',
          });
        // 需要重新登录，跳转到登录页面，清除pinia中的数据
        window.sessionStorage.clear();
        router.push('/');
    } else if (code == 403) {
        ElMessage.error('权限不足.');
    } else if (code == 500) {
        ElMessage.error('服务端异常，请联系管理员.');
    } else if (code == 432) {
        ElMessage.error(msg);
    }
    return Promise.reject(msg);
}, (error) => {
    let {message, code} = error.response.data;
    console.log(code);
    ElMessage.error(message);
    window.sessionStorage.clear();
    router.push('/');
    return Promise.reject(error);
})

// 导出
export default request;