<template>
  <a-layout-header class="global-header">
    <div class="header-content">
      <!-- Logo、标题和菜单区域 -->
      <div class="logo-section">
        <img src="/src/assets/leijun.png" alt="Logo" class="logo" />
        <span class="site-title">Nitro AI Coder</span>
        
        <!-- 菜单区域 -->
        <a-menu
          v-model:selectedKeys="selectedKeys"
          mode="horizontal"
          :items="menuItems"
          @click="handleMenuClick"
        />

      </div>

      <!-- 用户区域 -->
      <div class="user-section">
        <a-button type="primary" @click="handleLogin">
          登录
        </a-button>
      </div>
    </div>
  </a-layout-header>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import type { MenuProps } from 'ant-design-vue'

const router = useRouter()
// 当前选中菜单
const selectedKeys = ref<string[]>(['/'])
// 监听路由变化，更新当前选中菜单
router.afterEach((to, from) => {
  selectedKeys.value = [to.path]
})


// 处理菜单点击
const handleMenuClick: MenuProps['onClick'] = (e) => {
  const key = e.key as string
  selectedKeys.value = [key]
  // 跳转到对应页面
  router.push(key)
}


// 菜单配置
const menuItems = [
  {
    key: 'home',
    label: '首页',
  },
  {
    key: 'about',
    label: '关于',
  }
]


// 登录处理
const handleLogin = () => {
  console.log('登录按钮被点击')
  // TODO: 实现登录逻辑
}
</script>

<style scoped>
.global-header {
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 0;
  position: sticky;
  top: 0;
  z-index: 1000;
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  height: 64px;
}

.logo-section {
  display: flex;
  align-items: center;
  gap: 24px;
}

.logo {
  height: 32px;
  width: 32px;
}

.site-title {
  font-size: 18px;
  font-weight: 600;
  color: #1890ff;
  white-space: nowrap;
}

.header-menu {
  border-bottom: none;
  background: transparent;
  line-height: 64px;
}

.user-section {
  display: flex;
  align-items: center;
  gap: 12px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .header-content {
    padding: 0 16px;
  }
  
  .site-title {
    display: none;
  }
  
  .header-menu {
    display: none;
  }
  
  .logo-section {
    gap: 12px;
  }
}

@media (max-width: 1024px) {
  .logo-section {
    gap: 16px;
  }
}
</style>
