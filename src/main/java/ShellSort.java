
public class ShellSort extends SortExt {
	/* SHELL SORT
	 * Performance: n**2   - but *way* better than bubble, insertion, or selection for large lists
	 */
	public void sort(int a[]) {
		int n = a.length;
		int temp;
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i++) {
				for (int j = i - gap; j >= 0; j -= gap) {
				        ++_totalValCmp;
				        if (a[j] > a[j+gap]) {
					    temp = a[j];
					    a[j] = a[j + gap];
					    a[j + gap] = temp;
					    ++_totalswaps;
					}
				}
			}
		}
	}
}
