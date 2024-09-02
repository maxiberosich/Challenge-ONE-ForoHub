<h1 align="center"> ForoHub </h1>
<h2> Descripción </h2>
<p>En el siguiente desafio de Alura, desarrollare un foro, donde se puede crear usuarios y una vez que se loguee pueda postear mensajes. A su vez tambien se pueden dar respuestas a los distintos mensajes ya posteados</p>

## :hammer:Funcionalidades del proyecto

- `POST /login`: Para ingresar a la Api (Se debe enviar el nombre y contraseña, se busca en la DB y si esta dentro, devuelve el token de seguridad).
  
- `POST /topicos`: Crear Topico (Para crear un topico se debe enviar un titulo, un mensaje, el id del usuario y el id del curso. Luego se guardan estos datos en la DB).
- `GET /topicos`: Listado de Topicos (Se muestran todos los topicos guardados en la base de datos).
- `DELETE /topicos/idTopico`: Eliminar Topico (Se debe pasar el id del topico que se quiere eliminar. Solo es una eliminacion virtual, cambia de estado el topico).
- `GET /topicos/idTopico`: Mostrar un Topico particular(Se debe pasar el id del topico que se quiere ver).
- `PUT /topicos`: Modificar un Topico (Se debe enviar el id del topico que se quiere modificar, el titulo y el mensaje. Estos dos ultimos ya con las modificaciones).
  
- `POST /usuario`: Crear Usuario (Se debe enviar el nombre, email y contraseña).
- `PUT /usuario`: Actualizar Usuario (Se debe enviar el id del usuario que se desea modificar, nombre, email, contraseña).
  
- `POST /cursos`: Crear Curso (Se debe enviar el nombre y la categoria. El nombre no puede repetirse).
- `GET /cursos`: Listado Cursos (Se muestran todos los cursos guardados en la base de datos).
- `DELETE /cursos/idCurso`: Eliminar Curso (Se debe pasar el id del curso que se quiere eliminar. Solo es una eliminacion virtual, cambia de estado el curso).
- `GET /cursos/idCurso`: Mostrar un Curso particular(Se debe pasar el id del curso que se quiere ver).
- `PUT /cursos`: Modificar un Curso (Se debe enviar el id del curso que se quiere modificar, el nombre y la categoria. Estos dos ultimos ya con las modificaciones).
  
- `POST /respuestas`: Crear Respuesta (Se debe enviar el mensaje, el id del topico y el id del usuario).
- `DELETE /respuestas/idRespuesta`: Eliminar Respuesta (Se debe pasar el id del curso que se quiere eliminar. Es una eliminacion de la DB).
- `GET /respuestas/idRespuesta`: Mostrar una Respuesta particular(Se debe pasar el id de la respuesta que se quiere ver).
- `PUT /respuestas`: Modificar una Respuesta (Se debe enviar el id de la respuesta que se quiere modificar y el mensaje. Este ultimo ya con las modificaciones).
