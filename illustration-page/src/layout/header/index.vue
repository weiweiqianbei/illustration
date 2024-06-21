<template>
    <div class="header">
        <div class="header-left"></div>
        <div class="header-center" @submit.prevent="handleSubmit">
            <form class="header-from">
                <el-input v-model="headerSearch" size="large" placeholder="搜索作品" :prefix-icon="Search" />
            </form>
        </div>
        <div class="header-right">
            <div class="header-submit">
                <el-button style="font-weight: bold;" size="large" round @click='mytest'>投稿作品</el-button>
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
                            <el-dropdown-item>我的作品</el-dropdown-item>
                            <el-dropdown-item>设置</el-dropdown-item>
                            <el-dropdown-item>退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { Search } from '@element-plus/icons-vue';
import { useStore } from 'vuex';

const store = useStore();
const user = computed(() => store.state.myInfo.user);

const handleSubmit = () => {
  if (headerSearch.value.trim() === '') {
    return;
  }
  console.log('提交搜索内容:', headerSearch.value);
  // 在这里处理提交逻辑，例如发送请求到后端进行搜索
  // axios.post('/api/search', { query: headerSearch.value }).then(response => {
  //   console.log('搜索结果:', response.data);
  // }).catch(error => {
  //   console.error('搜索出错:', error);
  // });
};

const headerSearch = ref('')

const mytest = async () => {
    ElMessage({
        message: 'Congrats, this is a success message.',
        type: 'success',
    });
    console.log(user.name);
    console.log("OK");
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
</style>