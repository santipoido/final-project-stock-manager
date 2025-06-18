document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/categories')
        .then(response => response.json())
        .then(data => {
            const select = document.getElementById('phone');
            select.innerHTML = '<option value="">Seleccioná una categoría</option>';
            data.forEach(cat => {
                const option = document.createElement('option');
                option.value = cat.id;
                option.textContent = cat.categoryName;
                select.appendChild(option);


            });
            cargarProductos();
        })
        .catch(error => {
            console.error('Error al cargar categorías:', error);
            document.getElementById('phone').innerHTML =
                '<option value="">Error al cargar</option>';
        });
});


function agregarProducto() {
    const nombre = document.getElementById('firstname').value;
    const stock = parseInt(document.getElementById('lastname').value);
    const precio = parseFloat(document.getElementById('email').value);
    const categoriaId = document.getElementById('phone').value;

    if (!nombre || isNaN(stock) || isNaN(precio) || !categoriaId) {
        alert("Todos los campos son obligatorios.");
        return;
    }

    const nuevoProducto = {
        productName: nombre,
        stock: stock,
        price: precio,
        category: {
            id: categoriaId
        }
    };

    fetch('/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(nuevoProducto)
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al crear producto");
            return response.json();
        })
        .then(data => {
            alert("✅ Producto agregado con éxito: " + data.productName);
            document.querySelector("form").reset(); // limpia el form
            cargarProductos();
        })
        .catch(error => {
            console.error("Error al agregar producto:", error);
            alert(" No se pudo agregar el producto.");
        });
}

function cargarProductos() {
    fetch('/api/products')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('productosContainer');
            container.innerHTML = ''; // Limpiar antes de renderizar

            data.forEach((producto, i) => {
                const div = document.createElement('div');
                div.classList.add(`div${i + 1}`);
                div.innerHTML = `
  <strong>${producto.productName}</strong>
  <span>Stock: ${producto.stock}</span>
  <span>Precio: $${producto.price}</span>
  <span>Categoría: ${producto.category.categoryName}</span>
  <button class="btn-eliminar" onclick="eliminarProducto(${producto.id})">Eliminar</button>
`;
                container.appendChild(div);
            });
        })
        .catch(err => {
            console.error('Error al cargar productos:', err);
        });
}

function eliminarProducto(id) {
    if (!confirm("¿Estás seguro de que querés eliminar este producto?")) return;

    fetch(`/api/products/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al eliminar");
            alert("Producto eliminado");
            cargarProductos(); // refresca la lista
        })
        .catch(err => {
            console.error("Error al eliminar:", err);
            alert("No se pudo eliminar el producto");
        });
}