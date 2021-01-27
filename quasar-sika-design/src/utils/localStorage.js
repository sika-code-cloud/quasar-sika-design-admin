import { Cookies, LocalStorage, SessionStorage } from 'quasar'

export const localStorageKey = {
  loginData: 'loginData',
  token: 'authorization'
}

export function setLoginData(val) {
  SessionStorage.set(localStorageKey.loginData, val)
}

export function getLoginData() {
  return SessionStorage.getItem(localStorageKey.loginData)
}

export function removeToken() {
  LocalStorage.remove(localStorageKey.token)
}

export function setToken(val) {
  set(localStorageKey.token, val)
}

export function getToken() {
  const token = getQueryString(localStorageKey.token)
  if (token) {
    setToken(token)
  }
  return getItem(localStorageKey.token)
}

function getQueryString(name) {
  const reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
  const r = window.location.search.substr(1).match(reg)
  if (r != null) {
    return unescape(r[2])
  }
  return null
}

export function set(key, val) {
  if (isLocalStorageSupported()) {
    LocalStorage.set(key, val)
  } else {
    Cookies.set(key, val, { expires: 24 * 3600 })
  }
}

export function getItem(key) {
  if (isLocalStorageSupported()) {
    return LocalStorage.getItem(key)
  } else {
    return Cookies.get(key)
  }
}

export function getAll() {
  return LocalStorage.getAll()
}

export function getAllKeys() {
  return LocalStorage.getAllKeys()
}

export function getIndex(index) {
  return LocalStorage.getIndex(index)
}

export function getKey(index) {
  return LocalStorage.getKey(index)
}

export function getLength() {
  return LocalStorage.getLength()
}

export function isEmpty() {
  return LocalStorage.isEmpty()
}

export function remove(key) {
  return LocalStorage.remove(key)
}

// LocalStorage支持检测
function isLocalStorageSupported() {
  let isSupport = true
  try {
    isSupport = window.localStorage
    if (isSupport) {
      const key = 'local_storage_test'
      LocalStorage.set(key, '1')
      const value = LocalStorage.getItem(key)
      if (!value) {
        isSupport = false
      }
      LocalStorage.remove(key)
    } else {
      isSupport = false
    }
  } catch (e) {
    isSupport = false
  }
  return isSupport
}
