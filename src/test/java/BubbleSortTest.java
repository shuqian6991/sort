import org.junit.Test;

public class BubbleSortTest extends SortTestBase {
	@Test
	public void testBubble() {
		BubbleSort m = new BubbleSort();
		System.out.println("\nclass:" + m.getClass().getName());
		runTest(m);
	}

}
