import org.junit.Test;

public class MergeSortTest extends SortTestBase {
	@Test
	public void testSort() {
		MergeSort m = new MergeSort();
		System.out.println("\nclass:" + m.getClass().getName());
		runTest(m);
	}

}
