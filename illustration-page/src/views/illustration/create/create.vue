<template>
    <div class="upload-image">
        <el-upload
            class="avatar-uploader"
            ref="uploadRef"
            :http-request="customUploadRequest"
            :show-file-list="false"
            :on-success="handleSuccess"
            :on-change="justSelect"
            :auto-upload="false"
        >
            <img v-if="imageUrl"  :src="imageUrl" class="avatar">
            <el-icon><Plus /></el-icon>
        </el-upload>
    </div>
    <div class="upload-body">
        <div class="upload-form">
            <el-input
                v-model="uploadForm.title"
                size="large"
                style="background-color: #FFFFFF;"
                maxlength="32"
                placeholder="标题"
                show-word-limit
                type="text"
            />
            <el-input
                v-model="uploadForm.remark"
                maxlength="3000"
                size="large"
                style="background-color: #FFFFFF;"
                placeholder="说明"
                :autosize="{ minRows: 5, maxRows: 5 }"
                show-word-limit
                type="textarea"
            />
        </div>
    </div>
    <div class="upload-submit">
        <el-button style="width: 184px;" size="large" type="primary" round @click="illustrationCreate">投稿</el-button>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router';
import { Plus } from '@element-plus/icons-vue'
import { customUpload } from '@/api/file/handleFile.js';

const uploadForm = ref({
    title:'',
    remark:'',
})

const router = useRouter();
const imageUrl = ref('');
const uploadRef = ref(null);

const justSelect = (file) => {
    imageUrl.value = URL.createObjectURL(file.raw);
}

const handleSuccess = (response, file) => {
    ElMessage({
    message: '投稿成功',
    type: 'success',
    plain: true,
  })
    router.push('/');
}

// 自定义上传请求方法
const customUploadRequest = async ({ file, onSuccess, onError }) => {
  try {
    const response = await customUpload(file, uploadForm.value);
    return response;
  } catch (error) {
    ElMessage.error('图片上传失败');
    onError(error);
  }
}

const illustrationCreate = async () => {
    if (uploadRef.value) {
        uploadRef.value.submit();
    }
}

</script>

<style lang="scss" scoped>
.upload-image {
    background-color: rgb(51, 51, 51);
    position: relative;
    padding: 24px;
}
.upload-body {
    margin: 24px auto 0px;
    padding-left: 72px;
    padding-right: 72px;
    width: 1224px;
}
.upload-form {
    width: 740px;
    margin: auto;
}
.upload-submit {
    margin-top: 24px;
    text-align: center;
}
.avatar-uploader {
    display: flex;
    flex-direction: column;
}
.avatar-uploader .avatar {
    max-width: 600px;
    max-height: 600px;
    display: block;
}
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}
.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon {
    position: absolute;
    font-size: 45px;
    color: #FFFFFF;
    text-align: center;
}
</style>