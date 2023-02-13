<template>
  <main>
    <section class="main-left">
      <div class="welcome-words">
        <h2>Welcome to the BookBugs bookstore!</h2>
        <h2>
          We have the most popular books in the world and our books contains the
          world.
        </h2>
        <h2>Let us bring you to the world!</h2>
      </div>
      <div class="welcome-button">
        <button v-on:click="fetchDefaultCategory">Start Your Journey!</button>
        <!--        v-on:click="fetchDefaultCategory-->
      </div>
    </section>
    <section class="main-right">
      <div class="welcome-img">
        <img
          src="@/assets/images/site/world.png"
          alt="travel to th the world"
        />
      </div>
    </section>
  </main>
</template>

<script>
// import ApiService from "@/services/ApiService";
export default {
  name: "Home",
  data: function () {
    return {
      books: [],
    };
  },
  methods: {
    fetchDefaultCategory: function () {
      this.$router.push({ path: "/category/classics" });
    },
  },
};
</script>

<style scoped>
.home-page {
  display: flex;
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
main .main-left {
  /* border: 2px solid rgb(11, 230, 11); */
  height: 100%;
  width: 55%;
  /* position: absolute; */
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
}

main .main-left .welcome-words {
  /* border: 2px solid red; */
  position: relative;
  width: 65%;
  height: 30%;
  padding-bottom: 10px;
}
main .main-left .welcome-words h2 {
  color: #fff;
  position: relative;
  font-size: 24px;
  margin-block-start: 0em;
  margin-block-end: 0em;
  text-align: center;
}

main .main-left .welcome-button {
  /* border: 2px solid red; */
  position: relative;
  width: 65%;
  height: 10%;
  display: flex;
  align-items: center;
  justify-content: center;
}

main .main-left .welcome-button button {
  border: none;
  color: white;
  background-color: #9d8565;
  font-size: 20px;
  cursor: pointer;
  text-align: center;
  border-radius: 8px;
  padding: 5px;
  padding-left: 10px;
  padding-right: 10px;
}
main .main-right {
  /* border: 2px solid yellow; */
  height: 100%;
  width: 45%;
  right: 0%;
  /* position: absolute; */
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

main .main-right .welcome-img {
  /* border: 2px solid whitesmoke; */
  position: relative;
  width: 60%;
  height: 40%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  left: 5%;
}

main .main-right .welcome-img img {
  width: 200px;
  height: 200px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}
</style>
