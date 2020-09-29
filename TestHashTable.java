// --== CS400 File Header Information ==--
// Name: Caroline Machart
// Email: machart@wisc.edu
// Team: HC Team
// TA: Na Li
// Lecturer: Florian Heimerl
import java.util.NoSuchElementException;

/**
 * This class tests for correct implementation of HashTableMap.java.
 * 
 * @author Caroline Machart
 *
 */
public class TestHashTable {
	/**
	 * Test method for HashTableMap.put().
	 * 
	 * @return boolean returns true if all tests pass, false otherwise
	 */
	public static boolean test1() {
		try {
		HashTableMap hashIt = new HashTableMap();
		hashIt.put("what", "HEy");
		hashIt.put("lol", "what");
		hashIt.put("um", "cool");
		// test basic put function with size() method.
		if (hashIt.size() != 3) { 
			return false;
		}
		// tests put return with duplicate key.
		if (hashIt.put("what", "HEy")) { 
			return false;
		}
		hashIt.put("cool", "what");
		hashIt.put("rad", "cool");
		hashIt.put("hash", "HEy");
		hashIt.put("beau", "what");
		hashIt.put("truc", "cool");
		hashIt.put("lame", "HEy");
		hashIt.put("tame", "what");
		hashIt.put("weird", "cool");
		// tests size() after resize.
		if (hashIt.size() != 11) { 
			return false;
		}
		hashIt.clear();
		hashIt.put("cool", "what");
		// tests put after clear.
		if (hashIt.size() != 1) { 
			System.out.println(hashIt.size());
			return false;
		}
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Test method for HashTableMap.get().
	 * 
	 * @return boolean returns true if all tests pass, false otherwise
	 */
	public static boolean test2() {
		try {
		HashTableMap hashIt = new HashTableMap();
		hashIt.put("what", "HEy");
		hashIt.put("lol", "what");
		hashIt.put("um", "cool");
		// tests get with matching String key.
		if (!hashIt.get("what").equals("HEy")) {
			return false;
		}
		hashIt.put("cool", "what");
		hashIt.put("rad", "cool");
		hashIt.put("hash", "HEy");
		hashIt.put("beau", "what");
		hashIt.put("truc", "cool");
		hashIt.put("lame", "HEy");
		hashIt.put("tame", "what");
		hashIt.put("weird", "cool");
		//test get with resizing.
		if (!hashIt.get("truc").equals("cool")) { 
			return false;
		}
		HashTableMap hashOh = new HashTableMap();
		hashOh.put(1, 2);
		hashOh.put(3, 4);
		hashOh.put(5, 6);
		// tests get with matching Integer key.
		if (!hashOh.get(5).equals(6)) { 
			return false;
		}
		}catch (Exception e) {
			return false;
		}
		try {
			HashTableMap hashNo = new HashTableMap();
			hashNo.put(17, 2);
			hashNo.put(28, 4);
			hashNo.put(77, 6);
			hashNo.get(27);
		} catch (NoSuchElementException e) { // tests get without matching key.
			return true;
		}
		return false;
	}

	/**
	 * Test method for HashTableMap.containsKey().
	 * 
	 * @return boolean returns true if all tests pass, false otherwise
	 */
	public static boolean test3() {
		try {
		HashTableMap hashIt = new HashTableMap();
		hashIt.put("what", "HEy");
		hashIt.put("lol", "what");
		hashIt.put("um", "cool");
		// tests when matching key exists
		if (!hashIt.containsKey("what")) { 
			return false;
		}
		HashTableMap hashNo = new HashTableMap();
		hashNo.put(17, 2);
		hashNo.put(28, 4);
		hashNo.put(77, 6);
		// test when matching key doesn't exist
		if (hashNo.containsKey(27)) { 
			return false;
		}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Test method for HashTableMap.remove().
	 * 
	 * @return boolean returns true if all tests pass, false otherwise
	 */
	public static boolean test4() {
		try {
		HashTableMap hashIt = new HashTableMap();
		hashIt.put("what", "HEy");
		hashIt.put("lol", "what");
		hashIt.put("um", "cool");
		//tests remove with a few items and matching key.
		if (!hashIt.remove("lol").equals("what") || hashIt.size() != 2) {
			return false;
		}
		hashIt.put("cool", "what");
		hashIt.put("rad", "cool");
		hashIt.put("hash", "HEy");
		hashIt.put("beau", "what");
		hashIt.put("truc", "cool");
		hashIt.put("lame", "HEy");
		hashIt.put("tame", "what");
		hashIt.put("weird", "cool");
		//tests remove with resize and matching key.
		if (!hashIt.remove("hash").equals("HEy") || hashIt.size() != 9) {
			System.out.println(hashIt.size());
			return false;
		}
		hashIt.put("wow", "what");
		hashIt.put("crabs", "cool");
		hashIt.put("vegan", "HEy");
		hashIt.put("is", "what");
		hashIt.put("king", "cool");
		hashIt.put("huh", "HEy");
		hashIt.put("where", "what");
		hashIt.put("how", "cool");
		//tests remove again with more items and linkedlist. 
		if (!hashIt.remove("crabs").equals("cool") || hashIt.size() != 16) {
			System.out.println(hashIt.size());
			return false;
		}
		//tests remove with no matching key
		if (hashIt.remove("i don't exist") != null) {
			return false;
		}
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Test method for HashTableMap.clear().
	 * 
	 * @return boolean returns true if all tests pass, false otherwise
	 */
	public static boolean test5() {
		try {
		HashTableMap hashIt = new HashTableMap();
		hashIt.put(4, 5);
		hashIt.put(1, 2);
		hashIt.put(2, 3);
		hashIt.put(3, 4);
		//tests the size after objects were added.
		if (hashIt.size() !=4) { 
			return false;
		}
		hashIt.clear();
		//tests clear after objects were added.
		if (hashIt.size() != 0) { 
			return false;
		}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Main method calls test methods.
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
	}
}
