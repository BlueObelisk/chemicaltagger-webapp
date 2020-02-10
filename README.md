# ChemicalTagger-webapp Overview
[![Build Status](https://travis-ci.org/BlueObelisk/chemicalTagger-webapp.svg?branch=master)](https://travis-ci.org/BlueObelisk/chemicalTagger-webapp) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/uk.ac.cam.ch.wwmm/chemicalTagger-webapp/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.ac.cam.ch.wwmm/chemicalTagger-webapp)


This is the webapp component of the [ChemicalTagger](https://github.com/BlueObelisk/chemicaltagger) tool and is the web based demo of it can be found [here](http://chemicaltagger.ch.cam.ac.uk/).

# Compiling from Source
## [Ubuntu Bionic](http://releases.ubuntu.com/bionic/)
This will compile and install the Jquante to the Debian based OS.

1) Install the needed packages.
```bash
    sudo apt-get install git maven openjdk-11-jdk
```
2) Clone and build WAR file
```bash
    git clone https://github.com/BlueObelisk/chemicaltagger-webapp
    mvn clean package 
```

3) Alternatively, run a local instance
```bash
    mvn org.eclipse.jetty:jetty-maven-plugin:run
    firefox 127.0.0.1:8080
```
