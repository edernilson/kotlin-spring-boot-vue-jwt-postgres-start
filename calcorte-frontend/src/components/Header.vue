<template>
  <header class="mb-3 border-bottom">
    <div class="container-fluid d-grid gap-3 align-items-center" style="grid-template-columns: 1fr 2fr;">
      <div></div>
      <div class="d-flex align-items-center">
        <div class="w-100 me-3" role="search">
        </div>

        <div class="flex-shrink-0 dropdown">
          <a id="dropdownMenuAvatar" href="#" class="d-block link-dark text-decoration-none dropdown-toggle"
             data-bs-toggle="dropdown" aria-expanded="false">
            <img :src="linkAvatar()" alt="mdo" width="32" height="32" class="rounded-circle">
          </a>
          <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownMenuAvatar">
            <li><a class="dropdown-item" href="#">New project...</a></li>
            <li><a class="dropdown-item" href="#">Settings</a></li>
            <li><a class="dropdown-item" href="#">Profile</a></li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li><a class="dropdown-item" @click="logout">Sair</a></li>
          </ul>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "Header",
  computed: {
    ...mapGetters({User: "StateUser"}),
  },
  methods: {
    linkAvatar() {
      return "https://ui-avatars.com/api/?name=" + (!this.User ? "Indefinido" : this.User.fullName)
    },
    async logout() {
      await this.$store.dispatch("LogOut");
      this.$router.push("/login");
    },
  }
}
</script>

<style scoped>

</style>
