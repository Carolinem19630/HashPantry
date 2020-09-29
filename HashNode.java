// --== CS400 File Header Information ==--
// Name: Caroline Machart
// Email: machart@wisc.edu
// Team: HC Team
// TA: Na Li
// Lecturer: Florian Heimerl

/**
 * This class creates HashNode objects that contain Key-Value Pairs. 
 * 
 * @author Caroline Machart
 *
 * @param <KeyType> The generic object type for the key. 
 * @param <ValueType> The generic object type for the value.
 */
class HashNode<KeyType, ValueType> {
		private KeyType key;
		private ValueType value;
		private HashNode previous; 
		private HashNode next;

		/**
		 * HashNode constructor with 2 arguments. 
		 * 
		 * @param key The unique key value.
		 * @param value The value associated with key.
		 */
		HashNode(KeyType key, ValueType value) {
			this.key = key;
			this.value = value;
			this.previous = null;
			this.next = null;
		}

		/**
		 * HashNode constructor with 3 arguments. 
		 * 
		 * @param key The unique key value.
		 * @param value The value associated with key.
		 * @param previous previous HashNode is linkedlist. 
		 */
		HashNode(KeyType key, ValueType value, HashNode previous) {
			this.key = key;
			this.value = value;
			this.previous = previous;
			this.next = null;
		}

		/**
		 * Returns previous HashNode in linkedlist. 
		 * 
		 * @return HashNode reference to previous HashNode in linkedlist
		 */
		public HashNode getPrevious() {
			return previous;
		}

		/**
		 * Returns next HashNode in linkedlist. 
		 * 
		 * @return HashNode reference to next HashNode in linkedlist
		 */
		public HashNode getNext() {
			return next;
		}

		/**
		 * Sets the previous HashNode in linkedlist. 
		 * 
		 * @param HashNode reference to previous HashNode in linkedlist
		 */
		public void setPrevious(HashNode previous) {
			this.previous = previous;
		}

		/**
		 * Sets the next HashNode in linkedlist. 
		 * 
		 * @param HashNode reference to next HashNode in linkedlist
		 */
		public void setNext(HashNode next) {
			this.next = next;
		}
		
		/**
		 * Returns the unique key in HashNode.  
		 * 
		 * @return KeyType The unique key value at HashNode.
		 */
		public KeyType getKey() {
			return key;
		}
		
		/**
		 * Returns the value in HashNode.  
		 * 
		 * @return ValuetType The value at HashNode.
		 */
		public ValueType getValue() {
			return value;
		}

	}