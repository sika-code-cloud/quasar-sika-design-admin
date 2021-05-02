import axios from 'axios'
import commonUtil from './commonUtil'
import { getToken, setToken, localStorageKey } from './localStorage'

const BASE_URL = process.env.VUE_APP_BASE_API
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: BASE_URL,
  // 超时
  timeout: 30000
})
service.defaults.withCredentials = true

// 请求拦截器
service.interceptors.request.use(function(config) {
  if (!getToken()) {
    config.headers[localStorageKey.token] = getQueryString(localStorageKey.token)
    // const auth = post('/auth/current_user')
    // console.log(auth)
    // return systemError(error)
  } else {
    config.headers[localStorageKey.token] = getToken()
  }
  console.log(config.headers[localStorageKey.token])
  return config
}, function(error) {
  return systemError(error)
})
// 响应拦截器
service.interceptors.response.use(function(response) {
  console.log('interceptors.response-------------------' + JSON.stringify(response.headers))
  if (response.headers[localStorageKey.token]) {
    setToken(response.headers[localStorageKey.token])
  }
  const config = response.config
  // 未设置状态码则默认成功状态
  const result = response.data
  if (result.success === undefined) {
    return response.data
  }
  const data = result.data
  if (result.success) {
    return data
  } else {
    return bizError(result, config)
  }
}, function(error) {
  return systemError(error)
})

export function getQueryString(name) {
  const reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
  const r = window.location.search.substr(1).match(reg)
  if (r != null) {
    return unescape(r[2])
  }
  return null
}

// 系统错误处理
function systemError(error) {
  console.log('err' + error)
  let { message } = error
  if (message === 'Network Error') {
    message = '后端接口连接异常'
  } else if (message.includes('timeout')) {
    message = '系统接口请求超时'
  } else if (message.includes('Request failed with status code')) {
    message = '系统接口' + message.substr(message.length - 3) + '异常'
  }
  commonUtil.notifyError(message)
  return Promise.reject(message)
}

// 业务逻辑错误处理
function bizError(result, config) {
  const code = result.code
  const message = result.message
  if (code === 'AUTH_000001') {
    commonUtil.alert('登录状态已过期，重新登录').onOk(() => {
      location.href = '/user/login'
    }).onOk(() => {
      // console.log('>>>> second OK catcher')
    }).onCancel(() => {
      // console.log('>>>> Cancel')
    }).onDismiss(() => {
      // console.log('I am triggered on both OK and Cancel')
    })
    return Promise.reject(message)
  } else {
    if (config.showNotify) {
      commonUtil.notifyError(message)
    }
    return Promise.reject(message)
  }
}

// 展示错误响应false
export function showNotifyFalse() {
  return { showNotify: false }
}

export function showNotifyTrue() {
  return { showNotify: true }
}

// post请求
export function post(url, param, config) {
  const cfg = Object.assign(showNotifyTrue(), config)
  console.log(cfg + '----------')
  return service({
    url: url,
    method: 'post',
    ...cfg,
    data: param
  })
}

// post-upload请求
export function postUpload(url, param, config) {
  // 这里要把content-type设置为multipard/form-data，同时还要设置boundary
  const cfg = Object.assign(showNotifyTrue(), config)
  return service({
    url: url,
    method: 'post',
    ...cfg,
    data: param,
    headers: {
      'Content-Type': 'multipart/form-data; boundary = ' + new Date().getTime()
    }
  })
}

// post请求图片流
export function postForImage(url, param, config) {
  const cfg = Object.assign(showNotifyTrue, config)
  return service({
    url: url,
    method: 'post',
    ...cfg,
    responseType: 'arraybuffer',
    data: param
  }).then(response => {
    return 'data:image/png;base64,' + btoa(
      new Uint8Array(response)
        .reduce((data, byte) => data + String.fromCharCode(byte), '')
    )
  })
}

// get请求
export function get(url, config) {
  const cfg = Object.assign(showNotifyTrue, config)
  return service({
    url: url,
    method: 'post',
    ...cfg
  })
}

export function buildFullUrl(url) {
  return BASE_URL + url
}

export default service
