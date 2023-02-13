<template>
  <table>
    <tr>
      <th style="text-align:center">Book</th>
      <th style="text-align:center">Number</th>
      <th style="text-align:center">Price</th>
    </tr>
    <tr
        v-for="(item, index) in this.$store.state.orderDetails.lineItems"
        :key="item.productId"
    >

      <td>

        {{ $store.state.orderDetails.books[index].title }}
      </td>
      <td>{{ item.quantity }}</td>
      <td>
        <Price :amount="$store.state.orderDetails.books[index].price * item.quantity" />
      </td>

    </tr>
  </table>
</template>

<script>
import Price from "@/components/Price";
export default {
  name: "ConfirmationTable",
  components: { Price }
};
</script>

<style scoped>
table {
  width: fit-content;
  margin-top: 1em;
  margin-bottom: 1em;
  color: black;
  padding: 0 10px;
}

tr{
  min-height: 30px;
}

tr:first-child{
  background-color: whitesmoke;
  padding: 0.5em;
}

td {
  display: table-cell;
  padding: 0.5em 0.5em;
  background-color: white;
  vertical-align: middle;
}

tr:nth-child(even) > td {
  background-color: aliceblue;
}

td:nth-child(1) {
  text-align: left;
}

td:nth-child(2) {
  text-align: center;
}

td:nth-child(3) {
  text-align: right;
}
</style>
