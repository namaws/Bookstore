<template>
  <header>
    <section class="logo">
      <router-link to="/">
        <img class="logo1" src="@/assets/images/site/book.png" alt="BookBugs" />
        <img class="logo2" src="@/assets/images/site/bug.png" alt="BookBugs" />
      </router-link>
      <router-link to="/" tag="h1" class="text-logo"> BookBugs </router-link>
    </section>
    <section class="search-section">
      <form action="category.html">
        <input type="text" class="search-bar" placeholder="search..." /><br />
        <img src="@/assets/images/site/search.png" alt="search" />
      </form>
    </section>
    <section class="other-header-section">
      <router-link to="/cart" class="cart">
        <img src="@/assets/images/site/shopping-cart.png" alt="cart" />
        <h6>{{ $store.state.cart.numberOfItems }}</h6>
      </router-link>
      <router-link to="/" class="log-in">
        <img
          src="@/assets/images/site/round-account-button-with-user-inside.png"
          alt="log-in"
        />
      </router-link>
      <header-dropdown-menu></header-dropdown-menu>
    </section>
  </header>
</template>

<script>
import HeaderDropdownMenu from "@/components/HeaderDropdown";
export default {
  name: "AppHeader",
  components: { HeaderDropdownMenu },
  component: {
    HeaderDropdownMenu,
  },
};
</script>

<style scoped>
header {
  background: var(--secondary-background-color);
  /* position:absolute; */
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  flex-wrap: wrap;
  /* border: 2px solid whitesmoke; */
  height: 8%;
}
header .logo {
  /* position:absolute; */
  /* border: 2px solid red; */
  display: flex;
  padding-left: 22px;
  width: 20%;
  height: 100%;
  align-items: flex-end;
  flex-direction: row;
  flex-wrap: wrap;
}
header .logo .logo1 {

  height: 50px;
  width: auto;
  position: absolute;
  top: 25px;
  left: 30px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}

header .logo .logo2 {
  transform: rotate(50deg);
  /*background-size: 30px;*/
  /*background-repeat: no-repeat;*/
  /*background-position: center;*/
  height: 30px;
  width: auto;
  position: relative;
  left: 25px;
  /*background-image: url(../icon/bug.svg);*/
}

.text-logo {
  font-family: var(--title-font-family);
  color: #fff;
  position: relative;
  left: 40px;
  margin-block-start: 0.65em;
  /*margin-block-end: 0em;*/
}

.text-logo:hover {
  cursor: pointer;
}

header .search-section {
  /* position: absolute; */
  left: 20%;
  width: 55%;
  height: 100%;
  /* border: 2px solid goldenrod; */
  display: flex;
  justify-content: center;
  align-items: flex-end;
}
header .search-section form {
  display: flex;
}
header .search-section img {
  height: 40px;
  width: 40px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}

header. search-section img:hover {
  cursor: pointer;
}
header .other-header-section {
  /* why relative would change place */
  /* position: absolute; */
  height: 100%;
  width: 25%;
  /* border: 2px solid green; */
  display: flex;
  /*justify-content: flex-end;*/
  align-items: flex-end;
  padding-right: 0px;
  gap: 1em;
}

header .cart {
  display: flex;
  text-decoration: none;
}
header .cart img {
  /* position:absolute; */
  /* position: relative; */
  right: 214%;
  height: 40px;
  width: 40px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}

header .other-header-section h6 {
  position: relative;
  color: #3b4b81;
  font-weight: lighter;
  /* position: relative; */
  margin-block-start: 0em;
  margin-block-end: 0em;
  top: 10px;
  right: 20px;
}

header .log-in img {
  height: 40px;
  width: 40px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}

header .menu img {
  height: 40px;
  width: 40px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}
@media (max-width: 600px) {
  header {
    flex-direction: column;
  }
}
</style>
