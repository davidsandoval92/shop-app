# Shop app

## Resumen

Este proyecto consiste en dos microservicios desarrollados utilizando el framework Java Spring, Java 21 y docker:

1. **Microservicio del Dominio del Cliente**: Permite a los usuarios realizar operaciones CRUD en los datos de los clientes.
2. **Microservicio del Dominio de Pedidos**: Permite a los usuarios crear pedidos, recuperar pedidos y paginar eventos.

## Requerimientos

El proyecto cumple con los siguientes requisitos:

1. **API REST con Arquitectura Hexagonal**: El proyecto implementa una API REST con al menos tres controladores siguiendo una arquitectura hexagonal, adhiriéndose a los principios SOLID.

2. **Integración con Base de Datos**: La API interactúa con una base de datos que contiene al menos cuatro tablas, algunas de las cuales están relacionadas. Esta integración puede lograrse utilizando un ORM u otro método, asegurando el consumo fluido de métodos.

3. **Consulta de Conjunto de Datos Grande**: Uno de los endpoints implementados en los controladores (endpoint: http://localhost:8081/ms/order/v1/event/order/3?pageNumber=0&pageSize=5) consulta una tabla con al menos 2 millones de registros, admitiendo paginación para una recuperación eficiente de datos.

4. **Patrón de Repositorio Genérico**: El proyecto incorpora un patrón de repositorio genérico utilizando al menos uno de los dos ORMs comúnmente utilizados.

5. **Integración de Segunda API**: Se ha desarrollado una segunda API (order-api) utilizando Java Spring, que se comunica con la primera API (customer-api) para intercambiar información.

6. **Comunicación HTTP con Interruptor de Circuito**: La comunicación entre las APIs ocurre a través de HTTP con funcionalidad de interruptor de circuito para mejorar la confiabilidad y la tolerancia a fallos (endpoint: http://localhost:8080/ms/customer/v1/customer/1).

## Empezando

Para ejecutar los microservicios localmente, siga estos pasos:

1. Clone el repositorio: `git clone https://github.com/davidsandoval92/shop-app.git`
2. En la raiz del repositorio existe un docker compose.
3. Construya las imágenes de Docker: `docker-compose build`
4. Inicie los contenedores: `docker-compose up`
5. Es posible que la primer vez que utilice docker-compose up las apis presenten un error por la base de datos ya que es la primera vez que suben los servicios y los ms pueden ser mas rapidos que la db, por favor si estos ocurre volver a paso 3 y 4, despues de esto deberia funcionar con normalidad. (Fue probado en un windows 11 y un mac m1)
6. Acceda a las APIs utilizando los endpoints proporcionados.

## Uso

### Microservicio del Dominio del Cliente

#### Endpoints

## customer-api
- `GET http://localhost:8080/ms/customer/v1/customer/{customer-id}`: Consulta un cliente.
- `POST http://localhost:8080/ms/customer/v1/customer`: Crea un nuevo cliente.
- `DELETE http://localhost:8080/ms/customer/v1/customer/{customer-id}`: Elimina un cliente por ID.

- ## order-api
- `GET http://localhost:8081/ms/order/v1/order/customer/{customer-id}`: Consulta las ordenes por el ID de un cliente.
- `POST http://localhost:8081/ms/order/v1/order`: Crea un nueva orden.
- `GET http://localhost:8081/ms/order/v1/event/order/{order-id}?pageNumber=0&pageSize=5`: Obtiene todos los eventos paginados por el ID de la ordern.

- En el proyecto existe una carpeta llamada `postman` que contiene las colecciones de los endpoints antes mencionados.

## cosas que faltaron por implementar debido a tiempo:
- pruebas unitarias y de integracion
- implementacion de even driven with kafka

## Autor

### David Rodolfo Sandoval Penagos

