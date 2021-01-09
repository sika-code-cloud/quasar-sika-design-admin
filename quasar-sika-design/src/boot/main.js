// import '@/mock/index'
import '@/css/main.sass'
import '@/assets/js/sc-common.js'
import Vue from 'vue'
import numeral from 'vue-numeral-filter'
import Vuenime from 'vuenime'
Vue.use(numeral, { locale: 'en-gb' })
Vue.use(Vuenime)
