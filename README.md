# Sistema de Gestion Hospitalaria - SGH
Los datos recogidos en las diversas áreas del hospital, cuando relacionados se transforman en instrumentos útiles para la evaluación de la asistencia prestada, cantidad y tipo de recursos involucrados, control de costos generados en la producción de los servicios e índice de solución de los problemas. 

La gestión hospitalaria está vinculada a la alta complejidad, con una infinidad de  reglamentaciones que limita cambios en el sector, costos crecientes y la formación de un paciente cada vez más exigente. En ese escenario, las organizaciones de salud que consiguen desarrollar la habilidad de medir su desempeño y cambiar su trayectoria de acuerdo con los cambios en el ambiente, en tiempo real, generalmente tienen más eficiencia y quedan un paso adelante.

Lo mejor del sistema de gestión hospitalaria es que se adapta a las necesidades y formas de trabajo de cada especialidad clínica hospitalaria, potenciando su flexibilidad y permitiendo compartir información por distintos especialistas.

## Comenzando 🚀
Puedes obtener el proyecto de dos formas
```
	•  Git clone https://github.com/AndresDFX/SGH-DIU.git
	•  En la parte superior del proyecto “Download ZIP”
```

### Pre-requisitos 📋
•	Sistema operativo Windows, Linux  o MAC

•	Servidor de base de datos PostgreSQL 9.0.3 o superior

•	IDE NetBeans 8.1 o superior

•	Máquina virtual Java JDK 1.8 para compilar

### Instalación 🔧

1.	Debemos importar el proyecto Netbeans o cargar las clases en su IDE de preferencia

2.	En net beans debemos resolver las dependencias de las librerías dando clic en “Resolve Problems Project”

	![Alt text](/screenshots/resolveproblems_netbeans.png)
		
3.	Seleccionamos una a una las librerías de la carpeta “libraries”
	
	![Alt text](/screenshots/listlibrary_netbeans.png)

4.	Por ultimo ejecutamos “Clean and Build”

	![Alt text](/screenshots/build_netbeans.png)

5.	Para cargar la base de datos podemos realizar un restore o ejecutar el script SQL ambas convenciones se encuentran en la carpeta “db” del proyecto

## Deployment 📦
1.	Para ingresar al Rol del admin las credenciales son admin:admin
2.	Verificar que el nombre de la base de datos que cargamos sea igual en el gestor y en la fachada.

## Construido con 🛠️

* [Netbeans](https://netbeans.org/) - IDE
* [Java Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) – Biblioteca Grafica
* [PostgreSQL](https://www.postgresql.org/) – Gestor de BD

## Contribuyendo 🖇️
Por favor lee el [CONTRIBUTING.md](https://gist.github.com/AndresDFX/HSM) para detalles de nuestro código de conducta, y el proceso para enviarnos pull requests.

## Versionado 📌
Usamos [Git](https://git-scm.com/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/AndresDFX/HSM/tags).

## Autores ✒️

* **Andres Castaño** - *Analisis y Desarrollo* - [AndresDFX](https://github.com/AndresDFX)
* **Jesus Cuesta** - *Analisis y Desarrollo*

También puedes mirar la lista de todos los [contribuyentes](https://github.com/AndresDFX/HSM/contributors) quíenes han participado en este proyecto. 

## Licencia 📄

Este proyecto está bajo la Licencia GNU General Public License 3.0 - mira el archivo [LICENSE.md](LICENSE.md) para detalles
