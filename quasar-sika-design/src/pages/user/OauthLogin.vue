<template>
  <div>
    <q-card class="no-border-radius shadow-0">
      <q-img src="imgs/waiting.png" style="margin-top: -50px">
      </q-img>
      <div class="bg-primary row items-center" style="height: 50px">
        <div class="col">
          <div style="width: 150px" class="text-white oauth-login text-body1">
            授权登陆中。。。
          </div>
        </div>
      </div>
    </q-card>
  </div>
</template>

<script>
import { doOauthLogin } from '@/api/user'
import commonUtil from '@/utils/commonUtil'
import anime from 'animejs'

export default {
  name: 'OauthLogin',
  mounted() {
    this.textAnimal()
    this.doOauthLogin()
  },
  methods: {
    textAnimal() {
      anime({
        targets: '.oauth-login',
        width: '150%', // -> from '28px' to '100%',
        easing: 'easeInOutQuad',
        direction: 'alternate',
        duration: 1300,
        loop: true
      })
    },
    doOauthLogin() {
      doOauthLogin().then(response => {
        console.log('-------------' + response)
        this.success()
      })
    },
    success() {
      console.log('-------------------succces')
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
