
abstract class SortExt extends SortBase{

	protected int _totalswaps = 0;
	protected int _totalValCmp = 0;

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
}
