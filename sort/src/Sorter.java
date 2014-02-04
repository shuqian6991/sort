/*
 * A set of sorting algorithms
 */
public class Sorter {
	private int _totalswaps = 0;
	
	public void resetStats() {
		_totalswaps = 0;
	}
	public int getSwapCount() {
		return _totalswaps;
	}

	/*
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void selectionSort(int a[]) {
		int n = a.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (a[i] > a[j]) {
					int t = a[j];
					a[j] = a[i];
					a[i] = t;
					++_totalswaps;
				}
			}
		}
	}
	
	/*
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void bubbleSort(int a[]) {
		int n = a.length;
		int swaps;
		do {
			swaps = 0;
			for (int j = 0; j < n - 1; j++) {
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
	
	/*
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void insertionSort(int a[]) {
		int n = a.length;
		int x;
		for (int i = 1; i < n; i++) {
			x = a[i];
			for (int j = i - 1; j >= 0; j--) {
				if (a[j] > x) {
					a[j + 1] = a[j];
					a[j] = x;
					++_totalswaps;
				}
			}
		}
	}
	
	/*
	 * Performance: n**2   - but *way* better than bubble, insertion, or selection for large lists
	 */
	public void shellSort(int a[]) {
		int n = a.length;
		int temp;
		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i++) {
				for (int j = i - gap; j >= 0 && a[j] > a[j + gap]; j -= gap) {
					temp = a[j];
					a[j] = a[j + gap];
					a[j + gap] = temp;
					++_totalswaps;
				}
			}
		}
	}

	/*
	 * Average performance:  n log(n)    best: n    worst: n**2
	 */
	public void combSort(int a[]) {
		int gap = a.length;
		boolean swapped = true;
		while (gap > 1 || swapped) {
			if (gap > 1) {
				gap = (int) (gap / 1.24733);
			}
			swapped = false;
			for (int i = 0; i + gap < a.length; i++) {
				if (a[i] > a[i+gap]) {
					int t = a[i];
					a[i] = a[i + gap];
					a[i + gap] = t;
					swapped = true;
					++_totalswaps;
				}
			}
		}
	}

}