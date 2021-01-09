import axios from 'axios'
import commonUtil from './commonUtil'
import {
  LoadingBar
} from 'quasar'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  // 超时
  timeout: 30000
})
service.defaults.withCredentials = true

// 请求拦截器
service.interceptors.request.use(function(config) {
  return config
}, function(error) {
  return systemError(error)
})
// 响应拦截器
service.interceptors.response.use(function(response) {
  console.log(JSON.stringify(response.headers))
  // 未设置状态码则默认成功状态
  const result = response.data
  if (result.success === undefined) {
    return response.data
  }
  const data = result.data
  if (result.success) {
    return data
  } else {
    return bizError(result)
  }
}, function(error) {
  return systemError(error)
})

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
function bizError(result) {
  const code = result.code
  const message = result.message
  if (code === 'AUTH_000001') {
    commonUtil.confirm('登录状态已过期，重新登录').onOk(() => {
      location.href = '/user/login'
    }).onOk(() => {
      // console.log('>>>> second OK catcher')
    }).onCancel(() => {
      // console.log('>>>> Cancel')
    }).onDismiss(() => {
      // console.log('I am triggered on both OK and Cancel')
    })
  } else {
    commonUtil.notifyError(message)
    return Promise.reject(message)
  }
}

LoadingBar.setDefaults({
  color: 'light-blue-4',
  size: '4px',
  position: 'top'
})

export default service
