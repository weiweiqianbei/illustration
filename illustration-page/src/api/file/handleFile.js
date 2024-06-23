// 请求接口
import request from '@/utils/request.js';

// 登录接口
export async function customUpload(file, data) {
    try {
        const formData = new FormData();
        formData.append('file', file);  // 将文件添加到 FormData 中
        // formData.append(data);
        formData.append('data', JSON.stringify(data));  // // 将数据转为 JSON 字符串后添加
        const response = await request({
            url: '/illustration/upload',
            method: 'POST',
            data: formData, // 使用 FormData 作为请求体
            headers: {
                'Content-Type': 'multipart/form-data'  // 设置请求头为 multipart/form-data
            }
        });
        return response.data;
    } catch (error) {
        // 可以在这里处理异常情况，比如网络错误等
        console.error('图片上传失败', error);
        throw error; // 继续抛出错误，让调用方处理
    }
}