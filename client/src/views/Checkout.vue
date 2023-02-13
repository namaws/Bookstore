<template>
  <div class="checkout-page">
    <section class="checkout-page-body" v-if="!cart.empty">
      <form v-on:submit.prevent="submitOrder" v-on:reset="resetOrder" method="post">
        <div>
          <label for="name">Name</label>
          <input
            type="text"
            size="20"
            id="name"
            name="name"
            @blur="$v.name.$touch()"
            v-model.lazy="$v.name.$model"
          />
        </div>
        <template v-if="$v.name.$error">
          <span class="error" v-if="!$v.name.required">Name is required</span>
          <span class="error" v-else-if="!$v.name.minLength">
            Name must have at least
            {{ $v.name.$params.minLength.min }} letters.
          </span>
          <span class="error" v-else-if="!$v.name.maxLength">
            Name can have at most
            {{ $v.name.$params.maxLength.max }} letters.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>

        <div class="form-element">
          <label for="address">Address</label>
          <input
              class="textField"
              type="text"
              size="20"
              maxlength="45"
              id="address"
              name="address"
              @blur="$v.address.$touch()"
              v-model.lazy="$v.address.$model"
          />
        </div>
        <template v-if="$v.address.$error">
          <div class="error" v-if="!$v.address.required">
            Address is required
          </div>
          <div class="error" v-if="!$v.address.minLength">
            Address must have at least
            {{ $v.address.$params.minLength.min }} letters.
          </div>
          <div class="error" v-if="!$v.address.maxLength">
            Address can have at most
            {{ $v.address.$params.maxLength.max }} letters.
          </div>
        </template>

        <div>
          <label for="phone">Phone</label>
          <input
            class="textField"
            type="text"
            size="20"
            id="phone"
            name="phone"
            @blur="$v.phone.$touch()"
            v-model.lazy="$v.phone.$model"
          />
        </div>

        <template v-if="$v.phone.$error">
          <div class="error" v-if="!$v.phone.required">
            Phone is required
          </div>
          <div class="error" v-else-if="$v.phone.$invalid">
            Please enter a valid phone number.
          </div>
        </template>

        <div>
          <label for="email">Email</label>
          <input
              type="text"
              size="20"
              id="email"
              name="email"
              v-model.lazy="$v.email.$model"
              @blur="$v.email.$touch()"
          />
        </div>
        <template v-if="$v.email.$error">
          <div class="error" v-if="!$v.email.required">
            Email is required
          </div>
          <div class="error" v-else-if="$v.email.$invalid">
            Please enter a valid email address.
          </div>
        </template>

        <div>
          <label for="ccNumber">Credit card</label>
          <input
              type="text" size="20"
              id="ccNumber"
              name="ccNumber"
              @blur="$v.ccNumber.$touch()"
              v-model.lazy="$v.ccNumber.$model"
          />
        </div>
        <template v-if="$v.ccNumber.$error">
          <div class="error" v-if="!$v.ccNumber.required">
            Credit card is required
          </div>
          <div class="error" v-else-if="$v.ccNumber.$invalid">
            Please enter a valid credit card number.
          </div>
        </template>
        <div>
          <label>Exp Date</label>
          <select v-model="ccExpiryMonth">
            <option
              v-for="(month, index) in months"
              :key="index"
              :value="index + 1"
            >
              {{ month }} ({{ index + 1 }})
            </option>
          </select>
          <select v-model="ccExpiryYear">
            <option
                v-for="(year, index) in ccExpiryYear"
                :key="index"
                :value="index + 1"
            >
              {{ year }}
            </option>
          </select>
        </div>
        <template v-if="$v.ccExpiryMonth.$error">
          <div class="error" v-if="!$v.ccExpiryMonth.required">
            Month card is required
          </div>
          <div class="error">
            The date is not valid.
          </div>
        </template>
        <template v-if="$v.ccExpiryYear.$error">
          <div class="error" v-if="!$v.ccExpiryYear.required">
            Year card is required
          </div>
          <div class="error">
            The date is not valid.
          </div>
        </template>


        <div id="checkout-button-area">
          <button id="checkout-button" :class="[ { 'not-active': isClickedCompletePurchase } ,'button']" @click.prevent="submitOrder" type="submit">
            Complete Purchase
          </button>

          <button id="reset-button" class="button" @click="resetOrder" type="reset">
            Reset Form
          </button>
        </div>
        <div id="checkout-info">
          <span id="checkout-info-text">
            Your credit card will be charged
            <strong>
              <Price :amount="cart.total" />
            </strong>
            <br />(
            <strong>
              <Price :amount="cart.subtotal" />
            </strong>
            +
            <strong>
              <Price :amount="cart.surcharge" />
            </strong>
            shipping)
          </span>
        </div>
        <div v-if="checkoutStatus !== ''" class="form-text-holder">
          <template v-if="checkoutStatus === 'ERROR'">
            <div class="form-text form-error-text" v-if="$v.$invalid">
              Please fix the problems above and try again.
            </div>
          </template>

          <template v-if="checkoutStatus === 'PENDING'">
            <div class="form-text form-pending-text">Processing...</div>
          </template>
          <template v-if="checkoutStatus === 'OK'">
            <div class="form-text form-ok-text">Order placed...</div>
          </template>
          <template v-if="checkoutStatus === 'SERVER_ERROR'">
            <div class="form-text form-error-text">
              An unexpected error occurred, please try again.
            </div>
          </template>
        </div>
      </form>
    </section>
    <section
        v-else class="empty-cart-page"
    >
      <span> You need to add an item to your cart to checkout.</span>

      <div class="cart-buttons">
        <router-link :to="'/category/' + $store.getters.categoryName">
          <button class="button">Continue Shopping</button>
        </router-link>
      </div>
    </section>
  </div>
</template>

<script>
import Price from '@/components/Price.vue'
import {
  required,
  email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

import isCreditCard from "validator/lib/isCreditCard";
import isMobilePhone from "validator/lib/isMobilePhone";

const phone = value => isMobilePhone(value, "en-US");
const creditCard = value => isCreditCard(value);

export default {
  components: { Price },
  data() {
    return {
      name: "",
      address: "",
      phone: "",
      email: "",
      ccNumber: "",
      ccExpiryMonth: new Date().getMonth() + 1,
      ccExpiryYear: new Date().getFullYear(),
      checkoutStatus: "",
      isClickedCompletePurchase:false
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    address: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    phone: {
      required,
      phone: phone,
    },
    email: {
      required,
      email: email,
    },
    ccNumber: {
      required,
      creditCard: creditCard,
    },
    ccExpiryMonth: {
      required,
    },
    ccExpiryYear: {
      required,
    },
  },

  computed: {
    cart() {
      return this.$store.state.cart;
    },
    months() {
      return [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
    },
  },

  methods: {
    resetOrder() {
      console.log('Reset order')
      this.$v.$reset()
      this.checkoutStatus = ''
    },
    submitOrder() {
      console.log("Submit order");
      this.isClickedCompletePurchase = true
      this.$store.commit('CLEAR_ORDER_DETAILS', null)
      this.$v.$touch()
      if (this.$v.$invalid) {
        this.checkoutStatus = 'ERROR'
        this.isClickedCompletePurchase = false
      } else {
        this.checkoutStatus = 'PENDING';
        this.isClickedCompletePurchase = false
        setTimeout(() => {
          this.$store
              .dispatch('placeOrder', {
                name: this.name,
                address: this.address,
                phone: this.phone,
                email: this.email,
                ccNumber: this.ccNumber,
                ccExpiryMonth: this.ccExpiryMonth,
                ccExpiryYear: this.ccExpiryYear
              })
              .then(() => {
                this.isClickedCompletePurchase = false
                this.checkoutStatus = 'OK'
                this.$store.commit('CLEAR_CART')
                this.$router.push({ name: 'confirmation' })
              })
              .catch(reason => {
                this.checkoutStatus = 'SERVER_ERROR'
                this.isClickedCompletePurchase = false
                console.log('Error placing order', reason)
              })
        }, 2000);
      }
    },

    /* NOTE: For example yearFrom(0) == 2021 */
    yearFrom(index) {
      return new Date().getFullYear() + index;
    },
  },
};
</script>

<style scoped>
.checkout-page {
  background: rgba(105, 100, 100, 0.5);
  color: #fff;
  display: flex;
  justify-content: center;
}
.checkout-page-body {
  display: flex;
  padding: 1em;
}
.empty-cart-page{
   margin: 5rem;
 }
.empty-cart-page .cart-buttons{
  padding: 10px;

}
form {
  display: flex;
  flex-direction: column;
}

form > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

form > div > input,
form > div > select {
  background-color: #666666;
  margin-left: 0.5em;
}

form > .error {
  color: red;
  text-align: right;
}

.checkoutStatusBox {
  margin: 1em;
  padding: 1em;
  text-align: center;
}

.checkout-button {
  width: 13em;
}
#checkout-button.not-active{
  cursor:  not-allowed;
  color:#fff;
  background-color: #d3d3d3;
}
/*.ccDate {*/
/*  display: flex;*/
/*  justify-content: space-between;*/

/*}*/
</style>
