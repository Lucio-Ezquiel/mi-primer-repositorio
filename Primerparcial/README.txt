-Aplicación de Gestión de Tienda Electrónica
-Requisitos:

* Java Development Kit (JDK) 17 o superior
* Gradle
* H2 database version 2.3.232

-Estructura del Proyecto

El proyecto está organizado en las siguientes capas:

model: Contiene las clases de dominio (Producto, Categoria).
dao: Define las interfaces DAO (`GenericDao`, `ProductoDao`, `CategoriaDao`) y sus implementaciones (`ProductoDaoImpl`, `CategoriaDaoImpl`).
util: Incluye la clase `ConexionBaseDatos` para la gestión de la conexión a la base de datos H2.
main: Contiene la clase principal `AplicacionTienda` que orquesta la lógica del sistema.

-Cómo Ejecutar el Proyecto

Sigue estos pasos para compilar y ejecutar la aplicación:

1.  **Clonar el Repositorio:**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd nombre-del-repositorio
    ```

2.  **Compilar el Proyecto con Gradle:**
    Abre una terminal en la raíz del proyecto y ejecuta:
    ```bash
    ./gradlew build
    ```
    (En Windows, usa `gradlew.bat build`)

3.  **Ejecutar la Aplicación:**
    Después de una compilación exitosa, puedes ejecutar la aplicación con Gradle:
    ```bash
    ./gradlew run
    ```
    (En Windows, usa `gradlew.bat run`)

    Alternativamente, puedes ejecutar el JAR generado en el directorio `build/libs`:
    ```bash
    java -jar build/libs/nombre-del-proyecto-1.0-SNAPSHOT.jar
    ```
    (Reemplaza `nombre-del-proyecto` con el nombre de tu artefacto JAR si es diferente)

-Base de Datos H2

La base de datos H2 se configura en modo archivo y se creará automáticamente en un directorio `tienda_db` dentro de la raíz del proyecto la primera vez que se ejecute la aplicación. Las tablas `Categorias` y `Productos` también se crearán si no existen.

-Uso de Log4j2

La aplicación utiliza Log4j2 para el registro de eventos. Los logs se imprimirán en la consola y se guardarán en un archivo `logs/tienda.log` en la raíz del proyecto.

