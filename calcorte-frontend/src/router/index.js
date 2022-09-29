import Vue from "vue"
import VueRouter from "vue-router"
import store from "../store"
import Home from "../views/Home.vue"
import Login from "../views/Login.vue"
import Projetos from "../views/Projetos"
import Produtos from "../views/Produtos"
import Clientes from "../views/Clientes"

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Home",
    redirect: '/home' ,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
    meta: {requiresAuth: true},
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/projetos",
    name: "Projetos",
    component: Projetos,
    meta: {requiresAuth: true},
  },
  {
    path: "/produtos",
    name: "Produtos",
    component: Produtos,
    meta: {requiresAuth: true},
  },
  {
    path: "/clientes",
    name: "Clientes",
    component: Clientes,
    meta: {requiresAuth: true},
  },
]

const router = new VueRouter({
  linkActiveClass: "active",
  mode: "history",
  base: process.env.BASE_URL,
  routes,
})


router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (store.getters.isAuthenticated) {
      store.dispatch("SetCurrentPage", to.name)
      next()
      return
    }
    next("/login")
  } else {
    next()
  }
})


export default router
