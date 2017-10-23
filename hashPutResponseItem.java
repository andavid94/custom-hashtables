public class HashPutResponseItem {
	final int k;
	int index;
	final boolean replaced;
	String rd;
	
	public HashPutResponseItem(int k, int index, boolean replaced, String rd) {
		this.k = k;
		this.index = index;
		this.replaced = replaced;
		this.rd = rd;
	}

	public String toString() {
		String ans = "PUT took "+k+" steps; "+" at "+index;
		if (replaced) { ans += "; (value replaced);";}
		ans += rd;
		return ans;
	}

}
