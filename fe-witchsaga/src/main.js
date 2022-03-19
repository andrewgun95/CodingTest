import Vue from 'vue'
import App from './App.vue'
import 'bootstrap'; 
import 'bootstrap/dist/css/bootstrap.min.css';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource'
import Routes from './routes'
import { BContainer } from 'bootstrap-vue'
import { LayoutPlugin } from 'bootstrap-vue'
import { NavPlugin } from 'bootstrap-vue'
import { BFormInput } from 'bootstrap-vue'
import { BFormTextarea } from 'bootstrap-vue'
import { BButton } from 'bootstrap-vue'
import { BAlert } from 'bootstrap-vue'

Vue.component('b-alert', BAlert)
Vue.component('b-button', BButton)
Vue.component('b-form-textarea', BFormTextarea)
Vue.component('b-form-input', BFormInput)
Vue.use(NavPlugin)
Vue.use(LayoutPlugin)
Vue.component('b-container', BContainer)
Vue.use(VueRouter);
Vue.use(VueResource);

const router = new VueRouter({
  routes: Routes,
  mode: 'history'
});

new Vue({
  el:'#app',
  render: h => h(App),
  router: router
})
