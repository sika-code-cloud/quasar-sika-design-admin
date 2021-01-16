import { buildFullUrl, post, postForImage, showNotifyFalse, getQueryString } from 'utils/request'
import commonUtil from '@/utils/commonUtil'
import { removeToken } from '@/utils/localStorage'
// -----------------登录模块:开始----------------------------
// 登录 --- 绑定用户
export function bindOauthUser(data) {
  return post('/auth/bind_oauth_user/anon', data)
}
export function loginUsername(data) {
  return post('/auth/login_username/anon', data)
}

// 登录 --- 手机号密码登录
export function loginPhone(param) {
  const data = {
    phone: param.phone,
    password: param.phonePassword,
    rememberMe: param.rememberMe
  }
  return post('/auth/login_phone/anon', data)
}

// 去授权登录
export function toOauthLogin(source, path) {
  let clientUrl
  if (path) {
    clientUrl = commonUtil.getUrlRootPath() + path
  } else {
    clientUrl = window.location.href
  }
  clientUrl = commonUtil.getUrlRootPath() + '/user/oauth-login'
  console.log(clientUrl)
  return buildFullUrl('/auth/render/' + source + '/anon?clientUrl=' + clientUrl)
}

// 登录 --- 执行授权登录
export function doOauthLogin(param) {
  const data = {
    oauthToken: getQueryString('oauthToken'),
    source: getQueryString('source')
  }
  return post('/auth/do_oauth_login/anon', data)
}

// 退出
export function logout() {
  return post('/auth/logout').then(response => {
    removeToken()
  })
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
  let bindOauthUser = false
  if (getQueryString('bindOauthUser') === '1') {
    bindOauthUser = true
  }
  console.log('bindOauthUser----' + bindOauthUser)
  const data = {
    captchaCheckRequest: buildCheckCaptchaRequest(param),
    checkMailRequest: buildCheckMailRequest(param),
    registerRequest: buildRegisterRequest(param),
    bindOauthUser: bindOauthUser
  }
  return post('/auth/register/anon', data)
}

export function currentUser() {
  return post('/auth/current_user')
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
