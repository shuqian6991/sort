/**
 * Implementation of a BUBBLE SORT algorithm.
 * @author stevemansour
 *
 */
public class BubbleSort extends SortExt {
	/**
	 * The main sort routine.
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void sort(int[] a) {
		int n = a.length;
		int swaps;
		do {
			swaps = 0;
			for (int j = 0; j < n - 1; j++) {
			        ++totalValCmp;
				if (a[j + 1] < a[j]) {
					int t = a[j];
					a[j] = a[j + 1];
					a[j + 1] = t;
					++swaps;
					++totalswaps;
				}
			}
		} while (swaps > 0);
	}
}
