// store/illustration.js
import { getIllustrations, getIllustrationsByKey, getIllustrationsByUser } from "@/api/auth/illustration.js";

const illustration = {
    state: {
        items: [],
        total: undefined,
        currentPage: undefined
    },
    mutations: {
        setItems(state, items) {
            state.items = items;
        },
        setTotal(state, total) {
            state.total = total;
        },
        setCurrentPage(state, currentPage) {
            state.currentPage = currentPage;
        }
    },
    actions: {
        async getIllustrations({ commit }, p) {
            try {
                const res = await getIllustrations(p);
                if (res.code == 200) {
                    commit('setItems', res.data.illustraions);
                    commit('setTotal', res.data.total);
                    commit('setCurrentPage', res.data.currentPage);
                    return res;
                }
            } catch (error) {
                console.error("获取插画列表失败", error);
                throw error;
            }
        },
        async getIllustrationsByKey({ commit }, { key, p }) {
            try {
                const res = await getIllustrationsByKey(key,p);
                if (res.code == 200) {
                    commit('setItems', res.data.illustraions);
                    commit('setTotal', res.data.total);
                    commit('setCurrentPage', res.data.currentPage);
                    return res;
                }
            } catch (error) {
                console.error("获取插画列表失败", error);
                throw error;
            }
        },
        async getIllustrationsByUser({ commit }, { UID, p }) {
            try {
                const res = await getIllustrationsByUser(UID,p);
                if (res.code == 200) {
                    commit('setItems', res.data.illustraions);
                    commit('setTotal', res.data.total);
                    commit('setCurrentPage', res.data.currentPage);
                    return res;
                }
            } catch (error) {
                console.error("获取插画列表失败", error);
                throw error;
            }
        }
    },
    getters: {
        items: state => state.items,
        total: state => state.total,
        currentPage: state => state.currentPage
    }
};

export default illustration;