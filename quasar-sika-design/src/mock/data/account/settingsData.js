const basicSetting = {
  email: null,
  nickname: null,
  remark: null,
  country: null,
  avatar: null,
  provinceData: {
    value: null,
    label: null
  },
  cityData: {
    value: null,
    label: null
  },
  countyData: {
    value: null,
    label: null
  },
  address: null,
  phonePrefix: '+86',
  phone: null
}
const safeData = {
  password: '123456',
  phone: '138****9876',
  passwordQuestion: '我的生日是什么时候',
  slaveEmail: '466***93@qq.com',
  mfaEquipment: null
}
const accountBindData = {
  github: {
    id: null,
    username: null,
    iconClass: 'iconhuaban88',
    iconColor: 'black',
    desc: 'Github',
    code: 'github'
  },
  gitee: {
    id: null,
    username: null,
    iconClass: 'icongitee',
    iconColor: 'red',
    desc: 'Gitee',
    code: 'gitee'
  },
  baidu: {
    id: null,
    username: null,
    iconClass: 'iconbaidu',
    iconColor: 'blue',
    desc: '百度',
    code: 'baidu'
  }
}
const newMsgData = {
  passwordMsg: true,
  systemMsg: false,
  waitTaskMsg: true
}
const userUpdatePasswordData = {
  data: {
    oldPassword: null,
    newPassword: null,
    newConfirmPassword: null
  },
  config: {
    isOldPassword: true,
    isNewPassword: true,
    isNewConfirmPassword: true,
    updatePasswordDialog: false
  }
}
export default {
  basicSetting,
  safeData,
  accountBindData,
  newMsgData,
  userUpdatePasswordData
}
