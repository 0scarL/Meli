Requerimientos
La app debería contar con tres pantallas:
1. Búsqueda de productos.
2. Visualización de resultados de la búsqueda.
3. Detalle de un producto.

Lo que es mandatorio:
● Si tu perfi l es Android, debes crear un proyecto desde cero en Android Studio usando como lenguaje Kotlin o Java.
Utilizar las APIs de Mercado Libre Developers que veas necesarias.
Tienes la libertad de elegir:
● Las libs open source o de terceros que veas necesarias.
● Diseño UI abierto a tu creatividad, ya sea con enfoque imperativo o declarativo.
● Arquitectura, patrones de arquitectura y patrones de diseño que veas más adecuados para el funcionamiento de la app.

Estos son algunos puntos que vamos a revisar:
● Elección de arquitectura y patrones.
● Guidelines ofi ciales de la plataforma.
● Cómo aseguras la calidad del proyecto (p. ej.: tests unitarios).
● Diseño óptimo de UI.
● Uso de la memoria (p. ej.: memory leaks).
● Legibilidad del código y documentación.
● Experiencia del usuario.
● Permisos del sistema operativo pedidos al usuario.
● Control de errores y feedback al usuario.
● Control de casos border.

Aspectos relevantes de la solución implementada

Se implementa solución del problema con una app desarrollada 100% kotlin utilizando clean arquitecture, con patron de arquitectura MVVM, se utilizan 3 capas UI,Dominio, Data
en la capa de UI la gestión de la vista la realiza viewModel controlando estados y actualizaciones atravez de observers con live data mediante estados definidos con Sealed clases, 
el viewModel a su ves implementa diferentes casos de uso para obtener y gestionar la data, estos casos de uso obtienen de los respositorios la información de el origen de datos 
orquestado por el repositorio segun el caso, se tienen consultas directas a la db, y al api asi como consultas que pueden venir de una u otra fuente de datos para ser mostrada y gestionada segun sea requerido, se tienen casos de uso donde el usuario por ejemplo se puede agregar productos favoritos los cuales van siendo marcados, para 
posteriormente permitir que sus detalles sean consultados en la db implementada con Room, este escenario de favoritos es utilizado para que cuando el usuario tenga fallos de conexión le permita
visualizarlos y navegar en sus detalles. 

Las dependencias de las diferentes capas estan inyectadas con dagger hilt, se inyecta desde modulos de Retrofit, Room, asi como sus interfaces y clases implementadoras,
permitiendo el desacoplamiento requerido para facilitar el el unit test, sobre este respecto se han desarrollado 
tambien test de varios de los casos de uso y viewModel

Se cuenta con un proyecto bien estructurado para Productos y detalle de productos
UI: 
  View 
  ViewModel 
  ModelosDatos

Domain:
  UseCases
  ModelosDatos

Data:
   Modelos
   Repositorios
   DataSources:
        Room
        API 
  
 utilizando inyeccion de dependencias en todas las capas, 
 comunicacion con la base de datos de room con Flow con dos tablas de Detalley productos
 comunicacion con API usando retrofit implementado con dagger hilt 
 comunicacion ui - viewmodel  a traves de estados con sealed clases y estos obervados con liveDatas. 
 tres fragmentos y una actividad. 
 implementacion de test unitarios con junit - mockk
