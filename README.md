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
