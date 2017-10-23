
public class HashGetResponseItem<ValueType> {
	final ValueType val;
	final int k;
	int index;
	final boolean success;
	
	public HashGetResponseItem(ValueType val, int k, int index, boolean success) {
		this.val = val; // The value that is being returned (associated with the key).
		this.k = k; // Number of collisions that occurred while trying to find the key.
		this.index = index; // Position in the hash table where this was found,
		   // or if not found, the last hash table location that was examined.
		this.success = success; // True if the key was found.
	}
	
	public String toString() {
		if (val==null) { return "Value: null; "+k+" collisions; "+" at "+index;}
		else {return "Value: "+val.toString()+"; "+k+" collisions; "+" at "+index;}
	}

}
