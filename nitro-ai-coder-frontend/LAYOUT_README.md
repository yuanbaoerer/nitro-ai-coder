# 全局基础布局说明

## 项目结构

```
src/
├── layouts/
│   └── BasicLayout.vue          # 主布局文件
├── components/
│   └── layout/
│       ├── GlobalHeader.vue     # 全局头部组件
│       └── GlobalFooter.vue     # 全局底部组件
├── views/
│   ├── HomeView.vue            # 首页
│   └── AboutView.vue           # 关于页面
└── App.vue                     # 应用入口
```

## 布局特性

### 1. 响应式设计
- 支持桌面端和移动端适配
- 使用 Flexbox 布局
- 移动端自动隐藏部分菜单项

### 2. 组件化结构
- **BasicLayout.vue**: 主布局容器，包含上中下三部分
- **GlobalHeader.vue**: 顶部导航栏，包含 logo、菜单和用户区域
- **GlobalFooter.vue**: 底部版权信息

### 3. 技术栈
- Vue 3 + TypeScript
- Ant Design Vue 4.x
- Vue Router
- 响应式 CSS

## 使用方法

1. 所有页面都会自动使用 BasicLayout 布局
2. 在 `src/views/` 目录下创建新的页面组件
3. 在 `src/router/index.ts` 中配置路由
4. 菜单项在 `GlobalHeader.vue` 中的 `menuItems` 数组中配置

## 自定义配置

### 修改菜单项
编辑 `src/components/layout/GlobalHeader.vue` 中的 `menuItems` 数组：

```typescript
const menuItems = [
  {
    key: 'home',
    label: '首页',
    path: '/'
  },
  {
    key: 'about',
    label: '关于',
    path: '/about'
  }
  // 添加更多菜单项...
]
```

### 修改样式
- 全局样式在 `App.vue` 中定义
- 组件样式在各组件的 `<style scoped>` 中定义
- 响应式断点：768px

## 开发命令

```bash
# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build
```
