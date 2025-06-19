document.addEventListener('DOMContentLoaded', () => {
    cargarCategorias();
});


function agregarCategoria() {
    const nombre = document.getElementById('firstname').value;
    const token = localStorage.getItem("token");

    if (!nombre) {
        alert("Todos los campos son obligatorios.");
        return;
    }

    const nuevaCategoria = {
        name: nombre
    };

    fetch('/api/categories', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`   // <-- acá corregí
        },
        body: JSON.stringify(nuevaCategoria)
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al crear categoria");
            return response.json();
        })
        .then(data => {
            alert("✅ Categoria agregada con éxito: " + data.name);
            document.querySelector("form").reset();
            cargarCategorias();
        })
        .catch(error => {
            console.error("Error al agregar la categoria:", error);
            alert(" No se pudo agregar la categoria.");
        });
}

function cargarCategorias() {
    const token = localStorage.getItem("token");

    fetch('/api/categories', {
        headers: {
            'Authorization': `Bearer ${token}`   // <-- acá corregí
        }
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al cargar categorias");
            return response.json();
        })
        .then(data => {
            const container = document.getElementById('categoriasContainer');
            container.innerHTML = '';

            data.forEach((categoria, i) => {
                const div = document.createElement('div');
                div.classList.add(`div${i + 1}`);
                div.innerHTML = `
  <strong>${categoria.name}</strong>
  <button class="btn-eliminar" onclick="eliminarCategoria(${categoria.id})">Eliminar</button>
`;
                container.appendChild(div);
            });
        })
        .catch(err => {
            console.error('Error al cargar categorias:', err);
            alert("❌ No se pudieron cargar las categorias. Asegurate de estar logueado.");
        });
}


function eliminarCategoria(id) {
    if (!confirm("¿Estás seguro de que querés eliminar esta categoria?")) return;

    const token = localStorage.getItem("token");

    fetch(`/api/categories/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`   // <-- acá corregí
        }
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al eliminar categoria");
            alert("✅ Categoria eliminada");
            cargarCategorias();
        })
        .catch(err => {
            console.error("Error al eliminar:", err);
            alert("❌ No se pudo eliminar la categoria.");
        });
}