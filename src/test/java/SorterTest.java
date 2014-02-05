import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author sman
 *
 */
public class SorterTest {
	boolean calibrated = false;
	boolean showExamples = true;
	int listSize = 200;
	Random rgen = new Random();  // seed it
	int []array = new int[listSize];
	long startTime;
	long stopTime;
	Sorter srt = new Sorter();
	long setupOverhead;
	int loopCount = 2000;	// number of times we'll run each test; for a more accurate timing measurement
	enum SortMethod {SELECTION, BUBBLE, INSERTION, COMB, SHELL, HEAP};

	public void resetList() {
		for (int i=0; i < array.length; i++) {
			array[i] = rgen.nextInt(listSize);
		}
	}
	
	public String sortMethodToString( SortMethod m) {
		switch (m) {
		case SELECTION: return "SELECTION";
		case BUBBLE: return "BUBBLE";
		case INSERTION: return "INSERTION";
		case COMB: return "COMB";
		case SHELL: return "SHELL";
		case HEAP: return "HEAP";
		default: return "UNKNOWN SORT TYPE";
		}
	}
	public void invokeSortMethod( SortMethod m) {
		switch (m) {
		case SELECTION:	srt.selectionSort(array);	break;
		case BUBBLE:	srt.bubbleSort(array);		break;
		case INSERTION:	srt.insertionSort(array);	break;
		case COMB:		srt.combSort(array);		break;
		case SHELL:		srt.shellSort(array);		break;
		case HEAP:		srt.heapSort(array);		break;
		default: System.out.println("Unknown sort method invocation"); break;
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
			System.out.println("\nTotal swaps: " + srt.getSwapCount());
		}
		if (srt.getCompareCount() > 0) {
			System.out.println("\nTotal value compares: " + srt.getCompareCount());
		}
	}	
	
	public void printElapsedTime() {
		System.out.printf("%d iterations: %d msec\n\n", loopCount, stopTime-startTime - setupOverhead);
	}
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// rgen.setSeed( 19580427 );  // seed it for the same arrays every time
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

	public void runTest(SortMethod m) {
		calibrateSetupOverhead();
		srt.resetStats();
		resetList();
		sectionHeader( sortMethodToString(m) + " SORT");
		
		if (showExamples) {
			arrayPrint("Before:", array);
			invokeSortMethod(m);
			arrayPrint("After:", array);
			assertTrue(validateArray(array));
		}
		
		startTime = System.currentTimeMillis();
		System.out.printf("Iterating %d times...\n",loopCount);
		for (int i=0; i<loopCount; i++) {
			resetList();
			invokeSortMethod(m);
		}
		stopTime = System.currentTimeMillis();
		printElapsedTime();
	}

	@Test
	public void testBubble() {
		runTest(SortMethod.BUBBLE);
	}

	@Test
	public void testComb() {
		runTest(SortMethod.COMB);
	}
	
	@Test
	public void testSelection() {
		runTest(SortMethod.SELECTION);
	}

	@Test
	public void testInsertion() {
		runTest(SortMethod.INSERTION);
	}
	
	@Test
	public void testShell() {
		runTest(SortMethod.SHELL);
	}

	@Test
	public void testHeap() {
		runTest(SortMethod.HEAP);
	}

}
