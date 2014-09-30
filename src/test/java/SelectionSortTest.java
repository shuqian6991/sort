import org.junit.Test;

public class SelectionSortTest extends SortTestBase {
	@Test
	public void testSort() {
		SelectionSort m = new SelectionSort();
		System.out.println("\nclass:" + m.getClass().getName());
		runTest(m);
	}

}
