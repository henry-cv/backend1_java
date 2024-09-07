async function fetchOdontologos() {
  try {
    const response = await fetch("http://localhost:8080/odontologos/listar");
    // Cambia esta URL por la de tu API
    const data = await response.json();
    const tableBody = document.getElementById("odontologosTable").querySelector("tbody");

    // Limpiar la tabla antes de agregar nuevos datos
    tableBody.innerHTML = "";

    data.forEach((odontologo) => {
      const row = document.createElement("tr");
      row.innerHTML = `
                    <td data-label="ID">${odontologo.id}</td>
                    <td data-label="Matrícula">${odontologo.matricula}</td>
                    <td data-label="Nombre">${odontologo.nombre}</td>
                    <td data-label="Apellido">${odontologo.apellido}</td>
                `;
      tableBody.appendChild(row);
    });
  } catch (error) {
    console.error("Error al obtener los odontólogos:", error);
  }
}

// Llamar la función al cargar la página
//window.onload = fetchOdontologos;
document.addEventListener("DOMContentLoaded", (e) => {
  console.log("DOM cargado");
  fetchOdontologos();
});
const $inputMatricula = document.querySelector("#matricula");
const $inputNombre = document.querySelector("#nombre");
const $inputApellido = document.querySelector("#apellido");
const $formOdontologo = document.querySelector("#form-odontologo");
const $formGuardar = document.querySelector("#guardar");
const url = "http://localhost:8080/odontologos";
const $parrafoResultado = document.querySelector("#par-resultado");
const botonNuevo = document.querySelector("[name=btn-nuevo]");
const formBusqueda = document.querySelector("#form-busqueda");
const tareaBuscar = document.querySelector("[name=btn-buscar]");

function mostrarResultado(respuesta) {
  $parrafoResultado.textContent = "";
  $parrafoResultado.textContent = respuesta;
}
async function crearOdontologo() {
  const odontologo = {
    matricula: $inputMatricula.value,
    nombre: $inputNombre.value,
    apellido: $inputApellido.value,
  };
  const settings = {
    method: "POST",
    body: JSON.stringify(odontologo),
    headers: {
      "Content-Type": "application/json",
    },
  };
  try {
    let res = await fetch(`${url}/registrar`, settings);
    if (!res.ok) {
      //console.log(res);
      console.error(res.status);
      throw new Error(res);
    }
    //console.log("res exitosa");
    //console.log(res);
    let data = await res.json();
    //console.log("data");
    //console.log(data);
    console.log(`Estado: ${res.status}, Mensaje: ${res.statusText}`);

    let respuesta = res.status === 201 ? `Odontólogo creado con exito: ${data.nombre} ${data.apellido}` : "";
    mostrarResultado(respuesta);
    setTimeout(() => {
      $parrafoResultado.classList.toggle("oculto");
    }, 2000);
    fetchOdontologos();
  } catch (error) {
    console.error(error);
    mostrarResultado(error);
  }
}
$formOdontologo.addEventListener("submit", (e) => {
  e.preventDefault();
  crearOdontologo();
  e.reset;
});
botonNuevo.addEventListener("click", (e) => {
  console.info("hizo click en la tarea Nuevo");
  $formOdontologo.classList.toggle("oculto");
});
tareaBuscar.addEventListener("click", (e) => {
  console.info("hizo click en la tarea Buscar");
  formBusqueda.classList.toggle("oculto");
});
