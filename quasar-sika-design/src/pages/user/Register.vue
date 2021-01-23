<template>
  <div class="sc-design">
    <div class="q-px-md q-mb-lg">
      <q-form @submit="onSubmit" @reset="onReset" ref="registerForm">
        <div class="q-gutter-y-md">
          <div class="text-left text-body1">注册</div>
          <div class="row">
            <div class="col-12 q-gutter-y-sm">
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                v-model="registerData.username"
                debounce="1000"
                dense
                placeholder="用户名"
                maxlength="32"
                type="text"
                square
                :rules="[
                  (val) => (val && val.length > 0) || '请输入用户名',
                  (val) => (val && val.length >= 4) || '用户名必须大于等于4位',
                  checkRegisterUsername
                  ]"
              >
                <template v-slot:prepend>
                  <q-icon name="person" />
                </template>
                <template v-slot:hint>
                  <div class="text-positive">
                    {{ registerHintData.usernameHint }}
                  </div>
                </template>
              </q-input>
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
                clear-icon="cancel"
                v-model="registerData.phone"
                maxlength="11"
                type="tel"
                dense
                placeholder="手机号"
                debounce="1000"
                square
                :rules="[(val) => (val && val.length > 0) || '请输入手机号', checkRegisterPhone]"
              >
                <template v-slot:prepend>
                  <q-btn-dropdown
                    color="secondary"
                    flat
                    :label="phonePrefix"
                    style="margin-left: -12px"
                  >
                    <q-list>
                      <q-item
                        clickable
                        v-close-popup
                        @click="onItemClick('+86')"
                      >
                        <q-item-section>
                          <q-item-label>+86</q-item-label>
                        </q-item-section>
                      </q-item>

                      <q-item
                        clickable
                        v-close-popup
                        @click="onItemClick('+87')"
                      >
                        <q-item-section>
                          <q-item-label>+87</q-item-label>
                        </q-item-section>
                      </q-item>
                    </q-list>
                  </q-btn-dropdown>
                </template>
                <template v-slot:hint>
                  <div class="text-positive">
                    {{ registerHintData.phoneHint }}
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
              <div class="row">
                <div class="col text-left">
                  <q-btn
                    class="no-border-radius"
                    unelevated
                    type="submit"
                    :loading="registerLoading"
                    color="primary full-width"
                    label="注 册"
                    size="md"
                  >
                    <template v-slot:loading>
                      <q-spinner-hourglass class="on-left" />
                      注册...
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
  sendMailCode,
  getCaptchaVerifyCode,
  checkCaptchaVerifyCode,
  checkMailCode,
  register
} from '@/api/user'
import commonUtil from '@/utils/commonUtil'
import _ from 'lodash'

export default {
  name: 'Register',
  data() {
    return {
      registerHintData: {
        usernameHint: null,
        emailHint: null,
        phoneHint: null,
        captchaVerifyCodeHint: null,
        emailValidateCodeHint: null
      },
      registerData: {
        username: null,
        email: null,
        password: null,
        confirmPassword: null,
        phone: null,
        captchaVerifyCode: null,
        emailValidateCode: null,
        emailCode: 'MC_00002',
        captchaType: 20
      },
      usernameForShow: null,
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
      this.registerData = commonUtil.resetObj(this.registerData)
      this.registerHintData = commonUtil.resetObj(this.registerHintData)
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
        checkCaptchaVerifyCode(this.registerData).then(response => {
          resolve(true)
          this.registerHintData.captchaVerifyCodeHint = '图片验证码正确'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    checkEmailCode(val) {
      return new Promise((resolve, reject) => {
        checkMailCode(this.registerData).then(response => {
          resolve(true)
          this.registerHintData.emailValidateCodeHint = '邮箱验证码正确'
        }).catch(err => {
          resolve(err)
        })
      })
    },
    getCaptchaVerifyCode() {
      getCaptchaVerifyCode(this.registerData).then(response => {
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
      sendMailCode(this.registerData).then(response => {
        commonUtil.notifySuccess('验证码发发送成功')
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
