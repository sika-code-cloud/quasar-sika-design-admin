const basicSetting = {
  email: null,
  nickname: null,
  remark: null,
  country: null,
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
  bindGithubNo: null,
  bindGiteeNo: null,
  bindBaiduNo: null
}
const newMsgData = {
  passwordMsg: true,
  systemMsg: false,
  waitTaskMsg: true
}
export default {
  basicSetting,
  safeData,
  accountBindData,
  newMsgData
}
