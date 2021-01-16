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
                v-model="registerData.email"
                debounce="1000"
                dense
                placeholder="邮箱"
                maxlength="128"
                type="email"
                square
                :rules="[(val) => (val && val.length > 0) || '请输入邮箱',checkRegisterEmail]"
              >
                <template v-slot:prepend>
                  <q-icon name="mail" />
                </template>
                <template v-slot:hint>
                  <div class="text-positive">
                    {{ registerHintData.emailHint }}
                  </div>
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                type="text"
                maxlength="6"
                v-model="registerData.captchaVerifyCode"
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
                    {{ registerHintData.captchaVerifyCodeHint }}
                  </div>
                </template>
              </q-input>
              <q-input
                outlined
                clearable
                type="text"
                maxlength="6"
                v-model="registerData.emailValidateCode"
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
                    {{ registerHintData.emailValidateCodeHint }}
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
                    v-model="registerData.password"
                    dense
                    debounce="1000"
                    placeholder="密码"
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
                    v-model="registerData.confirmPassword"
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
                    :loading="registerLoading"
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
      <q-dialog v-model="successCard" persistent>
        <q-card
          class="my-card text-center q-col-gutter-y-lg q-px-md"
          style="width: 600px; max-width: 80vw"
        >
          <q-icon
            class="q-mt-md text-h4"
            name="check_circle"
            color="positive"
            size="60px"
          />
          <div>
            <div class="text-h6">
              恭喜！账户：<span>{{ usernameForShow }} </span> 注册成功
            </div>
            <div class="text-grey">
              已经为您自动登录，赶快加入QuasarSikaDesign享受极致纵享丝滑的体验吧！
            </div>
          </div>
          <q-card-actions align="center" class="q-mb-md">
            <q-btn v-close-popup glossy color="primary" to="/account/center" label="个人中心" />
            <q-btn v-close-popup glossy color="secondary" to="/" label="进入首页" />
          </q-card-actions>
        </q-card>
      </q-dialog>
    </div>
  </div>
</template>

<script>
import {
  checkRegisterUsername,
  checkRegisterEmail,
  checkRegisterPhone,
  sendUserRegisterMailCode,
  getRegisterCaptchaVerifyCode,
  checkRegisterCaptchaVerifyCode,
  checkUserRegisterMailCode,
  register
} from '@/api/user'
import commonUtil from '@/utils/commonUtil'
import _ from 'lodash'

export default {
  name: 'ForgetPassword',
  data() {
    return {
      registerHintData: {
        emailHint: null,
        captchaVerifyCodeHint: null,
        emailValidateCodeHint: null
      },
      registerData: {
        email: null,
        password: null,
        confirmPassword: null,
        captchaVerifyCode: null,
        emailValidateCode: null
      },
      usernameForShow: null,
      showPassword: false,
      phonePrefix: '+86',
      captchaVerifyCodeUrl: '',
      isPwd: true,
      validateCodeLoading: false,
      successCard: false,
      emailCountDown: 60,
      registerLoading: false
    }
  },
  created() {
    this.getCaptchaVerifyCode()
  },
  methods: {
    onSubmit() {
      this.register()
      // this.$router.push({
      //   path: '/user/registerResult',
      //   query: { email: this.email }
      // })
    },
    onReset() {
      this.usernameForShow = this.registerData.username
      commonUtil.resetObj(this.registerData)
      commonUtil.resetObj(this.registerHintData)
    },
    onItemClick(value) {
      this.phonePrefix = value
    },
    checkRegisterUsername(val) {
      return new Promise((resolve, reject) => {
        checkRegisterUsername(this.registerData).then(response => {
          resolve(true)
          this.registerHintData.usernameHint = '用户名可用'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    checkRegisterEmail(val) {
      return new Promise((resolve, reject) => {
        checkRegisterEmail(this.registerData).then(response => {
          resolve(true)
          this.registerHintData.emailHint = '邮箱可用'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    checkRegisterPhone(val) {
      return new Promise((resolve, reject) => {
        checkRegisterPhone(this.registerData).then(response => {
          resolve(true)
          this.registerHintData.phoneHint = '手机号可用'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    checkCaptchaVerifyCode(val) {
      return new Promise((resolve, reject) => {
        checkRegisterCaptchaVerifyCode(this.registerData).then(response => {
          resolve(true)
          this.registerHintData.captchaVerifyCodeHint = '图片验证码正确'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    checkEmailCode(val) {
      this.showPassword = (val === '1')
      console.log(this.showPassword)
      return new Promise((resolve, reject) => {
        checkUserRegisterMailCode(this.registerData).then(response => {
          resolve(true)
          this.registerHintData.emailValidateCodeHint = '邮箱验证码正确'
          // this.showPassword = true
        }).catch(err => {
          // this.showPassword = false
          resolve(err)
        })
      })
    },
    getCaptchaVerifyCode() {
      getRegisterCaptchaVerifyCode().then(response => {
        this.captchaVerifyCodeUrl = response
      })
    },
    getEmailVerifyCode() {
      if (_.isEmpty(this.registerData.email)) {
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
      sendUserRegisterMailCode(this.registerData).then(response => {
        commonUtil.notifySuccess('验证码发送成功')
      }).catch(err => {
        console.log(err)
        this.validateCodeLoading = false
        this.emailCountDown = 60
        clearInterval(interval)
      })
    },
    register(callBack) {
      // we set loading state
      this.registerLoading = true
      // simulate a delay
      setTimeout(() => {
        register(this.registerData).then(response => {
          // we're done, we reset loading state
          this.success()
        }).catch(err => {
          console.log(err)
          this.registerLoading = false
        })
      }, 1000)
    },
    success() {
      commonUtil.notifySuccess('注册成功')
      this.registerLoading = false
      this.successCard = true
      this.$refs.registerForm.reset()
    }
  },
  computed: {
    passwordValida: function() {
      return this.registerData.password === this.registerData.confirmPassword
    }
  }
}
</script>

<style scoped>
.q-field__control {
  padding-left: 0;
}
</style>
