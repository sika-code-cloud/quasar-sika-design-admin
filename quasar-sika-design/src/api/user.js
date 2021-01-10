import request, { post } from 'utils/request'
import { setLoginUser, setToken } from 'utils/localStorage'

// 登录方法
export function loginUsername(data) {
  return post('/auth/login_username/anon', data).then(response => {
    console.log(response)
    storageUserData(response)
  })
}

// 登录方法
export function loginPhone(data) {
  return request({
    url: '/auth/login_phone/anon',
    method: 'post',
    data: {
      phone: data.phone,
      password: data.phonePassword
    }
  }).then(response => {
    console.log(response)
    storageUserData(response)
  }).catch(err => {
    return Promise.reject(err)
  })
}

// 校验用户名
export const checkRegisterUsername = (param) => {
  return post('/auth/check_register_username/anon', buildRegisterRequest(param), { showNotify: false })
}
//
// // 校验用户名
// export function checkRegisterUsername(param) {
//   const data = buildRegisterRequest(param)
//   return request({
//     url: '/auth/check_register_username/anon',
//     method: 'post',
//     data: data
//   })
// }

// 校验邮箱
export function checkRegisterEmail(param) {
  const data = buildRegisterRequest(param)
  return request({
    url: '/auth/check_register_email/anon',
    method: 'post',
    data: data
  })
}

// 注册用户-校验手机号
export function checkRegisterPhone(param) {
  const data = buildRegisterRequest(param)
  return request({
    url: '/auth/check_register_phone/anon',
    method: 'post',
    data: data
  })
}

// 获取注册图片验证码
export function getRegisterCaptchaVerifyCode(param) {
  const data = {
    width: 100,
    height: 30,
    type: 20
  }
  return request({
    url: '/auth/get_captcha_verify_code/anon',
    method: 'post',
    responseType: 'arraybuffer',
    data: data
  })
}

// 校验注册图片验证码
export function checkRegisterCaptchaVerifyCode(param) {
  const data = buildCheckCaptchaRequest(param)
  return request({
    url: '/auth/check_captcha_verify_code/anon',
    method: 'post',
    data: data
  })
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
  return request({
    url: '/auth/send_user_register_mail_code/anon',
    method: 'post',
    data: data
  })
}

// 校验注册验证码
export function checkUserRegisterMailCode(param) {
  const data = {
    request: buildCheckMailRequest(param)
  }
  return request({
    url: '/auth/check_user_register_mail_code/anon',
    method: 'post',
    data: data
  })
}

// 注册方法
export function register(param) {
  const data = {
    captchaCheckRequest: buildCheckCaptchaRequest(param),
    checkMailRequest: buildCheckMailRequest(param),
    registerRequest: buildRegisterRequest(param)
  }
  return request({
    url: '/auth/register/anon',
    method: 'post',
    data: data
  }).then(response => {
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
