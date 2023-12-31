package bricey;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class CLIApp {
	
	public void launch() {
		Random random = new Random();

		Class[] itemsToChoose = new Class[] {
			Book.class
		};

		Scanner scanner = new Scanner(System.in);
		Map<Integer, LibraryItem> items = SerializationManager.loadItems();

		boolean isFinished = false;
		while (!isFinished) {
			System.out.println("What do you want to make?");

			for (Class c : itemsToChoose) {
				// It comes in the form of package.class
				// so it would be "bricey.Book" and I only
				// want the "Book" part
				String trimmedName = c.getName().split("\\.")[1];
				System.out.println(" - " + trimmedName);
			}

			String name = scanner.nextLine();
			Class itemChoosen = null;

			LibraryItem item = null;
			// include the package name in there
			final String itemName = "bricey." + name.trim();
			try {
				itemChoosen = Class.forName(itemName);
				boolean validItem = Arrays.stream(itemsToChoose)
					.anyMatch(c -> c.getName().equals(itemName));
				if (validItem == true) {

					item = (LibraryItem) itemChoosen.newInstance();
				} else {
					throw new Exception("Couldn't find your item!");
				}
			} catch (Exception e) {
				System.err.println("Error processing your item choice: " + itemName);
				System.err.println(e.toString());
				System.exit(1);
			}

			ConfigurableField<?>[] fields = item.getFields();
			for (ConfigurableField<?> field : fields) {
				System.out.println("- Enter value for " + field.getPrompt());
				String input = scanner.nextLine().trim();
			
				try {
					field.setValue(input.trim());
				} catch (ClassCastException e) {
					System.out.println("Invalid type!");
				}
			}
			item.setMembersByFields(fields);
			// my sorry attempt at a UID
			item.setId(random.nextInt(1000));
			System.out.println("New Item created!: \n\n" + item.toString());
			items.put(item.getId(), item);

			isFinished = Utils.askYesOrNoQuestion(scanner, "Are you done making items?");
		}

		// Write changes to disk
		SerializationManager.saveItems(items);
	}

}
