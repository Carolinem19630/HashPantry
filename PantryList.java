import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PantryList {
	
	/**
	 * Instance Variables for the PantryList Class.
	 * pantryList is the instance of the pantry.
	 * groceryList is the instance variable where items are added to an
	 * ArrayList when they need to be bought at the grocery store.
	 */
	private static HashTableMap pantryList;
	private static ArrayList<String> groceryList = new ArrayList<String>();
	
	/**
	 * No argument constructor for the PantryList Class.
	 * Default Capacity = 10.
	 */
	public PantryList() {
		pantryList = new HashTableMap();
	}
	
	/**
	 * Constructor with specified capacity for the PantryList
	 * @param capacity
	 */
	public PantryList(int capacity) {
		pantryList = new HashTableMap(capacity);
	}
	
	/**
	 * put method inserts an item into the pantry list. 
	 * @param name
	 * @param amount
	 * @return true if successfuly inserted, false if not
	 */
	public boolean put(String name, String amount) {
		return pantryList.put(name, amount);
	}
	
	/**
	 * get method return the amount of an item specified in the argument.
	 * If the name does not exist, NoSuchElementException is thrown.
	 * @param name
	 * @return amount of item left.
	 * @throws NoSuchElementException if item does not exist in pantry.
	 */
	public String get(String name) throws NoSuchElementException {
		return (String) pantryList.get(name);
	}
	
	/**
	 * size method returns the amount of items in the pantry.
	 * @return amount of items in the pantry.
	 */
	public int size() {
		return pantryList.size();
	}
	
	/**
	 * contains method checks if an item is in the hash table.
	 * @param name
	 * @return boolean true if the item is in the pantry, false if not.
	 */
	public boolean contains(String name) {
		return pantryList.containsKey(name);
	}
	
	/**
	 * remove method takes the item in the argument and removes it from the pantry.
	 * If the item is not in the pantry, the remove method returns null.
	 * @param name
	 * @return amount of the removed item, null if the item is not in the pantry.
	 */
	public String remove(String name) {
		return (String) pantryList.remove(name);
	}
	
	/**
	 * Takes an item from the list and adds the name to the grocery list.
	 * @param name
	 */
	public void moveToGroceryList(String name) {
		groceryList.add(name);
		pantryList.remove(name);
	}
	
	/**
	 * getGroceryList method concatenates the items in the grocery list into one
	 * string which is the grocery list
	 * @return
	 */
	public String getGroceryList() {
		String gList = "";
		if (groceryList.size() == 0) {
			return ("No Items in Grocery List");
		}
		if (groceryList.size() == 1) {
			gList += groceryList.get(0);
			return gList;
		}
		gList += groceryList.get(0);
		for (int i = 1; i < groceryList.size(); i++) {
			gList += (" " + groceryList.get(i));
		}
		return gList;
	}
	
	/**
	 * clear method clears the whole table.
	 * Resets the size to 0.
	 */
	public void clear() {
		pantryList.clear();
	}
}