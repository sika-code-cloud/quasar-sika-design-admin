<template>
  <div class="sc-design">
    <div class="q-px-md q-mb-lg">
      <q-form @submit="onSubmit" @reset="onReset" ref="registerForm">
        <div class="q-gutter-y-md">
          <div class="text-left text-body1">找回密码</div>
          <div class="row">
            <div class="col-12 q-gutter-y-sm">
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                v-model="forgetPasswordData.email"
                debounce="1000"
                dense
                placeholder="邮箱"
                maxlength="128"
                type="email"
                square
                :rules="[(val) => (val && val.length > 0) || '请输入邮箱',checkForgetPasswordEmail]"
              >
                <template v-slot:prepend>
                  <q-icon name="mail" />
                </template>
                <template v-slot:hint>
                  <div class="text-positive">
                    {{ forgetPasswordHintData.emailHint }}
                  </div>
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                type="text"
                maxlength="6"
                v-model="forgetPasswordData.captchaVerifyCode"
                dense
                placeholder="图片验证码"
                square
                debounce="1000"
                :rules="[
                  (val) => (val && val.length > 0) || '请输入验证码',
                  checkCaptchaVerifyCode
                ]"
              >
                <template v-slot:error>
                  Please use maximum 3 characters.
                </template>
                <template v-slot:prepend>
                  <q-icon name="qr_code" />
                </template>
                <template v-slot:after>
                  <img style="width: 100px" :src="captchaVerifyCodeUrl" @click="getCaptchaVerifyCode"
                       class="login-code-img" />
                </template>
                <template v-slot:hint>
                  <div class="text-positive">
                    {{ forgetPasswordHintData.captchaVerifyCodeHint }}
                  </div>
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                type="text"
                maxlength="6"
                v-model="forgetPasswordData.emailValidateCode"
                dense
                placeholder="邮箱验证码"
                debounce="1000"
                square
                :rules="[(val) => (val && val.length > 0) || '请输入验证码', checkEmailCode]"
              >
                <template v-slot:prepend>
                  <q-icon name="attach_email" />
                </template>
                <template v-slot:after>
                  <q-btn
                    unelevated
                    class="no-border-radius"
                    :loading="validateCodeLoading"
                    @click="getEmailVerifyCode"
                    color="secondary"
                    label="获取验证码"
                  >
                    <template v-slot:loading>
                      <q-icon name="alarm" class="on-left" />
                      {{ emailCountDown }} s
                    </template>
                  </q-btn>
                </template>
                <template v-slot:hint>
                  <div class="text-positive">
                    {{ forgetPasswordHintData.emailValidateCodeHint }}
                  </div>
                </template>
              </q-input>
              <transition appear
                          :animation-duration="2"
                          enter-active-class="animated fadeInDown"
                          leave-active-class="animated fadeOutUp">
                <div v-if="showPassword" class="q-gutter-y-sm " style="animation-duration: 0.5s;">
                  <q-input
                    outlined
                    clearable
                    clear-icon="cancel"
                    :type="isPwd ? 'password' : 'text'"
                    v-model="forgetPasswordData.password"
                    dense
                    debounce="1000"
                    placeholder="新密码"
                    maxlength="32"
                    square
                    :rules="[
                  (val) => (val && val.length > 0) || '请输入密码',
                  (val) => (val && val.length >= 6) || '密码长度必须大于等于6位'
                ]"
                  >
                    <template v-slot:prepend>
                      <q-icon name="lock" />
                    </template>
                    <template v-slot:append>
                      <q-icon
                        :name="isPwd ? 'visibility_off' : 'visibility'"
                        class="cursor-pointer"
                        @click="isPwd = !isPwd"
                      />
                    </template>
                  </q-input>
                  <q-input
                    outlined
                    clearable
                    clear-icon="cancel"
                    :type="isPwd ? 'password' : 'text'"
                    v-model="forgetPasswordData.confirmPassword"
                    dense
                    debounce="1000"
                    placeholder="确认密码"
                    maxlength="32"
                    square
                    :rules="[
                  (val) => (val && val.length > 0) || '请输入确认密码',
                  (val) => (passwordValida) || '两次密码不一致'
                ]"
                  >
                    <template v-slot:prepend>
                      <q-icon name="lock" />
                    </template>
                    <template v-slot:append>
                      <q-icon
                        :name="isPwd ? 'visibility_off' : 'visibility'"
                        class="cursor-pointer"
                        @click="isPwd = !isPwd"
                      />
                    </template>
                  </q-input>
                </div>
              </transition>
              <div class="row">
                <div class="col text-left">
                  <q-btn
                    class="no-border-radius"
                    unelevated
                    type="submit"
                    :loading="loading"
                    color="primary full-width"
                    label="提 交"
                    size="md"
                  >
                    <template v-slot:loading>
                      <q-spinner-hourglass class="on-left" />
                      提交...
                    </template>
                  </q-btn>
                </div>
                <div class="col text-right">
                  <q-btn
                    to="/user/login"
                    color="primary"
                    flat
                    label="使用已有账户登录"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </q-form>
    </div>
  </div>
</template>

<script>
import {
  getCaptchaVerifyCode,
  checkCaptchaVerifyCode,
  sendMailCode,
  checkMailCode,
  checkForgetPasswordEmail,
  findBackPassword
} from '@/api/user'
import commonUtil from '@/utils/commonUtil'
import _ from 'lodash'

export default {
  name: 'ForgetPassword',
  data() {
    return {
      forgetPasswordHintData: {
        emailHint: null,
        captchaVerifyCodeHint: null,
        emailValidateCodeHint: null
      },
      forgetPasswordData: {
        email: null,
        password: null,
        confirmPassword: null,
        captchaVerifyCode: null,
        emailValidateCode: null,
        emailCode: 'MC_00003',
        captchaType: 30
      },
      usernameForShow: null,
      showPassword: false,
      captchaVerifyCodeUrl: '',
      isPwd: true,
      validateCodeLoading: false,
      successCard: false,
      emailCountDown: 60,
      loading: false
    }
  },
  created() {
    this.getCaptchaVerifyCode()
  },
  methods: {
    onSubmit() {
      this.findBackPassword()
    },
    onReset() {
      this.usernameForShow = this.forgetPasswordData.username
      commonUtil.resetObj(this.forgetPasswordData)
      commonUtil.resetObj(this.forgetPasswordHintData)
    },
    checkCaptchaVerifyCode(val) {
      return new Promise((resolve, reject) => {
        checkCaptchaVerifyCode(this.forgetPasswordData).then(response => {
          resolve(true)
          this.forgetPasswordHintData.captchaVerifyCodeHint = '图片验证码正确'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    checkEmailCode(val) {
      return new Promise((resolve, reject) => {
        checkMailCode(this.forgetPasswordData).then(response => {
          resolve(true)
          this.forgetPasswordHintData.emailValidateCodeHint = '邮箱验证码正确'
          this.showPassword = true
        }).catch(err => {
          this.showPassword = false
          resolve(err)
        })
      })
    },
    getCaptchaVerifyCode() {
      getCaptchaVerifyCode(this.forgetPasswordData).then(response => {
        this.captchaVerifyCodeUrl = response
      })
    },
    getEmailVerifyCode() {
      if (_.isEmpty(this.forgetPasswordData.email)) {
        commonUtil.notifyAlert('请输入邮箱')
        return
      }
      // we set loading state
      this.validateCodeLoading = true
      const interval = setInterval(() => {
        this.emailCountDown--
        if (this.emailCountDown <= 0) {
          this.validateCodeLoading = false
          this.emailCountDown = 60
          clearInterval(interval)
        }
      }, 1000)
      sendMailCode(this.forgetPasswordData).then(response => {
        commonUtil.notifySuccess('验证码发送成功')
      }).catch(err => {
        console.log(err)
        this.validateCodeLoading = false
        this.emailCountDown = 60
        clearInterval(interval)
      })
    },
    checkForgetPasswordEmail(val) {
      return new Promise((resolve, reject) => {
        checkForgetPasswordEmail(this.forgetPasswordData).then(response => {
          resolve(true)
          this.forgetPasswordHintData.emailHint = '邮箱正确'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    findBackPassword() {
      // we set loading state
      this.loading = true
      // simulate a delay
      setTimeout(() => {
        findBackPassword(this.forgetPasswordData).then(response => {
          // we're done, we reset loading state
          this.success()
        }).catch(err => {
          console.log(err)
          this.loading = false
        })
      }, 1000)
    },
    success() {
      commonUtil.alert('重置密码成功，返回登录').onOk(() => {
        location.href = '/user/login'
      })
      this.loading = false
      this.successCard = true
      this.$refs.registerForm.reset()
    }
  },
  computed: {
    passwordValida: function() {
      return this.forgetPasswordData.password === this.forgetPasswordData.confirmPassword
    }
  }
}
</script>

<style scoped>
.q-field__control {
  padding-left: 0;
}
</style>
