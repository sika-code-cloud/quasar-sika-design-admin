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
              <div class="lt-sm col-xs-12 q-mb-md">
                <span class="text-center block">
                  <q-img
                    src="~assets/head.png"
                    width="180px"
                    :ratio="10 / 10"
                  />
                </span>
                <span class="text-center block">
                  <q-btn
                    unelevated
                    color="primary"
                    label="更换头像"
                    icon="unarchive"
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
              <div class="gt-xs col-md-8 col-sm-7">
                <span class="text-center block">
                  <q-img
                    src="~assets/head.png"
                    width="180px"
                    :ratio="10 / 10"
                  />
                </span>
                <span class="text-center block">
                  <input type="file" style="display: none" id="headFile">
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
                    <q-btn flat unelevated color="primary" label="修改" />
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
                <q-item>
                  <q-item-section avatar>
                    <q-icon
                      size="xl"
                      color="orange"
                      style="cursor: pointer"
                      class="iconfont iconhuaban88 q-ml-sm"
                    />
                  </q-item-section>
                  <q-item-section>
                    <q-item-label>绑定Github</q-item-label>
                    <q-item-label class="text-grey-6"
                    >{{ userBindData.bindGithubNo }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" @click="thirdLogin('github')" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section avatar>
                    <q-icon
                      size="xl"
                      color="primary"
                      style="cursor: pointer"
                      class="iconfont icongitee q-ml-sm"
                    />
                  </q-item-section>
                  <q-item-section>
                    <q-item-label>绑定Gitee</q-item-label>
                    <q-item-label class="text-grey-6">
                      {{ userBindData.bindGiteeNo }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" @click="thirdLogin('gitee')" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
                <q-item>
                  <q-item-section avatar>
                    <q-icon
                      size="xl"
                      color="info"
                      style="cursor: pointer"
                      class="iconfont iconbaidu q-ml-sm"
                    />
                  </q-item-section>
                  <q-item-section>
                    <q-item-label>绑定百度</q-item-label>
                    <q-item-label class="text-grey-6"
                    >{{ userBindData.bindBaiduNo }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section avatar>
                    <q-btn flat unelevated color="primary" label="绑定" @click="thirdLogin('baidu')" />
                  </q-item-section>
                </q-item>
                <q-separator inset="" spaced="10px" />
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
  </div>
</template>

<script>
import ACCOUNT_SETTINGS_DATA from '@/mock/data/account/settingsData'
// import { getLoginData } from '@/utils/localStorage'
import { toOauthLogin, updateUser, currentUser } from '@/api/user'
import { listForCity, listForCounty, listForProvince } from '@/api/chinaCity'
import { listThirdOauthUser } from '@/api/thirdOauthUser'
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
      safeData,
      loginUser: {},
      provinces: [],
      cities: [],
      counties: [],
      provincesCache: [],
      citiesCache: [],
      countiesCache: [],
      basicLoading: false
    }
  },
  methods: {
    popFileUpload() {
      document.getElementById('headFile').click()
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
    },
    buildThirdOauthUserData() {
      const data = {
        userId: this.loginUser.id
      }
      listThirdOauthUser(data).then(response => {
        for (let i = 0; i < response.length; ++i) {
          const res = response[i]
          const source = _.lowerCase(res.source)
          if (source === 'github') {
            this.userBindData.bindGithubNo = res.username
          } else if (source === 'gitee') {
            this.userBindData.bindGiteeNo = res.username
          } else if (source === 'baidu') {
            this.userBindData.bindBaiduNo = res.username
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
      updateUser(userData).then(data => {
        commonUtil.notifySuccess('更新成功')
        this.basicLoading = false
      })
    }
  },
  created() {
    currentUser().then(data => {
      this.loginUser = data.user
      this.buildUserBasicData()
      this.buildSafeData()
      this.buildThirdOauthUserData()
      this.buildAreaData()
    })
  },
  mounted() {
  }
}
</script>

<style scoped>
@import 'http://at.alicdn.com/t/font_2136554_eo99fwwjrkv.css';
</style>
