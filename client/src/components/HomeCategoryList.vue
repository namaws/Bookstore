<template>
  <section class="main-right">
    <div class="welcome-img">
      <img src="icon/world.svg" alt="travel to th the world" />
    </div>
  </section>
</template>

<script>
export default {
  name: "HomeCategoryList",
};
</script>

<style scoped>
.main-right {
  /* border: 2px solid yellow; */
  height: 100%;
  width: 45%;
  right: 0%;
  /* position: absolute; */
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.main-right .welcome-img {
  /* border: 2px solid whitesmoke; */
  position: relative;
  width: 60%;
  height: 40%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  left: 5%;
}

.main-right .welcome-img img {
  width: 200px;
  height: 200px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}
</style>
