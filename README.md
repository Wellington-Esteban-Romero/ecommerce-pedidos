# 🛒 API de Gestión de Productos y Pedidos

Este proyecto es una API REST construida con **Spring Boot 3**, **Maven** y **Lombok**, que permite gestionar productos, procesar pedidos de múltiples clientes en paralelo y registrar ventas en un historial. Se enfoca en la **concurrencia segura**, validación de stock y generación de reportes automáticos.

## 🚀 Características principales
- **Gestión de productos** con nombre, precio y stock.
- **Acceso concurrente al stock**, evitando condiciones de carrera.
- **Procesamiento de pedidos en paralelo**, asegurando stock suficiente.
- **Validación automática** para evitar ventas sin stock suficiente.
- **Registro de ventas** en un historial.
- **Reportes automáticos**:
    - Cada **10 segundos**: Resumen del stock (productos agotados, stock bajo y stock alto).
    - Cada **30 segundos**: Resumen de ingresos y productos más vendidos.

---

## 📌 Datos de prueba predefinidos
Al iniciar la aplicación, se cargarán los siguientes productos de prueba en el sistema:

| Producto            | Precio | Stock |
|---------------------|--------|-------|
| iMac               | 1500   | 10    |
| Nintendo Switch    | 300    | 5     |
| Monitor ASUS       | 400    | 20    |

Estos productos estarán disponibles en la API sin necesidad de ser creados manualmente.

---

## 📌 Requisitos previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:

1. **Java 17 o superior**
    - Verifica tu versión con:
      ```sh
      java -version
      ```
    - Si no lo tienes, descárgalo desde [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) o usa [Adoptium](https://adoptium.net/).

2. **Apache Maven**
    - Verifica la instalación con:
      ```sh
      mvn -version
      ```
    - Descárgalo desde [Maven](https://maven.apache.org/download.cgi) si no lo tienes.

3. **Spring Boot** (se instala automáticamente con Maven).

4. **Lombok**
    - Si usas **IntelliJ IDEA**, instala el plugin desde *File > Settings > Plugins*.
    - Si usas **Eclipse**, instala [Lombok](https://projectlombok.org/setup/eclipse).

---

## 💻 Instalación y ejecución

### 1️⃣ Clonar el repositorio
Si el código está en GitHub o GitLab, clónalo con:
```sh
git clone https://github.com/Wellington-Esteban-Romero/ecommerce-pedidos.git
cd proyecto-springboot
```

### 2️⃣ Compilar el proyecto
Ejecuta el siguiente comando en la raíz del proyecto para descargar dependencias y compilar el código:
```sh
mvn clean install
```

### 3️⃣ Ejecutar la aplicación
Para iniciar la API, usa:
```sh
mvn spring-boot:run
```
O si ya tienes el `.jar` generado:
```sh
java -jar target/nombre-del-jar.jar
```

---

## 🌍 Endpoints disponibles
Puedes probar la API con **Postman**, `curl` o cualquier cliente HTTP.

### 📌 Agregar un producto
```sh
curl -X POST http://localhost:8080/api/producto \
     -H "Content-Type: application/json" \
     -d '{"nombre":"HP OMEN","precio":1340,"stock":10}'
```
### 📌 Listar productos
```sh
curl -X GET http://localhost:8080/api/productos
```

### 📌 Realizar un pedido
```sh
curl -X POST http://localhost:8080/api/pedido \
     -H "Content-Type: application/json" \
     -d '{"productos":{"iMac":2, "HP OMEN":5}}'
```

---

## 📜 Ejemplo de logs esperados
Si todo funciona correctamente, la consola mostrará:
```
Estado del stock:
Nintendo Switch - STOCK BAJO
HP OMEN - STOCK BAJO
iMac - STOCK ALTO
Monitor ASUS - STOCK ALTO
Estado del stock:
Nintendo Switch - STOCK BAJO
HP OMEN - STOCK BAJO
iMac - STOCK ALTO
Monitor ASUS - STOCK ALTO
Ingresos: 9700.0 $ . Productos más vendidos: {HP OMEN=5, iMac=2}
```

---

## 🛠 Tecnologías utilizadas
- **Java 21**
- **Spring Boot 3**
- **Maven**
- **Lombok**
- **ConcurrentHashMap** para gestión segura del stock

---

## 📄 Licencia
Este proyecto está bajo la licencia MIT. Puedes usarlo, modificarlo y distribuirlo libremente.

---

## ✨ Contribuciones
¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto, sigue estos pasos:
1. Haz un **fork** del repositorio.
2. Crea una nueva rama con una característica o mejora.
3. Envía un **pull request**.

---

## 📩 Contacto
Si tienes dudas o sugerencias, contáctame en **correo@ejemplo.com**.

---

🚀 ¡Gracias por usar esta API! 🎯

