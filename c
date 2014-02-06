#!/bin/sh
java -cp ~/.m2/repository/emma/emma/2.1.5320/emma-2.1.5320.jar emma report -r xml,html -sp "src/main/java" -in coverage.ec -in target/coverage.em
