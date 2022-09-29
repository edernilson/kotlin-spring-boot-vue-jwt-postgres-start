import Vuex from "vuex"
import Vue from "vue"
import createPersistedState from "vuex-persistedstate"
import auth from "./modules/auth"
import produtos from "./modules/produtos"

// Load Vuex
Vue.use(Vuex)

// Create store
export default new Vuex.Store({
  modules: {
    auth,
    produtos,
  },
  plugins: [createPersistedState()],
})
