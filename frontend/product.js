function getProducts() {
    var apiEndpoint = 'http://localhost:8080/products'; 
  
    fetch(apiEndpoint)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error: ' + response.status + ' ' + response.statusText);
        }
      })
      .then(data => {
        displayProducts(data);
      })
      .catch(error => {
        console.error('Error:', error.message);
      });
  }
  
  function displayProducts(products) {
    var productList = document.getElementById('product-list');
  
    products.forEach((product, index) => {
      var productItem = document.createElement('div');
      productItem.classList.add('product-item');
      productItem.id = 'product-' + index;
  
      var productImage = document.createElement('img');
      productImage.src = product.image;
      productImage.alt = product.productName;
      productImage.classList.add('product-image');
      productItem.appendChild(productImage);
  
      var productName = document.createElement('h3');
      productName.textContent = product.productName;
      productName.classList.add('product-name');
      productItem.appendChild(productName);
  
      var productPrice = document.createElement('p');
      productPrice.textContent = 'Price: Rs.' + product.price;
      productPrice.classList.add('product-price');
      productItem.appendChild(productPrice);
  
      var productDescription = document.createElement('p');
      productDescription.textContent = product.description;
      productDescription.classList.add('product-description');
      productItem.appendChild(productDescription);
  
      productList.appendChild(productItem);
    });
  }
  
  
  window.onload = function() {
    getProducts();
  };