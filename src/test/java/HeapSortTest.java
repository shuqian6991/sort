import org.junit.Test;

public class HeapSortTest extends SortTestBase {
	@Test
	public void testSort() {
		HeapSort m = new HeapSort();
		System.out.println("\nclass:" + m.getClass().getName());
		runTest(m);
	}

}
