// 请求接口
import request from '@/utils/request.js';

// 登录接口
export async function getIllustrations(params) {
    try {
        const response = await request({
            url: '/illustration',
            method: 'GET',
            params: params
        });
        return response.data;
    } catch (error) {
        // 可以在这里处理异常情况，比如网络错误等
        console.error('列表请求出错', error);
        throw error; // 继续抛出错误，让调用方处理
    }
}