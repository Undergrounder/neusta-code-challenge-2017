# Wer Wo?
Anwendung um schneller herauszubekommen, wer wo sitzt und wie man Leute im Schuppen findet.

[![Build Status](https://travis-ci.org/Undergrounder/neusta-code-challenge-2017.svg?branch=master)](https://travis-ci.org/Undergrounder/neusta-code-challenge-2017)

## Voraussetzungen
* Java 8
* Tomcat 8, wenn man die "War"-Datei deployen will.

## Subprojekte
```
                               /- fatjar
core <- facades <- frontend <-|
                               \- war
```
* core: Datenmodel und Services
* facades: Anwendungsfassade
* frontent: Web-Anwendung
* fatjar: Standalone-Anwendung. 
  * Kann direkt ausgeführt werden.
* war: "Web application Archive"-Anwendung
  * Kann in Tomcat 8 deployed werden.
  
## Tests und statische Code-Analysen ausführen
Die Anforderungen des Projektes werden mit Integrationtests (in fatjar) getestet.  
Um Bugs zu vermeiden und die Entwicklung schneller zu machen, benutzten wir auch Unit-Tests und statische Code-Analysen.

```
gradlew test jacocoTestReport
```

Die Reports können in folgenden Verzeichnissen gefunden werden:
* /core/build/reports
* /facades/build/reports
* /frontend/build/reports
* /fatjar/build/reports
* /war/build/reports


## Deployment
Das Datenbank wird sich beim Starten in dem Verzeichnis `~/roomsmanager` erstellen.
### Fat-Jar
Fat-Jar-Datei ausführen.
```
java -jar roomsmanager-fatjar-1.0-SNAPSHOT.jar
```

### War
War-Datei in Tomcat deployen.
https://tomcat.apache.org/tomcat-8.0-doc/html-manager-howto.html#Deploy

## Entwicklung
### Java Dokumentation
Zum Erzeugen der Dokumentation muss folgendes Befehl ausgeführt werden:
```
gradlew javadoc
```

Die generierte Java-Dokumentation kann in diesen Verzeichnissen gefunden werden:
* /core/build/docs/javadoc
* /facades/build/docs/javadoc
* /frontend/build/docs/javadoc
* /fatjar/build/docs/javadoc
* /war/build/docs/javadoc

### Bauen
```
gradlew build
```
Relevante Artifakte:
* /fatjar/build/libs/roomsmanager-fatjar-1.0-SNAPSHOT.jar
* /war/build/libs/roomsmanager-war-1.0-SNAPSHOT.war

### Bauen und ausführen
```
gradlew :roomsmanager-war:bootRun
```

## Versionierung
Wir benutzen [SemVer](https://semver.org/) für die Versionierung.

## Authors
* Tello Lasheras, Adrian (a.lasheras@neusta.de)