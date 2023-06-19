var token = localStorage.getItem('authkey');
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
    productList.innerHTML=null;
  
    products.forEach((product, index) => {
      var productItem = document.createElement('div');
      productItem.classList.add('product-item');
      productItem.id = product.productId;
  
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
  
      var deleteButton = document.createElement('button');
      deleteButton.textContent = 'Delete';
      deleteButton.classList.add('delete-button');
      deleteButton.addEventListener('click', function() {
        deleteProduct(product.productId, token);
        productItem.remove();
      });
      productItem.appendChild(deleteButton);
  
      productList.appendChild(productItem);
    });
  }
  function deleteProduct(productId, token) {

    fetch('http://localhost:8080/products/' + productId, {
      method: 'DELETE',
      headers: {
        'Authorization': 'Bearer ' + token
      }
    })
      .then(function(response) {
        if (response.ok) {
          console.log('Product deleted successfully');
        } else {
          console.error('Error deleting product:', response.status);
        }
      })
      .catch(function(error) {
        console.error('Error deleting product:', error);
      });
  }
      getProducts();

  // http://localhost:8080/products/

  function handleFormSubmit() {  
    var searchInput = document.getElementById('searchInput');
    var searchTerm = searchInput.value;
    console.log(searchTerm);
  
    fetch('http://localhost:8080/products/' + searchTerm, {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Error: ' + response.status);
        }
        return response.json();
      })
      .then(data => {
        console.log(data.image);
        let arr = [];
        arr.push(data);
        displayProducts(arr);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }
  
  var searchForm = document.getElementById('searchForm');
  searchForm.addEventListener('click', () => {
    handleFormSubmit();
  });



   