<template>
  <main class="form-signin w-100 h-100 m-auto">
    <form @submit.prevent="submit">
      <div class="text-center">
        <img class="mb-4" src="../assets/logo.png" alt="" width="72" height="57">
        <h1 class="h3 mb-3 fw-normal">Acesso ao Sistema</h1>
      </div>
      <p v-if="showError" id="error" class="text-danger">Email ou senha inválidos</p>
      <div class="form-floating">
        <input v-model="email" type="email" class="form-control" id="username" placeholder="name@calcorte.com">
        <label for="floatingInput">Email</label>
      </div>
      <div class="form-floating">
        <input v-model="password" type="password" class="form-control" id="password" placeholder="Password">
        <label for="floatingPassword">Senha</label>
      </div>

      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Lembrar-me
        </label>
      </div>
      <button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
      <p class="mt-5 mb-3 position-absolute bottom-0 text-muted text-center">Eder Nilson - &copy; 2022 - version 1.0</p>
    </form>
  </main>
</template>
<script>
import {mapActions} from "vuex";

export default {
  name: 'Login',
  data() {
    return {
      email: '',
      password: '',
      showError: false
    }
  },
  methods: {
    ...mapActions(["LogIn"]),
    async submit() {
      const User =
        {
          "email": this.email,
          "password": this.password
        }
      try {
        await this.LogIn(User);
        console.log("After LogIn:");
        this.$router.push("/");
        this.showError = false
      } catch (error) {
        this.showError = true
      }
    }
  }
}
</script>
<style scoped>
main {
  display: flex;
  align-items: center;
  align-content: center;
  padding-top: 40px;
  padding-bottom: 40px;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
