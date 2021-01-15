<template>
  <div class="sc-design">
    <div>
      <q-form @submit="onSubmit" @reset="onReset" ref="loginForm">
        <div class="q-gutter-y-sm q-mx-md">
          <div class="row">
            <div class="col">
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                v-model="loginData.loginName"
                dense
                debounce="1000"
                placeholder="用户名|手机号|邮箱"
                square
                :rules="[
                      (val) => (val && val.length > 0) || '请输入登录名'
                    ]"
              >
                <template v-slot:prepend>
                  <q-icon name="person" />
                </template>
              </q-input>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <q-input
                outlined
                clearable
                clear-icon="cancel"
                :type="isPwd ? 'password' : 'text'"
                v-model="loginData.password"
                dense
                debounce="1000"
                placeholder="密码"
                square
                :rules="[(val) => (val && val.length > 0) || '请输入密码']"
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
          </div>
          <div class="row">
            <div class="col text-left">
              <q-checkbox v-model="loginData.rememberMe" label="自动登录" />
            </div>
            <div class="col text-right">
              <q-btn color="primary" to="/user/register" flat label="绑定新用户" />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <q-btn
                dense
                unelevated
                label="绑 定"
                size="17px"
                color="primary q-mt-sm"
                class="full-width no-border-radius"
                type="submit"
                :loading="loginLoading"
              >
                <template v-slot:loading>
                  <q-spinner-ios class="on-left" />
                  绑定...
                </template>
              </q-btn>
            </div>
          </div>
        </div>
      </q-form>
    </div>
  </div>
</template>

<script>
import { loginPhone, loginUsername, toOauthLogin } from '@/api/user'
import commonUtil from '@/utils/commonUtil'

export default {
  name: 'OauthBind',
  data() {
    return {
      iconActive: {
        weibo: 'grey'
      },
      loginData: {
        loginName: null,
        password: null,
        rememberMe: true
      },
      isPwd: true,
      loginLoading: false
    }
  },
  methods: {
    onSubmit() {
      this.login()
    },
    login() {
      this.loginLoading = true
      setTimeout(() => {
        this.loginUsername()
      }, 1000)
    },
    loginPhone() {
      loginPhone(this.loginData).then(response => {
        this.success()
      }).catch(err => {
        console.log(err)
        this.loginLoading = false
      })
    },
    loginUsername() {
      loginUsername(this.loginData).then(response => {
        this.success()
      }).catch(err => {
        console.log(err)
        this.loginLoading = false
      })
    },
    success() {
      commonUtil.notifySuccess('登录成功')
      this.loginLoading = false
      this.$router.push({
        path: '/'
      })
    },
    onReset() {
      commonUtil.resetObj(this.loginData)
    },
    mouseOver(iconKey, event) {
      this.activeForLoginType(iconKey, 'text-primary')
    },
    mouseLeave(iconKey, event) {
      this.activeForLoginType(iconKey, 'text-grey')
    },
    activeForLoginType(iconKey, targetColor) {
      this.iconObject[iconKey].class.color = targetColor
    },
    thirdLogin(iconKey, event) {
      window.open(toOauthLogin(iconKey, '/'), '_self')
    }
  },
  computed: {
    loginIcon: function() {
      return this.iconObject
    }
  }
}
</script>

<style scoped>
/*@import '~assets/icons/iconfont.sass';*/
@import 'http://at.alicdn.com/t/font_2136554_eo99fwwjrkv.css';

.q-tab-panel {
}

.flip-list-move {
  transition: transform 1s;
}
</style>
