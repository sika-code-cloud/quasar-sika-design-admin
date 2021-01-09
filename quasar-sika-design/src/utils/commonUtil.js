import { Notify, Dialog } from 'quasar'

function getRandomData(datas) {
  return datas[Math.floor(Math.random() * datas.length)]
}

function getRandomRangeInt(minValue, maxValue) {
  return getRandomFloorInt(maxValue) + minValue
}

function getRandomFloorInt(maxValue) {
  return Math.floor(Math.random() * maxValue)
}

function getRandomCeilInt(maxValue) {
  return Math.ceil(Math.random() * maxValue)
}

function resetObj(obj) {
  Object.keys(obj).forEach((key) => {
    obj[key] = null
  })
}

/* notify - begin */
function notifySuccess(message) {
  Notify.create({
    color: 'white',
    textColor: 'positive',
    icon: 'check_circle',
    position: 'top',
    message: message
  })
}

function notifyError(message) {
  Notify.create({
    color: 'white',
    textColor: 'negative',
    icon: 'error',
    position: 'top',
    message: message
  })
}

function notifyWaring(message) {
  Notify.create({
    color: 'white',
    textColor: 'warning',
    icon: 'warning',
    position: 'top',
    message: message
  })
}

function notifyAlert(message) {
  Notify.create({
    color: 'white',
    textColor: 'negative',
    icon: 'add_alert',
    position: 'top',
    message: message
  })
}

function notifyInfo(message) {
  Notify.create({
    color: 'white',
    textColor: 'info',
    icon: 'info',
    position: 'top',
    message: message
  })
}

/* notify - end */

/* confirm - begin */
function confirm(message, title) {
  if (!title) {
    title = '系统提示'
  }
  return Dialog.create({
    title: title,
    message: message,
    cancel: '取消',
    ok: '确认',
    persistent: true
  })
}

/* confirm - end */
export default {
  getRandomData,
  getRandomRangeInt,
  getRandomCeilInt,
  getRandomFloorInt,
  notifySuccess,
  notifyInfo,
  notifyWaring,
  notifyAlert,
  notifyError,
  confirm,
  resetObj
}
