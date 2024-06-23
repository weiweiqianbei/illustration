<template>
    <div>
      <el-container>
        <el-header>
            <Header />
        </el-header>
        <el-main>
          <Main />
        </el-main>
      </el-container>
    </div>
</template>

<script setup>
import Header from './header/index.vue'
import Main from './main/index.vue'
</script>

<style lang="scss" scoped>
</style>