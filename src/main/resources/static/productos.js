document.addEventListener('DOMContentLoaded', () => {
    cargarCategorias();
    cargarProductos();
});


function agregarProducto() {
    const nombre = document.getElementById('firstname').value;
    const stock = parseInt(document.getElementById('lastname').value);
    const precio = parseFloat(document.getElementById('email').value);
    const categoriaId = document.getElementById('phone').value;
    const token = localStorage.getItem("token");

    if (!nombre || isNaN(stock) || isNaN(precio) || !categoriaId) {
        alert("Todos los campos son obligatorios.");
        return;
    }

    const producto = {
        name: nombre,
        stock: stock,
        price: precio,
        category: {
            id: categoriaId
        }
    };

    const id = window.productoEnEdicion;

    const url = id ? `/api/products/update/${id}` : '/api/products/create';
    const method = id ? 'PUT' : 'POST';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(producto)
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al guardar producto");
            return response.json();
        })
        .then(data => {
            alert(id ? "✅ Producto modificado con éxito: " + data.name : "✅ Producto agregado con éxito: " + data.name);

            // Resetear el formulario
            const form = document.querySelector("form");
            form.reset();

            // Restaurar el estado del botón principal
            const btn = document.getElementById("btn-agregar");
            btn.innerText = "AGREGAR PRODUCTO";
            btn.style.display = "inline-block";
            btn.disabled = false;

            // Limpiar producto en edición y recargar productos
            window.productoEnEdicion = null;
            cargarProductos();
        })
}

function cargarProductos() {
    const token = localStorage.getItem("token");

    fetch('/api/products/get', {
        headers: {
            'Authorization': `Bearer ${token}`   // <-- acá corregí
        }
    })
    .then(response => {
        if (!response.ok) throw new Error("Error al cargar productos");
        return response.json();
    })
    .then(data => {
        const container = document.getElementById('productosContainer');
        container.innerHTML = '';

        data.forEach((producto, i) => {
            const div = document.createElement('div');
            div.classList.add(`div${i + 1}`);
            div.innerHTML = `
  <strong>${producto.name}</strong>
  <span>Stock: ${producto.stock}</span>
  <span>Precio: $${producto.price}</span>
  <span>Categoría: ${producto.category.name}</span>
  <button class="btn-eliminar" onclick="eliminarProducto(${producto.id})">Eliminar</button>
  <button class="btn-modificar" onclick="cargarProductoEnFormulario(${producto.id})">Modificar</button>
`;
            container.appendChild(div);
        });
    })
    .catch(err => {
        console.error('Error al cargar productos:', err);
        alert("❌ No se pudieron cargar los productos. Asegurate de estar logueado.");
    });
}

function cargarCategorias() {
    const token = localStorage.getItem("token");

    fetch('/api/categories/get', {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al cargar categorías");
            return response.json();
        })
        .then(data => {
            const select = document.getElementById("phone");
            // Primero vaciamos por si ya tenía opciones
            select.innerHTML = '<option value="">Seleccioná una categoría</option>';

            data.forEach(categoria => {
                const option = document.createElement("option");
                option.value = categoria.id;
                option.textContent = categoria.name;
                select.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error al cargar categorías:", error);
            alert("❌ No se pudieron cargar las categorías.");
        });
}


function eliminarProducto(id) {
    if (!confirm("¿Estás seguro de que querés eliminar este producto?")) return;

    const token = localStorage.getItem("token");

    fetch(`/api/products/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`   // <-- acá corregí
        }
    })
    .then(response => {
        if (!response.ok) throw new Error("Error al eliminar producto");
        alert("✅ Producto eliminado");
        cargarProductos();
    })
    .catch(err => {
        console.error("Error al eliminar:", err);
        alert("❌ No se pudo eliminar el producto.");
    });
}


function cargarProductoEnFormulario(id) {
    const token = localStorage.getItem("token");

    fetch(`/api/products/get/${id}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(producto => {
            document.getElementById("firstname").value = producto.name;
            document.getElementById("lastname").value = producto.stock;
            document.getElementById("email").value = producto.price;
            document.getElementById("phone").value = producto.category.id;

            window.productoEnEdicion = producto.id;

            const btn = document.getElementById("btn-agregar");
            btn.innerText = "GUARDAR CAMBIOS";
            btn.style.display = "inline-block";
            btn.disabled = false;

            document.querySelector("form").scrollIntoView({ behavior: "smooth" });
        })
        .catch(error => {
            console.error("Error al cargar producto para modificar:", error);
            alert("❌ No se pudo cargar el producto.");
        });
}
