<template>
  <div class="cart-page">
    <section
      v-if="$store.state.cart.numberOfItems === 0"
      class="empty-cart-page"
    >
      <span>
         Your cart is empty.
      </span>
      <div class="cart-buttons">
      <router-link :to="'/category/' + $store.getters.categoryName">
        <button class="button">Continue Shopping</button>
      </router-link>
    </div>
    </section>
    <section v-else class="non-empty-cart-page">
      <div class="cart-buttons">
        <router-link :to="'/category/' + $store.getters.categoryName">
          <button class="button">Continue Shopping</button>
        </router-link>
        <router-link to="/checkout/">
        <button class="button checkout">PROCEED TO CHECKOUT</button>
        </router-link>
      </div>
      <cart-table> </cart-table>
      <button class="button link-like-button" @click="clearCart()">
        clear the cart
      </button>
    </section>
  </div>
</template>

<script>
import CartTable from "@/components/CartTable";
export default {
  name: "Cart",
  components: { CartTable },
  methods: {
    clearCart() {
      this.$store.dispatch("clearCart");
    },
  },
};
</script>

<style scoped>
.cart-page {
  padding: 1em;
  display: flex;
  flex-direction: column;
}
.non-empty-cart-page {
  display: flex;
  flex-direction: column;
  align-self: center;
  gap: 1em;
}
.empty-cart-page {
  display: flex;
  flex-direction: column;
  align-self: center;
  gap: 1em;
}

.cart-buttons {
  display: flex;
  justify-content: space-between;
}

.link-like-button {
  background-color: transparent;
  align-self: start;
}
</style>
