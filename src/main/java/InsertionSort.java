public class InsertionSort extends SortExt {
	/**
	 * An implementation of the INSERTION SORT algorithm.
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void sort(int[] a) {
		int n = a.length;
		int x;
		for (int i = 1; i < n; i++) {
			x = a[i];
			for (int j = i - 1; j >= 0; j--) {
				++totalValCmp;
				if (a[j] > x) {
					a[j + 1] = a[j];
					a[j] = x;
					++totalswaps;
				}
			}
		}
	}
}
