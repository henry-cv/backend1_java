
document.addEventListener('DOMContentLoaded', () => {

    // const apiUrl = 'http://localhost:8080/pacientes/listar'; 

    const apiUrl = 'https://rickandmortyapi.com/api/character'; 
    const fetchData = async () => {
        try {
         
            const response = await fetch(apiUrl);
            if (!response.ok) {
                throw new Error('Error al obtener los datos');
            }
            const data = await response.json();
            console.log(data);
            

            renderTable(data);
        } catch (error) {
            console.error('Error:', error);
        }
    };


    const renderTable = (data) => {
        
        const usuarios = data.results
        const tableBody = document.querySelector('.table-pacients tbody');

        
        tableBody.innerHTML = '';


        usuarios.forEach(usuario => {
            
            const row = document.createElement('tr');
    
            row.innerHTML = `
                <td>${usuario.name}</td>
                <td>${usuario.apellido}</td>
                <td>${usuario.dni}</td>
                <td>${usuario.domicilio}</td>
                <td class= "action-btns">
                    <button class="btn  edit-btn" data-id="${usuario.id}">Editar</button>
                    <button class="btn  delete-btn" data-id="${usuario.id}">Borrar</button>
                </td>
            `;

  
            tableBody.appendChild(row);
        });

        addEventListeners();
    };


    const addEventListeners = () => {
        //editar
        const editButtons = document.querySelectorAll('.edit-btn');
        editButtons.forEach(button => {
            button.addEventListener('click', (e) => {
                const userId = e.target.getAttribute('data-id');
             
                window.location.href = `editarPaciente.html?id=${userId}`;
            });
        });

        //  borrar
        const deleteButtons = document.querySelectorAll('.delete-btn');
        deleteButtons.forEach(button => {
            button.addEventListener('click', (e) => {
                const userId = e.target.getAttribute('data-id');
                const confirmDelete = confirm('¿Estás seguro de que deseas borrar este usuario?');
                if (confirmDelete) {
                    deleteUser(userId);
                }
            });
        });
    };


    const deleteUser = async (id) => {
        try {
            console.log(id);
            
            const response = await fetch(`${apiUrl}/${id}`, {
                method: 'DELETE'
            });

            if (!response.ok) {
                alert('Error al borrar el usuario')
                throw new Error('Error al borrar el usuario');
            }

            fetchData();
        } catch (error) {
            console.error('Error:', error);
        }
    };

    fetchData();
});