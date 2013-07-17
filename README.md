Proyecto Winter-Spring
============================

Este proyecto es la implementación de WINTER-SPRING, implementa una solicitud hecha al navegador mmediante solicitudes
(HTTP request) envia test de los metodos (GET, POST, PUT Y DELETE).

Entorno de desarrollo
------------------

El entorno de desarollo en el cual se implemento, fue el siguiente:

1. Spring Tool Suite version. 3.3.0 Release
2. Mongodb version. 2.4.6
3. Apache Tomcat version 7.0 Server
4. Java JDK 1.7.0_11
5. Rest Cliente plugin para chrome https://chrome.google.com/webstore/detail/hgmloofddffdnphfgcellkdfbfbjeloo

Instalación del proyecto Winter Spring
------------------

1.Descargar desde el repositorio de github el proyecto.
2.Importar el proyecto desde el repositorio local a Spring Tool Suite.
3.Iniciar el servicio de mongodb.
4.Ejecutar el proyecto.

Descripcion del Proyecto.
------------------

El patron de arquitecura de este proyecto es el de Modelo Vista Controlador(MVC), el modelo representado
por PersonasReposity, el controlador por PersonaController y la vista por requenrimientos no es necesario
implementarla ya que se puede visualizar en el navegador web.

la Base de datos NoSQL se identica como: personas
la Coleccion : ejemplodb
los Documentos tiene la siguiente estructura:

{
    "_id": ObjectId("4efa8d2b7d284dad101e4bc7"),
    "Nombres": "Juan Carlos",
    "Sexo": "Masculino",
}

Version:0.1

Soporte
-------
Para obtener informacion adicional envie un mensaje a:

oscarjairchacon@gmail.com



