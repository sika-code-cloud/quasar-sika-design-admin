const routes = [
  {
    path: '/home',
    name: '/home',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/dashboard/Analysis')
      }
    ]
  },
  {
    path: '/system-manage/user/base',
    name: '/system-manage/user/base',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/user/BaseUser')
      }
    ]
  },
  {
    path: '/system-manage/user/oauth',
    name: '/system-manage/user/oauth',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/user/OauthUser')
      }
    ]
  },
  {
    path: '/system-manage/role',
    name: '/system-manage/role',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/Role')
      }
    ]
  },
  {
    path: '/system-manage/menu',
    name: '/system-manage/menu',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/Menu')
      }
    ]
  },
  {
    path: '/system-manage/department',
    name: '/system-manage/department',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/Department')
      }
    ]
  },
  {
    path: '/system-manage/post',
    name: '/system-manage/post',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/Post')
      }
    ]
  },
  {
    path: '/system-manage/dict',
    name: '/system-manage/dict',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/Dict')
      }
    ]
  },
  {
    path: '/system-manage/parameter',
    name: '/system-manage/parameter',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/Parameter')
      }
    ]
  },
  {
    path: '/system-manage/notice',
    name: '/system-manage/notice',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/Notice')
      }
    ]
  },
  {
    path: '/system-manage/log/operate',
    name: '/system-manage/log/operate',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/log/OperateLog')
      }
    ]
  },
  {
    path: '/system-manage/log/login',
    name: '/system-manage/log/login',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-manager/log/LoginLog')
      }
    ]
  },
  {
    path: '/system-monitor/online-user',
    name: '/system-monitor/online-user',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-monitor/OnlineUser')
      }
    ]
  },
  {
    path: '/system-monitor/time-task',
    name: '/system-monitor/time-task',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-monitor/TimeTask')
      }
    ]
  },
  {
    path: '/system-monitor/data-monitor',
    name: '/system-monitor/data-monitor',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-monitor/DataMonitor')
      }
    ]
  },
  {
    path: '/system-monitor/service-monitor',
    name: '/system-monitor/service-monitor',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-monitor/ServiceMonitor')
      }
    ]
  },
  {
    path: '/system-monitor/cache-monitor',
    name: '/system-monitor/cache-monitor',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/system-monitor/CacheMonitor')
      }
    ]
  },
  {
    path: '/dashboard/analysis',
    name: '/dashboard/analysis',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/dashboard/Analysis')
      }
    ]
  },
  {
    path: '/dashboard/monitor',
    name: '/dashboard/monitor',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/dashboard/Monitor')
      }
    ]
  },
  {
    path: '/dashboard/workplace',
    name: '/dashboard/workplace',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/dashboard/Workplace')
      }
    ]
  },
  {
    path: '/form/basic-form',
    name: '/form/basic-form',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/form/BasicForm')
      }
    ]
  },
  {
    path: '/form/advanced-form',
    name: '/form/advanced-form',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/form/AdvancedForm')
      }
    ]
  },
  {
    path: '/form/step-form',
    name: '/form/step-form',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/form/StepForm')
      }
    ]
  },
  {
    path: '/profile/basic',
    name: '/profile/basic',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/profile/basic')
      }
    ]
  },
  {
    path: '/profile/advanced',
    name: '/profile/advanced',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/profile/advanced')
      }
    ]
  },
  {
    path: '/account/center',
    name: '/account/center',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/account/Center')
      }
    ]
  },
  {
    path: '/account/settings',
    name: '/account/settings',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/account/Settings')
      }
    ]
  },
  {
    path: '/result/success',
    name: '/result/success',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/result/Success')
      }
    ]
  },
  {
    path: '/result/fail',
    name: '/result/fail',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/result/Fail')
      }
    ]
  },
  {
    path: '/exception/403',
    name: '/exception/403',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages//exception/403')
      }
    ]
  },
  {
    path: '/exception/404',
    name: '/exception/404',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages//exception/404')
      }
    ]
  },
  {
    path: '/exception/500',
    name: '/exception/500',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages//exception/500')
      }
    ]
  },
  {
    path: '/list/search/projects',
    name: '/list/search/projects',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/search/Projects')
      }
    ]
  },
  {
    path: '/list/search/applications',
    name: '/list/search/applications',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/search/Applications')
      }
    ]
  },
  {
    path: '/list/search/articles',
    name: '/list/search/articles',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/search/Articles')
      }
    ]
  },
  {
    path: '/list/user-list',
    name: '/list/user-list',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/UserList')
      }
    ]
  },
  {
    path: '/list/goods-list',
    name: '/list/goods-list',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/GoodsList')
      }
    ]
  },
  {
    path: '/list/card-list',
    name: '/list/card-list',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/CardList')
      }
    ]
  },
  {
    path: '/list/basic-list',
    name: '/list/basic-list',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/BasicList')
      }
    ]
  },
  {
    path: '/list/table-list',
    name: '/list/table-list',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/list/TableList')
      }
    ]
  },
  {
    path: '/user/login',
    name: '/user/login',
    component: () => import('layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/user/Login.vue')
      }
    ]
  },
  {
    path: '/user/oauth-login',
    name: '/user/oauth-login',
    component: () => import('layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/user/OauthLogin.vue')
      }
    ]
  },
  {
    path: '/user/oauth-bind',
    name: '/user/oauth-bind',
    component: () => import('layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/user/OauthBind.vue')
      }
    ]
  },
  {
    path: '/user/forget-password',
    name: '/user/forget-password',
    component: () => import('layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/user/ForgetPassword.vue')
      }
    ]
  },
  {
    path: '/user/register',
    name: '/user/register',
    component: () => import('layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/user/Register.vue')
      }
    ]
  },
  {
    path: '/user/registerResult',
    name: '/user/registerResult',
    props: true,
    component: () => import('layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/user/RegisterResult.vue')
      }
    ]
  },
  {
    path: '/editor/customer',
    name: '/editor/customer',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/editor/Customer')
      }
    ]
  },
  {
    path: '/editor/markdown',
    name: '/editor/markdown',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/editor/Markdown')
      }
    ]
  },
  {
    path: '/',
    name: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/dashboard/Analysis.vue')
      }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
