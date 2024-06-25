<template>
    <div>
        <div class="header">
            <div class="header-img">
                <el-avatar :size="96" :src="user.headPortrait" />
            </div>
            <div class="header-txt">
                <h2>{{ user.userName }}</h2>
            </div>
        </div>
    </div>
    <el-divider style="width: 1224px;margin: 0px auto;" />
    <div>
        <div class="main-body">
            <ul class="index-datas">
                <li v-for="(item, index) in items" :key="index">
                    <div class="index-data-img">
                        <el-link :href="item.path" target="_blank" :underline="false">
                            <el-image style="width: 184px; height: 184px" :src="item.path" fit="cover" />
                        </el-link>
                    </div>
                    <div class="index-data">
                        <el-link style="font-weight: bold;" :href="item.path" target="_blank" :underline="false">{{ item.title }}</el-link>
                    </div>
                    <div class="index-data">
                        <div>
                            <el-link class="index-data-user" :href="`http://localhost/users/${item.userId}`" :underline="false">
                                <el-avatar style="margin-right: 4px;" :size="24" :src="item.user.headPortrait" />
                                {{ item.user.userName }}
                            </el-link>
                        </div>
                        <div style="margin-left: auto;">
                            <el-button v-if="shouldShowButton" type="primary" :icon="Delete" @click="deleteArt(item.id)" />
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="index-page">
            <el-pagination v-model:currentPage="pager.p" :page-size="24" background layout="prev, pager, next" :total="totalItems" @current-change="handleCurrentChange" />
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { Delete } from '@element-plus/icons-vue';
import { deleteIllustrations } from '@/api/auth/illustration.js';

const router = useRouter();
const route = useRoute();
const store = useStore();

const DEFAULT_PAGE_NUMBER = 1;
const theUerID = computed(() => decodeURIComponent(route.params.uid || ''));
const pager = ref({
    p: router.currentRoute.value.query.p ? parseInt(router.currentRoute.value.query.p) : DEFAULT_PAGE_NUMBER
});
const user = computed(() => store.state.myInfo.user);
const items = ref([]);
const totalItems = ref(0);
const shouldShowButton = ref(false);

const fetchData = async () => {
    if (user.value.id == theUerID.value) {
        shouldShowButton.value = true;
    } else {
        shouldShowButton.value = false;
    }
    document.title = user.value.userName;
    const UID = theUerID.value;
    const p = pager.value;
    const res = await store.dispatch('getIllustrationsByUser', { UID, p });
    items.value = res.data.illustraions;
    totalItems.value = res.data.total;
}

onMounted(async () => {
    fetchData();
});

const handleCurrentChange = (Number) => {
    const query = {p: Number};
    router.push( { name: 'Users', query } );
    fetchData();
}

const deleteArt = async (artId) => {
    console.log('id======>',artId);
    const res = await deleteIllustrations(artId);
    console.log(res);
    if (res.code === 200) {
        fetchData();
        return;
    } else {
        ElMessage.error('删除失败');
    }
}
</script>

<style lang="scss" scoped>
.header {
    display: flex;
    margin: 0px auto;
    padding-top: 80px;
    padding-right: 72px;
    padding-left: 72px;
    width: 1224px;
}
.header-img {
    margin-right: 24px
}
.header-txt {
    padding-top: 24px;
    padding-bottom: 24px;
}
.main-body {
    width: 1224px;
    margin: 0 auto;
    padding-left: 72px;
    padding-right: 72px;
}
.index-datas {
    display: grid;
    grid-template-columns: repeat(6, 184px);
    gap: 24px;
    list-style: none;
    margin: 0px;
    padding: 0px;
}
.index-data {
    display: flex;
    margin-top: 4px;
}
.index-data-user {
    align-items: center;
}
.el-link {
    align-items: center;
    color: rgb(31, 31, 31);
    cursor: pointer;
    display: inline-flex;
    flex-direction: row;
    font-size: 14px;
    justify-content: center;
    outline: none;
    padding: 0;
    position: relative;
    text-decoration: none;
    vertical-align: middle;
}
.index-page {
    display: flex;
    justify-content: center;
}
</style>