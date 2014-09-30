import org.junit.Test;

public class InsertionSortTest extends SortTestBase {
	@Test
	public void testSort() {
		InsertionSort m = new InsertionSort();
		System.out.println("\nclass:" + m.getClass().getName());
		runTest(m);
	}
}
