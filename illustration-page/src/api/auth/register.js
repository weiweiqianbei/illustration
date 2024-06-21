// 请求接口
import request from '@/utils/request.js';

// 登录接口
export async function register(data) {
    try {
        const response = await request({
            url: '/user/register',
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