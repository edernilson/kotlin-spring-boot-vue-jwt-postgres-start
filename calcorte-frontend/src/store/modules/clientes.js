import axios from "axios"

const state = {
  clientes: [],
}

const getters = {
  clientes: (state) => state.clientes,
}

const actions = {
  async getClientes({commit}) {
    const response = await axios.get("clients")
    await commit("setClientes", response.data)
  },
  async createCliente({commit}, payload) {
    const response = await axios.post("clients", payload)
    await commit("createCliente", response.data)
  },
  async updateCliente({commit}, payload) {
    const response = await axios.put("clients/"+payload.id, payload )
    await commit("updateCliente", response.data)
  },
  async deleteCliente({commit}, payload) {
    await axios.delete("clients/"+payload )
    await commit("deleteCliente", payload)
  }
}

const mutations = {
  setClientes(state, clientes) {
    state.clientes = clientes
  },
  createCliente(state, cliente) {
    state.clientes.push(cliente)
  },
  updateCliente(state, cliente) {
    const index = state.clientes.findIndex(prod => prod.id === cliente.id)
    state.clientes.splice(index, 1, cliente)
  },
  deleteCliente(state, id) {
    const index = state.clientes.findIndex(prod => prod.id === id)
    console.log("index", index)
    state.clientes.splice(index, 1)
    console.log("state.clientes", state.clientes)
  },
}

export default {
  state,
  getters,
  actions,
  mutations,
}
