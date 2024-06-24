<template>
    <div class="header">
        <div class="header-left">
            <el-link href="http://localhost/" :underline="false">
                <el-image style="width: 64px; height: 64px" src="http://localhost/images/logo/logo.png" fit="cover" />
            </el-link>
        </div>
        <div class="header-center" @submit.prevent="handleSubmit">
            <form class="header-from">
                <el-input v-model="headerSearch" size="large" placeholder="搜索作品" :prefix-icon="Search" />
            </form>
        </div>
        <div class="header-right">
            <div class="header-submit">
                <el-button style="font-weight: bold;" size="large" round @click='createIllustration'>投稿作品</el-button>
            </div>
            <div class="header-user">
                <el-dropdown  size="large"  trigger="click">
                    <el-button class="el-icon--right userInfo" size="large" circle>
                        <el-avatar :src="user.headPortrait" />
                        <el-icon class="el-icon--right"><arrow-down /></el-icon>
                    </el-button>
                    <template #dropdown>
                        <div style="width: 200px; padding: 7px 20px;">
                            <el-row>
                                <el-col :span="24">
                                    <div>
                                        <el-avatar :size="64" :src="user.headPortrait" />    
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                    <div>
                                        <el-text class="mx-1"  size="large">{{ user.userName }}</el-text>
                                    </div>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="24">
                                    <div>
                                        <el-text class="mx-1" type="info" size="small">{{ user.userId }}</el-text>
                                    </div>
                                </el-col>
                            </el-row>
                        </div>
                        <el-dropdown-menu>
                            <!-- <el-dropdown-item>我的作品</el-dropdown-item> -->
                            <el-dropdown-item @click="centerDialogVisible = true">设置</el-dropdown-item>
                            <el-dropdown-item divided>退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>
    <el-dialog
    v-model="centerDialogVisible"
    title="Warning"
    width="648"
    align-center
  >
    <template #header="{ titleId, titleClass }">
      <div class="my-header">
        <h4 :id="titleId" :class="titleClass">编辑个人资料</h4>
      </div>
    </template>
    <div class="header-line">
        <el-text class="header-line-txt" tag="b">个人头像</el-text>
        <div class="head-line-data">
            <el-upload
                class="head-uploader"
                ref="uploadRef"
                :http-request="userInfoUploadRequest"
                :show-file-list="false"
                :on-success="userHandleSuccess"
                :on-change="justUserSelect"
                :auto-upload="false"
            >
                <el-avatar v-if="user.headPortrait" :size="96" :src="user.headPortrait" />
                <el-icon><Plus /></el-icon>
            </el-upload>
        </div>
    </div>
    <div class="header-line">
        <el-text class="header-line-txt" tag="b">昵称</el-text>
        <div class="head-line-data">
            <el-input
                v-model="user.userName"
                style="width: 392px;"
                size="large"
                maxlength="15"
                show-word-limit
                type="text"
            />
        </div>
    </div>
    <div class="header-line">
        <el-text class="header-line-txt" tag="b">用户id</el-text>
        <div class="head-line-data">
            <el-input
                v-model="user.userId"
                style="width: 392px;"
                size="large"
                show-word-limit
                type="text"
            />
        </div>
    </div>
    <div class="header-line">
        <el-text class="header-line-txt" tag="b">邮箱地址</el-text>
        <div class="head-line-data">
            <el-input
                v-model="user.email"
                style="width: 392px;"
                size="large"
                show-word-limit
                type="text"
            />
        </div>
    </div>
    <template #footer>
      <div class="dialog-footer">
        <div>
            <el-button style="width: 100%;font-weight: bold;"  size="large" type="primary" round @click="updateUser">保存修改</el-button>
        </div>
        <div>
            <el-button style="width: 100%;font-weight: bold;"  size="large" key="plain" text bg round @click="centerDialogVisible = false">取消</el-button>
        </div>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, computed } from 'vue';
import { Search } from '@element-plus/icons-vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { Plus } from '@element-plus/icons-vue'
import { updateUserInfo } from '@/api/auth/myInfo.js';

const router = useRouter();
const store = useStore();
const user = computed(() => store.state.myInfo.user);

const centerDialogVisible = ref(false);
const uploadRef = ref(null);

const hasFile = ref(false);

const handleSubmit = () => {
  if (headerSearch.value.trim() === '') {
    return;
  }
  console.log('提交搜索内容:', headerSearch.value);
};

const headerSearch = ref('')

const createIllustration = () => {
    router.push('/illustration/create');
}

const justUserSelect = (file) => {
    hasFile.value = true;
    user.value.headPortrait = URL.createObjectURL(file.raw);
}

const userHandleSuccess = async (response, file) => {
    await store.dispatch('getMyInfo');
    ElMessage({
        message: '修改成功',
        type: 'success',
        plain: true,
    })
    centerDialogVisible.value = false;
    router.replace({ path: router.currentRoute.value.path, query: router.currentRoute.value.query });
}

// 自定义上传请求方法
const userInfoUploadRequest = async ({ file, onSuccess, onError }) => {
    try {
        const response = await updateUserInfo(file, user.value);
        return response;
    } catch (error) {
        ElMessage.error('修改失败');
        onError(error);
    }
}

const updateUser = async () => {
    if (hasFile.value) {
        uploadRef.value.submit();
    } else {
        userInfoUploadRequest({}, null, null).then(res=>{
            userHandleSuccess(null, null);
        }).catch(e=>{
            console.error(e);
        })
        return;
    }
}
</script>

<style lang="scss" scoped>
.header {
    display: grid;
    align-items: center;
    grid-template-columns: 1fr minmax(0px,528px) 1fr;
    gap: 50px;
    height: 64px;
    padding: 0px 24px;
    background-color: rgb(255, 255, 255);
}
.header-right {
    display: grid;
    gap: 24px;
    grid-template-columns: repeat(2, auto);
    justify-content: flex-end;
}
.userInfo {
    border: 0px;
}
.userInfo:hover {
    background-color: #FFFFFF;
}
.my-header {
    text-align: center;
}
.dialog-footer {
    margin: 0px auto;
    display: flex;
    flex-direction: column;
    gap: 8px;
    width: 392px;
}
.header-line {
    margin-bottom: 20px;
}
.header-line-txt {
    margin-left: 24px;
    margin-right: 24px;
}
.head-line-data {
    margin-top: 4px;
    margin-left: 24px;
    margin-right: 24px;
}
.el-upload .el-icon {
    position: absolute;
    font-size: 45px;
    color: #FFFFFF;
    text-align: center;
}
</style>