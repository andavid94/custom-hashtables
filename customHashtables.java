// David An

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CustomHashtables {
	public class SeparateChainingHashtable<Key, Value> implements CustomHashInterface<Key, Value> {

		ArrayList<SeparateChain<Key, Value>> list;			//declare variable list
		int currentTableSize;								//declare variable currentTableSize
		Set <Key> keySet;									//declare variable keySet

		/*
		 * Constructs the list used to keep track of the hashTable, and the keySet of the list. Fills the 
		 * list with empty instances of SeparateChain objects.
		 * 
		 * @param	tableSize 	the initial tableSize of the hashTable
		 * @param	rehashingThreshold	rehashing threshold of hashtable
		 */
		public SeparateChainingHashtable(int tableSize, double rehashingThreshold){
			list = new ArrayList<SeparateChain<Key, Value>>();
			keySet = new HashSet<Key>();
			currentTableSize = tableSize;			//assign passed tableSize to variable currentTableSize
			for (int i = 0; i < tableSize; i++){
				list.add(new SeparateChain<Key, Value>());	//add empty instance of SeparateChain objects
			}
		}

		/*
		 * Returns the number of keys in the hash table
		 * 
		 * @return		the integer size of the keySet
		 */
		public int size() {
			return keySet().size();
		}

		/*
		 * Returns the size of the hash table
		 * 
		 * @return		the integer value of currentTableSize
		 */
		public int getTableSize() {
			return currentTableSize;
		}

		/*
		 * Inserts the passed key value pair into the hash table
		 * 
		 * @param	k	inserted key
		 * @param	v	inserted value
		 * 
		 * @return		HashPutResponseItem with the updated SeparateChain object
		 */
		public HashPutResponseItem putAndMore(Key k, Value v){
			int arrayIndex = myMod(k.hashCode(), currentTableSize);	//hashes value pair into int
			keySet.add(k);											//add key to keySet
			SeparateChain<Key, Value> temp = list.get(arrayIndex);	//grab object, assign value at arrayIndex
			HashPutResponseItem item = temp.insert(k, v);		//update item by inserting key/value pair 
			item.index = arrayIndex;							//set index of item to arrayIndex
			return item;
		}

		/*
		 * Removes all items from the hash table
		 */
		public void clear() {
			for (int i = 0; i < currentTableSize; i++){
				list.get(i).clear();			//for every head of chain, set pointer to null
			}
			keySet.clear();						//clear keySet
		}

		/*
		 * Returns the corresponding value in the response item.
		 * 
		 * @param	key		key of desired value
		 * 
		 * @return 		HashGetResponseItem<Value> of corresponding key
		 */
		public HashGetResponseItem<Value> getAndMore(Key key) {
			int arrayIndex = myMod(key.hashCode(), currentTableSize);	//hashes value into int	
			SeparateChain<Key, Value> temp = list.get(arrayIndex);	//grab object, assign value at arrayIndex
			HashGetResponseItem item = temp.find(key);				//update item by grabbing value 
			item.index = arrayIndex;								//set index of item to arrayIndex
			return item;
		}

		/*
		 * Returns the loading factor of the hash table
		 * 
		 * @return 		lambda, or loading factor
		 */
		public double getLambda(){
			double elements = (double)keySet.size();			//type-cast # of elements
			double lambda = elements / currentTableSize;		//calculate loading factor
			return lambda;
		}

		/*
		 * Collect and returns a set of keys that are currently in the hash table
		 * 
		 * @return		current keySet
		 */
		public Set<Key> keySet() {
			return keySet;
		}
	}


	/* Useful for turning int value into hash table index values.
	 * Avoids the bug where % can return a negative value.
	 */
	public int myMod(int a, int b) {
		return ((a % b) + b) % b; 
	}

	/* primeTest is useful in implementing rehashing.
	 * It takes an int and returns true if it is prime, and false otherwise.
	 */
	public boolean primeTest(int n) {
		if (n < 2) { return false; }
		if (n==2 || n==3) { return true; }
		if ((n % 2) == 0 || (n % 3) == 0) { return false; }
		int limit = (int) Math.ceil(Math.sqrt(n))+1;
		for (int k=5; k <= limit; k+=2) {
			if (n % k == 0) { 
				return false; }
		}
		return true;
	}

}
