import java.util.HashSet;

/**
 * Represents linked lists in hash tables using separate chaining.
 */

public class SeparateChain<Key, Value> {
	
	public class SC_Node<Key, Value> {
		Key key;
		Value value;
		SC_Node next;
		
		// Constructor for node objects of these linked lists.
		public SC_Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			next = null;
		}
		@Override
		public String toString() {
			return "Key: "+key + "; Value: "+value;
		}
		
	}
	SC_Node head; // Reference to first node on the list, if there is one.
	
	
	HashGetResponseItem find(Key key) {
		int collisions = 0; // Get ready to count the collisions (link traversals beyond the first).
		SC_Node current = head;  // Start looking at the head of the list.
		while (current != null) {
			if (key.equals(current.key)) {
				//System.out.println("   (they are equal)");
				return new HashGetResponseItem(current.value, collisions, -1, true);
				// The location of -1 is a placeholder, and should be overwritten by
				// the hash table GET method (i.e., getAndMore).
				// We return true, because the item was found.
			}
			current = current.next;
			collisions += 1;
		}
		return new HashGetResponseItem(null, collisions, -1, false);
		// The response item is given a null first value, because the key was not found.
		// Also, the boolean false is given, confirming that the key was not found.
	}
	
	// The following method is used to insert or update the value associated with the key.
	HashPutResponseItem insert(Key key, Value value) {
		int collisions = 0;
		SC_Node previous = null;
		SC_Node current = head;
		while (current != null) {
			if (key.equals(current.key)) {
				// Here we've found that the key is already in the list.
				// So we update the value and return a response item that
				// indicates this was an update.
				current.value = value;
				return new HashPutResponseItem(collisions, -1, true, "");
			}
			previous = current; // We'll need this if we get to the end of the list.
			current = current.next; // Moving on.
			collisions += 1;
		}
		// We got to the end of the list, without finding the key, so insert.
		HashPutResponseItem item = new HashPutResponseItem(collisions, -1, false, "");
		SC_Node newNode = new SC_Node<Key, Value>(key, value);
		if (head==null) { head = newNode; } // Case where the list was empty.
		else { previous.next = newNode; } // If not empty, we need previous in order to link to the new node.
		return item;
	}
	
	// The next method (clear) is basically emptying the list by cutting off the link 
	// to the first node. It's the job of Java's garbage collector to find and recycle 
	// the memory used by the nodes of the list, if any.
	void clear() {
		head = null;
	}
	
	// toString formats a description of this linked list's contents.
	@Override
    public String toString() {
    	String ans = "Chain: ";
		SC_Node current = head;
		while (current != null) {
			ans += "["+current.key+","+current.value+"]";
			current = current.next;
		}
        return ans;
    }
	
	// The following function can be removed, if you are not implementing
	// rehashing, or if you want to do rehashing your own way.

    void insertAllPairsFromThisChain(CustomHashtables.Inserter ins) {
    	SC_Node<Key, Value> current = head;
		while (current != null) {
			Key k = current.key;
			Value v = current.value;
	    	ins.insertPair(k, v);
			current = current.next;
		}
    }

	
    // getKeysFromThisChain is useful when implementing  your hash table's keySet method.
    // It returns a set of the keys that occur in this chain, without the values.
    HashSet<Key> getKeysFromThisChain() {
    	HashSet<Key> theKeys = new HashSet();
    	SC_Node<Key, Value> current = head;
		while (current != null) {
			Key k = current.key;
	    	theKeys.add(k);
			current = current.next;
		}
    	return theKeys;
    }
}
