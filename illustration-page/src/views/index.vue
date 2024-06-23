<template>
    <div class="main-body">
        <ul class="index-datas">
            <li v-for="(item, index) in items" :key="index">
                <div class="index-data-img">
                    <el-link :href="item.path" target="_blank" :underline="false">
                        <el-image style="width: 184px; height: 184px" :src="item.path" fit="cover" />
                    </el-link>
                </div>
                <div class="index-data">
                    <el-link style="font-weight: bold;" href="https://element-plus.org" target="_blank" :underline="false">{{ item.title }}</el-link>
                </div>
                <div class="index-data">
                    <el-link class="index-data-user" href="https://element-plus.org" target="_blank" :underline="false">
                        <el-avatar style="margin-right: 4px;" :size="24" :src="item.user.headPortrait" />
                        {{ item.user.userName }}
                    </el-link>
                </div>
            </li>
        </ul>
    </div>
    <div class="index-page">
        <el-pagination :current-page="pager.p" background layout="prev, pager, next" :total="total" @current-change="myTest" />
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const store = useStore();

const router = useRouter();
const DEFAULT_PAGE_NUMBER = 1;
const pager = ref({
  p: router.currentRoute.value.query.p ? parseInt(router.currentRoute.value.query.p) : DEFAULT_PAGE_NUMBER
});

const items = ref([]);
const total = ref();

onMounted(async () => {
  const res = await store.dispatch('getIllustrations', pager.value);
  items.value = res.data.illustraions;
  total.value = res.data.total;
  console.log(items.value);
})
const myTest =  async (Number) => {
    console.log(Number);
}
</script>

<style lang="scss" scoped>
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