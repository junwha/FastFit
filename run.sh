#!/bin/bash
git clone https://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:x-oauth-basic@github.com/junwha0511/CSE-364-Software-Engineering && cd CSE-364-Software-Engineering
git checkout shellfile # TODO: remove this part!

mongod --fork --logpath /var/log/mongodb.log;

mvn package -P init -P docker
java -jar target/cse364-project-1.0-SNAPSHOT.jar
