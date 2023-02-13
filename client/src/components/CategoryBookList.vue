<template>
  <main>
    <ul>
      <category-book-list-item
        v-for="book in $store.state.selectedCategoryBooks"
        :key="book.id"
        :book="book"
      >
      </category-book-list-item>
    </ul>
  </main>
</template>

<script>
import CategoryBookListItem from "./CategoryBookListItem";

export default {
  name: "CategoryBookList",
  props: {
    books: {
      type: Array,
      required: true,
    },
  },
  components: {
    CategoryBookListItem,
  },
};
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 1em;
  gap: 1em;
}
</style>
