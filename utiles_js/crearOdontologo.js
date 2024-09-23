//Para crear un nuevo Odontólogo
const url = "http://localhost:8080/odontologos";

let matricula = "jdbc-001";
let nombre = "Steve";
let apellido = "Spencer";

async function crearOdontologo() {
  const odontologo = {
    matricula,
    nombre,
    apellido,
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
      console.log(res);
      console.error(res.status);
      throw new Error(res);
    }
    console.log("res exitosa");
    console.log(res);
    let data = await res.json();
    console.log("data");
    console.log(data);
    console.log(`Estado: ${res.status}, Mensaje: ${res.statusText}`);
    renderizarPublicacion(data);
  } catch (error) {
    console.error(error.status);
  }
}
crearOdontologo();
