import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
// 引入router规则
import router from './router'

// 引入icon图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

app.use(createPinia())
app.use(router)

app.mount('#app')
