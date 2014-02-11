#!/bin/sh

#
#   EMMA CODE COVERAGE
#
java -cp ~/.m2/repository/emma/emma/2.1.5320/emma-2.1.5320.jar emma report -r xml,html -sp "src/main/java" -in coverage.ec -in target/coverage.em

#
#   JACOCO CODE COVERAGE
#
#java -javaagent:org.jacoco.core-0.6.4.201312101107.jar=include=com.sman.*,output=jacoco_index.html org.junit.runner.JUnitCore some.package.ClassTest
