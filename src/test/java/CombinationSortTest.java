import org.junit.Test;

public class CombinationSortTest extends SortTestBase {
	@Test
	public void testSort() {
		CombinationSort m = new CombinationSort();
		System.out.println("\nclass:" + m.getClass().getName());
		runTest(m);
	}

}
