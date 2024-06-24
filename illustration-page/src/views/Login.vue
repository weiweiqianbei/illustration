<template>
    <div class="account-container">
        <div class="account-background"></div>
        <div class="account-register">
            <el-link class="botton-register" href="http://localhost/signup" :underline="false">注册账号</el-link>
        </div>
        <div class="account-form-container">
            <div class="account-form">
                <h3>插画分析网站</h3>
                <el-form  ref="formRef" :model="loginFrom"> 
                    <el-form-item>
                        <el-input v-model="loginFrom.account" placeholder="邮箱地址或用户ID" />
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="loginFrom.password" placeholder="密码" type="password" show-password />
                    </el-form-item>
                    <div class="dont-know">
                        <el-link type="primary" :underline="false">不知道密码</el-link>
                    </div>
                    <el-button class="account-button" type="primary" round :disabled="isLoginDisabled" @click="handleLogin">登录</el-button>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script setup>
    // 导入ref
    import { ref, computed } from 'vue';
    import { useStore } from 'vuex';
    import { useRouter } from 'vue-router';
    import { setToken } from '@/utils/token/index.js';

    const store = useStore();
    const router = useRouter();

    // 声明表单绑定值
    const loginFrom = ref({
        account: undefined,
        password: undefined
    });
    // 计算属性判断登录按钮是否应该禁用
    const isLoginDisabled = computed(() => {
        return !loginFrom.value.account || !loginFrom.value.password;
    });
    // 声明登录方法
    const handleLogin = async () => {
        try {
            const res = await store.dispatch('login', loginFrom.value);
            if (res.code == 200) {
                // 将 token 存储到 pinia 中
                setToken("myToken", res.data);
                await store.dispatch('getMyInfo');
                ElMessage({
                    message: '登录成功',
                    type: 'success',
                });
                // 跳转页面
                router.go(0);
            }
        } catch (error) {
            console.error("登录失败", error);
        }
    }
</script>

<style lang="scss" scoped>
.account-container {
    height: 100%;
    width: 100%;
    position: relative;
}
.account-background {
    background-image: url('http://localhost/images/background/31251762_p0.jpg');
    background-size: cover;
    position: fixed;
    inset: 0px;
}
.account-register {
    display: flex;
    justify-content: flex-end;
    position: absolute;
    right: 0px;
    margin-top: 16px;
    margin-right: 16px;
    margin-left: 16px;
}
.account-form-container {
    position: relative;
    margin: 0px auto;
    padding-top: 20vh;
    padding-bottom: 136px;
    width: 392px;
}
.botton-register {
    align-items: center;
    justify-content: center;
    user-select: none;
    white-space: nowrap;
    color: #FFFFFF;
    background-color: rgba(0,0,0,0.32);
    font-size: 14px;
    line-height: 22px;
    font-weight: bold;
    padding-right: 24px;
    padding-left: 24px;
    border-radius: 999999px;
    height: 40px;
}
.account-form {
    padding: 0px 40px 40px;
    border-radius: 24px;
    background-color: #FFFFFF;
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    text-align: center;
    box-sizing: border-box;
    form {
        margin-top: 40px;
        
    }
}
.dont-know {
    display: flex;
}
.account-button {
    margin-top: 24px;
    width: -webkit-fill-available;
    font-size: 14px;
    line-height: 22px;
    font-weight: bold;
    height: 40px;
}
</style>