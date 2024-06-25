// 请求接口
import request from '@/utils/request.js';

// 获取所有图片接口
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

// 模糊查询图片接口
export async function getIllustrationsByKey(key, params) {
    try {
        const response = await request({
            url: '/illustration/' + key,
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

// 查询用户发布图片接口
export async function getIllustrationsByUser(UID, params) {
    try {
        const response = await request({
            url: '/illustration/users/' + UID,
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

// 删除图片接口
export async function deleteIllustrations(artId) {
    try {
        const response = await request({
            url: '/illustration/' + artId,
            method: 'DELETE'
        });
        return response.data;
    } catch (error) {
        // 可以在这里处理异常情况，比如网络错误等
        console.error('删除失败', error);
        throw error; // 继续抛出错误，让调用方处理
    }
}