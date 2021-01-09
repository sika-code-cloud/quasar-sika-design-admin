import { LocalStorage } from 'quasar'

export const localStorageKey = {
  loginUser: 'user',
  token: 'token'
}

export function setLoginUser(val) {
  set(localStorageKey.loginUser, val)
}

export function setToken(val) {
  set(localStorageKey.token, val)
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
