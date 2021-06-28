# ChemicalTagger-webapp Overview
![Build Status](https://github.com/BlueObelisk/chemicalTagger-webapp/workflows/Java%20CI%20with%20Maven/badge.svg) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/uk.ac.cam.ch.wwmm/chemicalTagger-webapp/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.ac.cam.ch.wwmm/chemicalTagger-webapp)


This is the webapp component of the [ChemicalTagger](https://github.com/BlueObelisk/chemicaltagger) tool and is the web based demo running [here](http://chemicaltagger.ch.cam.ac.uk/).

# Compiling from Source
## [Ubuntu Bionic](http://releases.ubuntu.com/bionic/)
This will compile and install the ChemicalTagger-webapp on the Debian based OS.

1) Install the needed packages.
```bash
    sudo apt-get install git maven openjdk-11-jdk
```
2) Clone and build WAR file
```bash
    git clone https://github.com/BlueObelisk/chemicaltagger-webapp
    cd chemicaltagger-webapp
    mvn clean package
```
   Observe output WAR: ./target/chemicalTagger-webapp-1.1-SNAPSHOT.war

3) Alternatively, run a local instance
```bash
    mvn org.eclipse.jetty:jetty-maven-plugin:run    
```

4) Then browse to 127.0.0.1:8080
```bash
    firefox 127.0.0.1:8080
```

