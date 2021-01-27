import { post } from 'utils/request'

// 城市列表
export function list(data) {
  return post('/china_city/list/anon', data)
}

export function listForParentCode(parentCode) {
  const data = {
    parentCode: parentCode
  }
  return post('/china_city/list/anon', data)
}

// 城市列表
export function listForProvince() {
  return listForParentCode(1)
}

// 城市列表
export function listForCity(provinceCode) {
  return listForParentCode(provinceCode)
}

// 城市列表
export function listForCounty(cityCode) {
  return listForParentCode(cityCode)
}

// 城市详情
export function find(data) {
  return post('/china_city/find/anon', data)
}

// 城市分页信息
export function page(data) {
  return post('/china_city/page/anon', data)
}

// 保存
export function save(data) {
  return post('/china_city/save', data)
}

// 更新
export function updateById(data) {
  return post('/china_city/update_by_id', data)
}
