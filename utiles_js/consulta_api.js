const ingresar = require("prompt-sync")({sigint:true});
const sitios = ["http://localhost:8080/odontologos", "http://localhost:8080/pacientes", "http://localhost:8080/turnos"]
console.info("Ingrese 1 para odontologos, 2 para pacientes, 3 para turnos")
let opcion=parseInt(ingresar("Ingrese opción para listar: "));

const url1 = sitios[opcion-1];

async function listar() {
  
  try {
    let res = await fetch(`${url1}/listar`);
    //console.log("res");
    //console.log(res);

    if (!res.ok) {
      console.log("Error respuesta: " + res.ok);
      console.error(`status: ${res.status}, texto: ${res.statusText}`);
      //console.error(res);
      throw new Error(`status: ${res.status}, texto: ${res.statusText}`);
      //throw new Error(res);
    }
    let data = await res.json();
    console.log("data");
    console.log(data);
    //localStorage.setItem("jwt_todo", JSON.stringify(data.jwt));    
    console.log(JSON.stringify(data.jwt));
  } catch (error) {
    console.log("ERROR manejado desde catch");
    console.log(error);
  }
  return;
}      


async function buscarId() {
  let id = parseInt(ingresar("Ingrese id para buscar: "));  
  try {
    let res = await fetch(`${url1}/${id}`);
    //console.log("res");
    //console.log(res);

    if (!res.ok) {
      console.log("Error respuesta: " + res.ok);
      console.error(`status: ${res.status}, texto: ${res.statusText}`);
      //console.error(res);
      throw new Error(`status: ${res.status}, texto: ${res.statusText}`);
      //throw new Error(res);
    }
    let data = await res.json();
    console.log("data");
    console.log(data);
    //localStorage.setItem("jwt_todo", JSON.stringify(data.jwt));    
    console.log(JSON.stringify(data.jwt));
  } catch (error) {
    console.log("ERROR manejado desde catch");
    console.log(error);
  }
  return;
}

console.info("Ingrese b para buscar por Id, l para listar todos")
let tarea = (ingresar("Ingrese letra de tarea: "));
switch(tarea){
  case "b": buscarId();
  break;
  case "l": listar();
  break;
  default: return console.warn("Tarea No implementada");
}
