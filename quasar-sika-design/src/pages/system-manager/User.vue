<template>
  <div class="sc-design">
    <div class="q-mx-md">
      <q-card square flat class="q-py-md">
        <div class="sc-query-form">
          <q-form>
            <div class="row q-gutter-y-sm q-pt-sm">
              <q-item class="col-xl-3 col-sm-6 col-xs-12">
                <q-item-section class="col-2 text-right gt-sm">
                  <q-item-label>用户名</q-item-label>
                </q-item-section>
                <q-item-section class="col">
                  <q-input
                    outlined
                    v-model="queryCondition.username"
                    label="用户名称"
                    dense
                    square
                    clearable
                  >
                  </q-input>
                </q-item-section>
              </q-item>
              <q-item class="col-xl-3 col-sm-6 col-xs-12">
                <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                  <q-item-label>手机号码</q-item-label>
                </q-item-section>
                <q-item-section class="col">
                  <q-input
                    outlined
                    v-model="queryCondition.desc"
                    label="手机号码"
                    dense
                    square
                    clearable
                  >
                  </q-input>
                </q-item-section>
              </q-item>
              <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
                <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                  <q-item-label>邮箱</q-item-label>
                </q-item-section>
                <q-item-section class="col">
                  <q-input
                    outlined
                    v-model="queryCondition.desc"
                    label="邮箱"
                    dense
                    square
                    clearable
                  >
                  </q-input>
                </q-item-section>
              </q-item>
              <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
                <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                  调用次数
                </q-item-section>
                <q-item-section v-show="showQuery" class="col">
                  <q-input
                    outlined
                    v-model="queryCondition.callCount"
                    label="服务调用次数"
                    dense
                    square
                    clearable
                  >
                  </q-input>
                </q-item-section>
              </q-item>
              <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
                <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                  <q-item-label>状态</q-item-label>
                </q-item-section>
                <q-item-section v-show="showQuery" class="col">
                  <q-select
                    behavior="menu"
                    outlined
                    options-dense
                    v-model="queryCondition.state"
                    :options="tableListData.stateValue"
                    label="状态"
                    dense
                    square
                    clearable
                  >
                  </q-select>
                </q-item-section>
              </q-item>
              <q-item v-show="showQuery" class="col-xl-3 col-sm-6 col-xs-12">
                <q-item-section v-show="$q.screen.gt.sm" class="col-2 text-right">
                  <q-item-label>时间</q-item-label>
                </q-item-section>
                <q-item-section v-show="showQuery" class="col">
                  <q-input
                    outlined
                    v-model="queryCondition.callNextTime"
                    label="上次调度时间"
                    dense
                    square
                  >
                    <template v-slot:prepend>
                      <q-icon name="event" class="cursor-pointer">
                        <q-menu
                          square
                          :offset="[12, 10]"
                          transition-show="jump-down"
                          transition-hide="jump-up"
                        >
                          <q-date v-model="queryDate" mask="YYYY-MM-DD HH:mm" today-btn>
                            <div class="row items-center justify-end">
                              <q-btn
                                v-close-popup
                                label="Close"
                                color="primary"
                                flat
                              />
                            </div>
                          </q-date>
                        </q-menu>
                      </q-icon>
                    </template>
                    <template v-slot:append>
                      <q-icon
                        v-if="queryDate !== ''"
                        name="cancel"
                        @click="queryDate = ''"
                        class="cursor-pointer"
                      />
                      <q-icon name="access_time" class="cursor-pointer">
                        <q-menu
                          square
                          :offset="[12, 10]"
                          transition-show="jump-down"
                          transition-hide="jump-up"
                        >
                          <q-time
                            v-model="queryDate"
                            mask="YYYY-MM-DD HH:mm"
                            format24h
                          >
                            <div class="row items-center justify-end">
                              <q-btn
                                v-close-popup
                                label="Close"
                                color="primary"
                                flat
                              />
                            </div>
                          </q-time>
                        </q-menu>
                      </q-icon>
                    </template>
                  </q-input>
                </q-item-section>
              </q-item>
              <q-item class="col-xs-12 q-pr-sm">
                <q-item-label class="col-12 text-right">
                  <q-btn
                    outline
                    unelevated
                    label="重置"
                    class="q-mr-sm"
                    size="12px"
                    style="padding: 4px 10px"
                    dense
                    color="secondary"
                    @click="resetQuery"
                  />
                  <q-btn
                    unelevated
                    label="查询"
                    size="12px"
                    style="padding: 4px 10px"
                    dense
                    color="primary"
                    class="q-mr-sm"
                    :loading="queryLoad"
                    @click="doQuery"
                  >
                    <template v-slot:loading>
                      <q-spinner-ios class="on-center" />
                    </template>
                  </q-btn>
                  <q-btn-dropdown
                    v-model="showQuery"
                    persistent
                    size="12px"
                    style="padding: 4px 10px"
                    dense
                    flat
                    color="primary"
                    :label="tableLabel"
                    @before-show="show"
                    @before-hide="hide"
                  >
                  </q-btn-dropdown>
                </q-item-label>
              </q-item>
            </div>
          </q-form>
        </div>
        <div class="sc-table">
          <q-card  square flat>
            <div class="col">
              <q-table
                class="my-sticky-header-table"
                square
                flat
                title-class="text-body1"
                color="primary"
                :data="filterListData"
                :columns="tableListData.tableListDatas.columns"
                :visible-columns="visibleColumns"
                row-key="id"
                selection="multiple"
                :selected.sync="selected"
                :pagination.sync="pagination"
                hide-selected-banner
                virtual-scroll
                :loading="loading"
                @request="onRequest"
                hide-pagination
              >
                <template v-slot:top="props">
                  <div class="col-12">
                    <div class="row">
                      <div class="col-sm-8 col-xs-12 q-gutter-sm">
                        <q-btn
                          label="新增"
                          color="info"
                          size="12px"
                          style="padding: 2px 6px;"
                          dense
                          icon="add"
                          unelevated
                          @click="openDialog('singleAddFlag')"
                        />
                        <q-btn
                          label="修改"
                          color="primary"
                          size="12px"
                          style="padding: 2px 6px;"
                          dense
                          icon="edit"
                          unelevated
                          @click="openDialog('batchUpdateFlag')"
                        />
                        <q-btn
                          label="删除"
                          color="warning"
                          size="12px"
                          style="padding: 2px 6px;"
                          dense
                          icon="delete"
                          unelevated
                          @click="openDialog('batchDeleteFlag')"
                        />
                        <q-btn
                          label="导入"
                          color="positive"
                          size="12px"
                          style="padding: 2px 6px;"
                          dense
                          icon="south"
                          unelevated
                          @click="openDialog('importFlag')"
                        />
                        <q-btn
                          label="导出"
                          color="secondary"
                          size="12px"
                          style="padding: 2px 6px;"
                          dense
                          icon="north"
                          unelevated
                          @click="exportTable"
                        />
                      </div>
                      <div class="col-sm-4 col-xs-12 text-right">
                        <q-btn rounded flat dense size="md" icon="refresh" @click="onRequest">
                          <q-tooltip>刷新</q-tooltip>
                        </q-btn>
                        <q-btn rounded flat dense size="md" icon="unfold_less">
                          <q-tooltip>密度</q-tooltip>
                        </q-btn>
                        <q-btn rounded flat dense size="md" icon="settings">
                          <q-menu :offset="[0, 12]">
                            <q-list dense>
                              <q-item
                                clickable
                                :active="column.check"
                                @click="select(column)"
                                :key="index"
                                v-for="(column, index) in showColumns"
                              >
                                <q-item-section>{{ column.label }}</q-item-section>
                              </q-item>
                            </q-list>
                          </q-menu>
                        </q-btn>
                        <q-btn
                          rounded
                          flat
                          round
                          dense
                          :icon="props.inFullscreen ? 'fullscreen_exit' : 'fullscreen'"
                          @click="props.toggleFullscreen"
                        >
                          <q-tooltip>全屏</q-tooltip>
                        </q-btn>
                      </div>
                    </div>
                  </div>
                </template>
                <template v-slot:header-cell-operate="props">
                  <q-th :props="props">
                    <q-icon name="settings" size="1.2rem" class="q-mr-sm" />
                    <span>{{ props.col.label }}</span>
                  </q-th>
                </template>
                <template v-slot:body-cell-avatar="props">
                  <q-td :props="props">
                    <q-avatar>
                      <img :src="props.row.avatar" :alt="props.row.avatar"
                           v-if="props.row.avatar && props.row.avatar !== ''">
                      <img src="imgs/head.png" alt="默认头像" v-else>
                    </q-avatar>
                  </q-td>
                </template>
                <template v-slot:body-cell-availableBool="props">
                  <q-td :props="props">
                    <q-toggle
                      v-model="props.row.availableBool"
                      checked-icon="check"
                      color="primary"
                      unchecked-icon="clear"
                      @input="toggle(props.row)"
                    />
                  </q-td>
                </template>
                <template v-slot:body-cell-operate="props">
                  <q-td :props="props" style="width: 160px">
                    <div class="col-12 q-gutter-sm" style="width: 160px">
                      <q-btn label="编辑" unelevated color="primary" icon="edit" dense size="12px"
                             style="padding: 2px 6px" @click="openDialog('singleUpdate')" />
                      <q-btn label="删除" unelevated color="warning" icon="delete" dense size="12px"
                             style="padding: 2px 6px"
                             @click="openDialog('singleDelete', props.row)" />
                    </div>
                  </q-td>
                </template>
                <template v-slot:bottom="scope">
                  <div class="full-width text-right ">
                    <div class="inline-block">
                    <span class="inline-block text-body2">
                      每页数量：
                    </span>
                      <span class="inline-block"><q-select v-model="pagination.rowsPerPage" filled dense
                                                           :options="[5,10,20,30, 50]" /></span>
                    </div>
                    <div class="inline-block q-mx-sm items-center">
                      <div class="inline-block text-body2">
                        总数量：
                      </div>
                      <div class="inline-block">
                        <q-badge color="primary" align="middle" outline>{{ pagination.rowsNumber }}</q-badge>
                      </div>
                    </div>
                    <q-pagination
                      v-model="pagination.page"
                      color="primary"
                      :max="scope.pagesNumber"
                      size="13px"
                      :max-pages="4"
                      :boundary-numbers="false"
                      :boundary-links="true"
                      class="float-right"
                      @input="onRequest"
                    />
                  </div>
                </template>
              </q-table>

              <q-inner-loading :showing="queryLoad">
                <q-spinner-ios size="40px" color="primary" />
              </q-inner-loading>
            </div>
          </q-card>
        </div>
        <div class="sc-dialog-container">
          <q-dialog class="sc-single-add" v-model="dialogData.config.singleAddFlag" persistent>
            <q-card square style="width: 600px">
              <q-toolbar>
                <q-icon name="post_add" size="md"></q-icon>
                <q-toolbar-title class="text-body1">新建规则</q-toolbar-title>
                <q-btn flat round dense icon="close" v-close-popup />
              </q-toolbar>
              <div class="q-px-md">
                <form
                  ref="addDataForm"
                  @submit.prevent.stop="onSubmit"
                  @reset.prevent.stop="onReset"
                >
                  <div class="q-gutter-sm q-ma-sm">
                    <q-item-label>
                      <span class="q-mr-xs text-red">*</span>规则名称
                    </q-item-label>
                    <q-input
                      ref="username"
                      placeholder="请输入"
                      outlined
                      v-model="username"
                      dense
                      square
                      :rules="[
                    (val) => (val && val.length > 0) || '请输入规则名称'
                  ]"
                      clearable
                    >
                    </q-input>
                  </div>
                  <div class="q-gutter-sm q-ma-sm">
                    <q-item-label>描述</q-item-label>
                    <q-input
                      type="textarea"
                      outlined
                      placeholder="请输入"
                      v-model="username"
                      square
                    >
                    </q-input>
                  </div>
                  <div class="q-gutter-sm q-ma-sm q-mb-lg">
                    <q-btn
                      outline
                      unelevated
                      label="重置"
                      type="reset"
                      class="q-mr-sm no-border-radius"
                      color="secondary"
                    />
                    <q-btn
                      unelevated
                      type="submit"
                      label="提交"
                      color="primary"
                      class="no-border-radius"
                    />
                  </div>
                </form>
              </div>
            </q-card>
          </q-dialog>
          <q-dialog class="sc-single-update" v-model="dialogData.config.singleUpdateFlag">
            <q-card square style="width: 600px">
              <q-toolbar>
                <q-icon name="post_add" size="md"></q-icon>
                <q-toolbar-title class="text-body1">新建规则</q-toolbar-title>
                <q-btn flat round dense icon="close" v-close-popup />
              </q-toolbar>
              <div class="q-px-md">
                <form
                  ref="addDataForm"
                  @submit.prevent.stop="onSubmit"
                  @reset.prevent.stop="onReset"
                >
                  <div class="q-gutter-sm q-ma-sm">
                    <q-item-label>
                      <span class="q-mr-xs text-red">*</span>规则名称
                    </q-item-label>
                    <q-input
                      ref="username"
                      placeholder="请输入"
                      outlined
                      v-model="username"
                      dense
                      square
                      :rules="[
                    (val) => (val && val.length > 0) || '请输入规则名称'
                  ]"
                      clearable
                    >
                    </q-input>
                  </div>
                  <div class="q-gutter-sm q-ma-sm">
                    <q-item-label>描述</q-item-label>
                    <q-input
                      type="textarea"
                      outlined
                      placeholder="请输入"
                      v-model="username"
                      square
                    >
                    </q-input>
                  </div>
                  <div class="q-gutter-sm q-ma-sm q-mb-lg">
                    <q-btn
                      outline
                      unelevated
                      label="重置"
                      type="reset"
                      class="q-mr-sm no-border-radius"
                      color="secondary"
                    />
                    <q-btn
                      unelevated
                      type="submit"
                      label="提交"
                      color="primary"
                      class="no-border-radius"
                    />
                  </div>
                </form>
              </div>
            </q-card>
          </q-dialog>
          <q-dialog class="sc-batch-update" v-model="dialogData.config.batchUpdateFlag"></q-dialog>
          <q-dialog class="sc-select-bottom" v-model="dialogData.config.selectBottomFlag" full-width seamless
                    position="bottom">
            <q-card class="q-mx-sm">
              <q-card-section class="row">
                <div class="col-sm-6 col-xs-12 q-mb-sm q-gutter-sm">
                  <div class="inline-block">
                    已选择<span class="text-weight-bold text-primary q-mx-xs">
                    {{ dialogData.data.selectBottom.selectedNum }}</span>项
                  </div>
                  <div class="inline-block">
                    启用<span class="text-weight-bold text-info q-mx-xs">
                    {{ dialogData.data.selectBottom.availableNum }}</span>项,
                  </div>
                  <div class="inline-block">
                    禁用<span class="text-weight-bold text-warning q-mx-xs">
                    {{ dialogData.data.selectBottom.unAvailableNum }}</span>项
                  </div>
                </div>
                <div class="text-right col-sm-6 col-xs-12 q-gutter-sm">
                  <q-btn
                    unelevated
                    color="negative"
                    label="批量删除"
                    @click="deleteDatas"
                  />
                  <q-btn unelevated color="warning" label="批量禁用" />
                  <q-btn unelevated color="primary" label="批量启用" />
                </div>
              </q-card-section>
            </q-card>
          </q-dialog>
        </div>
      </q-card>
    </div>
  </div>
</template>

<script>
import { date } from 'quasar'
import USER_DATA from '@/mock/data/system-manager/userData'
import commonUtil from 'src/utils/commonUtil'
import { pageUser, updateUser } from 'src/api/user'

export default {
  name: 'ScQueryTable',
  data() {
    return {
      tableListData: USER_DATA,
      filterListData: [],
      queryCondition: USER_DATA.queryCondition,
      dialogData: USER_DATA.dialogData,
      queryLoad: false,
      username: null,
      addData: false,
      queryDate: date.formatDate(Date.now(), 'YYYY-MM-DD HH:mm'),
      showQuery: true,
      seamless: false,
      loading: false,
      tableLabel: '展开',
      selected: [],
      pagination: {
        // sortBy: 'calories',
        descending: false,
        page: 1,
        rowsPerPage: 10,
        rowsNumber: 10
      },
      visibleColumns: [
        'username',
        'nickname',
        'phone',
        'email',
        'avatar',
        'remark',
        'availableBool',
        'updateDate',
        'createDate',
        'operate'
      ]
    }
  },
  methods: {
    exportTable() {
      commonUtil.exportTable(this.tableListData.tableListDatas.columns, this.filterListData)
    },
    onRequest(page) {
      console.log(page)
      if (page && page.pagination) {
        this.pagination = page.pagination
      }
      console.log(this.pagination)
      this.queryLoad = true
      const pageNum = this.pagination.page
      const rowsPerPage = this.pagination.rowsPerPage
      const sortColumn = this.pagination.sortBy
      let sortType = null
      if (this.pagination.descending === true) {
        sortType = 'DESC'
      } else if (this.pagination.descending === false) {
        sortType = 'ASC'
      }
      console.log(rowsPerPage)
      pageUser({
        sortColumn: sortColumn,
        sortType: sortType,
        pageSize: rowsPerPage,
        pageNum: pageNum
      }).then(response => {
        this.queryLoad = false
        this.pagination.page = response.pageNum
        this.pagination.rowsNumber = response.total
        // 需要先赋值，否则更新数据不生效
        response.list.forEach(function(o, i) {
          o.availableBool = o.available === 1
        })
        this.filterListData.splice(0, this.filterListData.length, ...response.list)
      }).catch(e => {
        this.queryLoad = false
      })
    },
    deleteData(data) {
      commonUtil.confirm('确认删除').onOk(() => {
        updateUser({
          id: data.id,
          isDeleted: 1
        }).then(response => {
          commonUtil.notifySuccess('删除成功')
          this.onRequest()
        })
      })
    },
    toggle(data, evt) {
      let availableBoolDesc = '禁用'
      console.log(data)
      if (data.availableBool) {
        availableBoolDesc = '启用'
      }
      commonUtil.confirm('确认' + availableBoolDesc).onCancel(() => {
        data.availableBool = !data.availableBool
      }).onOk(() => {
        let available = 0
        if (data.availableBool) {
          available = 1
        }
        updateUser({
          id: data.id,
          available: available
        }).then(response => {
          commonUtil.notifySuccess(availableBoolDesc + '成功')
        })
      })
    },
    show(evt) {
      this.showQuery = true
      this.tableLabel = '收起'
    },
    hide(evt) {
      this.showQuery = false
      this.tableLabel = '展开'
    },
    deleteDatas() {
      console.log(JSON.stringify(this.selected))
      commonUtil.showLoading('正在删除...')
      setTimeout(() => {
        for (let i = 0; i < this.selected.length; ++i) {
          console.log(JSON.stringify(this.selected[i]))
          for (let j = 0; j < this.tableListData.tableListDatas.datas.length; ++j) {
            if (this.selected[i].id === this.tableListData.tableListDatas.datas[j].id) {
              this.tableListData.tableListDatas.datas.splice(j, 1)
              break
            }
          }
        }
        commonUtil.hideLoading()
        commonUtil.notifySuccess('成功删除【' + this.selected.length + '】条数据，即将刷新')
        this.selected = []
      }, 1000)
    },
    select(columnFromClient) {
      this.visibleColumns = []
      const columns = this.tableListData.tableListDatas.columns
      for (let i = 0; i < columns.length; ++i) {
        if (columns[i].name === columnFromClient.name) {
          columns[i].check = !columns[i].check
        }
        if (columns[i].check) {
          this.visibleColumns[i] = columns[i].name
        }
      }
    },
    onSubmit() {
      if (!this.$refs.username.validate()) {
        return
      }
      commonUtil.showLoading('添加规则...')
      setTimeout(() => {
        commonUtil.hideLoading()
        this.addData = false
        this.$refs.addDataForm.reset()
        commonUtil.notifySuccess('添加成功')
      }, 1000)
    },

    onReset() {
      this.username = null
    },
    resetQuery() {
      this.queryCondition = {}
    },
    doQuery() {
      this.queryLoad = false
      this.filterListData = []
      const datas = this.tableListData.tableListDatas.datas
      for (let i = 0; i < datas.length; ++i) {
        const data = datas[i]
        if (this.isMatchData(data)) {
          this.filterListData.push(data)
        }
      }
    },
    isMatchData(data) {
      const listQueryData = this.queryCondition
      let usernameFlag = false
      if (!listQueryData.username || data.username.search(listQueryData.username) !== -1) {
        usernameFlag = true
      }
      let descFlag = false
      if (!listQueryData.desc || data.desc.search(listQueryData.desc) !== -1) {
        descFlag = true
      }
      let stateFlag = false
      if (!listQueryData.state || data.state === listQueryData.state) {
        stateFlag = true
      }
      return usernameFlag && descFlag && stateFlag
    },
    openDialog(key, data) {
      this.dialogData.config[key] = true
    },
    addRow() {
      this.addData = true
    },
    computeAvailable(data) {
      const available = {
        trueNum: 0,
        falseNum: 0
      }
      if (!data) {
        return available
      }
      console.log(data)
      for (let i = 0; i < data.length; ++i) {
        if (data[i].available === 1) {
          available.trueNum++
        } else {
          available.falseNum++
        }
      }
      return available
    }
  },
  computed: {
    pagesNumber() {
      return Math.ceil(this.data.length / this.pagination.rowsPerPage)
    },
    showColumns() {
      const showColumns = []
      for (let i = 0; i < this.tableListData.tableListDatas.columns.length; ++i) {
        const columnData = this.tableListData.tableListDatas.columns[i]
        if (!columnData.required) {
          showColumns.push(columnData)
        }
      }
      return showColumns
    }
  },
  mounted() {
    this.showQuery = this.$q.screen.gt.xs
    this.tableLabel = this.$q.screen.gt.xs ? '收起' : '展开'
    this.onRequest()
  },
  watch: {
    selected(newSelected, oldSelected) {
      this.dialogData.config.selectBottomFlag = newSelected.length > 0
      const availableData = this.computeAvailable(newSelected)
      this.dialogData.data.selectBottom.selectedNum = newSelected.length
      this.dialogData.data.selectBottom.availableNum = availableData.trueNum
      this.dialogData.data.selectBottom.unAvailableNum = availableData.falseNum
    },
    tableListData: {
      handler(newValue, oldValue) {
        // this.doQuery()
      },
      immediate: true,
      deep: true
    }
  }
}
</script>

<style lang="sass">
</style>
