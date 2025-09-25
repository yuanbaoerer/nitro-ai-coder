<template>
  <a-layout-header class="header">
    <a-row :wrap="false">
      <!-- 左侧：Logo和标题 -->
      <a-col flex="200px">
        <RouterLink to="/">
          <div class="header-left">
            <img class="logo" src="@/assets/leijun.png" alt="Logo" />
            <h1 class="site-title">Nitro AI Coder</h1>
          </div>
        </RouterLink>
      </a-col>
      <!-- 中间：导航菜单 -->
      <a-col flex="auto">
        <a-menu
          v-model:selectedKeys="selectedKeys"
          mode="horizontal"
          :items="menuItems"
          @click="handleMenuClick"
        />
      </a-col>
      <!-- 右侧：用户操作区域 -->
      <a-col>
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown>
              <a-space>
                <a-avatar :src="loginUserStore.loginUser.userAvatar" />
                {{ loginUserStore.loginUser.userName ?? '无名' }}
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item @click="showUserProfile">
                    <UserOutlined />
                    个人中心
                  </a-menu-item>
                  <a-menu-item @click="doLogout">
                    <LogoutOutlined />
                    退出登录
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </a-layout-header>

  <!-- 个人中心表单模态框 -->
  <Modal
    v-model:open="userProfileVisible"
    title="个人中心"
    ok-text="保存"
    cancel-text="取消"
    @ok="saveUserProfile"
    width="600px"
  >
    <Form
      ref="formRef"
      :model="userForm"
      layout="vertical"
      :label-col="{ span: 24 }"
      :wrapper-col="{ span: 24 }"
    >
      <Form.Item
        label="头像"
        name="userAvatar"
      >
          <Upload
            list-type="picture-card"
            :file-list="fileList"
            :before-upload="beforeUpload"
            @remove="handleRemoveAvatar"
          >
            <template #default>
              <div v-if="fileList.length < 1">
                <UploadOutlined />
                <div style="margin-top: 8px">上传头像</div>
              </div>
            </template>
          </Upload>
        </Form.Item>
      <Form.Item
        label="用户名"
        name="userName"
        :rules="[{ required: true, message: '请输入用户名' }]"
      >
        <Input
          v-model:value="userForm.userName"
          placeholder="请输入用户名"
        />
      </Form.Item>
      <Form.Item
        label="个人简介"
        name="userProfile"
      >
        <Input.TextArea
          v-model:value="userForm.userProfile"
          placeholder="请输入个人简介"
          :rows="4"
        />
      </Form.Item>
    </Form>
  </Modal>
</template>

<script setup lang="ts">
import { computed, h, ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { type MenuProps, message, Modal, Form, Input, Upload } from 'ant-design-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { userLogout, updateUser } from '@/api/userController.ts'
import { LogoutOutlined, HomeOutlined,GithubOutlined, UserOutlined, UploadOutlined } from '@ant-design/icons-vue'

const loginUserStore = useLoginUserStore()
const router = useRouter()
// 当前选中菜单
const selectedKeys = ref<string[]>(['/'])
// 监听路由变化，更新当前选中菜单
router.afterEach((to, from, next) => {
  selectedKeys.value = [to.path]
})

// 菜单配置项
const originItems = [
  {
    key: '/',
    icon: () => h(HomeOutlined),
    label: '主页',
    title: '主页',
  },
  {
    key: '/admin/userManage',
    label: '用户管理',
    title: '用户管理',
  },
  {
    key: '/admin/appManage',
    label: '应用管理',
    title: '应用管理',
  },
  {
    key: 'others',
    icon: () => h(GithubOutlined),
    label: h('a', { href: 'https://github.com/yuanbaoerer/nitro-ai-coder', target: '_blank' }, 'yuanbaoerer'),
    title: 'github',
  },
]

// 过滤菜单项
const filterMenus = (menus = [] as MenuProps['items']) => {
  return menus?.filter((menu) => {
    const menuKey = menu?.key as string
    if (menuKey?.startsWith('/admin')) {
      const loginUser = loginUserStore.loginUser
      if (!loginUser || loginUser.userRole !== 'admin') {
        return false
      }
    }
    return true
  })
}

// 展示在菜单的路由数组
const menuItems = computed<MenuProps['items']>(() => filterMenus(originItems))

// 处理菜单点击
const handleMenuClick: MenuProps['onClick'] = (e) => {
  const key = e.key as string
  selectedKeys.value = [key]
  // 跳转到对应页面
  if (key.startsWith('/')) {
    router.push(key)
  }
}

// 退出登录
const doLogout = async () => {
  const res = await userLogout()
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success('退出登录成功')
    await router.push('/user/login')
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}

// 个人中心相关状态
const userProfileVisible = ref(false)
const userForm = reactive({
  userName: '',
  userProfile: '',
  userAvatar: '',
})
const formRef = ref<any>()
const fileList = ref<{uid: string; name: string; status: 'done'; url: string}[]>([])

// 处理头像移除
const handleRemoveAvatar = () => {
  userForm.userAvatar = ''
  fileList.value = []
}

// 显示个人中心
const showUserProfile = () => {
  // 初始化表单数据
  userForm.userName = loginUserStore.loginUser.userName || ''
  userForm.userProfile = loginUserStore.loginUser.userProfile || ''
  userForm.userAvatar = loginUserStore.loginUser.userAvatar || ''
  
  // 初始化头像文件列表
  fileList.value = []
  if (userForm.userAvatar) {
    fileList.value = [{ 
      uid: '-1', 
      name: 'avatar.png', 
      status: 'done', 
      url: userForm.userAvatar 
    }]
  }
  
  userProfileVisible.value = true
}

// 头像上传前处理
const beforeUpload = (file: File) => {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
  if (!isJpgOrPng) {
    message.error('只能上传JPG/PNG格式的图片!')
    return Upload.LIST_IGNORE
  }
  const isLt2M = file.size / 1024 / 1024 < 2
  if (!isLt2M) {
    message.error('图片大小不能超过2MB!')
    return Upload.LIST_IGNORE
  }
  // 这里只是模拟上传，实际项目中应该上传到服务器获取URL
  const reader = new FileReader()
  reader.readAsDataURL(file)
  reader.onload = (e) => {
    userForm.userAvatar = e.target?.result as string
  }
  return false
}

// 保存用户信息
const saveUserProfile = async () => {
  try {
    await formRef.value?.validate()
    
    // 准备更新用户信息的参数
    const updateParams = {
      id: loginUserStore.loginUser.id,
      userName: userForm.userName,
      userProfile: userForm.userProfile,
      userAvatar: userForm.userAvatar
    }
    
    const res = await updateUser(updateParams)
    
    if (res.data.code === 0) {
      // 更新本地用户信息
      loginUserStore.setLoginUser({
        ...loginUserStore.loginUser,
        userName: userForm.userName,
        userProfile: userForm.userProfile,
        userAvatar: userForm.userAvatar
      })
      
      message.success('保存成功')
      userProfileVisible.value = false
    } else {
      message.error('保存失败，' + res.data.message)
    }
  } catch (error) {
    console.error('表单验证失败', error)
  }
}
</script>

<style scoped>
.header {
  background: #fff;
  padding: 0 24px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo {
  height: 48px;
  width: 48px;
}

.site-title {
  margin: 0;
  font-size: 18px;
  color: #1890ff;
}

.ant-menu-horizontal {
  border-bottom: none !important;
}
</style>
