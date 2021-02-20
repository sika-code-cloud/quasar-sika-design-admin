import { date } from 'quasar'

const queryCondition = {
  username: null,
  desc: null,
  callCount: null,
  state: null,
  callNextTime: null
}
const tableListDatas = {
  columns: [{
    check: true,
    name: 'id',
    required: true,
    label: '编号',
    align: 'left',
    field: 'id',
    sortable: true,
    format: (val) => `${val}`
  }, {
    check: true,
    name: 'username',
    required: true,
    label: '用户名称',
    align: 'left',
    field: 'username',
    format: (val) => `${val}`
  }, {
    check: true,
    name: 'nickname',
    align: 'left',
    label: '用户昵称',
    field: 'nickname'
  }, {
    check: true,
    name: 'phone',
    align: 'left',
    label: '手机号',
    field: 'phone',
    sortable: true,
    sort: (a, b) => a - b
  }, {
    check: true,
    name: 'email',
    label: '邮箱',
    align: 'left',
    field: 'email'
  }, {
    check: true,
    name: 'avatar',
    align: 'center',
    label: '头像',
    field: 'avatar'
  }, {
    check: true,
    name: 'remark',
    align: 'left',
    label: '备注',
    field: 'remark'
  }, {
    check: true,
    name: 'availableBool',
    align: 'center',
    label: '状态',
    field: 'availableBool'
  }, {
    check: true,
    name: 'updateDate',
    align: 'left',
    label: '更新时间',
    field: 'updateDate',
    format: (val) => date.formatDate(val, 'YYYY-MM-DD HH:mm:ss'),
    sortable: true
  }, {
    check: true,
    name: 'createDate',
    align: 'left',
    label: '创建时间',
    field: 'createDate',
    format: (val) => date.formatDate(val, 'YYYY-MM-DD HH:mm:ss'),
    sortable: true
  }, {
    check: true,
    name: 'operate',
    align: 'center',
    label: '操作',
    field: 'operate'
  }],
  datas: []
}
export default {
  queryCondition,
  tableListDatas
}
