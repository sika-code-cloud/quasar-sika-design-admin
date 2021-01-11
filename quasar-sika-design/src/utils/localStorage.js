import { LocalStorage } from 'quasar'

export const localStorageKey = {
  loginUser: 'user',
  token: 'authorization'
}

export function setLoginUser(val) {
  set(localStorageKey.loginUser, val)
}

export function getLoginUser(val) {
  return getItem(localStorageKey.loginUser)
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
  LocalStorage.set(key, val)
}

export function getItem(key) {
  return LocalStorage.getItem(key)
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
