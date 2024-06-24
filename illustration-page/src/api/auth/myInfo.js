// 请求接口
import request from '@/utils/request.js';

// 获取个人信息接口
export async function getMyInfo() {
    try {
        const response = await request({
            url: '/user',
            method: 'GET'
        });
        return response.data;
    } catch (error) {
        // 可以在这里处理异常情况，比如网络错误等
        console.error('获取个人信息请求出错', error);
        throw error; // 继续抛出错误，让调用方处理
    }
}

// 修改个人信息接口
export async function updateUserInfo(file, data) {
    try {
        const formData = new FormData();
        if (file) {
            formData.append('file', file);  // 将文件添加到 FormData 中
        }
        formData.append('data', JSON.stringify(data));  // // 将数据转为 JSON 字符串后添加
        const response = await request({
            url: '/user',
            method: 'PATCH',
            data: formData, // 使用 FormData 作为请求体
            headers: {
                'Content-Type': 'multipart/form-data'  // 设置请求头为 multipart/form-data
            }
        });
        return response.data;
    } catch (error) {
        // 可以在这里处理异常情况，比如网络错误等
        console.error('修改个人信息请求出错', error);
        throw error; // 继续抛出错误，让调用方处理
    }
}