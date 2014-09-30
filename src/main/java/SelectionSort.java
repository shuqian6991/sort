
public class SelectionSort extends SortExt {
	/* SELECTION SORT
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void sort(int a[]) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = i + 1; j < n; j++) {
			        ++_totalValCmp;
				if (a[i] > a[j]) {
					int t = a[j];
					a[j] = a[i];
					a[i] = t;
					++_totalswaps;
				}
			}
		}
	}
}
