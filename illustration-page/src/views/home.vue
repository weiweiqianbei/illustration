<template>
    <div v-if="isAuthenticated">
        <HomePage />
    </div>
    <div v-else>
        <LoginPage />
    </div>
</template>

<script setup>
import { computed } from 'vue';
import { useStore } from 'vuex';
import LoginPage from '@/views/Login.vue';
import HomePage from '@/layout/index.vue'; // 确保路径正确

// 使用 store
const store = useStore();
const isAuthenticated = computed(() => store.getters.isAuthenticated);
</script>

<style lang="scss" scoped>

</style>