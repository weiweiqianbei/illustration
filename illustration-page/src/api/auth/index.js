// 请求接口
import request from '@/utils/request.js';

// 登录接口
export async function login(data) {
    try {
        const response = await request({
            url: '/user/login',
            method: 'POST',
            data: data
        });
        return response.data;
    } catch (error) {
        // 可以在这里处理异常情况，比如网络错误等
        console.error('登录请求出错', error);
        throw error; // 继续抛出错误，让调用方处理
    }
}

// 登出接口
export async function logout() {
    try {
        const response = await request({
            url: '/user/logout',
            method: 'GET'
        });
        return response.data;
    } catch (error) {
        // 可以在这里处理异常情况，比如网络错误等
        console.error('登出请求出错', error);
        throw error; // 继续抛出错误，让调用方处理
    }
}