import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from '@/pages/LoginPage.vue'
import HomePage from '@/pages/HomePage.vue'
import { API_URL } from './util'

const routes = [
  { path: '/', name: "login", component: LoginPage},
  { path: '/home', name: "home", component: HomePage}
]

export const router = createRouter({
    history: createWebHistory(),
    routes
  }
)

router.beforeResolve(async (to, from) => {
  if(to.name === 'login') {
    const response = await fetch(`${API_URL}/api/auth/status`, {
      method: "GET",
      credentials: 'include'
    })

    if(response.ok) {
      const json = await response.json()
      if(json === true) {
        return { name: 'home' }
      }
    } else console.log(response.statusText)
  }
  else {
    const response = await fetch(`${API_URL}/api/auth/status`, {
      method: "GET",
      credentials: 'include'
    })

    if(response.ok) {
      const json = await response.json()

      if(json === false) {
        return { name: 'login' }
      }
    } else console.log(response.statusText)
  }
})