import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from './components/LoginPage.vue'
import HomePage from './components/HomePage.vue'

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
  console.log(from.name)
  if(to.name === 'login') {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/auth/status`, {
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
    const response = await fetch(`${import.meta.env.VITE_API_URL}/auth/status`, {
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