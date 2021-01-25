import { buildFullUrl, post, postForImage, showNotifyFalse, getQueryString } from 'utils/request'
import commonUtil from '@/utils/commonUtil'
import { removeToken, setLoginData } from '@/utils/localStorage'

// -----------------登录模块:开始----------------------------
// 登录 --- 绑定用户
export function bindOauthUser(data) {
  return post('/auth/bind_oauth_user/anon', data)
}

export function loginUsername(data) {
  return post('/auth/login_username/anon', data)
}

// 用户 --- 修改用户基础信息
export function updateUserBaseData(data) {
  return post('/user/update_by_id', data)
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
  if (path === '/') {
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
  return postForFalse('/auth/check_register_username/anon', buildRegisterRequest(param))
}

// 校验邮箱 -- 忘記密碼
export const checkForgetPasswordEmail = (param) => {
  return postForFalse('/auth/check_forget_password_email/anon', param)
}
// 校验邮箱
export const checkRegisterEmail = (param) => {
  return postForFalse('/auth/check_register_email/anon', buildRegisterRequest(param))
}

// 注册用户-校验手机号
export const checkRegisterPhone = (param) => {
  return postForFalse('/auth/check_register_phone/anon', buildRegisterRequest(param))
}

// 获取注册图片验证码
export function getCaptchaVerifyCode(param) {
  const data = {
    width: 100,
    height: 30,
    type: param.captchaType
  }
  return postForImage('/auth/get_captcha_verify_code/anon', data)
}

// 校验注册图片验证码
export function checkCaptchaVerifyCode(param) {
  return postForFalse('/auth/check_captcha_verify_code/anon', buildCheckCaptchaRequest(param))
}

// 发送用户注册验证码
export function sendMailCode(param) {
  const data = {
    request: {
      to: param.email,
      code: param.emailCode
    },
    captchaCheckRequest: {
      clientCode: param.captchaVerifyCode,
      type: param.captchaType
    }
  }
  return post('/auth/send_mail_code/anon', data)
}

// 校验注册验证码
export function checkMailCode(param) {
  const data = {
    request: buildCheckMailRequest(param)
  }
  return post('/auth/check_mail_code/anon', data, showNotifyFalse())
}

// 修改密码
export function findBackPassword(param) {
  const data = {
    captchaCheckRequest: buildCheckCaptchaRequest(param),
    checkMailRequest: buildCheckMailRequest(param),
    findBackPasswordRequest: param
  }
  return post('/auth/find_back_password/anon', data)
}
// 注册方法
export function register(param) {
  let bindOauthUser = false
  if (getQueryString('bindOauthUser') === '1') {
    bindOauthUser = true
  }
  const data = {
    captchaCheckRequest: buildCheckCaptchaRequest(param),
    checkMailRequest: buildCheckMailRequest(param),
    registerRequest: buildRegisterRequest(param),
    bindOauthUser: bindOauthUser
  }
  return post('/auth/register/anon', data)
}

export function currentUser() {
  return post('/auth/current_user').then(response => {
    setLoginData(response)
    return response
  })
}

function buildRegisterRequest(registerData) {
  return registerData
}

function buildCheckCaptchaRequest(data) {
  return {
    clientCode: data.captchaVerifyCode,
    type: data.captchaType
  }
}

function buildCheckMailRequest(data) {
  return {
    to: data.email,
    code: data.emailCode,
    clientMailCode: data.emailValidateCode
  }
}

function postForFalse(url, data) {
  return post(url, data, showNotifyFalse())
}

// -----------------注册功能:结束----------------------------
