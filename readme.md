# User Manual CarLook

### Inhaltsverzeichnis

[Motivation](#motivation)<br>
[Installation](#installation)<br>
[Hilfreiche Links](#hilfreiche-links)<br>


### Motivation
Die vorliegende Anwendung der Firma Carlook ermöglicht die Verwaltung von gebrauchten Autos. Konkret wurden hier zwei 
Use Cases (Anwendungsfälle) umgesetzt: die Eingabe eines neuen Autos sowie die Suche nach neuen Autos. Die Eingabe von 
neuen Autos ist nur priviligierten Benutzern ermöglicht (hier der User 'sascha'). Einfache User (hier der User 'ingo')
können hingegen "nur" nach Autos suchen. Beide Benutzer können sich auf der Startseite der Anwendung einloggen. 

Nach erfolgreichem Login können auf der personalisierten Startseite auf der linken Seite (Side Drawer) die
verschiedenen Tabs ausgewählt werden. Jeder Tab repräsentiert einen Anwendungsfall. Auf der horizontalen Navigationsleiste
wird der Name des Benutzers angezeigt. Zudem kann ein User sich ausloggen.

Die Anwendung wurde mit Hilfe der Frameworks [Vaadin 14](https://vaadin.com/) und [Spring Boot](https://spring.io/projects/spring-boot) entwickelt.
Für die Datenbankanbindung wurde der Standard JPA (Java Persistence API) verwendet, der wiederum auf JDBC aufbaut. Die Anwendung
kann als ein Framework für eigene Entwicklungen (z.B. SE-2 Semesterprojekt )angesehen und (gerne) verwendet werden.   

### Installation

Für die Installation und Ausführung der Anwendung muss zuvor eine gültige VPN-Verbindung zu unserer Hochschule 
hergestellt werden. Eine Anleitung dazu finden sie hier:

https://ux-2s18.inf.h-brs.de/faq/vpn

(Login ist notwendig, da sich die Seite auf unserem Intranet finden)

Der Download der Source erfolgt über GitHub:
https://github.com/aldaGit/carlook 

In IntelliJ kann aus dem Repository ein neues Projekt direkt erzeugt werden (File --> New --> Project from VersionControl --> Git).

Die erste Installation eines Projekts basierend Spring Boot kann aufgrund komplexer Initialisierungen etwas länger dauern.

Nach der Installation der Source Codes kann das Projekt gebaut werden. Dazu über das Maven-Menu auf der rechten Seite
von IntelliJ im Reiter "Maven Projects" das Plugin "spring-boot_run" per Doppelklick wählen. Die Anwendung wird zunächst kompililiert und 
dann innerhalb des Build-In-Server von Spring Boot (hier: Tomcat) gestartet.

Die Anwendung kann dann unter: <br>
[http://localhost:8080/](http://localhost:8080/)

abgerufen werden.

Die Login-Daten der Benutzer:
User 'Sascha' (Priviligierter User):
- Login: sascha
- Password: abc

User 'Ingo' (Einfacher User):
- Login: ingo
- Password: abc
 

### Hilfreiche Links
For further reference, please consider the following sections:

@Table( name ="user" , schema = "carlook" )

Überblick über CSS

https://css-tricks.com/snippets/css/a-guide-to-flexbox/

https://www.baeldung.com/spring-data-jpa-projections

https://stackoverflow.com/questions/20735205/launching-spring-application-address-already-in-use

https://nanofaroque.medium.com/nullpointerexception-resolution-in-spring-autowiring-9db09aefcab

https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

Erklärung der Werte für DDL-Erzeugung:

https://www.baeldung.com/spring-boot-data-sql-and-schema-sql

https://vaadin.com/docs/v14/flow/routing/tutorial-router-layout.html


* [Creating CRUD UI with Vaadin](https://spring.io/guides/gs/crud-with-vaadin/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)

