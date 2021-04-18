import { post } from 'utils/request'
// -----------------用户模块:开始----------------------------
// 列表
export function listOauthUser(data) {
  return post('/third_oauth_user/list', data)
}

// 详情
export function findOauthUser(data) {
  return post('/third_oauth_user/find', data)
}

// 分页信息
export function pageOauthUser(data) {
  return post('/third_oauth_user/page', data)
}

// 保存
export function saveOauthUser(data) {
  return post('/third_oauth_user/save', data)
}

// 更新
export function updateOauthUser(data) {
  return post('/third_oauth_user/update_by_id', data)
}
