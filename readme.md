# User Manual HelloCar

### Inhaltsverzeichnis

[Motivation](#motivation)<br>
[Installation](#installation)<br>
[Tipps und Tricks](#tipps-und-tricks)<br>
[Hilfreiche Links](#hilfreiche-links)<br>
[Video-Tutorien](#links-zu-video-tutorien)<br>


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

### Tipps und Tricks 
Die Java 11 JDK kann [hier](https://www.oracle.com/de/java/technologies/javase-jdk11-downloads.html) als .zip heruntergeladen werden (u.U. muss ein kostenloser Oracle-Account erstellt werden). JDK entpacken, an gewünschtem Ort ablegen und in Intellij unter File>Project Structure auf den Button 'New' klicken und auf '+JDK'. Dann den entpackten JDK-11-Ordner auswählen. 
<br>
Wenn spring-boot:run fehlschlägt/ die Anwendung unter localhost:8080 nicht zu erreichen ist: 
  * Fehler anzeigen: Im Run-Tab am unteren Rand der Anwendung, im linken Bereich auf den vorletzten Eintrag im Menü-Baum mit den roten Ausrufezeichen auf "run" klicken
  * Mögliche Fehler:
    * Die Datenbank-Verbindung zur PostgreSQL schlägt fehl. Darauf hinweisende Fehler (evtl. etwas hochscrollen/suchen) wären z.B.: ``org.postgresql.util.PSQLException: Der  Verbindungsversuch schlug fehl. at org.postgresql.core.v3.ConnectionFactoryImpl.openConnectionImpl(ConnectionFactoryImpl.java:315) ~[postgresql-42.2.18.jar:42.2.18]``, ``j.LocalContainerEntityManagerFactoryBean : Failed to initialize JPA EntityManagerFactory: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]``
  * Mögliche Lösungen: 
    * Ins VPN einwählen, gucken, dass man das aktuelle Zertifikat (seit März neu) hat
    * In den Sicherheitseinstellungen des Betriebsystems gucken, ob IntelliJ bzw. Java11 die  Rechte haben, durch die Firewall nach außen zu kommunizieren und evtl. ändern

### Hilfreiche Links
Diese Links soll eine Unterstützung bieten bei der prototypischen Umsetzung des Semesterprojekts mit den Frameworks
Vaadin und Spring Boot sowie mit Spring / JPA. Diese Links werden sporadisch erweitert, es lohnt sich also, dieses
Readme regelmäßig zu aktualisieren (git pull). 


#### Überblick über CSS
Das Thema CSS wird in der Vorlesung nur am Rande eingeführt. Dennoch sollten die Teams sich bemühen, ihre Software
mit CSS entsprechend visuell aufzubearbeiten.

Hier ein Link guter zur Erläuterung der Flex-Box-Technologie, die verwendet wird, um Elemente in einem HTML-Dokument
visuelle anzuordnen.
https://css-tricks.com/snippets/css/a-guide-to-flexbox/

#### JPA (Java Persitence API)
In dieser Anwendung wird der Standard JPA verwendet, um von einer objektorientierten Anwendung auf eine relationalen 
Datenbank (hier: eine Datenbank aus dem DBMS PostgreSQL) zuzugreifen. JPA stellt die Verbindung zur Datenbank her
und führt nach den Queries und Inserts (etc.) das notwendige Object-Relational-Mapping (ORM) durch. Hier eine erste
Einführung:<br>
https://spring.io/guides/gs/accessing-data-jpa/ 

Ein grundlegendes in SQL ist die Projection von Relation über den Befehl SELECT. In JPA wird dies über DTOs geregelt, wobei
die Attribute in einem DTO die Projectionsattribute darstellen. Eine gute Einführung gibt es hier: 
https://www.baeldung.com/spring-data-jpa-projections

Auch wenn mit den Standard-Queries von JPA viele Dinge bereits abgefagt werden können, so sind für einige Szenarien
oftmals komplexere Queries notwendig. Diese Queries funktionieren in JPA ähnlich wie in SQL, jedoch bauen diese
dann im FROM-Teil auf Klassen und nicht auf Relationen / Tabellen auf. Eine gute Einführung in die Thematik findet sich hier:
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

In JPA kann ein Schema aus einer Datenbank mit einem objektorientierten Entity-Schema abgeglichen werden. Die
Semantik dieses Abgleichs kann man in der Datei 'application.properties' unter der Variable 'spring.jpa.hibernate.ddl-auto'
setzen. Die verschiedenen Optionen werden hier gut beschrieben:
https://www.baeldung.com/spring-boot-data-sql-and-schema-sql

In dem Framework ist diese Variable auf 'none' gesetzt. Bei der Bereitstellung einer neuen Datenbank kann durch Zuweisung
der Varibale auf den Wert 'create' das objektorientierte Schema unmittelbar in die Datenbank kopiert werden. 

#### Spring Dependency Injection
Die Verbindung von Objekten mit der Annotation @Autowired wirkt bequem, es gibt aber einige Dinge, die man beachten muss. 
Hier eine gute Zusammenfassung mit einigen Fallstricken: 
https://nanofaroque.medium.com/nullpointerexception-resolution-in-spring-autowiring-9db09aefcab

#### Vaadin
Eine gute Einführung in Vaadin bietet die [offizielle Dokumentation von Vaadin](https://vaadin.com/). In der Vorlesung wird Vaadin 14 
verwendet. Ein Überblick über die aktuellen Versionen gibt es hier:<br>
https://vaadin.com/start#tech-stack-examples

Ein gutes Tutorial mit den grundlegenden Features von Vaadin (Flow) 14:<br>
https://vaadin.com/docs/v14/flow/overview

Hier ein guter Überblick über die UI-Components, die man verwenden kann:<br>
https://vaadin.com/components

Vaadin bietet über die Technologie "Flow" die Möglichkeit an, eine Web-Anwendung rein in der Sprache Java zu entwickeln.
Diese Variante wird in der Vorlesung SE-2 (BWI) eingeführt und verwendet. Ein guter Überblick:<br>
https://vaadin.com/flow

Die kommerziellen Produkte zu Vaadin (u.a. den [Vaadin Designer](https://vaadin.com/designer), [Pro Components](https://vaadin.com/components/vaadin-board)) erhalten sie über eine Lizenz, die allerdin
sehr teuer ist. Als Student erhalten sie jedoch einen freien Zugang, den es sich lohnt anzuschauen:<br>
https://vaadin.com/student-program

In der offiziellen Abgabe des Semesterprojekt sind allerdings nur die freien UI-Components zu verwenden! 

Der Web-Designer bietet die Möglichkeit, eine Web-Anwendung flexibel in einem Browser zusammenzubauen und zu konfigurieren. 
Im Anschluss kann die Anwendung als "Rumpf" heruntergeladen werden:<br>
https://start.vaadin.com/

Der Router-Mechanismus wird verwendet, um zwischen diversen Views zu navigieren. Eine Einleitung dazu gibt es hier:<br>
https://vaadin.com/docs/v14/flow/routing/tutorial-router-layout.html

Bei der Ausführung bzw. Start eines Tomcat-Servers kann es passieren, dass der Netzwerk-Port schon belegt ist mit einer
laufnenden Instanz. Dann sollte man diese Instanz löschen, eine Anleitung gibt es hier:<br>
https://stackoverflow.com/questions/20735205/launching-spring-application-address-already-in-use

Sie brauchen einen Dialog z.B. zur Bestätigung einer Aktion über Buttons? Dann schauen sie sich mal Dialogs an:<br>
https://vaadin.com/components/vaadin-dialog 


#### PostgreSQL
Eine Übersicht über die implementierten SQL-Befehle bei PostgreSQL gibt es hier:<br>
https://www.postgresql.org/docs/12/sql-commands.html

Ein gutes Web-Tool zur Erstellung von Tabellen, Schemas usw. ist das Tool PhpPGAdmin. Dieses ist bei uns auch 
betrieben, jedes Team bekommt dazu einen eigenen Zugriff (Login, Password). Dazu bitte eine E-Mail schreiben an mich 
(sascha.alda@h-brs.de). Link dazu:<br>
https://dumbo.inf.h-brs.de/phppgadmin/

Hinweis: der Zugriff auf die Datenbank ist nur mit einer gültigen VPN-Verbindung möglich!

#### Links zu Video-Tutorien
Eine ausführliche Einleitung in diese Anwendung erfolgt in einem Video-Tutorium, welches auf YouTube abrufbar ist:<br>

[1. Teil Vaadin-Tutorial](https://www.youtube.com/watch?v=XnvjqxhCxA0)
<br>
(Installation der Anwendung aus GitHub; Demo der Anwendung; erste Einführung Maven Goals und Plugins)

[2. Teil Vaadin-Tutorial](https://www.youtube.com/watch?v=-Zev_pxh_po)
<br>
(Vorstellung Software-Architektur (Java und Modell, Layer Architecture); Einführung in Vaadin, LoginForm, Code-Walkthrough (LoginView, LoginControl, DAOs, JDBCController); Einführung JDBC; Einführung PostgreSQL (Installation auf Dumbo); Tool PhpPGAdmin)

[3. Teil Vaadin-Tutorial](https://www.youtube.com/watch?v=j1_6cW-EckY)
<br>
(Einführung Spring JPA ; Entities und Tabellen; CRUD-Operationen; komplexe Queries; Präsentation JUnit-Test, Round-Trip-Test; Konfiguration JPA; Anpassung LoginControl, Demo mit JPA)

[4. Teil Vaadin-Tutorial](https://www.youtube.com/watch?v=6FChU4IrUO8)
<br>
(Vertiefung JPA; Entwicklung von Entities über IntelliJ (Persistence-View); Abbildungen von Spalten auf Attribute; Abbildung von n:m-Beziehungen; Einrichtung einer als Datenbank als DataSource in IntelliJ)

[5. Teil Vaadin-Tutorial](https://www.youtube.com/watch?v=Y_s7bu1V3DU)
<br>
(Einführung in Maven (Default Build Lifecycle, Phasen, Goals, Plugins), POM-File; Konfiguration in IntelliJ)

[6. Teil Vaadin-Tutorial](https://www.youtube.com/watch?v=kJ1tEK69CFA)
<br>
Einführung in das App Layout, Erklärung der View-Klasse App-Layout, Vaadin-Lifecycle 

[7. Teil Vaadin-Tutorial](https://www.youtube.com/watch?v=zZQdZgjJZI8)
<br>
Erläuterung der Views zur Eingabe und Anzeige von Autos (FormLayout, Grid, Textfelder, Ereignis-Behandlung von Buttons, JPA (Speichern und Auslesen von Autos))
 