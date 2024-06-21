<template>
    <div class="account-container">
        <div class="account-background"></div>
        <div class="account-register">
            <el-link class="botton-register" href="http://localhost/" :underline="false">登录</el-link>
        </div>
        <div class="account-form-container">
            <div class="account-form">
                <h3>注册账号</h3>
                <el-form  ref="formRef" :model="registerFrom"> 
                    <el-form-item>
                        <el-input v-model="registerFrom.account" placeholder="邮箱地址" />
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="registerFrom.password" placeholder="密码" type="password" show-password />
                    </el-form-item>
                    <el-button class="account-button" type="primary" round :disabled="isLoginDisabled" @click="handleRegister">注册</el-button>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { ref, computed } from 'vue';
    import { register } from '@/api/auth/register.js';
    import { useStore } from 'vuex';
    import { useRouter } from 'vue-router';
    import { setToken } from '@/utils/token/index.js';

    const store = useStore();
    const router = useRouter();

    const registerFrom = ref({
        account: '',
        password: ''
    });
    // 计算属性判断登录按钮是否应该禁用
    const isLoginDisabled = computed(() => {
        return !registerFrom.value.account || !registerFrom.value.password;
    });

    const handleRegister = async () => {
        try {
            const b = await register(registerFrom.value);
            if (b.code == 200) {
                const res = await store.dispatch('login', registerFrom.value);
                if (res.code == 200) {
                // 将 token 存储到 pinia 中
                setToken("myToken", res.data);
                ElMessage({
                    message: '注册成功',
                    type: 'success',
                });
                // 跳转页面
                router.push('/');
                }
            }
        } catch (error) {
            console.error("注册失败", error);
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