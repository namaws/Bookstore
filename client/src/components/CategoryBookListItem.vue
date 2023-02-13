<template>
  <li class="book-box">
    <div class="book-image">
      <img
        :src="require('@/assets/images/books/' + bookImageFileName(book))"
        :alt="book.title"
      />
      <button class="read-now-button">Read Now</button>
    </div>

    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">
      <Price :amount="book.price" />
    </div>
    <button class="button" @click="addToCart(book)">Add to Cart</button>
  </li>
</template>

<script>
import Price from "@/components/Price";
export default {
  name: "categoryBookListItem",
  components: { Price },
  props: {
    book: {
      type: Object,
      required: true,
    },
  },
  methods: {
    addToCart(book) {
      //asynchronos call
      this.$store.dispatch("addToCart", book);
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
.book-box {
  display: flex;
  flex-direction: column;
  background-color: var(--card-background-color);
  padding: 1em;
  gap: 0.25em;
  width: 250px;
}

.book-title {
  font-weight: bold;
  color: #2c3144;
}

.book-author {
  color: #2c3144;
  font-size: medium;
  align-self: flex-end;
}
.book-price {
  color: #2c3144;
  font-size: medium;
  align-self: flex-end;
}

.book-image {
  position: relative;
  width: 210px;
  height: auto;
  display: flex;
  justify-content: center;
}

.book-image img {
  opacity: 1;
  display: block;
  width: 200px;
  height: auto;
  transition: 0.5s ease;
  backface-visibility: hidden;
}

main {
  /* border: 2px solid rgb(11, 230, 11); */
  min-height: 84%;
  top: 10%;
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  /* position:relative; */
}
.read-now-button {
  trasition: 0.5s ease;
  opacity: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  test-align: center;
  cursor: pointer;
}

.book-image:hover img {
  opacity: 0.3;
}

.book-image:hover .read-now-button {
  opacity: 1;
}
</style>
