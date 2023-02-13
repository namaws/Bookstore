<template>
  <div class="cart-table">
    <ul>
      <li class="cart-heading">
        <div class="cart-heading-book">Book</div>
        <div class="cart-heading-quantity">Quantity</div>
        <div class="cart-heading-subtotal">Amount</div>
      </li>

      <li v-for="item in $store.state.cart.items" :key="item.book.bookId">
        <div class="cart-book-image">
          <img
            :src="
              require('@/assets/images/books/' + bookImageFileName(item.book))
            "
            :alt="item.book.title"
            width="150px"
            height="auto"
          />
        </div>
        <div class="cart-book-title">{{ item.book.title }}</div>
        <div class="cart-book-price">
          <!--          ${{ (item.book.price / 100).toFixed(2) }}-->
          <Price :amount="item.book.price" />
        </div>
        <div class="cart-book-quantity">
          <span class="row-book-quantity">{{ item.quantity }}</span>
          <button
            class="icon-button inc-button"
            @click="updateCart(item.book, item.quantity + 1)"
          >
            <i class="fas fa-plus-circle"></i>
          </button>
          <button
            class="icon-button dec-button"
            @click="updateCart(item.book, item.quantity - 1)"
          >
            <i class="fas fa-minus-circle"></i>
          </button>
        </div>
        <div class="subtotal">
          <Price :amount="item.book.price * item.quantity" />
        </div>
      </li>
      <div class="row-sep"></div>
      <div class="total">total:</div>
      <div class="total">{{ $store.state.cart.numberOfItems }} books</div>
      <div class="total"><Price :amount="$store.state.cart.subtotal" /></div>
    </ul>
  </div>
</template>

<script>
import Price from "@/components/Price";
export default {
  name: "CartTable",
  components: { Price },
  methods: {
    addToCart(book) {
      //asynchronos call
      this.$store.dispatch("addToCart", book);
    },
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", { book, quantity });
    },
    bookImageFileName(book) {
      let name = book.title.toLowerCase();
      name = name.replace(/ /g, "-");
      return `${name}.jpg`;
    },
  },
};
</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  background-color: var(--secondary-background-color);
  margin: 0 auto;
  padding: 1em;
}

ul,
li {
  display: contents;
}

.row-sep {
  grid-column: 1 / -1; /*start from column 1 and column -1 (backwards) */
  background-color: var(--card-background-color);
  height: 2px;
}

.cart-heading {
  background-color: var(--secondary-background-color);
  color: var(--default-text-color);
}

.cart-heading > * {
  background-color: var(--secondary-background-color);
  color: white;
}

.cart-heading-book {
  grid-column: 1/ 3;
  padding: 0 1em;
}

.cart-heading-quantity {
  grid-column: 3/ 5;
  padding: 0 1em;
}

.cart-heading.subtotal {
  grid-column: -2/ -1;
  padding: 0 1em;
}

.cart-book-image {
  padding: 0 1em;
}
.cart-book-title {
  padding: 0 1em;
}
.cart-book-price {
  padding: 0 1em;
}
.cart-book-quantity {
  padding: 0 1em;
}

.icon-button {
  background-color: transparent;
  color: #9d8565;
  font-size: 1rem;
  border: none;
  height: 1em;
  margin-left: 0.5em;
}

.icon-button:hover {
  color: #fff;
  cursor: pointer;
}

.inc-button {
  margin-left: 0.5em;
}

.dec-button {
  margin-left: 0.2em;
}
.total {
  padding: 0 1em;
}
</style>
