import { buildFullUrl, post, postForImage, showNotifyFalse } from 'utils/request'
import { setLoginUser, setToken } from 'utils/localStorage'
import commonUtil from 'src/utils/commonUtil'
// -----------------登录模块:开始----------------------------
// 登录 --- 用户名密码登录
export function loginUsername(data) {
  return post('/auth/login_username/anon', data).then(response => {
    console.log(response)
    storageUserData(response)
  })
}

// 登录 --- 手机号密码登录
export function loginPhone(param) {
  const data = {
    phone: param.phone,
    password: param.phonePassword
  }
  return post('/auth/login_phone/anon', data).then(response => {
    console.log(response)
    storageUserData(response)
  }).catch(err => {
    return Promise.reject(err)
  })
}

// 授权登录
export function oauthLogin(source, path) {
  let clientUrl
  if (path) {
    clientUrl = commonUtil.getUrlRootPath() + path
  } else {
    clientUrl = window.location.href
  }
  console.log(clientUrl)
  return buildFullUrl('/auth/render/' + source + '/anon?clientUrl=' + clientUrl)
}

// -----------------登录功能:结束----------------------------

// -----------------注册功能:开始----------------------------
// 校验用户名
export const checkRegisterUsername = (param) => {
  return post('/auth/check_register_username/anon', buildRegisterRequest(param), showNotifyFalse)
}

// 校验邮箱
export const checkRegisterEmail = (param) => {
  return post('/auth/check_register_email/anon', buildRegisterRequest(param), showNotifyFalse)
}

// 注册用户-校验手机号
export const checkRegisterPhone = (param) => {
  return post('/auth/check_register_phone/anon', buildRegisterRequest(param), showNotifyFalse)
}

// 获取注册图片验证码
export function getRegisterCaptchaVerifyCode() {
  const data = {
    width: 100,
    height: 30,
    type: 20
  }
  return postForImage('/auth/get_captcha_verify_code/anon', data)
}

// 校验注册图片验证码
export function checkRegisterCaptchaVerifyCode(param) {
  return post('/auth/check_captcha_verify_code/anon', buildCheckCaptchaRequest(param), showNotifyFalse)
}

// 发送用户注册验证码
export function sendUserRegisterMailCode(param) {
  const data = {
    request: { to: param.email },
    captchaCheckRequest: {
      clientCode: param.captchaVerifyCode,
      type: 20
    }
  }
  return post('/auth/send_user_register_mail_code/anon', data, showNotifyFalse)
}

// 校验注册验证码
export function checkUserRegisterMailCode(param) {
  const data = {
    request: buildCheckMailRequest(param)
  }
  return post('/auth/check_user_register_mail_code/anon', data, showNotifyFalse)
}

// 注册方法
export function register(param) {
  const data = {
    captchaCheckRequest: buildCheckCaptchaRequest(param),
    checkMailRequest: buildCheckMailRequest(param),
    registerRequest: buildRegisterRequest(param)
  }
  return post('/auth/register/anon', data).then(response => {
    console.log(response)
    storageUserData(response)
  }).catch(err => {
    return Promise.reject(err)
  })
}

function storageUserData(data) {
  setLoginUser(data.user)
  setToken(data.token)
}

function buildRegisterRequest(registerData) {
  return registerData
}

function buildCheckCaptchaRequest(registerData) {
  return {
    clientCode: registerData.captchaVerifyCode,
    type: 20
  }
}

function buildCheckMailRequest(registerData) {
  return {
    to: registerData.email,
    clientMailCode: registerData.emailValidateCode
  }
}

// -----------------注册功能:结束----------------------------
