window.addEventListener("load", () => {

const apiUrl = ['http://localhost:8080/turnos', 'shift']; 
const  apiPatients = ['http://localhost:8080/pacientes','pacient'];
const apiDentist = ['http://localhost:8080/odontologos', 'dentist']


const formCreated = document.getElementById('form-created');



const fetchDataList = async ([Url, type]) => {

   
    try {
     
        const response = await fetch(`${Url}/listar`);
        if (!response.ok) {
            throw new Error('Error al obtener los datos');
        }
        const data = await response.json();
    

   
        const info = data.filter(item => item.odontologoSalidaDto || item.matricula || item.domicilioSalidaDto);

        if (info.length > 0) {
            const typeTable = `table-${type}`;
            const typeBody = `tbody-${type}`;

            renderTable(info, typeTable, typeBody);
        } else {
            console.log('No se encontraron datos relevantes.');
        }
    } catch (error) {
        console.error('Error:', error);
    }
};



const renderTable = (info, typeTable, typeBody) => {

    const table = document.querySelector(`.${typeTable}`);
    const tbody = table.querySelector(`.${typeBody}`);
    
    if (typeTable === "table-shift") {
   
        const thead = table.querySelector('thead');
   
            thead.innerHTML = '';
      
        const headerRow = document.createElement('tr');
        const headers = ["Turno N°", "Odontologo", "Paciente"];
        headers.forEach(headerText => {
            const th = document.createElement('th');
            th.textContent = headerText;
            headerRow.appendChild(th);
        });
        thead.appendChild(headerRow);

        tbody.innerHTML = '';

        info.forEach(item => {
            const tr = document.createElement('tr');

     
            const tdId = document.createElement('td');
            tdId.textContent = item.id;
            tr.appendChild(tdId);

            const tdOdontologo = document.createElement('td');
            if (item.odontologoSalidaDto) {
                const odontologo =`Dr ${item.odontologoSalidaDto.nombre} ${item.odontologoSalidaDto.apellido}` ; 
                tdOdontologo.textContent = odontologo 
            } 
            tr.appendChild(tdOdontologo);

            // Paciente columna
            const tdPaciente = document.createElement('td');
            if (item.pacienteSalidaDto) {
                const paciente = `${item.pacienteSalidaDto.nombre} ${item.pacienteSalidaDto.apellido}`
                tdPaciente.textContent = paciente ;
            } 
            tr.appendChild(tdPaciente);

            tbody.appendChild(tr);
        });

        console.log(info);
    
    } else{

        const claves = Object.keys(info[0]);


        tbody.innerHTML = '';

        info.forEach(item => {
            const tr = document.createElement('tr')


            
                
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
            
            if (typeTable === "table-dentist") {
                td.textContent = `Dr ${nombreCompleto}`;
            } else {
                td.textContent = nombreCompleto; 
            }
            

            tr.appendChild(td);
            tbody.appendChild(tr);

        });
    }

};


formCreated.addEventListener('submit', async function(event) {
    event.preventDefault();

    const selectedOdont = document.querySelector('input[name="table-dentist"]:checked');
    const selectedPacient = document.querySelector('input[name="table-pacient"]:checked');


    
    if(!selectedOdont && !selectedPacient ){
        alert("para poder reservar un turno debe escoger un paciente y un odontologo ")
    }else{        
            const idOdontologo = selectedOdont.value;
            const idPaciente = selectedPacient.value;
            const fechaHora = new Date(new Date().setDate(new Date().getDate() + 1)).toISOString().slice(0, 19);

            console.log(fechaHora);
            

            const newShift =
                {
                
                    idPaciente ,
                    idOdontologo,
                    fechaHora
                }

                try {
                    const response = await fetch(`${apiUrl[0]}/registrar`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(newShift)
                    });

                    if (response.ok) {
                        const result = await response.json();
                        fetchDataList(apiUrl);
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



fetchDataList(apiPatients);
fetchDataList(apiDentist);
fetchDataList(apiUrl);




});