#!/bin/sh
java -cp "${JAVAEXTERN}/*:target/sort.jar" org.junit.runner.JUnitCore MergeSortTest
java -cp "${JAVAEXTERN}/*:target/sort.jar" org.junit.runner.JUnitCore SorterTest

