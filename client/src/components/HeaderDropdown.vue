<template>
  <div class="header-dropdown">
    <router-link to="/" class="menu">
      <img src="@/assets/images/site/menu.png" alt="menu" />
    </router-link>
    <ul>
      <router-link  v-for="category in $store.state.categories"
                    :key="category.categoryId"
                    :to="`/category/${category.name}`"
                    tag="li">{{category.name}}</router-link>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeaderDropdownMenu",
};
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.categories-button {
  background-color: var(--primary-color-dark);
  margin-top: 0.25em;
}

.header-dropdown ul {
  background-color: white;
  color: black;
  display: none;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: lightgray;
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 8em;
  z-index: 1;
  cursor: pointer;
}

header .menu img {
  height: 40px;
  width: 40px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}
</style>
