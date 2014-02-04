#!/bin/sh
java -cp "${JAVAEXTERN}/*:target" org.junit.runner.JUnitCore MergeSortTest
java -cp "${JAVAEXTERN}/*:target" org.junit.runner.JUnitCore SorterTest

