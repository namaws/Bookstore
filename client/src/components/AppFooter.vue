<template>
  <footer>
    <section class="foot-left">
      <h6>&copy;copyright 2021 Mao</h6>
    </section>

    <section class="foot-right">
      <div class="social-media-icon">
        <a href="https://www.facebook.com/">
          <img src="@/assets/images/site/facebook.png" alt="FB" />
        </a>
        <a href="https://twitter.com/">
          <img src="@/assets/images/site/twitter.png" alt="Twitter" />
        </a>
      </div>

      <div class="other-footer-information">
        <a href="#">Contact us</a>
        <p>|</p>
        <a href="#">Direction</a>
      </div>
    </section>
  </footer>
</template>

<script>
export default {
  name: "AppFooter",
};
</script>

<style scoped>
footer {
  background: var(--secondary-background-color);
  display: flex;
  gap: 1em;
}

.links,
.social-media-icons {
  display: flex;
  flex-direction: column;
}

.social-media-icons {
  text-align: center;
  gap: 0.25em;
}

footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  /* position:absolute; */
  /* border:2px solid red; */
  height: 8%;
  width: 100%;
  bottom: 0%;
  position: fixed;
}

footer .foot-left {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  padding-left: 10px;
  /* position: relative; */
  width: 50%;
  height: 100%;
  /* border: 2px solid white; */
}

footer .foot-left img {
  padding: 10px;
  width: 20px;
  height: 20px;
  left: 20px;
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
}
/* why i can't put footer .footer-left h6{}? the function will not be implemented if I use this.*/
footer .foot-left h6 {
  color: #fff;
  font-weight: lighter;
  font-size: 16px;
}

footer .foot-right {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  /* position: relative; */
  width: 50%;
  height: 100%;
  /* border: 2px solid white; */
}

footer .foot-right .social-media-icon {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
  width: 60px;
  height: 50%;
  position: relative;
  /* border: 2px solid green; */
}

footer .foot-right img {
  filter: invert(100%) sepia(0%) saturate(1%) hue-rotate(158deg)
    brightness(103%) contrast(100%);
  width: 20px;
  height: 20px;
  padding: 5px;
}

footer .foot-right .other-footer-information {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
  /*width: 200px;*/
  height: 50%;
  /* border: 2px solid red; */
}

footer .foot-right .other-footer-information a {
  text-decoration: none;
  color: #fff;
  padding: 5px;
  padding-right: 20px;
  font-weight: lighter;
  font-size: 16px;
}

footer .foot-right .other-footer-information p {
  color: #fff;
  margin-inline-start: 0px;
  margin-inline-end: 10px;
  font-size: 16px;
}
</style>
