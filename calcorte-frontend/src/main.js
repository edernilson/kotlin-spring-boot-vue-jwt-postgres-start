import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import axios from "axios"

axios.defaults.withCredentials = true
// axios.defaults.baseURL = "http://localhost:8080/api"
// axios.defaults.baseURL = process.env.VUE_APP_API_URL ? process.env.VUE_APP_API_URL : "http://localhost:8080/api"
axios.defaults.baseURL = "http://localhost:8081/api"

// console.log("process.env.VUE_APP_API_URL", process.env.VUE_APP_API_URL)

axios.interceptors.request.use(
  (config) => {
    let token = localStorage.getItem('authtoken')

      console.log(`Bearer ${token}`)
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }

    return config
  },
  (error) => {
    if (error) {
      const originalRequest = error.config
      if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true
        store.dispatch("LogOut")
        return router.push("/login")
      }
    }
    return Promise.reject(error)
  }
)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  store,
  router,
}).$mount("#app")
