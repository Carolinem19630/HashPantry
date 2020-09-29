// --== CS400 File Header Information ==--
// Name: Caroline Machart
// Email: machart@wisc.edu
// Team: HC Team
// TA: Na Li
// Lecturer: Florian Heimerl
import java.util.NoSuchElementException;
import java.util.LinkedList;
/**
 * This class creates a generic hash table made up of an array and linked lists that contains 
 * HashNode objects at each LinkedList node. This class implements MapADT. 
 * 
 * @author Caroline Machart
 *
 * @param <KeyType> The generic object type for the key. 
 * @param <ValueType> The generic object type for the value.
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	//the hash table that contains the key-value pairs
	private LinkedList<HashNode<KeyType, ValueType>>[] pairs; 
	private int capacity; //current capacity of array pairs
	private int size; //current number of key-value pairs

	/**
	 * The single argument HashTableMap constructor. 
	 * 
	 * @param capacity - The current capacity of the pairs array.
	 */
	public HashTableMap(int capacity) {
		this.pairs = new LinkedList[capacity];
		this.capacity = capacity;
	}

	/**
	 * The no-argument HashTableMap constructor. Sets capacity to 10. 
	 * 
	 */
	public HashTableMap() {// with default capacity = 10
		this.pairs = new LinkedList[10];
		this.capacity = 10;
	}

	/**
	 * The implementation of MapADT put method. Adds the key-value pair to pairs if it doesn't 
	 * already exist. 
	 * 
	 * Algorithm: 
	 * 1. Check if key already has a match in pairs. If so, return false.
	 * 2. If element exists at index, find the end of the LinkedList and save that reference to 
	 *    previous HashNode.
	 * 3. Sets next of that reference and then adds new pair to the end of the linkedlist, 
	 *    setting previous to next.
	 * 4. Otherwise, if the array at that index is null, create a new LinkedList and add new key-value 
	 *    pair.
	 * 5. Increment size and check for resizing. 
	 * 
	 * @param key The unique key value to hash and add to hash table. 
	 * @param value Value that accompanies that value to be added to the hash table. 
	 * @return boolean true if put was successful, false if the key already exists in pairs. 
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		if (containsKey(key)) {
			return false;
		}
		int hashIndex = hash(key);
		int i = 0;
		if (pairs[hashIndex] != null) {
			while (pairs[hashIndex].get(i).getNext() != null) {
				++i;
			}
			HashNode<KeyType, ValueType> previous = pairs[hashIndex].get(i);
			HashNode<KeyType, ValueType> newNode 
				= new HashNode<KeyType, ValueType>(key, value, previous);
			pairs[hashIndex].add(newNode);
			previous.setNext(newNode);
		} else {
			pairs[hashIndex] = new LinkedList();
			pairs[hashIndex].add(new HashNode<KeyType, ValueType>(key, value));
		}
		size++;
		if ((double) size() / (double) capacity >= .8) {
			resize();
		}
		return true;
	}
	
	/**
	 * The implementation of MapADT get method. Finds key in pairs and returns value 
	 * associated with the key.
	 * 
	 * Algorithm: 
	 * 1. Hash the key to get hash index. 
	 * 2. If pairs at hashIndex isn't null, search for the key within the linkedlist. 
	 * 3. If the key equals a key within the linkedlist, return the value at that node. 
	 * 4. If the pairs at the index is null or the key isn't found in the linkedlist, 
	 * 	  throw NoSuchElementException.
	 * 
	 * @param key The unique key value to hash and find in the hash table.
	 * @return ValueType The value associated with the key. 
	 * @throws NoSuchElementException If the key doesn't exist in pairs, then error is thrown 
	 *         with message.
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		int hashIndex = hash(key);
		if (pairs[hashIndex] != null) {
			int i = 0;
			while (pairs[hashIndex].get(i) != null) {
				if (key.equals(pairs[hashIndex].get(i).getKey())) {
					return (ValueType) pairs[hashIndex].get(i).getValue();
				} 
				else {
					if (pairs[hashIndex].get(i).getNext() == null) {
						throw new NoSuchElementException("Key not found");
					}
					++i;
				}
			}

		}
		throw new NoSuchElementException("Key not found");
	}
	
	/**
	 * The implementation of MapADT size method. 
	 * 
	 * @return int number of key-value pairs in pairs. 
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * The implementation of the MapADT containsKey method. Checks whether key is in hash table.
	 * 
	 * Algorithm: 
	 * 1. Hash the key to get hash index. 
	 * 2. If pairs at hashIndex isn't null, search for the key within the linkedlist. 
	 * 3. If the key equals a key within the linkedlist, return true. 
	 * 4. If the pairs at the index is null or the key isn't found in the linkedlist, return false;
	 * 
	 * @param key The unique key value to hash and find in the hash table.
	 * @return boolean true if key exists in table, false if not.  
	 */
	@Override
	public boolean containsKey(KeyType key) {
		int hashIndex = hash(key);
		if (pairs[hashIndex] != null) {
			int i = 0;
			while (pairs[hashIndex].get(i) != null) {
				if (key.equals(pairs[hashIndex].get(i).getKey())) {
					return true;
				} else {
					if (pairs[hashIndex].get(i).getNext() == null) {
						return false;
					}
					++i;
				}
			}

		}
		return false;
	}

	
	/**
	 * The implementation of MapADT remove method. Removes the key-value pair 
	 * and returns value removed.
	 * 
	 * Algorithm: 
	 * 1. Hash key and get hash index
	 * 2. If the index at pairs isn't null:
	 * 	  -compare key at node to the key
	 * 	  -remove node if it matches and return value
	 * 	  -if it doesn't match check if the next node is null
	 * 	  -if not increment
	 * 3. If there's no match return null
	 * @param key -key to remove 
	 * @return ValueType the value of the key-value pair to remove
	 */
	@Override
	public ValueType remove(KeyType key) {
		int hashIndex = hash(key);
		if (pairs[hashIndex] != null) {
			int i = 0;
			while (pairs[hashIndex].get(i) != null) {  //search the index for node
				if (key.equals(pairs[hashIndex].get(i).getKey())) { //compare keys
					HashNode<KeyType, ValueType> previous = null;
					HashNode<KeyType, ValueType> next = null;
					if (i != 0) {
						previous = pairs[hashIndex].get(i).getPrevious(); 
					}
					if (pairs[hashIndex].get(i).getNext() != null) {
						next = pairs[hashIndex].get(i).getNext();
					}
					HashNode<KeyType, ValueType> toRemove = pairs[hashIndex].get(i); 
					toRemove.setNext(null);
					toRemove.setPrevious(null);
					pairs[hashIndex].remove(i);
					//set pairs at hashIndex to null if no other nodes
					if (previous == null && next == null) { 
						pairs[hashIndex] = null;
					}
					//set previous's next reference to the node after toRemove
					if (previous != null) { 
						previous.setNext(next);
					}
					//set next's previous reference to the node previous toRemove
					if (next != null) { 
						next.setPrevious(previous);
					}
					size--;
					return (ValueType) toRemove.getValue();
				} else {
					//check if next reference is null
					if (pairs[hashIndex].get(i).getNext() == null) { 
						return null;
					}
					++i;
				}
			}

		}
		return null;
	}
	
	/**
	 * The implementation of MapADT clear method. Clears array and sets new array to same capacity.
	 * 
	 */
	@Override
	public void clear() {
		pairs = new LinkedList[capacity];
		size = 0;
	}

	/**
	 * Private helper method that resizes the array when the size is equal to or 
	 * greater than 80% of capacity.
	 * 
	 * Algorithm:
	 * 1. Double capacity
	 * 2. Create a new bigger array of LinkedLists
	 * 3. Rehash the array and move all the key-value pairs over to new array.
	 * 4. Set pairs field to bigger array.
	 * 
	 */
	private void resize() {
		capacity = capacity * 2;
		LinkedList<HashNode<KeyType, ValueType>>[] bigger = new LinkedList[capacity];
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i] != null) {
				for (int j = 0; j < pairs[i].size(); j++) {
					if (pairs[i].get(j) != null) {
						KeyType key = (KeyType) pairs[i].get(j).getKey();
						bigger[hash(key)]= new LinkedList();
						HashNode<KeyType, ValueType> toAdd 
							= new HashNode<KeyType, ValueType>
								(key, pairs[i].get(j).getValue());
						bigger[hash(key)].add(toAdd);
					}
				}
			}
		}
		pairs = bigger;
	}
	
	/**
	 * Private helper method that computes the hash index. 
	 * 
	 * @param key
	 * @return
	 */
	private int hash(KeyType key) {
		int hashCode = Math.abs(key.hashCode()); //abs value of hashcode of key
		int hashIndex = hashCode % capacity; 
		return hashIndex;
	}
}
