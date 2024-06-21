<template>
    <div class="main-body">
        <ul class="index-datas">
            <li v-for="(item, index) in items" :key="index">
                <div class="index-data-img">
                    <el-link href="https://element-plus.org" target="_blank" :underline="false">
                        <el-image style="width: 184px; height: 184px" :src="url" fit="cover" />
                    </el-link>
                </div>
                <div class="index-data">
                    <el-link style="font-weight: bold;" href="https://element-plus.org" target="_blank" :underline="false">一只小鸟</el-link>
                </div>
                <div class="index-data">
                    <el-link class="index-data-user" href="https://element-plus.org" target="_blank" :underline="false">
                        <el-avatar style="margin-right: 4px;" :size="24" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
                        张三
                    </el-link>
                </div>
            </li>
        </ul>
    </div>
    <div class="index-page">
        <el-pagination background layout="prev, pager, next" :total="1000" @current-change="myTest" />
    </div>
</template>

<script setup>
import { ref } from 'vue';

const items = ref([
  {
    id: 1,
    name: 'Item 1',
    imageUrl: 'item1.jpg'
  },
  {
    id: 2,
    name: 'Item 2',
    imageUrl: 'item2.jpg'
  },
  {
    id: 3,
    name: 'Item 3',
    imageUrl: 'item3.jpg'
  },
  {
    id: 2,
    name: 'Item 2',
    imageUrl: 'item2.jpg'
  },
  {
    id: 3,
    name: 'Item 3',
    imageUrl: 'item3.jpg'
  },
  {
    id: 2,
    name: 'Item 2',
    imageUrl: 'item2.jpg'
  },
  {
    id: 3,
    name: 'Item 3',
    imageUrl: 'item3.jpg'
  },
  {
    id: 2,
    name: 'Item 2',
    imageUrl: 'item2.jpg'
  },
  {
    id: 3,
    name: 'Item 3',
    imageUrl: 'item3.jpg'
  },
  {
    id: 2,
    name: 'Item 2',
    imageUrl: 'item2.jpg'
  },
  {
    id: 3,
    name: 'Item 3',
    imageUrl: 'item3.jpg'
  },
  {
    id: 2,
    name: 'Item 2',
    imageUrl: 'item2.jpg'
  },
  {
    id: 3,
    name: 'Item 3',
    imageUrl: 'item3.jpg'
  }
]);
const url = 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg';
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