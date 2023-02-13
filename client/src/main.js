import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "@/assets/css/global.css";
import store from "./store";
import { CART_STORAGE_KEY } from "@/store";
import Vuelidate from 'vuelidate';
import TreeView from 'vue-json-tree-view';

Vue.config.productionTip = false;
Vue.use(Vuelidate);
Vue.use(TreeView);

new Vue({
  router,
  store,
  render: function (h) {
    return h(App);
  },
  created() {
    const cartString = localStorage.getItem(CART_STORAGE_KEY);
    if (cartString) {
      const shoppingCart = JSON.parse(cartString);
      this.$store.commit("SET_CART", shoppingCart);
    }
  },
}).$mount("#app");
