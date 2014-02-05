/*
 * A set of sorting algorithms. The code is cobbled together from various sources that
 * explained each algorithm.
 * I've inserted some metrics to keep track of how well each algorithm does.
 * _totalswaps = the number of swaps the algorithm required
 * _totalValCmp = the number of data value compares the algotrithm required
 */
public class Sorter {
	private int _totalswaps = 0;
	private int _totalValCmp = 0;
	
	public void resetStats() {
		_totalswaps = 0;
		_totalValCmp = 0;
	}
	public int getSwapCount() {
		return _totalswaps;
	}
	public int getCompareCount() {
		return _totalValCmp;
	}

	/* SELECTION SORT
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void selectionSort(int a[]) {
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
	
	/* BUBBLE SORT
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void bubbleSort(int a[]) {
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
	
	/* INSERTION SORT
	 * complexity n**2
	 * Performance:  worst = n**2 ; best = n  ; average - n**2
	 */
	public void insertionSort(int a[]) {
		int n = a.length;
		int x;
		for (int i = 1; i < n; i++) {
			x = a[i];
			for (int j = i - 1; j >= 0; j--) {
			        ++_totalValCmp;
				if (a[j] > x) {
					a[j + 1] = a[j];
					a[j] = x;
					++_totalswaps;
				}
			}
		}
	}
	
	/* SHELL SORT
	 * Performance: n**2   - but *way* better than bubble, insertion, or selection for large lists
	 */
	public void shellSort(int a[]) {
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

	/* COMBINATION SORT
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
				++_totalValCmp;
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
	
	/*
	 * HEAP SORT
	 */
	private int hs_leftChild(int i) { return 2*i+1; }	
	private int hs_rightChild(int i) { return 2*i+2; }
	
	private void makeMaxHeap(int a[],int i,int size)
	{
	    int L=hs_leftChild(i);
	    int R=hs_rightChild(i);
	    ++_totalValCmp;
	    int max = (L<=size-1 && a[L]>a[i]) ? L : i;
	    ++_totalValCmp;
	    if(R<=size-1 && a[R]>a[max])
	        max = R;
	    if(max != i)
	    {
	        int tmp = a[i];
	        a[i]=a[max];
	        a[max]=tmp;
	        ++_totalswaps;
	        makeMaxHeap(a,max,size);
	    }
	}

	private void buildMaxHeap(int a[],int size)
	{
	    for(int i=size/2 - 1 ;i>=0;i--) {
	        makeMaxHeap(a,i,size);
	    }
	}

	public void heapSort(int a[])
	{
		int size = a.length;
	    int i,temp;
	    buildMaxHeap(a,size);
	    for(i=size-1;i>0;i--)
	    {
	        temp=a[i];
	        a[i]=a[0];
	        a[0]=temp;
	        size=size - 1;
	        ++_totalswaps;
	        makeMaxHeap(a,0,size);
	    }
    }

}
