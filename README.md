# Conexify

<img src="https://lh3.googleusercontent.com/fife/ALs6j_E7SV82Tmqjp8MPO5NSVe0cMl8qdJTbqv47ZbcgY9bIHD4CRpW9fOslbka3ePwEPn0CRtOVyM8yYddWpyNldSIuJPRHo8jrVIuoi8fy-_7CKTY8yUdPNIJt-UTGbf_kDQ7uXrKaPUHeEDPTKa-RSeA7WdKfX3UQWHTmcVnvRT3aiaMJq_cYeDf3ajniu3CvjR1C2QF6BoZmff27TiMBfI_50KGI6mwDzo-CeMbi62pl3hpNelVjKGeGKpZDX9i82k-cjEcUKAEVkgl9u1EQP6jsJvtkiqtMD5NutqpMi4uLugNEusZIdJWCTUCD6rPJFOKf-WvGnHQd0n4NjAvwZmqyPCHuI5AJopAH4dD_WsalPodurPttTclt8tQsS9vWGr5QThlYC8zXgRjJeElYSYTBDu1oNCAVpBqczH6VMhw_M4kqTjtdFg8a0uO7JvzuXbG_ko1ezyCUVa0G3bBGGlMJcz3TCe2A-2M_MTO62KZ0PvpLaiCPY8a4JHAA9psR8bKSJSBeeOhBGYZ_5vwwBtCVUy7IcUB2Z4O43S_toqMVhatC20K5gtwV2slXD98beaKTZi_AkDawzPRR9TYwdb9HWRYcyEapg_vfG4kmzG7B9Yj4QDg4WPLXP9fXNv3WfndCY_STXHFjAHofhfzlv6zTqA5XJlPweL0q_p9zsGCxZzMQbdjnDIVns5QUXxBLJzGLJh4vQhJ04a3SF7zKFDU_nxrP_onQswB-_ZM1EuDJ_cbH1UPKesjEpNofag3NlqSrGSQuHNSokBzcJ8m1ON5achVZpqBMb7RFPzjYu6jeCrTaX40tAoeID5JVUxR3LepKfKxlV_0TAX1N7zciju739l4_NR81jlNdZuElOaXtqUmW6IopPIvdnl67-BLvhQ_QibAAepiE_k_8q2MHfkcT2L-CwmjI0bgFXsNwf2sf1X2HuhlOGLIY_WuSzuwWA9JIyajWTCBbASyRlpFQ2R8cnNkg271RDiH23uifGpieqmfWdsDMuCzwgkTYF1r0gA3CXp9RtLLD9yKyUpkqfGdnAs42KFghwbCKvk_FIv1fMk1egx2ABHUpe5fvRktYtxRVptZjYVdKoGRnV40RqkG3ROBq3kUn9Uv4CqJLOzYCj3OlnGvS0g6hjMWLmGpqEjAbhPuw464_WRShyUx1MK5nnXapRHecWzGpu0aIiJ4hiV1VZVIZ1ImzzqR0glxGwNWNOkS2YYal7yWR9LQQEFQRmSXeoQbhQ4NxCzZWlV8JXX0nNhk_iQ3piefdGCjfEIUn4ris9v6T5qEyrrltuz_zC3UNrn-_mFv9CRSiSegh1vohMFJUdAgkHhkWPxfepSuCYerCL5cAdv4XJdmzEbWtPMrA2pTq8qAEQZGY1HYgiets-f7gKkF8fHvPtkpCjx5DN5_u6pJqlfWGumGLESnuk_bmOQcbgo2UTAa_b-LzYIjNiC7sFcss1djhddga3kUZKy_fmGsabi28Gng1YiYcm-R8cpn0MFJOfaJX79CwysWTaG_TiAs-cWIXuvbhXCexThclcGzqpnMsE6LIaCmez3vMv3IAS0T5HAWGmw9yA9U0accGhMT4P6IN4ZnRWq0F_LuF9AVN-794kcmGRA=w1920-h947" alt="banner conexify" />

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
