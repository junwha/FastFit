#!/bin/bash
git clone https://github.com/junwha0511/CSE-364-Software-Engineering.git && cd CSE-364-Software-Engineering
  
mvn package -P init -P docker
java -jar target/cse364-project-1.0-SNAPSHOT.jar