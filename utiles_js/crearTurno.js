//Para crear un nuevo Odontólogo
const url = "http://localhost:8080/turnos";

let idPaciente = 3;
let idOdontologo = 3;
let fechaHora = "2024-09-06T20:00:00";

async function crearTurno() {
  const turno = {
    idPaciente:3,
    idOdontologo:3,
  };
  const settings = {
    method: "POST",
    body: JSON.stringify(turno),
    headers: {
      "Content-Type": "application/json",
    },
  };
  try {
    let res = await fetch(`${url}/registrar`, settings);
    if (!res.ok) {
//      console.log(res);
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
crearTurno();
