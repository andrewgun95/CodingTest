<template>
  <div id="app">
    <header-vue class="title"></header-vue>
    <b-container fluid>
      <router-view></router-view>
    </b-container>
    <footer-vue></footer-vue>
  </div>
</template>

<script>
  import Header from './components/Header.vue'
  import Footer from './components/Footer.vue'



  export default {
    components: {
      'header-vue': Header,
      'footer-vue': Footer,
    }
  }
</script>

<style>
  /* poppins-300 - latin */
  @font-face {
    font-family: 'Poppins Light';
    src: local('Poppins Light'), url('assets/fonts/Poppins-Light.woff') format('woff'),
  }

  /* poppins-regular - latin */
  @font-face {
    font-family: 'Poppins Regular';
    font-style: normal;
    font-weight: normal;
    src: local('Poppins Light'), url('assets/fonts/Poppins-Light.woff') format('woff');
  }

  @font-face {
    font-family: 'Poppins Light';
    font-style: normal;
    font-weight: normal;
    src: local('Poppins Light'), url('assets/fonts/Poppins-Light.woff') format('woff');
  }

  @font-face {
    font-family: 'PlayfairDisplay Bold';
    font-style: normal;
    font-weight: normal;
    src: local('PlayfairDisplay Bold'), url('assets/fonts/PlayfairDisplay-Bold.woff') format('woff');
  }

  .title {
    font-family: "PlayfairDisplay Bold";
  }

  .txt-content {
    font-family: "Poppins Light";
  }

  .txt-content-regular{
    font-family: "Poppins Regular";
  }

  #app {
    font-family: "Poppins Regular";
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  nav {
    background: #444;
    padding: 14px 0;
    margin-bottom: 40px;
  }

  .router-link-active {
    background: #eee;
    color: #444;
  }

   .txt-uppercase {
    text-transform: capitalize;
  }
</style>