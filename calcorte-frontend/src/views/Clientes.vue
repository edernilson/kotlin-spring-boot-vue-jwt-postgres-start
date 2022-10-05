<template>
  <div class="container-fluid">
    <div class="d-flex flex-row justify-content-between border-bottom mb-3">
      <h2 class="">Clientes</h2>
      <button type="button"
              class="btn btn-primary btn-sm m-2"
              data-bs-toggle="modal"
              data-bs-target="#editModal"
              @click="addClick()">
        Adicionar Cliente
      </button>
    </div>

    <div v-if="listaClientes.length === 0" class="text-center mt-5">
      Não há clientes cadastrados
    </div>

    <table v-else class="table table-striped table-hover table-sm">
      <thead>
      <th>Nome</th>
      <th>Telefone</th>
      <th>Email</th>
      <th>Nome Contato</th>
      <th>Telefone Contato</th>
      <th>Ações</th>
      </thead>
      <tbody class="table-group-divider">
      <tr v-for="cliente in listaClientes">
        <td>{{ cliente.name }}</td>
        <td>{{ cliente.phone }}</td>
        <td>{{ cliente.email }}</td>
        <td>{{ cliente.contactName }}</td>
        <td>{{ cliente.contactPhone }}</td>
        <td>
          <button
            type="button"
            class="btn btn-light btn-sm mr-1"
            data-bs-toggle="modal"
            data-bs-target="#editModal"
            @click="editClick(cliente)">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                 class="bi bi-pencil-square" viewBox="0 0 16 16">
              <path
                d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
              <path fill-rule="evenodd"
                    d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
            </svg>
          </button>
          <button
            type="button" @click="deleteClick(cliente)"
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
         aria-labelledby="editModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg modal0-dialog-centered" @shown="$refs.code.focus()">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLabel">{{ modalTitle }}</h5>
            <button ref="closeButton" type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Fechar"></button>
          </div>
          <div class="modal-body">
            <div class="input-group mb-3">
              <span class="input-group-text">Nome</span>
              <input type="text" class="form-control" v-model="form.name" autofocus>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Telefone</span>
              <input type="tel" class="form-control" v-model="form.phone">
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Email</span>
              <input type="email" class="form-control" v-model="form.email">
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Nome de Contato</span>
              <input type="text" class="form-control" v-model="form.contactName">
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text">Telefone de Contato</span>
              <input type="tel" class="form-control" v-model="form.contactPhone">
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
  name: "Clientes",
  data() {
    return {
      modalTitle: "",
      form: {
        id: 0,
        name: "",
        phone: "",
        email: "",
        contactName: "",
        contactPhone: "",
      }
    }
  },
  computed: {
    ...mapGetters({listaClientes: "clientes"}),
  },
  methods: {
    ...mapActions(["getClientes", "createCliente", "updateCliente", "deleteCliente"]),
    async loadClientes() {
      this.showError = false
      try {
        await this.getClientes();
      } catch (error) {
        this.showError = true
      }
    },
    addClick() {
      this.modalTitle = "Adicionar Cliente";
      this.form.id = 0;
      this.form.name = "";
      this.form.phone = "";
      this.form.email = "";
      this.form.contactName = "";
      this.form.contactPhone = "";
    },
    editClick(cliente) {
      this.modalTitle = "Adicionar Cliente";
      this.form.id = cliente.id;
      this.form.name = cliente.name;
      this.form.phone = cliente.phone;
      this.form.email = cliente.email;
      this.form.contactName = cliente.contactName;
      this.form.contactPhone = cliente.contactPhone;
    },
    async createClick() {
      this.showError = false
      try {
        await this.createCliente({
          name: this.form.name,
          phone: this.form.phone,
          email: this.form.email,
          contactName: this.form.contactName,
          contactPhone: this.form.contactPhone,
        });
        this.$refs.closeButton.click();
      } catch (error) {
        this.showError = true
      }
    },
    async updateClick() {
      this.showError = false
      try {
        await this.updateCliente({
          id: this.form.id,
          name: this.form.name,
          phone: this.form.phone,
          email: this.form.email,
          contactName: this.form.contactName,
          contactPhone: this.form.contactPhone,
        });
        this.$refs.closeButton.click();
      } catch (error) {
        this.showError = true
      }
    },
    async deleteClick(cliente) {
      this.showError = false
      if (!confirm(`Confirma exclusão do cliente ${cliente.name}?`)) {
        return;
      }
      try {
        await this.deleteCliente(cliente.id);
      } catch (error) {
        console.log(error)
        this.showError = true
      }
    }
  },
  mounted: function () {
    this.loadClientes()
  },
}
</script>

<style scoped>

</style>
