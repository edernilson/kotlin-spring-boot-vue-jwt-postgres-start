import Vuex from "vuex"
import Vue from "vue"
import createPersistedState from "vuex-persistedstate"
import auth from "./modules/auth"
import produtos from "./modules/produtos"
import clientes from "./modules/clientes"

// Load Vuex
Vue.use(Vuex)

// Create store
export default new Vuex.Store({
  modules: {
    auth,
    produtos,
    clientes
  },
  plugins: [createPersistedState()],
})
