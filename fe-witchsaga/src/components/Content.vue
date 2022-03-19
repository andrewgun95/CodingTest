<template>
  <b-container>
    <b-row>
      <b-col lg="12" class="text-center">
        <div v-for="info in infos" :key="info.id">
          <p class="title">{{ info.message }}</p>
          <p>
            <small
              ><strong>Date : {{ info.date }}</strong></small
            >
          </p>
          <hr />
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      infos: [],
      result: 0,
    };
  },
  mounted: function () {
    this.timer = setInterval(() => {
      this.loadData();
    }, 1000);
  },
  created() { this.loadData() },
  methods: {
    loadData() {
      this.$http
        .get("http://localhost:8081/witch-saga/v1/villages/")
        .then(function (data) {
          return data.json();
        })
        .then(function (data) {
          var infoArray = [];
          for (let key in data.data) {
            infoArray.push(data.data[key]);
          }
          this.infos = infoArray;
        });
    },
  },
};
</script>

<style scoped>
</style>