#!/bin/bash
git clone https://github.com/junwha0511/CSE-364-Software-Engineering.git && cd CSE-364-Software-Engineering
# Edit below after Java/Maven project setting is done

mvn install
java -cp target/cse364-project-1.0-SNAPSHOT.jar com.cse364.Main Adventure educator
