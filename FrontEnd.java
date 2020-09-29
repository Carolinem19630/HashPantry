// --== CS400 File Header Information ==--
// Name: Caroline Machart
// Email: machart@wisc.edu
// Team: HC
// TA: Na Ta
// Lecturer: Florian Heimerl
// Notes to Grader: N/A
import java.util.Scanner;

/**
 * This class acts as the front-end to the HC Food Pantry project.
 * 
 * @author Caroline Machart
 *
 */
public class FrontEnd {
	
	/**
	 * The main class takes user commands and directs them to the appropriate
	 * method.
	 * 
	 */
	public static void main(String[] args) {
		PantryList pantry = new PantryList();
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to your pantry");
		int command = 0;
		while (command != 8) {
			menu();
			if (scnr.hasNextInt()) { // takes integer as command
				command = scnr.nextInt();
			} else { // if the user doesn't enter an integer
				menu();
				System.out.println("Enter a number between 1 and 8, this time!");
				scnr.nextLine();
				if (scnr.hasNextInt()) {
					command = scnr.nextInt();
				}
			}
			// directs the user to a method based on command
			switch (command) {
			case 1: // add item-quantity pair
				addPair(scnr, pantry);
				break;
			case 2: // get quantity of item in pantry
				getQuantity(scnr, pantry);
				break;
			case 3: // empty pantry
				emptyPantry(scnr, pantry);
				break;
			case 4: // check for item in pantry
				checkForItem(scnr, pantry);
				break;
			case 5: // remove item from pantry
				removeItem(scnr, pantry);
				break;
			case 6: // get item from pantry and add to grocery list
				addToGrocery(scnr, pantry);
				break;
			case 7: // get grocery list
				getGrocery(scnr, pantry);
			default:
				break;

			}
		}
		scnr.close();
		System.out.println("You have closed the pantry");
	}

	/**
	 * Empties pantry by calling PantryList.clear() and prints out message to
	 * indicate it was emptied.
	 * 
	 * @param scnr   Scanner object.
	 * @param pantry The pantry object.
	 */
	public static void emptyPantry(Scanner scnr, PantryList pantry) {
		pantry.clear();
		System.out.println("Pantry is now empty");
		scnr.nextLine();
		do {
			System.out.println("Press return for menu");
		} while (!scnr.hasNextLine());
	}

	/**
	 * Prints out grocery list by calling PantryList.getGroceryList().
	 * 
	 * @param scnr   Scanner object.
	 * @param pantry The pantry object.
	 */
	public static void getGrocery(Scanner scnr, PantryList pantry) {
		System.out.println(pantry.getGroceryList());
		scnr.nextLine();
		do {
			System.out.println("Press return for menu");
		} while (!scnr.hasNextLine());
	}

	/**
	 * Prompts user for item to put on grocery list and calls
	 * PantryList.moveToGroceryList(). Prints message to indicate whether item
	 * was added to grocery list.
	 * 
	 * @param scnr   Scanner object.
	 * @param pantry The pantry object.
	 */
	public static void addToGrocery(Scanner scnr, PantryList pantry) {
		System.out.println("What item would you like to add to your grocery list?");
		scnr.nextLine();
		String groceryItem = scnr.nextLine().toLowerCase().trim();
		try {
			pantry.moveToGroceryList(groceryItem);
			System.out.println("Added " + groceryItem + " to your grocery list");
		} catch (Exception e) {
			System.out.println("Can't add item to grocery list");
		}
		do {
			System.out.println("Press m for menu");
		} while (!scnr.hasNextLine());
	}

	/**
	 * Prompts user for item to remove from pantry and calls
	 * PantryList.remove(). Prints message to indicate whether item was
	 * removed.
	 * 
	 * @param scnr
	 * @param pantry
	 */
	public static void removeItem(Scanner scnr, PantryList pantry) {
		System.out.println("What item would you like to remove from your pantry?");
		scnr.nextLine();
		String toRemove = scnr.nextLine().toLowerCase().trim();
		if (pantry.remove(toRemove) != null) {
			System.out.println("Removed " + toRemove + " from your pantry");
		} else {
			System.out.println("Item wasn't in pantry");
		}
		do {
			System.out.println("Press return for menu");
		} while (!scnr.hasNextLine());
	}

	/**
	 * Prompts user for item and calls PantryList.contains(). Prints message
	 * to indicate whether item was found.
	 * 
	 * @param scnr   Scanner object.
	 * @param pantry The pantry object.
	 */
	public static void checkForItem(Scanner scnr, PantryList pantry) {
		System.out.println("What item are you looking for in the pantry?");
		scnr.nextLine();
		String find = scnr.nextLine().toLowerCase().trim();
		try {
			if (pantry.contains(find)) {
				System.out.println("You have " + find);
			} else {
				System.out.println("You don't have " + find);
			}
		} catch (Exception e) {
			System.out.println("Try again");
		}
		do {
			System.out.println("Press return for menu");
		} while (!scnr.hasNextLine());

	}

	/**
	 * Prompts user for item and prints the return of PantryList.get().
	 * Catches exception and prints error message.
	 * 
	 * @param scnr   Scanner object.
	 * @param pantry The pantry object.
	 */
	public static void getQuantity(Scanner scnr, PantryList pantry) {
		System.out.println("Enter item to find out how much you have left: ");
		scnr.nextLine();
		String item = scnr.nextLine().toLowerCase().trim();
		try {
			String quantity = pantry.get(item);
			System.out.println("You have " + quantity + " of " + item);
		} catch (Exception e) {
			System.out.println("You don't have any " + item);
		}
		do {
			System.out.println("Press return for menu");
		} while (!scnr.hasNextLine());

	}

	/**
	 * Prompts user for item and quantity and calls PantryList.put().
	 * Prints out message to indicate whether item-quantity pair was added.
	 * 
	 * @param scnr   Scanner object.
	 * @param pantry The pantry object.
	 */
	public static void addPair(Scanner scnr, PantryList pantry) {
		System.out.println("What item would you like to add?");
		scnr.nextLine();
		String item = scnr.nextLine().toLowerCase().trim();
		System.out.print("What quantity of " + item + "?");
		String quantity = scnr.nextLine().toLowerCase().trim();
		if (pantry.put(item, quantity)) {
			System.out.println(quantity + " " + item + " was added");
		}
		else {
			System.out.println(item + " wasn't added");
		}
		do {
			System.out.println("Press return for menu");
		} while (!scnr.hasNextLine());
	}

	/**
	 * Menu that contains the list of commands and prompts user.
	 * 
	 */
	public static void menu() {
		System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
		System.out.println("List of commands: ");
		System.out.println("Add grocery item and quantity: 1");
		System.out.println("Get quantity of item in pantry: 2");
		System.out.println("Empty pantry: 3");
		System.out.println("Check if you have item: 4");
		System.out.println("Remove item: 5");
		System.out.println("Add item from pantry to grocery list: 6");
		System.out.println("Get grocery list: 7");
		System.out.println("quit: 8");
		System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
		System.out.println("Enter Command (1-8):");
	}
}
