import { defineStore } from 'pinia'

export const useAlertsStore = defineStore('alerts', {
    state: () => ({ 
        menuList: []
    }),
    getters: {
      Array: (state) => state.menuList,
    },
    actions: {
      setMenuList(data) {
        this.menuList = data;
      }
    },
})