#Descripcion:

Una empresa de salud muy importante nos encomienda la tarea de fabricar el software para la gestión
de turnos de sus consultorios médicos. Para ello la aplicación necesita poder cargar en el sistema las
especialidades que se atenderán (para la primera etapa del producto se cargarán clínica médica,
dermatología, pediatría y cardiología) y cuatro profesionales como mínimo para cada una de ellas. Otro
dato a tener en cuenta es edilicio, ya que la clínica cuenta con tres consultorios (los cuales se llamarán
“1”, “2” y “3”). También se cargarán los datos del paciente y la gestión de turnos. Para ello hay que
tener en cuenta que la clínica atenderá de 8 a 23hs de lunes a sábados. Fuera de ese horario no se
podrán otorgar turnos, ni tampoco si en un horario determinado el profesional no está disponible.

#Instrucciones:

1- Crear la bd con el script "DB".

2- Ir a src/main/resources/application.properties y cambiar las configuraciones de username y password.

3- Levantar la aplicacion, hibernate se encargara de la creacion de tablas y sus relaciones.

4- Como opcional deje un script llamado "datos" el cual carga profesionales, especialidades, edilicios y pacientes, asi no se realiza toda la carga desde el sistema.

5- Levantar interfaz grafica angular: https://github.com/juanpi8345/pruebaTecnica-frontend
