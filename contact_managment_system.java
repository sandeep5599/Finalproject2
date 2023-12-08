import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private boolean isFriend;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isFriend = false;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Friend: " + (isFriend ? "Yes" : "No");
    }
}

class FriendManagementSystem {
    private ArrayList<Contact> contacts;

    public FriendManagementSystem() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact, boolean addToFriends) {
        contacts.add(contact);
        System.out.println(contact.getName() + " has been added to your contacts.");

        if (addToFriends) {
            contact.setFriend(true);
            System.out.println(contact.getName() + " has been added to your friends.");
        }
    }

    public void removeContact(Contact contact) {
        if (contacts.remove(contact)) {
            System.out.println(contact.getName() + " has been removed from your contacts.");
        } else {
            System.out.println(contact.getName() + " is not in your contacts.");
        }
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Your contacts list is empty.");
        } else {
            System.out.println("Your Contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void displayFriends() {
        ArrayList<Contact> friendList = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.isFriend()) {
                friendList.add(contact);
            }
        }

        if (friendList.isEmpty()) {
            System.out.println("You have no friends in your contacts.");
        } else {
            System.out.println("Your Friends:");
            for (Contact friend : friendList) {
                System.out.println(friend);
            }
        }
    }

    public void searchContact(String contactName) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(contactName)) {
                System.out.println("Contact found:\n" + contact);
                return;
            }
        }
        System.out.println("Contact not found with name: " + contactName);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}

class FriendManagementSystemApp {
    public static void main(String[] args) {
        FriendManagementSystem friendSystem = new FriendManagementSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            try {
                System.out.println("\nMenu:");
                System.out.println("1. Add a contact");
                System.out.println("2. Delete a contact");
                System.out.println("3. Display all contacts");
                System.out.println("4. Display friends");
                System.out.println("5. Search for a contact");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter the phone number: ");
                        String phoneNumber = scanner.nextLine();

                        System.out.print("Do you want to add " + name + " to your friends? (yes/no): ");
                        String addToFriendsChoice = scanner.nextLine().toLowerCase();
                        boolean addToFriends = addToFriendsChoice.equals("yes");

                        Contact newContact = new Contact(name, phoneNumber);
                        friendSystem.addContact(newContact, addToFriends);
                        break;

                    case 2:
                        System.out.print("Enter the name of the contact to remove: ");
                        String contactNameToRemove = scanner.nextLine();

                        for (Contact contact : friendSystem.getContacts()) {
                            if (contact.getName().equalsIgnoreCase(contactNameToRemove)) {
                                friendSystem.removeContact(contact);
                                break;
                            }
                        }
                        break;

                    case 3:
                        friendSystem.displayContacts();
                        break;

                    case 4:
                        friendSystem.displayFriends();
                        break;

                    case 5:
                        System.out.print("Enter the name to search for: ");
                        String searchName = scanner.nextLine();
                        friendSystem.searchContact(searchName);
                        break;

                    case 6:
                        System.out.println("Exiting the Friend Management System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = 0;
            } catch (NoSuchElementException e) {
                System.out.println("Input not provided. Exiting.");
                choice = 6;
            }

        } while (choice != 6);

        scanner.close();
    }
}
