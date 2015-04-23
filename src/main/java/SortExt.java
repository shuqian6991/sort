
abstract class SortExt extends SortBase{

	protected int totalswaps = 0;
	protected int totalValCmp = 0;

	public void resetStats() {
		totalswaps = 0;
		totalValCmp = 0;
	}
	
	public int getSwapCount() {
		return totalswaps;
	}
	
	public int getCompareCount() {
		return totalValCmp;
	}
}
