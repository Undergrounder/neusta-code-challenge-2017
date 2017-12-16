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

Die Tests können so ausgeführt werden:

###Windows
```
gradlew test
```

###Unix / Linux / MacOs
```
./gradlew test
```

## Versionierung
Wir benutzten [SemVer](https://semver.org/) für die Versionierung.

##Copyright
&copy; 2017 Team Neusta

## Authors
* Tello Lasheras, Adrian (a.lasheras@neusta.de)