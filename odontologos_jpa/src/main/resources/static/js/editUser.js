document.addEventListener('DOMContentLoaded', () => {

 
    const getUserIdFromUrl = () => {
        const urlParams = new URLSearchParams(window.location.search);
        const userId = urlParams.get('id'); 
        if (!userId) {
            alert('No se pudo obtener el ID del usuario.'); 
        }
        return userId; 
    };

 
    const fetchData = async () => {
        try {
            const userId = getUserIdFromUrl();
            if (!userId) return;

            const apiUrl = `https://rickandmortyapi.com/api/character/${userId}`;
            const response = await fetch(apiUrl);
            if (!response.ok) {
                throw new Error('Error al obtener los datos');
            }
            const data = await response.json();
            console.log(data);
            chargeUserData(data); 
            
        } catch (error) {
            console.error('Error:', error);
        }
    };


    const chargeUserData = (user) => {
        console.log(user);
        document.getElementById('nombre').value = user.name; 
   I
    };


    fetchData();


    //cuando de click en actualizar
    const updateUserData = async (event) => {
        event.preventDefault(); 

        const userId = getUserIdFromUrl(); 

        if (!userId) {
            alert('No se pudo obtener el ID del usuario.');
            return;
        }

        const apiUrl = `https://rickandmortyapi.com/api/character/${userId}`;

        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const dni = document.getElementById('dni').value;
        const domicilio = document.getElementById('domicilio').value;

        const updatedUser = {
            nombre,
            apellido,
            dni,
            domicilio
        };

        try {
            const response = await fetch(apiUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedUser) 
            });

            if (!response.ok) {
                throw new Error('Error al actualizar los datos del usuario'); 
            }

            alert('Usuario actualizado correctamente');

            const goBack = confirm('Usuario actualizado correctamente. ¿Deseas volver a la página principal (lista de pacientes)?');

            if (goBack) {
                window.location.href = './pacientes.html';
            } else {
                alert('Puedes seguir editando los datos del usuario.');
            }

        } catch (error) {
            console.error('Error:', error); 
        }
    };
    updateUserData();
    document.getElementById('editUserForm').addEventListener('submit', updateUserData);
}); 
