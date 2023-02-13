import Vue from "vue";
import Vuex from "vuex";
import ApiService from "@/services/ApiService";
import { ShoppingCart } from "@/models/ShoppingCart";

Vue.use(Vuex);
export const CART_STORAGE_KEY = "cart";
export default new Vuex.Store({
  state: {
    categories: [],
    selectedCategoryName: "",
    selectedCategoryBooks: [],
    cart: new ShoppingCart(),
    orderDetails: null
  },

  // a way of changing the states
  mutations: {
    SET_CATEGORIES(state, newCategories) {
      state.categories = newCategories;
    },
    SET_SELECTED_CATEGORY_NAME(state, name) {
      state.selectedCategoryName = name;
    },
    SET_SELECTED_CATEGORY_BOOKS(state, books) {
      state.selectedCategoryBooks = books;
    },
    ADD_TO_CART(state, book) {
      state.cart.addItem(book, 1);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    UPDATE_CART(state, { book, quantity }) {
      state.cart.update(book, quantity);
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    SET_CART(state, shoppingCart) {
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(shoppingCart));
      let newCart = new ShoppingCart();
      shoppingCart.items.forEach((item) => {
        newCart.addItem(item.book, item.quantity);
      });
      state.cart = newCart;
    },
    CLEAR_CART(state) {
      state.cart.clear();
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
    },
    CLEAR_ORDER_DETAILS(state){
      state.orderDetails = null
    },
    SET_ORDER_DETAILS(state,order){
      state.orderDetails = order;
    }

  },

  // call from outside
  actions: {
    fetchCategories(context) {
      ApiService.fetchCategories()
        .then((categories) => {
          console.log("Data: ", categories);
          context.commit("SET_CATEGORIES", categories);
        })
        .catch((reason) => {
          console.log("Error: " + reason);
        });
    },
    setCategoryNames(context, categoryName) {
      context.commit("SET_SELECTED_CATEGORY_NAME", categoryName);
    },
    fetchSelectedCategoryBooks(context) {
      return ApiService.fetchSelectedCategoryBooks(context.state.selectedCategoryName)
        .then((data) => {
          console.log("Data: " + data);
          context.commit("SET_SELECTED_CATEGORY_BOOKS", data);
        })
        .catch((reason) => {
          throw reason
        });
    },
    addToCart(context, book) {
      context.commit("ADD_TO_CART", book);
    },
    updateCart(context, { book, quantity }) {
      context.commit("UPDATE_CART", { book, quantity });
    },
    clearCart(context) {
      context.commit("CLEAR_CART");
    },
    placeOrder({ commit, state }, customerForm) {
      return ApiService.placeOrder({
        cart: state.cart,
        customerForm: customerForm
      }).then((orderDetails) => {
        commit('CLEAR_CART')
        commit('SET_ORDER_DETAILS',orderDetails)
      })
    },
  },
  getters: {
    surcharge(state) {
      return state.surcharge;
    },
    defaultCategoryName() {
      return "Classics";
    },
    categoryName(state, getters) {

      return state.selectedCategoryName || getters.defaultCategoryName;
    },
  },
});
