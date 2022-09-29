import axios from "axios"

const state = {
  produtos: [],
}

const getters = {
  produtos: (state) => state.produtos,
}

const actions = {
  async getProdutos({commit}) {
    const response = await axios.get("products")
    await commit("setProdutos", response.data)
  },
  async createProduto({commit}, payload) {
    const response = await axios.post("products", payload)
    await commit("createProduto", response.data)
  },
  async updateProduto({commit}, payload) {
    const response = await axios.put("products/"+payload.id, payload )
    await commit("updateProduto", response.data)
  },
  async deleteProduto({commit}, payload) {
    console.log(payload)
    await axios.delete("products/"+payload )
    await commit("deleteProduto", payload)
  }
}

const mutations = {
  setProdutos(state, produtos) {
    state.produtos = produtos
  },
  createProduto(state, produto) {
    state.produtos.push(produto)
  },
  updateProduto(state, produto) {
    const index = state.produtos.findIndex(prod => prod.id === produto.id)
    state.produtos.splice(index, 1, produto)
  },
  deleteProduto(state, id) {
    const index = state.produtos.findIndex(prod => prod.id === id)
    console.log("index", index)
    state.produtos.splice(index, 1)
    console.log("state.produtos", state.produtos)
  },
}

export default {
  state,
  getters,
  actions,
  mutations,
}
