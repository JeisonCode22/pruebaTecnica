# Prueba Tecnica
## Requisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

- **Java 17 o superior**
- **MariaDB**
- **Maven**
- **CMD para ejecutar el archivo .jar**

## Configuración de la base de datos
Para configurar la base de datos basta con tomar el archivo que esta en src/main/resources/DDL.sql ejecutarlo 
y ya con esto quedaría lista nuestra BD para levantar el proyecto

## Ejecutar el proyecto
Abrir la cmd y ejecutar el comando
- **mvn clean package**
#### esto nos generará un JAR y se creará en el directorio /target dentro del proyecto
#### una vez tengamos el jar generado ejecutamos el comando 
- **java -jar nombreDelJarGenerado.jar**

## Probar en postman
la coleccion de postman para este proyecto está dentro del directorio de resources (src/main/resources)
debemos cambiar el host del server en la nube por localhost

## Good Luck :D
