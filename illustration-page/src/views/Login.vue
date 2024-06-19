<template>
    <div class="login-container">
        <div class="login-background"></div>
        <div class="login-register">
            <el-link class="botton-register" href="https://element-plus.org" target="_blank">注册账号</el-link>
        </div>
        <div class="login-form-container">
            <div class="login-form">
                <h3>插画分析网站</h3>
                <el-form  ref="formRef" :model="loginFrom" label-width="auto"> 
                    <el-form-item>
                        <el-input v-model="loginFrom.account" placeholder="邮箱地址或用户ID" />
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="loginFrom.password" placeholder="密码" type="password" show-password>
                            <template #suffix>
                                <el-icon class="el-input__icon"><View /></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <div class="dont-know">
                        <el-link type="primary" :underline="false">不知道密码</el-link>
                    </div>
                    <el-button class="form-button" type="primary" round disabled @click="handleLogin">登录</el-button>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script setup>
    // 导入ref
    import { ref } from 'vue';
    // 声明表单绑定值
    const loginFrom = ref({
        account: undefined,
        password: undefined
    })
    // 声明登录方法
    function handleLogin() {
        
    }
</script>

<style lang="scss" scoped>
.login-container {
    height: 100%;
    width: 100%;
    position: relative;
}
.login-background {
    background-image: url('../assets/background/31251762_p0.jpg');
    background-size: cover;
    position: fixed;
    inset: 0px;
}
.login-register {
    display: flex;
    justify-content: flex-end;
    position: absolute;
    right: 0px;
    margin-top: 16px;
    margin-right: 16px;
    margin-left: 16px;
}
.login-form-container {
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
.login-form {
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
.form-button {
    margin-top: 24px;
    width: -webkit-fill-available;
    font-size: 14px;
    line-height: 22px;
    font-weight: bold;
    height: 40px;
}
</style>