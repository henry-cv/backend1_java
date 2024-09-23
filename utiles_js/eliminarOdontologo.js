//Para crear un nuevo Odontólogo

const ingresar = require("prompt-sync")({sigint:true});
const sitios = ["http://localhost:8080/odontologos", "http://localhost:8080/pacientes", "http://localhost:8080/turnos"]
console.info("Ingrese 1 para odontologos, 2 para pacientes, 3 para turnos")
let opcion=parseInt(ingresar("Ingrese opción para Borrado: "));

const url = sitios[opcion-1];

async function borrarRegistro() {  
  let id = parseInt(ingresar("Ingrese id para buscar: "));  
  const settings = {
    method: "DELETE",    
    headers: {
      "Content-Type": "application/json",
    },
  };
  try {
    let res = await fetch(`${url}/${id}`, settings);
    if (!res.ok) {
      //console.log(res);
      console.error(res.status);
      throw new Error(res);
    }
    console.log("res exitosa");
    //console.log(res);
    let data = await res.json();
    console.log("data");
    console.log(data);
    console.log(`Estado: ${res.status}, Mensaje: ${res.statusText}`);
    renderizarPublicacion(data);
  } catch (error) {
    console.error(error.status);
  }
}
borrarRegistro();
