<template>
  <div></div>
</template>

<script>
import { doOauthLogin } from '@/api/user'
import commonUtil from '@/utils/commonUtil'

export default {
  name: 'OauthLogin',
  created() {
    this.doOauthLogin()
  },
  methods: {
    doOauthLogin() {
      doOauthLogin().then(response => {
        this.success()
      }).catch(err => {
        console.log(err)
      })
    },
    success() {
      commonUtil.notifySuccess('登录成功')
      this.$router.push({
        path: '/'
      })
    }
  }
}
</script>

<style scoped>

</style>
