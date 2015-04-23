/* Implementation of the COMBINATION SORT algorithm.
 * Average performance:  n log(n)    best: n    worst: n**2
 */
public class CombinationSort extends SortExt {
	/**
	 * Main sort routine.
	 */
	public void sort(int[] a) {
		int gap = a.length;
		boolean swapped = true;
		while (gap > 1 || swapped) {
			if (gap > 1) {
				gap = (int) (gap / 1.24733);
			}
			swapped = false;
			for (int i = 0; i + gap < a.length; i++) {
				++totalValCmp;
				if (a[i] > a[i + gap]) {
					int t = a[i];
					a[i] = a[i + gap];
					a[i + gap] = t;
					swapped = true;
					++totalswaps;
				}
			}
		}
	}
}
