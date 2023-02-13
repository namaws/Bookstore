const portFrom = {
    'http:': ':8080',
    'https:': ':8443'
}
const apiUrl = location.protocol + '//' +
    location.hostname +
    portFrom[location.protocol] +
    process.env.BASE_URL + 'api'

export default {
  fetchCategories() {
    const url = apiUrl + "/categories";
    console.log("GET from " + url);
    return fetch(url)
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
        throw new Error("Network response was not ok.");
      })
      .catch((reason) => {
        console.log("Error fetching category data", reason);
      });
  },
  fetchSelectedCategoryBooks(categoryName) {
    const url = apiUrl + "/categories/name/" + categoryName + "/books";
    console.log("GET from " + url);
    return fetch(url)
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
        throw new Error("find books by categoryId fails")
      })
      .catch((reason) => {
        console.log("Error fetching category data", reason);
        throw reason;
      });
  },
  placeOrder(order) {
      console.log("POSTing to " + `${apiUrl}/orders`);
      return fetch(`${apiUrl}/orders`, {
          method: "POST",
          body: JSON.stringify(order),
          headers: {
              "Content-Type": "application/json"
          }
      }).then(stream => {
          if (stream.ok) {
              return stream.json();
          }
          throw new Error("Network response was not ok.");
      });
    }
};
