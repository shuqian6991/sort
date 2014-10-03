import java.util.Random;

import org.junit.Before;

import static org.junit.Assert.assertTrue;

public class SortTestBase {
	private boolean calibrated = false;
	protected boolean showExamples = false;
	protected boolean showMetrics = true;
	protected boolean showOverhead = false;
	protected int listSize = 200;
	protected Random rgen = new Random();  // seed it
	protected int []array = new int[listSize];
	protected long startTime;
	protected long stopTime;
	protected long setupOverhead;
	protected int loopCount = 2000;	// number of times we'll run each test; for a more accurate timing measurement

	public void resetList() {
		for (int i=0; i < array.length; i++) {
			array[i] = rgen.nextInt(listSize);
		}
	}
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
		if (showOverhead) {
		    System.out.printf("SetupOverhead: startTime = %d,  stopTime = %d,  elapsed = %d%n%n", startTime, stopTime, setupOverhead);
		}
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
	}	

	public void printMetrics(SortExt m) {
		if (showMetrics) {
			System.out.println("Avg swaps per sort: " + m.getSwapCount()/loopCount);
			System.out.println("Avg value compares per sort: " + m.getCompareCount()/loopCount);
		}
	}
	
	public void printElapsedTime() {
		System.out.printf("%d iterations: %d msec%n", loopCount, stopTime-startTime - setupOverhead);
	}
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// rgen.setSeed( 19580427 );  // seed it for the same arrays every time (for debugging)
	}
	
	/*
	 * returns true if the array is correctly sorted
	 * otherwise it returns false.
	 */
	public boolean validateArray(int a[]) {
		for (int i = 0; i+1 < a.length; i++) {
			if (a[i] > a[i+1]) {
				return false;
			}
		}
		return true;
	}

	public void runTest(SortExt m) {
		calibrateSetupOverhead();
		m.resetStats();
		resetList();
		
		if (showExamples) {
			arrayPrint("Before:", array);
			m.sort(array);
			arrayPrint("After:", array);
			assertTrue(validateArray(array));
		}
		startTime = System.currentTimeMillis();
		for (int i=0; i<loopCount; i++) {
			resetList();
			m.sort(array);
		}
		stopTime = System.currentTimeMillis();
		printElapsedTime();
		printMetrics(m);
	}

}
