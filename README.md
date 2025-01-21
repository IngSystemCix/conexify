# Conexify

Red social para conectar a personas con intereses en común.

## Descripción

Conexify es una red social que permite a los usuarios conectarse con otras personas que comparten sus mismos intereses. Los usuarios pueden crear publicaciones, comentar publicaciones, seguir a otros usuarios, enviar mensajes privados y mucho más.

## Autor

- [Juan Bladimir Romero Collazos](https://github.com/IngSystemCix)

## Tecnologías

- Java
- JavaServer Faces
- PrimeFaces
- Wildfly
- MariaDB
- Maven
- XHTML
- CSS
- JavaScript
- Jax-RS

## Instalación

1. Clonar el repositorio
2. Crear un archivo `.env` en la carpeta resources del proyecto con las siguientes variables de entorno:

```
GMAIL_EMAIL=
GMAIL_APP_PASS=
GMAIL_HOSTNAME=
GMAIL_SMTP_PORT=
```

3. Descagar Wildfly 35.0.0.Final desde [aquí](https://www.wildfly.org/downloads/)
4. Descomprimir el archivo descargado
5. Copiar la carpeta `wildfly-35.0.0.Final` en la unidad `C:`
6. Descargar el driver de MariaDB en Maven desde [aquí](https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client)
7. Copiar el archivo `.jar` descargado en la carpeta `modules\system\layers\base\org\mariadb\main` de Wildfly
8. Crear un archivo `module.xml` en la carpeta `modules\system\layers\base\org\mariadb\main` con el siguiente contenido:

> [!NOTE]
> Reemplazar `mariadb-java-client-3.x.x.jar` por el nombre del archivo `.jar` descargado
> Reemplazar `3.x.x` por la versión del driver descargado

```xml
<module xmlns="urn:jboss:module:1.9" name="org.mariadb">
    <resources>
        <resource-root path="mariadb-java-client-3.x.x.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```

9. Crear un archivo `standalone.xml` en la carpeta `standalone\configuration` de Wildfly con el siguiente contenido:

> [!NOTE]
> Reemplazar `${username}` por el nombre de usuario de la base de datos
> Reemplazar `${password}` por la contraseña de la base de datos

```xml
<datasource jndi-name="java:/ConexifyDS" pool-name="ConexifyDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:mariadb://localhost:3306/conexify</connection-url>
    <driver>org.mariadb</driver>
    <security>
        <user-name>${username}</user-name>
        <password>${password}</password>
    </security>
</datasource>
```

10. Iniciar Wildfly ejecutando el archivo `standalone.bat` en la carpeta `bin` de Wildfly
11. Ejecutar el comando `mvn clean install` en la carpeta raíz del proyecto
12. Copiar el archivo `conexify-ear/target/conexify.war` en la carpeta `standalone\deployments` de Wildfly
13. Acceder a la aplicación en `http://localhost:8080/conexify`
