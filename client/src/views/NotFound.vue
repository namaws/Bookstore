<template>
  <div>
    <p>We're sorry that page cannot be found on this site.</p>
    <router-link
        :to="{
        name: 'home',
      }"
    >
      <button class="normal-2x-button">Continue</button>
    </router-link>
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
div {
  text-align: center;
  margin-top: 12rem;
}
p{
  margin-bottom: 5rem;
}

div a button {
  margin-top: 1.9em;
  margin-bottom: 1.9em;
  display: inline-block;
  background: var(--primary-color);
  color: white;
  text-decoration: none;
  padding: 0.5em 1em;
  cursor: pointer;
}

</style>
