window.addEventListener("load", () => {

const apiUrl = 'http://localhost:8080/turnos'; 
const  apiPatients = 'http://localhost:8080/pacientes';
const apiDentist = 'http://localhost:8080/odontologos'

const formCreated = document.getElementById('form-created');

LoadDentist=()=>{



}

const fetchDataList = async (url) => {
    try {
     
        const response = await fetch(`${url}/listar`);
        if (!response.ok) {
            throw new Error('Error al obtener los datos');
        }
        const data = await response.json();
        let info = [] ;
        let typeTable = ""
        let typeBody = ""
        // console.log(data);
        data.forEach(item => {
      
            if (item.odontologoSalidaDto) {
                info.push(item) 
                typeTable = "turno"
                typeBody = ""
            }else if(item.matricula){
                info.push(item) 
                typeTable = "table-odont"
                typeBody = "tbody-odont"
            }else if (item.domicilioSalidaDto){
                info.push(item) 
                typeTable = "table-pacient"
                typeBody = "tbody-pacient"
            }
        });
        renderTable(info, typeTable, typeBody);
    } catch (error) {
        console.error('Error:', error);
    }
};

fetchDataList(apiPatients);
fetchDataList(apiDentist);


const renderTable = (info, typeTable, typeBody) => {
    const claves = Object.keys(info[0]);
    const table = document.querySelector(`.${typeTable}`);
    const tbody = table.querySelector(`.${typeBody}`);
    const thead = table.querySelector('thead .encabezado');


    thead.innerHTML = '';
    tbody.innerHTML = '';


    //encabezados
    // const thCheck = document.createElement('th');
    // thCheck.textContent = ' ';
    // thead.appendChild(thCheck);


    // claves.forEach(clave => {
    //     if (clave === "nombre" || clave === "apellido") {
    //         const th = document.createElement('th');
    //         th.textContent = clave.charAt(0).toUpperCase() + clave.slice(1);
    //         thead.appendChild(th);
    //     }
    // });



   
    info.forEach(item => {
        const tr = document.createElement('tr');

       //creacion del check box para cada tabla y que no permita elegir mas de uno
        const tdCheck = document.createElement('td');
        const check = document.createElement('input');
        check.type = 'checkbox';
        check.name = typeTable; 
        check.value = item.id;

        check.addEventListener('change', (event) => {
         
            const checkboxes = document.querySelectorAll(`input[name="${typeTable}"]`);
            checkboxes.forEach(box => {
                if (box !== event.target) {
                    box.checked = false;
                }
            });
        });

        tdCheck.appendChild(check);
        tr.appendChild(tdCheck);

     
        let nombreCompleto = ""; 

        claves.forEach(clave => {
            if (clave === "nombre") {
                nombreCompleto += item[clave];
            }
            if (clave === "apellido") {
                nombreCompleto += " " + item[clave]; 
            }
        });
        
  
  
        nombreCompleto = nombreCompleto.trim();
        
        const td = document.createElement('td');
        
        if (typeTable === "table-odont") {
            td.textContent = `Dr ${nombreCompleto}`;
        } else {
            td.textContent = nombreCompleto; 
        }
        

        tr.appendChild(td);
        tbody.appendChild(tr);

    });
};


formCreated.addEventListener('submit', async function(event) {
    event.preventDefault();

    const selectedOdont = document.querySelector('input[name="table-odont"]:checked');
    const selectedPacient = document.querySelector('input[name="table-pacient"]:checked');


    
    if(!selectedOdont && !selectedPacient ){
        alert("para poder reservar un turno debe escoger un paciente y un odontologo ")
    }else{
        
    const idOdontologo = selectedOdont.value;
    const idPaciente = selectedPacient.value;
    const fechaHora = new Date(new Date().setDate(new Date().getDate() + 1)).toISOString().slice(0, 19);

    console.log(fechaHora);
    

    const newShift ={
        
            idPaciente ,
           idOdontologo,
            fechaHora
           }

    


        try {
            const response = await fetch(`${apiUrl}/registrar`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },


                body: JSON.stringify(newShift)
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Turno registrado con éxito:', result);
                formCreated.reset()
            } else {
                console.error('Error al registrar el turno:', response.statusText);
            }
        } catch (error) {
            console.error('Error al realizar la solicitud:', error);
        }
 

    }




});





});