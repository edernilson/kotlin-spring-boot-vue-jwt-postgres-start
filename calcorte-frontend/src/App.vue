<template>
  <div id="app">
    <div v-if="this.$route.name==='Login'">
      <router-view></router-view>
    </div>
    <div v-else class="grid-container">

      <Header></Header>
      <SideBar></SideBar>
      <main class="main-content">
        <router-view></router-view>
      </main>
      <Footer></Footer>
    </div>
  </div>
</template>

<script>
import Header from "./components/Header";
import Footer from "./components/Footer";
import SideBar from "./components/SideBar";
export default {
  name: 'app',
  components: {SideBar, Footer, Header},
}
</script>

<style>
html,
body,
#app,
#app > div{
  height: 100%;
  width: 100%;
}

body {
  background-color: #f5f5f5;
}

Header { grid-area: header; }
SideBar { grid-area: menu; }
.main-content { grid-area: main; }
Footer { grid-area: footer; }

.grid-container {
  margin: 0 auto;
  display: grid;
  grid-template-columns: 250px auto auto;
  grid-template-rows: 55px auto 50px;
  grid-template-areas:
    'header header header'
    'menu main main'
    'footer footer footer';
  gap: 10px;
  padding: 10px;
}

.grid-container {
  height: 100%;
}
</style>
