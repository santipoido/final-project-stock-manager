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

    const nuevoProducto = {
        name: nombre,
        stock: stock,
        price: precio,
        category: {
            id: categoriaId
        }
    };

    fetch('/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`   // <-- acá corregí
        },
        body: JSON.stringify(nuevoProducto)
    })
    .then(response => {
        if (!response.ok) throw new Error("Error al crear producto");
        return response.json();
    })
    .then(data => {
        alert("✅ Producto agregado con éxito: " + data.name);
        document.querySelector("form").reset();
        cargarProductos();
    })
    .catch(error => {
        console.error("Error al agregar producto:", error);
        alert(" No se pudo agregar el producto.");
    });
}

function cargarProductos() {
    const token = localStorage.getItem("token");

    fetch('/api/products', {
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

    fetch('/api/categories', {
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

    fetch(`/api/products/${id}`, {
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
