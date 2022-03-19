<template>
  <b-row>
    <b-container>
      <b-col lg="12" class="mb-5 title justify-content-center">
        <h1>The story: Geekseat Witch Saga: Return of The Coder!</h1>
        <b-col class="txt-content">
          <p class="text-uppercase">Somewhere far far away, there is a village which is controlled by a dark and cunning witch.</p>
        </b-col>
      </b-col>
      <b-col lg="12" class="justify-content-center">
        <img src="../assets/img-home.svg" style="width: 100%;">
      </b-col>
      <b-col class="mt-5 title">
        <h2>Villages Info</h2>
        <hr>
      </b-col>

      <list-content></list-content>
    </b-container>
  </b-row>
</template>

<script>
  import Content from './Content.vue'
  export default {
    components: {
      'list-content': Content
    }
  }
</script>

<style>

</style>