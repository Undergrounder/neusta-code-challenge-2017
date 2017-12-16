#Wer Wo?
Anwendung um schneller herauszubekommen, wer wo sitzt und wie man Leute im Schuppen findet.

##Voraussetzungen
* Java 8
* Tomcat 8, wenn man die "War"-Datei deployen will.

##Subprojekte
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
  
## Tests ausführen
Die Anforderungen des Projektes werden mit Integrationtests getestet.  
Um Bugs zu vermeiden und die Entwicklung schneller zu machen, benutzten wir auch Unit-Tests.

```
gradlew test
```

## Deployment
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

## Versionierung
Wir benutzen [SemVer](https://semver.org/) für die Versionierung.

##Copyright
&copy; 2017 Team Neusta

## Authors
* Tello Lasheras, Adrian (a.lasheras@neusta.de)