<script setup lang="ts">
import { reactive } from 'vue'
import { userRegister } from '@/api/userController.ts'
import { message } from 'ant-design-vue'
import { useRouter, RouterLink } from 'vue-router'

const formState = reactive<API.UserRegisterRequest>({
  userAccount: '',
  userPassword: '',
  checkPassword: ''
})

const router = useRouter()

/**
 * 提交表单
 * @param values
 */
const handleSubmit = async (values: any) => {
  // 检查密码是否一致
  if (values.userPassword !== values.checkPassword) {
    message.error('两次输入的密码不一致')
    return
  }

  const res = await userRegister(values)
  // 注册成功
  if (res.data.code === 0) {
    message.success('注册成功')
    // 自动跳转到登录页面
    router.push({
      path: '/user/login',
      replace: true
    })
  } else {
    message.error('注册失败，' + res.data.message)
  }
}
</script>

<template>
  <div id="userRegisterPage">
    <div class="logo-container">
      <img src="/leijun_favicon.ico" alt="Nitro AI Coder" class="logo-icon" />
      <h2 class="title">Nitro AI Coder - 用户注册</h2>
    </div>
    <div class="desc">零代码生成应用</div>
    <a-form :model="formState" name="basic" autocomplete="off" @finish="handleSubmit">
      <a-form-item 
        name="userAccount" 
        :rules="[
          { required: true, message: '请输入账号' },
          { min: 4, message: '账号不能小于 4 位' }
        ]"
      >
        <a-input v-model:value="formState.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        name="userPassword"
        :rules="[
          { required: true, message: '请输入密码' },
          { min: 8, message: '密码不能小于 8 位' },
          { pattern: /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{8,}$/, message: '密码必须包含字母和数字' }
        ]"
      >
        <a-input-password v-model:value="formState.userPassword" placeholder="请输入密码" />
      </a-form-item>
      <a-form-item
        name="checkPassword"
        :rules="[
          { required: true, message: '请再次输入密码' },
          {
            validator: (_: any, value: string) => {
              if (!value || formState.userPassword === value) {
                return (window as any).Promise.resolve()
              }
              return (window as any).Promise.reject(new Error('两次输入的密码不一致'))
            }
          }
        ]"
      >
        <a-input-password v-model:value="formState.checkPassword" placeholder="请再次输入密码" />
      </a-form-item>
      <div class="tips">
        已有账号？
        <RouterLink to="/user/login">去登录</RouterLink>
      </div>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%">注册</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>


<style scoped>
#userRegisterPage {
  max-width: 360px;
  margin: 0 auto;
  text-align: center;
  padding: 50px 20px;
}

.logo-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 16px;
}

.logo-icon {
  width: 32px;
  height: 32px;
  margin-right: 10px;
}

.title {
  margin-bottom: 16px;
  margin: 0;
}

.desc {
  color: #bbb;
  margin-bottom: 16px;
}

.tips {
  margin-bottom: 16px;
  color: #bbb;
  font-size: 13px;
  text-align: right;
}
</style>
