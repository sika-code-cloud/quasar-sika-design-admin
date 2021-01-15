<template>
  <div class="sc-design">
    <div>
      <q-form @submit="onSubmit" @reset="onReset" ref="loginForm">
        <q-tabs
          v-model="loginType"
          active-color="primary"
          indicator-color="primary"
          align="left"
          :breakpoint="0"
          narrow-indicator
          class="text-black"
        >
          <q-tab :name="loginTypes.usernameLogin" label="用户密码登录" />
          <q-tab :name="loginTypes.phoneLogin" label="手机号登录" />
        </q-tabs>
        <div class="q-gutter-y-sm">
          <q-tab-panels v-model="loginType" class="text-center">
            <q-tab-panel :name="loginTypes.usernameLogin" class="q-col-gutter-y-sm">
              <div class="row">
                <div class="col">
                  <q-input
                    outlined
                    clearable
                    clear-icon="cancel"
                    v-model="loginData.username"
                    dense
                    debounce="1000"
                    placeholder="用户名"
                    square
                    :rules="[
                      (val) => (val && val.length > 0) || '请输入用户名'
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
            </q-tab-panel>

            <q-tab-panel :name="loginTypes.phoneLogin" class="q-col-gutter-y-sm">
              <div class="row">
                <div class="col">
                  <q-input
                    outlined
                    clearable
                    clear-icon="cancel"
                    v-model="loginData.phone"
                    dense
                    debounce="1000"
                    placeholder="手机号"
                    square
                    :rules="[
                      (val) => (val && val.length > 0) || '请输入手机号'
                    ]"
                  >
                    <template v-slot:prepend>
                      <q-icon name="phone_iphone" />
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
                    v-model="loginData.phonePassword"
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
            </q-tab-panel>
          </q-tab-panels>
          <div class="q-mx-md">
            <div class="row">
              <div class="col text-left">
                <q-checkbox v-model="loginData.rememberMe" label="自动登录" />
              </div>
              <div class="col text-right">
                <q-btn color="primary" flat label="忘记密码" />
              </div>
            </div>
            <div class="row">
              <div class="col">
                <q-btn
                  dense
                  unelevated
                  label="登 录"
                  size="17px"
                  color="primary q-mt-sm"
                  class="full-width no-border-radius"
                  type="submit"
                  :loading="loginLoading"
                >
                  <template v-slot:loading>
                    <q-spinner-ios class="on-left" />
                    登录...
                  </template>
                </q-btn>
              </div>
            </div>
            <div class="row q-pt-md">
              <div class="col-auto text-left q-pt-sm">
                <span>其他登录方式</span>
                <q-icon
                  v-for="(val, key) in loginIcon"
                  v-bind:key="key"
                  :size="val.size"
                  :class="[val.class.iconName, val.class.color]"
                  @click="thirdLogin(key)"
                  @mouseover="mouseOver(key, $event)"
                  @mouseleave="mouseLeave(key, $event)"
                  style="cursor: pointer"
                  class="iconfont q-ml-sm"
                />
              </div>
              <div class="col text-right">
                <q-btn
                  to="/user/register"
                  color="primary"
                  flat
                  label="注册用户"
                />
              </div>
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

const iconObject = {
  // weixin: {
  //   class: { iconName: 'iconweixin', color: 'text-grey' },
  //   size: '22px'
  // },
  // zhifubao: {
  //   class: {
  //     iconName: 'iconzhifubao',
  //     color: 'text-grey'
  //   },
  //   size: '20px'
  // },
  // taobao: {
  //   class: { iconName: 'icontaobao', color: 'text-grey' },
  //   size: '22px'
  // },
  // weibo: {
  //   class: { iconName: 'iconweibo', color: 'text-grey' },
  //   size: '20px'
  // },
  github: {
    class: { iconName: 'iconhuaban88', color: 'text-grey' },
    size: '23px',
    type: 'link'
  },
  gitee: {
    class: { iconName: 'icongitee', color: 'text-grey' },
    size: '23px',
    type: 'link'
  },
  baidu: {
    class: { iconName: 'iconbaidu', color: 'text-grey' },
    size: '23px',
    type: 'link'
  }
}
const loginTypes = {
  usernameLogin: 'usernameLogin',
  phoneLogin: 'phoneLogin'
}
export default {
  name: 'Login',
  data() {
    return {
      iconObject,
      iconActive: {
        weibo: 'grey'
      },
      loginTypes,
      loginType: loginTypes.usernameLogin,
      loginData: {
        username: null,
        password: null,
        phone: null,
        rememberMe: true,
        phonePassword: null
      },
      accept: false,
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
        if (this.loginType === this.loginTypes.usernameLogin) {
          this.loginUsername()
        } else {
          this.loginPhone()
        }
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
