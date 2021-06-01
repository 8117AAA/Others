import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/components/Home'
import Show from '@/components/Show'
import Car from '@/components/Car'
import Me from '@/components/Me'

import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    component: Home,
    children: [{
        path: 'show',
        component: Show
      },
      {
        path: 'car',
        component: Car
      },
      {
        path: 'me',
        component: Me
      }
    ]
  }, {
    path: '/login',
    component: Login
  }]
})
