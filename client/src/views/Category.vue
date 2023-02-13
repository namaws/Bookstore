<!--  contains html-->
<template>
  <main>
    <div>
      <category-nav></category-nav>
      <category-book-list :books=$store.state.selectedCategoryBooks> </category-book-list>
    </div>
  </main>
</template>

<!--contains javaScript-->
<script>
import CategoryNav from "@/components/CategoryNav";
import CategoryBookList from "@/components/CategoryBookList";
export default {
  name: "category",
  components: {
    CategoryNav,
    CategoryBookList,
  },
  // the way to use data dynamically
  data: function () {
    return {
      books: [],
    };
  },
  created: function () {
    const self = this;
    if(this.$route.params.name !== this.$store.state.selectedCategoryName) {
      console.log("Start fetchCategories");
      this.$store.dispatch("setCategoryNames", this.$route.params.name);
      this.$store.dispatch("fetchSelectedCategoryBooks").catch(function(reason){
        console.log(reason)
        self.$router.push('/404')
      })
      console.log("Finish fetchCategories");
    }
  }

};
</script>

<!--contains CSS-->
<style scoped></style>
