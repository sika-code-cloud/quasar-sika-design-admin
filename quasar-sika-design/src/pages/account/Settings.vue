<template>
  <div class="q-px-md">
    <q-card flat square>
      <q-card-section class="q-pa-none q-py-md row">
        <div class="col-sm-2 col-xs-12 q-pb-sm">
          <q-tabs
            v-model="settingsTab"
            align="left"
            active-color="primary"
            active-bg-color="blue-1"
            class="text-grey-10"
            vertical
          >
            <q-tab
              name="basicSettings"
              label="基本设置"
              style="justify-content: left"
              content-class="q-pl-md"
            />
            <q-tab
              name="safeSettings"
              label="安全设置"
              style="justify-content: left"
              content-class="q-pl-md"
            />
            <q-tab
              name="accountBind"
              label="账号绑定"
              style="justify-content: left"
              content-class="q-pl-md"
            />
            <q-tab
              name="newMsg"
              label="新消息通知"
              style="justify-content: left"
              content-class="q-pl-md"
            />
          </q-tabs>
        </div>
        <q-separator :vertical="$q.screen.gt.xs" v-show="$q.screen.gt.xs" />
        <div class="col-sm col-xs-12 q-px-md q-pt-none">
          <q-tab-panels
            v-model="settingsTab"
            animated
            transition-prev="fade"
            transition-next="fade"
          >
            <q-tab-panel name="basicSettings" class="row q-pt-sm">
              <div class="text-h5 col-12 q-mb-md">基本设置</div>
              <div class="col-xs-12 q-mb-md" v-if="$q.screen.lt.sm">
                <span class="text-center block q-mb-md">
                 <q-avatar size="140px">
                   <img
                     alt=""
                     :src="userBasicData.avatar"
                     class="cursor-pointer"
                     @click="popFileUpload"
                   />
                 </q-avatar>
                </span>
                <span class="text-center block">
                  <input type="file" style="display: none" class="headFile" accept="image/*" @change="handleFile">
                  <q-btn
                    unelevated
                    color="primary"
                    label="更换头像"
                    icon="unarchive"
                    @click="popFileUpload"
                  />
                </span>
              </div>
              <div
                class="col-md-4 col-sm-5 col-xs-12 q-gutter-y-md q-pt-md q-pl-md q-pb-md"
              >
                <q-input
                  outlined
                  dense
                  square
                  label="邮箱"
                  v-model="userBasicData.email"
                />
                <q-input
                  outlined
                  dense
                  square
                  label="昵称"
                  v-model="userBasicData.nickname"
                />
                <q-input
                  type="textarea"
                  outlined
                  dense
                  square
                  label="个人简介"
                  v-model="userBasicData.remark"
                />
                <q-select
                  outlined
                  dense
                  square
                  behavior="menu"
                  label="所在省份"
                  use-input
                  input-debounce="0"
                  @filter="filterProvinces"
                  v-on:keydown.delete="deleteProvince"
                  options-dense
                  :options="provinces"
                  v-model="userBasicData.provinceData"
                  @input="changeProvince"
                />
                <q-select
                  outlined
                  dense
                  square
                  behavior="menu"
                  label="所在城市"
                  use-input
                  input-debounce="0"
                  @filter="filterCities"
                  v-on:keydown.delete="deleteCity"
                  options-dense
                  :options="cities"
                  v-model="userBasicData.cityData"
                  @input="changeCity"
                />
                <q-select
                  outlined
                  dense
                  square
                  behavior="menu"
                  label="所在县区"
                  use-input
                  input-debounce="0"
                  @filter="filterCounties"
                  v-on:keydown.delete="deleteCounty"
                  options-dense
                  :options="counties"
                  v-model="userBasicData.countyData"
                  v-if="countiesCache && countiesCache.length"
                />
                <q-input
                  outlined
                  dense
                  square
                  label="详细地址"
                  type="textarea"
                  v-model="userBasicData.address"
                  maxlength="255"
                />
                <span class="row q-gutter-x-sm">
                  <q-input
                    class="col"
                    outlined
                    dense
                    square
                    label="联系电话"
                    v-model="userBasicData.phone"
                  />
                </span>
                <q-btn label="更新基本信息" color="primary" unelevated @click="updateUserBaseInfo" :loading="basicLoading">
                  <template v-slot:loading>
                    <q-spinner-ios class="on-left" />
                    更新
                  </template>
                </q-btn>
              </div>
              <div class="col-md-8 col-sm-7" v-if="$q.screen.gt.xs">
                <span class="text-center block q-mb-md">
                 <q-avatar size="140px">
                   <img
                     alt=""
                     :src="userBasicData.avatar"
                     class="cursor-pointer"
                     @click="popFileUpload"
                   />
                 </q-avatar>
                </span>
                <span class="text-center block">
                  <input type="file" style="display: none" class="headFile" accept="image/*" @change="handleFile">
                  <q-btn
                    unelevated
                    color="primary"
                    label="更换头像"
                    icon="unarchive"
                    @click="popFileUpload"
                  />
                </span>
              </div>
            </q-tab-panel>
            <q-tab-panel name="safeSettings" class="q-pt-sm">
              <div class="text-h5 col-12 q-mb-md">安全设置</div>
              <q-list class="text-body2">
                <q-item>
                  <q-item-section>
                    <q-item-label>账户密码</q-item-label>
                    <q-item-label class="text-grey-6"
                    >当前密码强度：强
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="修改" @click="popUpdatePasswordDialog" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>密保手机</q-item-label>
                    <q-item-label class="text-grey-6">
                      已绑定手机：{{ safeData.phone }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="修改" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>密保问题</q-item-label>
                    <q-item-label class="text-grey-6"
                    >{{ accountSettingsData.safeData.passwordQuestion }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="设置" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>备用邮箱</q-item-label>
                    <q-item-label class="text-grey-6">
                      <q-chip
                        outline
                        color="info"
                        square
                        class="bg-green-1"
                        size="sm"
                      >已绑定
                      </q-chip
                      >
                      {{ safeData.email }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="修改" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label><strong>MFA 设备</strong></q-item-label>
                    <q-item-label class="text-grey-6"
                    >未绑定 MFA 设备，绑定后，可以进行二次确认
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
              </q-list>
            </q-tab-panel>
            <q-tab-panel name="accountBind" class="q-pt-sm">
              <div class="text-h5 col-12 q-mb-md">账号绑定</div>
              <q-list class="text-body2">
                <div v-for="(value, key) in userBindData" :key="key">
                  <q-item v-if="value">
                    <q-item-section avatar>
                      <q-icon
                        size="xl"
                        :color="value.iconColor"
                        style="cursor: pointer"
                        class="iconfont q-ml-sm"
                        :class="value.iconClass"
                      />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>
                        <q-chip square dense color="positive" text-color="white" v-if="value.id">
                          已绑定
                        </q-chip>
                        <q-chip square dense color="grey" text-color="white" v-else>
                          {{ value.desc }}
                        </q-chip>
                      </q-item-label>
                      <q-item-label class="text-grey-6 q-pl-xs"
                      >{{ value.username }}
                      </q-item-label>
                    </q-item-section>
                    <q-item-section avatar>
                      <q-btn flat unelevated color="negative" label="解绑" @click="unThirdBind(value)" v-if="value.id" />
                      <q-btn flat unelevated color="primary" label="绑定" @click="thirdLogin(key)" v-else />
                    </q-item-section>
                  </q-item>
                  <q-separator inset="" spaced="10px" />
                </div>
              </q-list>
            </q-tab-panel>
            <q-tab-panel name="newMsg">
              <div class="text-h5 col-12 q-mb-md">新消息通知</div>
              <q-list class="text-body2">
                <q-item>
                  <q-item-section>
                    <q-item-label>账户密码</q-item-label>
                    <q-item-label class="text-grey-6"
                    >其他用户的消息将以站内信的形式通知
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-toggle
                      v-model="accountSettingsData.newMsgData.passwordMsg"
                      checked-icon="check"
                      color="primary"
                      unchecked-icon="clear"
                    />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>系统消息</q-item-label>
                    <q-item-label class="text-grey-6">
                      系统消息将以站内信的形式通知
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-toggle
                      v-model="accountSettingsData.newMsgData.systemMsg"
                      checked-icon="check"
                      color="primary"
                      unchecked-icon="clear"
                    />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section>
                    <q-item-label>待办任务</q-item-label>
                    <q-item-label class="text-grey-6"
                    >待办任务将以站内信的形式通知
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-toggle
                      v-model="accountSettingsData.newMsgData.waitTaskMsg"
                      checked-icon="check"
                      color="red"
                      unchecked-icon="clear"
                    />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
              </q-list>
            </q-tab-panel>
          </q-tab-panels>
        </div>
      </q-card-section>
    </q-card>
    <q-dialog v-model="userUpdatePasswordData.config.updatePasswordDialog" persistent @before-hide="beforeHidePasswordDialog">
      <q-card class="q-px-md q-pb-md q-pt-sm sc-design" style="width: 500px; max-width: 80vw;">
        <q-card-section>
          <div class="text-h6">修改密码</div>
        </q-card-section>
        <q-form @submit="updatePassword" @reset="onResetUpdatePassword" ref="registerForm">
          <q-item-label class="q-gutter-y-sm q-px-md">

            <q-input
              outlined
              clearable
              clear-icon="cancel"
              :type="userUpdatePasswordData.config.isOldPassword ? 'password' : 'text'"
              v-model="userUpdatePasswordData.data.oldPassword"
              dense
              debounce="1000"
              placeholder="原密码"
              maxlength="32"
              square
              :rules="[
                  (val) => (val && val.length > 0) || '请输入密码'
                ]"
            >
              <template v-slot:prepend>
                <q-icon name="lock" />
              </template>
              <template v-slot:append>
                <q-icon
                  :name="userUpdatePasswordData.config.isOldPassword ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="userUpdatePasswordData.config.isOldPassword = !userUpdatePasswordData.config.isOldPassword"
                />
              </template>
            </q-input>
            <q-input
              outlined
              clearable
              clear-icon="cancel"
              :type="userUpdatePasswordData.config.isNewPassword ? 'password' : 'text'"
              v-model="userUpdatePasswordData.data.newPassword"
              dense
              debounce="1000"
              placeholder="新密码"
              maxlength="32"
              square
              :rules="[
                  (val) => (val && val.length > 0) || '请输入新密码',
                  (val) => (val && val.length >= 6) || '密码长度必须大于等于6位'
                ]"
            >
              <template v-slot:prepend>
                <q-icon name="lock" />
              </template>
              <template v-slot:append>
                <q-icon
                  :name="userUpdatePasswordData.config.isNewPassword ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="userUpdatePasswordData.config.isNewPassword = !userUpdatePasswordData.config.isNewPassword"
                />
              </template>
            </q-input>
            <q-input
              outlined
              clearable
              clear-icon="cancel"
              :type="userUpdatePasswordData.config.isNewConfirmPassword ? 'password' : 'text'"
              v-model="userUpdatePasswordData.data.newConfirmPassword"
              dense
              debounce="1000"
              placeholder="新确认密码"
              maxlength="32"
              square
              :rules="[
                  (val) => (val && val.length > 0) || '请输入确认新密码',
                  (val) => (passwordValida) || '两次密码不一致'
                ]"
            >
              <template v-slot:prepend>
                <q-icon name="lock" />
              </template>
              <template v-slot:append>
                <q-icon
                  :name="userUpdatePasswordData.config.isNewConfirmPassword ? 'visibility_off' : 'visibility'"
                  class="cursor-pointer"
                  @click="userUpdatePasswordData.config.isNewConfirmPassword = !userUpdatePasswordData.config.isNewConfirmPassword"
                />
              </template>
            </q-input>
          </q-item-label>
          <q-card-actions align="right" class="q-mb-sm q-mr-sm">
            <q-btn label="确认" unelevated color="primary" type="submit" style="width: 64px"/>
            <q-btn label="取消" unelevated color="primary" v-close-popup  style="width: 64px" />
          </q-card-actions>
        </q-form>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import ACCOUNT_SETTINGS_DATA from '@/mock/data/account/settingsData'
// import { getLoginData } from '@/utils/localStorage'
import { toOauthLogin, updateUser, currentUser, uploadHeadImg, updateCurrentPassword } from '@/api/user'
import { listForCity, listForCounty, listForProvince } from '@/api/chinaCity'
import { listThirdOauthUser, updateThirdOauthUser } from '@/api/thirdOauthUser'
import commonUtil from '@/utils/commonUtil'

const safeData = {
  phone: null,
  email: null
}

export default {
  name: 'Settings',
  data() {
    return {
      accountSettingsData: ACCOUNT_SETTINGS_DATA,
      settingsTab: 'basicSettings',
      userBasicData: ACCOUNT_SETTINGS_DATA.basicSetting,
      userBindData: ACCOUNT_SETTINGS_DATA.accountBindData,
      userUpdatePasswordData: ACCOUNT_SETTINGS_DATA.userUpdatePasswordData,
      safeData,
      loginUser: {},
      provinces: [],
      cities: [],
      counties: [],
      provincesCache: [],
      citiesCache: [],
      countiesCache: [],
      basicLoading: false,
      headFile: null
    }
  },
  methods: {
    onResetUpdatePassword() {
      this.userUpdatePasswordData.data = commonUtil.resetObj(this.userUpdatePasswordData.data)
    },
    beforeHidePasswordDialog() {
      this.onResetUpdatePassword()
    },
    popUpdatePasswordDialog() {
      this.userUpdatePasswordData.config.updatePasswordDialog = true
    },
    updatePassword() {
      updateCurrentPassword(this.userUpdatePasswordData.data).then(result => {
        console.log(result)
        this.userUpdatePasswordData.config.updatePasswordDialog = false
        commonUtil.notifySuccess('修改成功')
      })
    },
    popFileUpload() {
      document.getElementsByClassName('headFile')[0].click()
    },
    // 将头像显示
    handleFile: function(e) {
      const $target = e.target
      const file = $target.files[0]
      const reader = new FileReader()
      reader.onload = (data) => {
        const res = data.target
        this.userBasicData.avatar = res.result
      }
      reader.readAsDataURL(file)
      this.headFile = file
    },
    // 解除第三方绑定
    unThirdBind(thirdData) {
      const data = {
        id: thirdData.id,
        userId: 0
      }
      updateThirdOauthUser(data).then(response => {
        commonUtil.notifySuccess('解绑成功')
        this.buildThirdOauthUserData()
      })
    },
    thirdLogin(iconKey, event) {
      window.open(toOauthLogin(iconKey, '/account/settings'), '_self')
    },
    buildSafeData() {
      const loginUser = this.loginUser
      safeData.email = loginUser.email
      safeData.phone = loginUser.phone
    },
    buildUserBasicData() {
      const loginUser = this.loginUser
      const userBasicData = this.userBasicData

      userBasicData.email = loginUser.email
      userBasicData.nickname = loginUser.nickname
      userBasicData.remark = loginUser.remark
      userBasicData.phone = loginUser.phone
      userBasicData.provinceData.value = loginUser.provinceCode
      userBasicData.cityData.value = loginUser.cityCode
      userBasicData.countyData.value = loginUser.countyCode
      userBasicData.address = loginUser.address
      userBasicData.avatar = loginUser.avatar
      if (!userBasicData.avatar || userBasicData.avatar === '') {
        userBasicData.avatar = 'imgs/head.png'
      }
      console.log(userBasicData)
    },
    buildThirdOauthUserData() {
      const data = {
        userId: this.loginUser.id
      }
      Object.keys(this.userBindData).forEach((key) => {
        this.userBindData[key].id = null
        this.userBindData[key].username = null
      })
      listThirdOauthUser(data).then(response => {
        for (let i = 0; i < response.length; ++i) {
          const res = response[i]
          const source = _.lowerCase(res.source)
          if (this.userBindData[source] && !this.userBindData[source].id) {
            this.userBindData[source].username = res.username
            this.userBindData[source].id = res.id
          }
        }
      })
    },
    buildAreaData() {
      this.listForProvince()
      this.listForCity(this.loginUser.provinceCode)
      this.listForCounty(this.loginUser.cityCode)
    },
    listForProvince() {
      listForProvince().then(datas => {
        this.initAreaData(this.provinces, datas)
        this.initAreaData(this.provincesCache, datas)
        this.initUserBaseArea(this.userBasicData.provinceData, datas)
      })
    },
    listForCity(provinceCode) {
      listForCity(provinceCode).then(datas => {
        this.initAreaData(this.cities, datas)
        this.initAreaData(this.citiesCache, datas)
        this.initUserBaseArea(this.userBasicData.cityData, datas)
      })
    },
    listForCounty(cityCode) {
      listForCounty(cityCode).then(datas => {
        this.initAreaData(this.counties, datas)
        this.initAreaData(this.countiesCache, datas)
        this.initUserBaseArea(this.userBasicData.countyData, datas)
      })
    },
    initUserBaseArea(areaData, datasServer) {
      for (let i = 0; i < datasServer.length; ++i) {
        if (areaData.value === datasServer[i].code) {
          areaData.label = datasServer[i].cityName
        }
      }
    },
    initAreaData(datasClient, datasServer) {
      datasClient.splice(0, datasClient.length)
      for (let i = 0; i < datasServer.length; ++i) {
        const data = datasServer[i]
        datasClient.push({
          label: data.cityName,
          value: data.code
        })
      }
    },
    deleteProvince() {
      this.userBasicData.provinceData = commonUtil.resetObj(this.userBasicData.provinceData)
      this.countiesCache = commonUtil.resetArray(this.countiesCache)
      this.counties = commonUtil.resetArray(this.counties)
      this.cities = commonUtil.resetArray(this.cities)
      this.citiesCache = commonUtil.resetArray(this.cities)
      this.userBasicData.cityData = commonUtil.resetObj(this.userBasicData.cityData)
      this.userBasicData.countyData = commonUtil.resetObj(this.userBasicData.countyData)
    },
    deleteCity() {
      this.countiesCache = commonUtil.resetArray(this.countiesCache)
      this.counties = commonUtil.resetArray(this.counties)
      this.userBasicData.cityData = commonUtil.resetObj(this.userBasicData.cityData)
      this.userBasicData.countyData = commonUtil.resetObj(this.userBasicData.countyData)
    },
    deleteCounty() {
      this.userBasicData.countyData = commonUtil.resetObj(this.userBasicData.countyData)
    },
    changeProvince() {
      this.listForCity(this.userBasicData.provinceData.value)
      this.countiesCache = commonUtil.resetArray(this.countiesCache)
      this.counties = commonUtil.resetArray(this.counties)
      this.userBasicData.cityData = commonUtil.resetObj(this.userBasicData.cityData)
      this.userBasicData.countyData = commonUtil.resetObj(this.userBasicData.countyData)
    },
    changeCity() {
      this.listForCounty(this.userBasicData.cityData.value)
      this.userBasicData.countyData = commonUtil.resetObj(this.userBasicData.countyData)
    },
    changeCounty() {
    },
    filterProvinces(val, update) {
      console.log(val)
      if (val === '' || val === undefined) {
        update(() => {
          this.provinces = this.provincesCache
        })
        return
      }
      update(() => {
        const needle = val.toLowerCase()
        this.provinces = this.provincesCache.filter(v => v.label.toLowerCase().indexOf(needle) > -1)
      })
    },
    filterCities(val, update) {
      if (val === '' || val === undefined) {
        update(() => {
          this.cities = this.citiesCache
        })
        return
      }
      update(() => {
        const needle = val.toLowerCase()
        this.cities = this.citiesCache.filter(v => v.label.toLowerCase().indexOf(needle) > -1)
      })
    },
    filterCounties(val, update) {
      if (val === '' || val === undefined) {
        update(() => {
          this.counties = this.countiesCache
        })
        return
      }
      update(() => {
        const needle = val.toLowerCase()
        this.counties = this.countiesCache.filter(v => v.label.toLowerCase().indexOf(needle) > -1)
      })
    },
    updateUserBaseInfo() {
      const userData = {
        email: this.userBasicData.email,
        nickname: this.userBasicData.nickname,
        remark: this.userBasicData.remark,
        provinceCode: this.userBasicData.provinceData.value,
        cityCode: this.userBasicData.cityData.value,
        countyCode: this.userBasicData.countyData.value,
        address: this.userBasicData.address,
        phone: this.userBasicData.phone,
        id: this.loginUser.id
      }
      this.basicLoading = true
      if (this.headFile) {
        this.preUploadFileAndUpdate(userData)
      } else {
        updateUser(userData).then(data => {
          commonUtil.notifySuccess('更新成功')
          this.basicLoading = false
        })
      }
    },
    preUploadFileAndUpdate(userData) {
      // 请求接口前需要传的参数
      const formData = new FormData()
      formData.append('file', this.headFile)
      // 先上传头像 --获取上传的url
      uploadHeadImg(formData).then(result => {
        userData.avatar = result
        updateUser(userData).then(data => {
          commonUtil.notifySuccess('更新成功')
          this.basicLoading = false
        })
      })
    }
  },
  created() {
  },
  mounted() {
    currentUser().then(data => {
      this.loginUser = data.user
      this.buildUserBasicData()
      this.buildSafeData()
      this.buildThirdOauthUserData()
      this.buildAreaData()
    })
  },
  computed: {
    passwordValida: function() {
      return this.userUpdatePasswordData.data.newPassword === this.userUpdatePasswordData.data.newConfirmPassword
    }
  }
}
</script>

<style scoped>
@import 'http://at.alicdn.com/t/font_2136554_eo99fwwjrkv.css';
</style>
