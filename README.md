# Taller 1 AREP

## Para hacer uso de la aplicación 💻

1. Clonación del repositorio, haciendo uso del comando:
```
   git clone https://github.com/gabriela-castro-s/Taller-1-AREP.git
```
2. Ejecución de la aplicación, sobre la carpeta donde se encuentran los archivos haciendo uso del comando:
```
   mvn clean package exec:java -D "exec.mainClass"="edu.eci.arep.HttpServer"
```
3. Visualización de la aplicación usando el enlace http://localhost:35000 desde un buscador web, se recomienda usar Mozilla Firefox.

4. Generación de JavaDoc con el comando:
```
    mvn javadoc:javadoc
```
## Ejecutando pruebas 🤓

Para correr los test, debemos entrar a la carpeta que contiene los archivos y correr el siguiente comando
```
    mvn test
```
## Requisitos 📋
- Java: Ambiente de desarrollo
- Maven: Administrador del ciclo de vida del Proyecto
- Git: Controlador de versiones

## Construido con 🛠️

IntelliJ IDEA 2022.3.2 (Community Edition)

## Autores ✒️

* **Gabriela Castro Santamaría** [gabriela-castro-s](https://github.com/gabriela-castro-s) 