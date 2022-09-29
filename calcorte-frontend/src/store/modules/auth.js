import axios from "axios"

const state = {
  user: null,
  token: null,
  currentPage: "",
}

const getters = {
  isAuthenticated: (state) => !!state.user,
  StateUser: (state) => state.user,
  currentPage: (state) => state.currentPage,
}

const actions = {
  async LogIn({commit}, userForm) {
    const response = await axios.post("login", userForm)
    const { user, token } = response.data
    await commit("setUser", JSON.parse(user))
    await commit("setToken", token)
    localStorage.setItem('authtoken', token)
    return user
  },
  async LogOut({ commit }) {
    localStorage.removeItem('authtoken')
    commit("logout")
  },
  SetCurrentPage({commit}, currentPage) {
    commit("setCurrentPage", currentPage)
  }
}

const mutations = {
  setUser(state, user) {
    state.user = user
  },
  setToken(state, token) {
    state.token = token
  },
  logout(state, user) {
    state.user = null
    state.token = null
  },
  setCurrentPage(state, currentePage) {
    state.currentPage = currentePage
  },
}

export default {
  state,
  getters,
  actions,
  mutations,
}
