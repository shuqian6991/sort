import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class MergeSortTest {
	
	MergeSort srt = new MergeSort();
	int listSize = 200;
	Random rgen = new Random();  // seed it
	int []array = new int[listSize];
	long startTime;
	long stopTime;
	long setupOverhead;
	int loopCount = 2000;		// number of times we'll run each test; for a more accurate timing measurement
	boolean showExamples = true;
	boolean calibrated = false;

	/**
	 *  My mac laptop can often sort an array of 200 or more integers in less than 
	 *  a millisecond. So we can't get an accurate time measurement. So, we can run
	 *  each test n times then divide the total time by n to see how long the sort 
	 *  actually takes. So we'll set up loops to initialize an array, then sort it.
	 *  But we want to subtract out the time required to initialize the array n times.
	 *  This routine initializes the array n times (that is, loopCount times) and
	 *  saves the amount of time it takes into setupOverhead. We'll use setupOverhead
	 *  later by removing it from the amount of time the sort loop takes.
	 */
	public void calibrateSetupOverhead() {
		if (calibrated) return;
	
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loopCount; i++) {
			resetList();
		}
		stopTime = System.currentTimeMillis();
		setupOverhead = stopTime-startTime;
		System.out.printf("SetupOverhead: startTime = %d,  stopTime = %d,  elapsed = %d\n\n", startTime, stopTime, setupOverhead);
		calibrated = true;
	}

	public void sectionHeader(String s) {
		System.out.println("-------------------------------------------------------------------------------\n" + s);
	}
	
	public void arrayPrint(String s, int a[]) {
		System.out.println(s);
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%3d ",a[i]);
			if (i % 20 == 19) {
				System.out.println();
			}
		}
		if (srt.getSwapCount() > 0) {
			System.out.println("\nTotal copies: " + srt.getSwapCount() + "\n");
		}
	}	
	
	public void printElapsedTime() {
		System.out.printf("%d iterations: %d msec\n\n", loopCount, stopTime-startTime - setupOverhead);
	}
	
	public void resetList() {
		for (int i=0; i < array.length; i++) {
			array[i] = rgen.nextInt(listSize);
		}
	}
	
	public boolean validateArray(int a[]) {
		for (int i = 0; i+1 < a.length; i++) {
			if (a[i] > a[i+1]) {
				return false;
			}
		}
		return true;
	}

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSort() {
		calibrateSetupOverhead();
		srt.resetStats();
		resetList();
		sectionHeader( "MERGE SORT");
		
		if (showExamples) {
			arrayPrint("Before:", array);
			srt.sort(array);
			arrayPrint("After:", array);
			assertTrue(validateArray(array));
		}
		startTime = System.currentTimeMillis();
		System.out.printf("Iterating %d times...\n",loopCount);
		for (int i=0; i<loopCount; i++) {
			resetList();
			srt.sort(array);
		}
		stopTime = System.currentTimeMillis();
		printElapsedTime();
	}

}
