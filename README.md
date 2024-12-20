# Documentación del Trabajo CRUD

## Enunciado de la Actividad

El objetivo de esta actividad era desarrollar un conjunto de operaciones CRUD (Create, Read, Update, Delete) sobre una base de datos utilizando Java y MariaDB. El trabajo incluía las siguientes funcionalidades:

1. Crear una base de datos, tablas y cargar datos iniciales.
2. Insertar registros en una tabla seleccionada.
3. Mostrar todos los registros de una tabla con funcionalidad de paginación.
4. Exportar los registros de una tabla a un archivo XML.
5. Consultar un registro por su clave primaria (ID).
6. Consultar registros utilizando un filtro `LIKE`.
7. Modificar al menos un campo de un registro en la tabla.
8. Eliminar un registro proporcionando su ID.

---

## Descripción del Trabajo Realizado

### Conexión a la Base de Datos

- Se estableció la conexión a la base de datos utilizando el controlador JDBC de MariaDB.
- Se utilizó un archivo de configuración `config.properties` para gestionar la URL, el nombre de usuario y la contraseña de la base de datos.
- Dado que el entorno de ejecución usa WSL y no permite el uso de `localhost`, la conexión se configuró utilizando la dirección IP `192.168.19.40`. Los datos en el archivo de configuración fueron los siguientes:

  ```properties
  db.url=jdbc:mariadb://192.168.19.40:3306/empresa
  db.username=root
  db.password=root
  ```

### Inicialización de la Base de Datos

- Se implementó una funcionalidad para leer un archivo SQL (ubicado en `src/main/resources/db_scripts/DB_Schema_HR.sql`) que contiene las instrucciones necesarias para crear la base de datos y las tablas requeridas.
- Este archivo incluye la creación de las tablas:
  - `departamentos`
  - `empleados`
  - `tascas`
  - `historico`
  - `jobs`
- El sistema lee el archivo línea por línea, eliminando comentarios y ejecutando las instrucciones SQL.

### Inserción de Registros

- Se desarrolló una funcionalidad que permite insertar registros en la tabla `tascas`.
- El usuario proporciona una descripción y el ID del departamento asociado, y el programa inserta el registro en la base de datos.

### Paginación de Registros

- La funcionalidad de listar registros permite mostrar los datos de la tabla `tascas` en bloques de 10 o en un límite personalizado por el usuario.
- Se implementó utilizando la instrucción `LIMIT` y `OFFSET` de MariaDB.

### Exportación a XML

- Se implementó una funcionalidad para exportar todos los registros de la tabla `tascas` a un archivo XML.
- El archivo resultante se guarda como `tascas.xml` en el directorio del proyecto.

### Consultas a la Base de Datos

1. **Consulta por ID:**
   - Permite buscar un registro específico de la tabla `tascas` proporcionando su ID.

2. **Consulta con Filtro `LIKE`:**
   - Permite buscar registros cuyo campo `descripcion` contenga un texto parcial especificado por el usuario.

### Modificación de Registros

- Se implementó una funcionalidad que permite modificar la descripción de un registro en la tabla `tascas` especificando su ID y la nueva descripción.

### Eliminación de Registros

- Se desarrolló una opción para eliminar un registro de la tabla `tascas` proporcionando su ID.

---

## Arquitectura del Proyecto

- **Estructura de Paquetes:**
  - `com.accesadades.jdbc`: Contiene las clases principales del proyecto.

- **Clases Principales:**
  - `GestioDBHR`: Gestiona la conexión a la base de datos.
  - `CRUDHR`: Implementa las operaciones CRUD.
  - `Main`: Proporciona un menú interactivo para ejecutar las distintas funcionalidades.
  - `Tascas`: Define la estructura de datos para la tabla `tascas`.

- **Dependencias:**
  - El proyecto utiliza Maven para la gestión de dependencias, incluyendo el driver de MariaDB.
  - Dependencia principal en el archivo `pom.xml`:
    ```xml
    <dependency>
        <groupId>org.mariadb.jdbc</groupId>
        <artifactId>mariadb-java-client</artifactId>
        <version>3.1.2</version>
    </dependency>
    ```

---

## Notas Finales

- El sistema funciona correctamente con la base de datos `empresa` configurada en el servidor MariaDB en la dirección `192.168.19.40`.
- Se ha verificado que todas las operaciones CRUD funcionan como se espera.
- El archivo `config.properties` permite modificar fácilmente la configuración de conexión.

---

