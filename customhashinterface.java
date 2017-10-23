import java.util.Set;

public interface CustomHashInterface<Key, Value> {
	public HashPutResponseItem putAndMore(Key k, Value v);
	public void clear();
	public HashGetResponseItem getAndMore(Key key);
	public double getLambda();
	public Set keySet();
	public int getTableSize();
	public int size();
}
