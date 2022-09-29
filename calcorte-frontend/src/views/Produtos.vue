<template>
  <div class="container-fluid">
    <h2 class="float-start">Produtos</h2>
    <button type="button"
            class="btn btn-primary btn-sm m-2 float-end"
            data-bs-toggle="modal"
            data-bs-target="#editModal"
            @click="addClick()">
      Adicionar Produto
    </button>

    <table class="table table-striped table-hover table-sm">
      <thead>
      <th>Código</th>
      <th>Descrição</th>
      <th>Tipo</th>
      <th>Ações</th>
      </thead>
      <tbody class="table-group-divider">
      <tr v-for="prod in listaProdutos">
        <td>{{ prod.code }}</td>
        <td>{{ prod.description }}</td>
        <td>{{ prod.materialType }}</td>
        <td>
          <button
            type="button"
            class="btn btn-light btn-sm mr-1"
            data-bs-toggle="modal"
            data-bs-target="#editModal"
            @click="editClick(prod)">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                 class="bi bi-pencil-square" viewBox="0 0 16 16">
              <path
                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
              <path fill-rule="evenodd"
                    d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
            </svg>
          </button>
          <button
            type="button" @click="deleteClick(prod)"
            class="btn btn-light btn-sm mr-1">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill"
                 viewBox="0 0 16 16">
              <path
                d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
            </svg>
          </button>
        </td>
      </tr>
      </tbody>
    </table>

    <div class="modal fade" id="editModal" tabindex="-1"
         aria-labelledby="editModalLabal" aria-hidden="true">
      <div class="modal-dialog modal-lg modal0-dialog-centered" @shown="$refs.code.focus()">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLabel">{{ modalTitle }}</h5>
            <button ref="closeButton" type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Fechar"></button>
          </div>
          <div class="modal-body">
            <div class="input-group mb-3">
              <span class="input-group-text">Código</span>
              <input id="code" type="text" class="form-control" v-model="form.code" autofocus>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Descriçãp</span>
              <input type="text" class="form-control" v-model="form.description">
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Tipo de Material</span>
              <select class="form-select" v-model="form.materialType">
                <option value="PLYWOOD">Compensado</option>
                <option value="FORMICA">Fórmica</option>
                <option value="OTHER" selected>Outro</option>
              </select>
            </div>

            <button type="button" class="btn btn-light" data-bs-dismiss="modal">Cancelar</button>
            <button type="button" @click="createClick()" v-if="form.id==0" class="btn btn-primary float-end">Incluir
            </button>
            <button type="button" @click="updateClick()" v-if="form.id!==0" class="btn btn-primary float-end">Salvar
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  name: "Produtos",
  data() {
    return {
      modalTitle: "",
      form: {
        id: 0,
        code: "",
        description: "",
        materialType: "",
      }
    }
  },
  computed: {
    ...mapGetters({listaProdutos: "produtos"}),
  },
  methods: {
    ...mapActions(["getProdutos", "createProduto", "updateProduto", "deleteProduto"]),
    async loadProdutos() {
      this.showError = false
      try {
        await this.getProdutos();
      } catch (error) {
        this.showError = true
      }
    },
    addClick() {
      this.modalTitle = "Adicionar Produto";
      this.form.id = 0;
      this.form.code = "";
      this.form.description = "";
      this.form.materialType = "";
    },
    editClick(prod) {
      this.modalTitle = "Adicionar Produto";
      this.form.id = prod.id;
      this.form.code = prod.code;
      this.form.description = prod.description;
      this.form.materialType = prod.materialType;
    },
    async createClick() {
      this.showError = false
      try {
        await this.createProduto({
          code: this.form.code,
          description: this.form.description,
          materialType: this.form.materialType,
        });
        this.$refs.closeButton.click();
      } catch (error) {
        this.showError = true
      }
    },
    async updateClick() {
      this.showError = false
      try {
        await this.updateProduto({
          id: this.form.id,
          code: this.form.code,
          description: this.form.description,
          materialType: this.form.materialType,
        });
        this.$refs.closeButton.click();
      } catch (error) {
        this.showError = true
      }
    },
    async deleteClick(produto) {
      this.showError = false
      if (!confirm(`Confirma exclusão do código ${produto.code}?`)) {
        return;
      }
      try {
        await this.deleteProduto(produto.id);
      } catch (error) {
        this.showError = true
      }
    }
  }
  ,
  mounted: function () {
    this.loadProdutos()
  }
  ,
}
</script>

<style scoped>

</style>
