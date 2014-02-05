#!/bin/sh
java -cp "${JAVAEXTERN}/*:target/classes:target/test-classes" org.junit.runner.JUnitCore MergeSortTest
java -cp "${JAVAEXTERN}/*:target/classes:target/test-classes" org.junit.runner.JUnitCore SorterTest

