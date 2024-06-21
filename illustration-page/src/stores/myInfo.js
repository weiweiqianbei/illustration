// store/myInfo.js
import { getMyInfo } from "@/api/auth/myInfo.js";

const myInfo = {
    state: {
        user: JSON.parse(localStorage.getItem('userInfo')) || undefined
    },
    mutations: {
        setUser(state, user) {
            state.user = user;
            localStorage.setItem('userInfo', JSON.stringify(user));
        }
    },
    actions: {
        async getMyInfo({ commit }) {
            try {
                const res = await getMyInfo();
                if (res.code == 200) {
                    commit('setUser', res.data);
                    return res;
                }
            } catch (error) {
                console.error("获取用户信息失败", error);
                throw error;
            }
        }
    },
    getters: {
        user: state => state.user
    }
};

export default myInfo;