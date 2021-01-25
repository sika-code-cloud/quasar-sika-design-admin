import { post } from 'utils/request'

// 列表
export function listThirdOauthUser(data) {
  return post('/third_oauth_user/list', data)
}

// 详情
export function findThirdOauthUser(data) {
  return post('/third_oauth_user/find', data)
}

// 分页信息
export function pageThirdOauthUser(data) {
  return post('/third_oauth_user/page', data)
}

// 保存
export function saveThirdOauthUser(data) {
  return post('/third_oauth_user/save', data)
}

// 更新
export function updateThirdOauthUser(data) {
  return post('/third_oauth_user/update_by_id', data)
}
