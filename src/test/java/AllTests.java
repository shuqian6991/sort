import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ BubbleSortTest.class, 
				CombinationSortTest.class,
				HeapSortTest.class,
				InsertionSortTest.class,
				MergeSortTest.class,
				SelectionSortTest.class,
				ShellSortTest.class,
				ForceError.class})
public class AllTests {

}
