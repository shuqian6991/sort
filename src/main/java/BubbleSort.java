
public class BubbleSort extends SortExt {
	/* BUBBLE SORT
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void sort(int a[]) {
		int n = a.length;
		int swaps;
		do {
			swaps = 0;
			for (int j = 0; j < n - 1; j++) {
			        ++_totalValCmp;
				if (a[j + 1] < a[j]) {
					int t = a[j];
					a[j] = a[j + 1];
					a[j + 1] = t;
					++swaps;
					++_totalswaps;
				}
			}
		} while (swaps > 0);
	}
}
