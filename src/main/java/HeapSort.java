/**
 * An implementation of HEAP SORT.
 * Worst case performance		O(n log n)
 * Best case performance		Omega(n), O(n log n)
 * Average case performance		O(n log n)
 * Worst case space complexity	O(1) auxiliary
 */
public class HeapSort extends SortExt {
	
	private int hs_leftChild(int i) {
		return 2 * i + 1; 
	}	
	
	private int hs_rightChild(int i) {
		return 2 * i + 2;
	}

	private void makeMaxHeap(int[] a,int i,int size) {
		int L = hs_leftChild(i);
		int R = hs_rightChild(i);
		++totalValCmp;
		int max = L <= size - 1 && a[L] > a[i] ? L : i;
		++totalValCmp;
		if (R <= size - 1 && a[R] > a[max]) {
			max = R;
		}
		if (max != i) {
			int tmp = a[i];
			a[i] = a[max];
			a[max] = tmp;
			++totalswaps;
			makeMaxHeap(a,max,size);
		}
	}

	private void buildMaxHeap(int[] a,int size) 	{
		for (int i = size / 2 - 1; i >= 0; i--) {
			makeMaxHeap(a,i,size);
		}
	}

	/**
	 * The implementation of the sort routine.
	 */
	public void sort(int[] a)	{
		int size = a.length;
		int i;
		int temp;
		buildMaxHeap(a,size);
		for (i = size - 1; i > 0; i--) {
			temp = a[i];
			a[i] = a[0];
			a[0] = temp;
			size = size - 1;
			++totalswaps;
			makeMaxHeap(a,0,size);
		}
	}

}
