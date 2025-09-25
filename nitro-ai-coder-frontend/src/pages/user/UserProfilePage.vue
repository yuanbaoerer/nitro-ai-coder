<template>
  <div id="userProfilePage">
    <a-card title="个人中心" :bordered="false" class="profile-card">
      <a-form layout="vertical" :model="profileForm" ref="profileFormRef">
        <a-form-item label="用户名" name="userName" :rules="[{ required: true, message: '请输入用户名' }]">
          <a-input v-model:value="profileForm.userName" placeholder="请输入用户名" />
        </a-form-item>
        <a-form-item label="头像" name="userAvatar">
          <a-input v-model:value="profileForm.userAvatar" placeholder="请输入头像URL" />
          <a-image v-if="profileForm.userAvatar" :src="profileForm.userAvatar" :width="120" style="margin-top: 12px" />
        </a-form-item>
        <a-form-item label="简介" name="userProfile">
          <a-textarea v-model:value="profileForm.userProfile" placeholder="请输入用户简介" />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click="handleSave">保存修改</a-button>
          <a-button style="margin-left: 12px" @click="handleCancel">取消</a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { updateUser, getUserById } from '@/api/userController.ts'
import { message } from 'ant-design-vue'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { useRouter } from 'vue-router'

const router = useRouter()
const loginUserStore = useLoginUserStore()
const profileFormRef = ref<any>(null)
const profileForm = reactive<API.UserUpdateRequest>({
  id: undefined,
  userName: '',
  userAvatar: '',
  userProfile: '',
  userRole: 'user',
})

// 获取当前用户信息
const fetchUserInfo = async () => {
  try {
    const loginUser = loginUserStore.loginUser
    if (loginUser && loginUser.id) {
      const res = await getUserById({ id: loginUser.id })
      if (res.data.code === 0 && res.data.data) {
        const userData = res.data.data
        // 填充表单数据
        profileForm.id = userData.id
        profileForm.userName = userData.userName || ''
        profileForm.userAvatar = userData.userAvatar || ''
        profileForm.userProfile = userData.userProfile || ''
        profileForm.userRole = userData.userRole || 'user'
      } else {
        message.error('获取用户信息失败：' + (res.data.message || '未知错误'))
      }
    }
  } catch (error) {
    console.error('获取用户信息时发生错误', error)
    message.error('获取用户信息失败')
  }
}

// 保存修改
const handleSave = async () => {
  try {
    // 验证表单
    if (profileFormRef.value) {
      await profileFormRef.value.validate()
    }
    
    // 调用更新接口
    const res = await updateUser(profileForm)
    if (res.data.code === 0 && res.data.data) {
      message.success('保存成功')
      // 更新登录用户信息
      await loginUserStore.fetchLoginUser()
    } else {
      message.error('保存失败：' + (res.data.message || '未知错误'))
    }
  } catch (error) {
    console.error('保存用户信息失败', error)
    message.error('保存失败，请检查输入数据')
  }
}

// 取消操作
const handleCancel = () => {
  // 重置表单
  fetchUserInfo()
}

// 页面加载时获取用户信息
onMounted(() => {
  fetchUserInfo()
})
</script>

<style scoped>
#userProfilePage {
  padding: 24px;
  background: white;
  margin-top: 16px;
}

.profile-card {
  max-width: 600px;
  margin: 0 auto;
}
</style>