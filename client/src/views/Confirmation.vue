<template>
  <div id="confirmationView">
    <ul class="ul-confirmation" v-if="$store.state.orderDetails != null">
      <li>Confirmation #: {{ $store.state.orderDetails.order.confirmationNumber }}</li>
      <li>Time: {{ confirmationTime }}</li>
    </ul>
    <ul class="ul-confirmation error" v-else>
      <li>There is no transaction, please try again</li>
      <router-link
          :to="{
        name: 'home'
      }"
      >
        <button class="normal-2x-button">Continue</button>
      </router-link>
    </ul>
    <confirmation-table v-if="$store.state.orderDetails != null"> </confirmation-table>
    <ul v-if="$store.state.orderDetails != null">
      <li>{{ $store.state.orderDetails.customer.customerName }}</li>
      <li>{{ $store.state.orderDetails.customer.address }}</li>
      <li>{{ $store.state.orderDetails.customer.email }}</li>
      <li>{{ $store.state.orderDetails.phone }}</li>
      <li>**** **** **** {{ lastFourDigits }} ({{ expiration }})</li>
    </ul>
    <div id="customerInfo" v-if="$store.state.orderDetails != null"></div>

  </div>
</template>

<script>
import ConfirmationTable from "@/components/ConfirmationTable";
export default {
  name: "Confirmation",
  components: { ConfirmationTable },
  computed:{
    confirmationTime:function(){
      if(this.$store.state.orderDetails != undefined){
        var milliseconds = parseInt(this.$store.state.orderDetails.order.dateCreated)
        var date = new Date( milliseconds).toLocaleDateString();
        var time = new Date(milliseconds).toTimeString().split(":")[0] +  ":"
            + new Date(milliseconds).toTimeString().split(":")[1]
        return date + " " + time;
      }else{
        return ""
      }

    },
    lastFourDigits:function (){
      return this.$store.state.orderDetails.customer.ccNumber.substring(14)
    },
    expiration:function(){
      if(this.$store.state.orderDetails.customer != undefined){
        var milliseconds = parseInt(this.$store.state.orderDetails.customer.ccExpDate)
        var date = new Date(milliseconds).toLocaleDateString()
        return date.split("/")[1] + "-" + date.split("/")[0]
      }else{
        return ""
      }


    }
  }
};
</script>

<style scoped>
#confirmationVie, #router-view {
  display: flex;
  flex-direction: column;
  margin: 1em auto;
  justify-content: center;
  align-items: center;
}
ul {
  text-align: left;
  width: 350px;
}
ul.ul-confirmation{
  text-align: center;
}
ul.ul-confirmation.error{
  margin-top: 10rem;
}
ul > li {
  margin: 0.25em;
}
div a button {
  display: inline-block;
  background: var(--primary-color);
  color: white;
  text-decoration: none;
  padding: 0.5em 1em;
  cursor: pointer;
}
div a button:hover{
  background: var(--primary-color-dark);
  color: white;
}

</style>
