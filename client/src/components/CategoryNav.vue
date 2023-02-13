<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <template v-for="category in $store.state.categories">
        <li
          v-if="category.name === $route.params.name"
          :key="category.categoryId"
          class="button selected-category-button"
        >
          {{ category.name }}
        </li>

        <li
          v-else
          :key="category.categoryId"
          class="button unselected-category-button"
        >
          <router-link :to="'/category/' + category.name">
            {{ category.name }}
          </router-link>
        </li>
      </template>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "CategoryNav",
};
</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  text-align: center;
  background-color: var(--neutral-color);
}

.button.selected-category-button {
  background-color: var(--primary-color);
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: var(--neutral-color);
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: var(--primary-color-dark);
  color: white;
}

a {
  color: #fff !important;
  text-decoration: none;
}
</style>
