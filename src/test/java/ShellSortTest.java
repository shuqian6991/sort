import org.junit.Test;

public class ShellSortTest extends SortTestBase {
	@Test
	public void testSort() {
		ShellSort m = new ShellSort();
		System.out.println("\nclass:" + m.getClass().getName());
		runTest(m);
	}

}
