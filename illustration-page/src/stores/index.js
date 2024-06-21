// store/index.js
import { createStore } from 'vuex';
import { login } from '@/api/auth/index.js';

export default createStore({
    state: {
        isAuthenticated: sessionStorage.getItem('isAuthenticated') || false,
        token: sessionStorage.getItem('myToken') || null
    },
    mutations: {
        setAuthentication(state, status) {
            state.isAuthenticated = status;
        },
        setToken(state, token) {
            state.token = token;
        }
    },
    actions: {
        async login({ commit }, user) {
        try {
            const res = await login(user);
            if (res.code == 200) {
                commit('setAuthentication', true);
                commit('setToken', res.data);
                return res;
            }
        } catch (error) {
            console.error("登录请求失败", error);
            throw error;
        }
        }
    },
    getters: {
        isAuthenticated: state => state.isAuthenticated
    }
});
