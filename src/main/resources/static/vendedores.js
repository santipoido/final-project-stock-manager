document.addEventListener('DOMContentLoaded', () => {
    cargarUsuarios();
});


function handleUsuario() {
    if (window.usuarioEnEdicion) {
        modificarUsuario();
    } else {
        agregarUsuario();
    }
}
function agregarUsuario() {
    const nombre = document.getElementById('firstname').value;
    const apellido = document.getElementById('lastname').value;
    const username = document.getElementById('email').value;
    const password = document.getElementById('phone').value;
    const rol = document.getElementById('rol').value;
    const token = localStorage.getItem("token");

    if (!nombre || !apellido || !username || !password || !rol) {
        alert("Todos los campos son obligatorios.");
        return;
    }

    const nuevoUsuario = {
        name: nombre,
        lastname: apellido,
        username: username,
        password: password,
        role: rol
    };

    fetch('/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(nuevoUsuario)
    })
        .then(res => {
            if (!res.ok) throw new Error(`Registro fallido (${res.status})`);
            return res.text();
        })
        .then(msg => {
            alert("✅ " + msg);
            document.querySelector("form").reset();
            document.querySelector(".formbold-btn").innerText = "AGREGAR USUARIO";
            document.getElementById("phone").style.display = "block"; // vuelve a mostrar la contraseña
            window.usuarioEnEdicion = null;
            cargarUsuarios();
        })
        .catch(err => {
            console.error("Error al registrar usuario:", err);
            alert("❌ No se pudo registrar el usuario.");
        });
}


function modificarUsuario() {
    const id = window.usuarioEnEdicion;
    const nombre = document.getElementById('firstname').value;
    const apellido = document.getElementById('lastname').value;
    const username = document.getElementById('email').value;
    const password = document.getElementById('phone').value; // <- AÑADIR
    const rol = document.getElementById('rol').value;
    const token = localStorage.getItem("token");

    if (!nombre || !apellido || !username || !password || !rol) {
        alert("Todos los campos son obligatorios.");
        return;
    }

    const usuarioEditado = {
        name: nombre,
        lastname: apellido,
        username: username,
        password: password, // <- AÑADIR
        role: rol
    };

    fetch(`/api/users/update/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(usuarioEditado)
    })
        .then(res => {
            if (!res.ok) throw new Error(`Error al modificar (${res.status})`);
            return res.json();
        })
        .then(usuario => {
            alert("✅ Usuario modificado con éxito: " + usuario.name);
            document.querySelector("form").reset();
            document.querySelector(".formbold-btn").innerText = "AGREGAR USUARIO";
            document.getElementById("phone").style.display = "block";
            document.getElementById("password-group").style.display = "block"; // <- RESTAURA VISIBILIDAD
            window.usuarioEnEdicion = null;
            cargarUsuarios();
        })
        .catch(err => {
            console.error("Error al modificar usuario:", err);
            alert("❌ No se pudo modificar el usuario.");
        });
}

function cargarUsuarios() {
    const token = localStorage.getItem("token");

    fetch('/api/users/get', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(res => {
            console.log("cargarUsuarios status:", res.status);
            if (!res.ok) throw new Error(`Error ${res.status}`);
            return res.json();
        })
        .then(data => {
            const container = document.getElementById('usuariosContainer');
            container.innerHTML = '';

            data.forEach((u, i) => {
                const div = document.createElement('div');
                div.classList.add(`div${i + 1}`);
                div.innerHTML = `
                    <strong>${u.name} ${u.lastname}</strong>
                    <strong>${u.username}</strong>
                    <span>Rol: ${u.role}</span>
                    <button class="btn-eliminar" onclick="eliminarUsuario(${u.id})">Eliminar</button>
                    <button class="btn-modificar" onclick="cargarUsuarioEnFormulario(${u.id})">Modificar</button>
                `;
                container.appendChild(div);
            });
        })
        .catch(err => {
            console.error("Error al cargar usuarios:", err);
            alert("❌ No se pudieron cargar los usuarios.");
        });
}

function eliminarUsuario(id) {
    if (!confirm("¿Querés eliminar este usuario?")) return;

    const token = localStorage.getItem("token");
    fetch(`/api/users/delete/${id}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}` }
    })
        .then(res => {
            if (!res.ok) throw new Error();
            alert("✅ Usuario eliminado");
            cargarUsuarios();
        })
        .catch(err => {
            console.error("Error al eliminar:", err);
            alert("❌ No se pudo eliminar el usuario.");
        });
}

function cargarUsuarioEnFormulario(id) {
    const token = localStorage.getItem("token");

    fetch('/api/users/get', {
        method: 'GET',
        headers: { 'Authorization': `Bearer ${token}` }
    })
        .then(res => {
            if (!res.ok) throw new Error(`Error ${res.status}`);
            return res.json();
        })
        .then(data => {
            const u = data.find(x => x.id === id);
            if (!u) throw new Error("Usuario no encontrado");

            document.getElementById("firstname").value = u.name;
            document.getElementById("lastname").value = u.lastname;
            document.getElementById("email").value = u.username;
            document.getElementById("phone").value = "";
            document.getElementById("rol").value = u.role;

            document.getElementById("password-group").style.display = "block";

            window.usuarioEnEdicion = u.id;
            const btn = document.querySelector(".formbold-btn");
            btn.innerText = "GUARDAR CAMBIOS";
            document.querySelector("form").scrollIntoView({ behavior: "smooth" });
        })
        .catch(err => {
            console.error("Error al cargar usuario:", err);
            alert("❌ No se pudo cargar el usuario.");
        });
}