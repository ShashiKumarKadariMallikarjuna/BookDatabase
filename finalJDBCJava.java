package databasepro;

import java.sql.*;
import java.util.Scanner;
/**
 * @author Shashi Kumar Kadari Mallikarjuna
 * @author Shivam Gupta
 * @version  3/19/2019
 */
public class DatabasePro {
    // JDBC driver name and Database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/JDBC;user=jdbc;password=jdbc";
    static Scanner in = new Scanner(System.in);
    // String formatting for printing the writing groups, publisers nad books data
    static final String WRITING_GROUPS_FORMAT = "%-30s%-30s%-20s%-30s\n";
    static final String PUBLISHERS_FORMAT = "%-40s%-60s%-20s%-50s\n";
    static final String BOOKS_FORMAT = "%-25s%-30s%-25s%-20s%-20s\n";
    
    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null; // Initialize the connection
        Statement stmt = null;  // Initialize the statement that we're using
        try {
            // Loading the oracle JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            // Establishing a connection
            conn = DriverManager.getConnection(DB_URL);
            // Obtaining statement object from connection
            stmt = conn.createStatement();
            String sql = "";
            boolean repeat = true;
            int choice;
            int subchoice;
            String query = "";
            PreparedStatement pstmt = null;// Initializing preparedstatement
            ResultSet rs = null;
            // While loop to execute the option selected by the user
            while(repeat) {
                choice = mainMenu(); // User input
                switch(choice) {
                // If user chooses to display writing groups data
                case 1:
                    subchoice = writingGroupsMenu(); // Option to execute
                    switch(subchoice) {
                    // If user chooses to display all the details of the writing groups
                    case 1:
                        displayAllGroups(conn);
                        break;
                    // If user chooses to display all the details of a particular writing
                    // group along with all data for associated books and publshers
                    case 2:
                        displaySpecificGroup(conn);
                        break;
                    default:
                        break;
                    }
                    break;
                // If user chooses to display publishers data
                case 2:
                    subchoice = publishersMenu(); // option to execute
                    switch(subchoice) {
                    // if user chooses to display all the details of publishers
                    case 1:
                        displayAllPublishers(conn);
                        break;
                    // if user chooses to display all the details of a particular
                    // publisher along with all the data for the associated books
                    // and writing groups
                    case 2:
                        displaySpecificPublisher(conn);
                        break;
                    default:
                        break;
                    }
                    break;
                // if user chooses to display books data
                case 3:
                    subchoice = booksMenu(); // option to execute
                    switch(subchoice) {
                    // if user chooses to display all the book Titles
                    case 1:
                        displayAllBooks(conn);
                        break;
                    // if user chooses to display all the details of a 
                    // particular book along with all the data for the
                    // associated publisher and writing group
                    case 2:
                        displaySpecificBook(conn);
                        break;
                    default:
                        break;
                    }
                    break;
                // if user chooses to insert data into the database
                case 4:
                    subchoice = insertDataMenu(); // option to execute
                    switch(subchoice) {
                    // if user chooses to insert a new book
                    case 1:
                        insertBook(conn);
                        break;
                    // if user chooses to insert a publisher
                    case 2:
                        insertPublisher(conn);
                        break;
                    default:
                        break;
                    }
                    break;
                case 5:
                    subchoice = removeDataMenu(); // option to execute
                    switch(subchoice) {
                    case 1:
                        removeSpecificBook(conn);
                        break;
                    default:
                        break;
                    }
                    break;
                case 6:
                    repeat = false;
                    break;
                default:
                    break;
                }
                System.out.println();
            }   
            stmt.close();
            conn.close();
        } catch (SQLNonTransientConnectionException snce) {
            System.out.println("Server not started");
        } catch (SQLException | ClassNotFoundException se) {
            // handle errors for JDBC
            se.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        }
    }
    /**
     * Main menu
     * @return the user choice after input validation
     */
    public static int mainMenu() {
	System.out.println("--------Main Menu--------");
	System.out.println("1. List writing groups");
	System.out.println("2. List Publishers");
	System.out.println("3. List Books");
	System.out.println("4. Insert Data");
	System.out.println("5. Remove Data");
	System.out.println("6. Exit");
	return checkChoice(1,6);
    }
    /**
     * writing Groups menu which is displayed if the user chooses to display
     * the data from writing groups
     * @return the user choice after input validation
     */
    public static int writingGroupsMenu() {
	System.out.println("--------Sub Menu--------");
	System.out.println("1. List all writing groups");
	System.out.println("2. List all data for a specific group");
	System.out.println("3. Main Menu");
	return checkChoice(1, 3);
    }
    /**
     * publishers menu which is displayed if the user chooses to display
     * the data from publishers
     * @return the user choice after input validation
     */
    public static int publishersMenu() {
	System.out.println("--------Sub Menu--------");
	System.out.println("1. List all publishers");
	System.out.println("2. List all data for a specific publisher");
	System.out.println("3. Main Menu");
	return checkChoice(1, 3);
    }
    /**
     * books menu which is displayed if the user chooses to display the data from books
     * @return the user choice after input validation
     */
    public static int booksMenu() {
	System.out.println("--------Sub Menu--------");
	System.out.println("1. List all book titles");
	System.out.println("2. List all data for a specific book");
	System.out.println("3. Main Menu");
	return checkChoice(1, 3);
    }
    /** 
     * insert data menu which is displayed if the user chooses to insert data
     * into the database
     * @return the user choice after input validation
     */
    public static int insertDataMenu() {
	System.out.println("--------Sub Menu--------");
	System.out.println("1. Insert a new book");
	System.out.println("2. Insert a new publisher");
	System.out.println("3. Main Menu");
	return checkChoice(1, 3);
    }
    /**
     * remove data menu which is displayed if the user chooses to remove data
     * from the database
     * @return the user choice after input validation
     */ 
    public static int removeDataMenu() {
	System.out.println("--------Sub Menu--------");
	System.out.println("1. Remove a specific book");
	System.out.println("2. Main Menu");
	return checkChoice(1, 2);
    }
    /**
     * method to display all the writing groups
     * @param conn Connection
     * @throws SQLException 
     */
    public static void displayAllGroups(Connection conn) throws SQLException {
	Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM writingGroups"; // sql statement to execute
        ResultSet rs = stmt.executeQuery(sql); // retrieving data
        int i=1;
        System.out.printf("    "+WRITING_GROUPS_FORMAT,"Group Name", "Head Writer", "Year Formed", "Subject");
        // while loop to print the data from the writing groups
        while (rs.next()) {
            // retrieve by column name
            String groupName = rs.getString("groupName");
            String headWriter = rs.getString("headWriter");
            String yearFormed = rs.getString("yearFormed");
            String subject = rs.getString("subject");
            // display values
            System.out.printf("%-4d"+ WRITING_GROUPS_FORMAT,(i++) ,groupName, headWriter, yearFormed, subject);
        }
        rs.close();
        stmt.close();
    }
    /**
     * method to display all the publishers
     * @param conn Connection
     * @throws SQLException 
     */
    public static void displayAllPublishers(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Publishers"; // sql statement
        ResultSet rs = stmt.executeQuery(sql); // retrieving data
        int i=1;
        System.out.printf("    "+PUBLISHERS_FORMAT, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
        // while loop to print the data from the publishers
        while (rs.next()) {
            // retrieve by column name
            String publisherName = rs.getString("publisherName");
            String publisherAddress = rs.getString("publisherAddress");
            String publisherPhone = rs.getString("publisherPhone");
            String publisherEmail = rs.getString("publisherEmail");
            // display values
            System.out.printf("%-4d"+PUBLISHERS_FORMAT,(i++),publisherName, publisherAddress, publisherPhone, publisherEmail);
        }
        rs.close();
        stmt.close();
    }
       /**
        * method to display all the book titles
        * @param conn Connection
        * @throws SQLException 
        */
    public static void displayAllBooks(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Books"; // sql statement
        ResultSet rs = stmt.executeQuery(sql); // retrieving data
        int i=1;
        System.out.printf("    "+"%-40s\n", "Book Title"); 
        // while loop to print the book Titles
        while (rs.next()) {
            String bookTitle = rs.getString("bookTitle");
            System.out.printf("%-4d"+"%-40s\n",(i++),bookTitle);
        }
        rs.close();
        stmt.close();
    }
    /**
     * method to display the data for a group specified by the user
     * @param conn Connection
     * @throws SQLException 
     */
    public static void displaySpecificGroup(Connection conn) throws SQLException {
	in.nextLine();
	// user input of the wriring group name
	System.out.print("Enter a specific writing group name: ");
	String wgName = in.nextLine();
        String query = "SELECT * FROM writingGroups NATURAL JOIN books NATURAL JOIN publishers where groupName = ?"; //sql statement
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, wgName); // assigning the where condition with the user input
        ResultSet rs = pstmt.executeQuery(); // retrieving data
        int i=1;
        if (rs.next()) {
            System.out.printf("    "+"%-25s%-25s%-20s%-20s%-20s%-30s%-20s%-20s%-50s%-20s%-20s\n", "Group Name", "Publisher Name", "Head Writer", "Year Formed", "Subject", "Book Title", "Year Published", "No. of Pages", "Publisher Adrress", "Publisher Phone", "Publisher Email");
            // while loop to print the data from the writing groups, publishers, and books
            do {
                // retrieve by column name
                String groupName = rs.getString("groupName");
                String publisherName = rs.getString("publisherName");
                String headWriter = rs.getString("headWriter");
                String yearFormed = rs.getString("yearFormed");
                String subject = rs.getString("subject");
                String bookTitle = rs.getString("bookTitle");
                String yearPublished = rs.getString("yearPublished");
                String numberPages = rs.getString("numberPages");
                String publisherAddress = rs.getString("publisherAddress");
                String publisherPhone = rs.getString("publisherPhone");
                String publisherEmail = rs.getString("publisherEmail");
                // display values
                System.out.printf("%-4d%-25s%-25s%-20s%-20s%-20s%-30s%-20s%-20s%-50s%-20s%-20s\n",(i++) ,groupName, publisherName, headWriter, yearFormed, subject, bookTitle, yearPublished, numberPages, publisherAddress, publisherPhone, publisherEmail);
            } while (rs.next());
        } else {
            System.out.println("No data found");
        }
        rs.close();
        pstmt.close();
    }
    /**
     * method to display the data for a publisher specified by the user
     * @param conn Connection
     * @throws SQLException 
     */
    public static void displaySpecificPublisher(Connection conn) throws SQLException {
	in.nextLine();
	// user input of the publisher name
	System.out.print("Enter a specific publisher name: ");
	String pubName = in.nextLine();

	String query = "SELECT * FROM publishers NATURAL JOIN books NATURAL JOIN writinggroups where publisherName = ?";
	PreparedStatement pstmt = conn.prepareStatement(query); 
        pstmt.clearParameters(); // clearing the parameters
        pstmt.setString(1, pubName); // assigning the where condition with the user input                           
        ResultSet rs = pstmt.executeQuery(); // retrieving data
        int i=1;
        if (rs.next()) {
            System.out.printf("    "+"%-25s%-50s%-20s%-40s%-30s%-20s%-20s%-25s%-20s%-20s%-20s\n", "Publisher Name", "Publisher Adrress", "Publisher Phone", "Publisher Email", "Book Title", "Year Published", "No. of Pages", "Group Name", "Head Writer", "Year Formed", "Subject");
            // do while loop to print the data from the publishers, books and writingGroups
            do {
                // retrieve by column name
                String groupName = rs.getString("groupName");
                String publisherName = rs.getString("publisherName");
                String headWriter = rs.getString("headWriter");
                String yearFormed = rs.getString("yearFormed");
                String subject = rs.getString("subject");
                String bookTitle = rs.getString("bookTitle");
                String yearPublished = rs.getString("yearPublished");
                String numberPages = rs.getString("numberPages");
                String publisherAddress = rs.getString("publisherAddress");
                String publisherPhone = rs.getString("publisherPhone");
                String publisherEmail = rs.getString("publisherEmail");
                // display values
                System.out.printf("%-4d%-25s%-50s%-20s%-40s%-30s%-20s%-20s%-25s%-20s%-20s%-20s\n",(i++) ,publisherName, publisherAddress, publisherPhone, publisherEmail, bookTitle, yearPublished, numberPages, groupName, headWriter, yearFormed, subject);
            } while (rs.next());
        } else {
            System.out.println("No data found");
        }
        rs.close();
        pstmt.close();
    }
    /**
     * method that displays the data for a single book specified by the user
     * @param conn Connection
     * @throws SQLException 
     */
    public static void displaySpecificBook(Connection conn) throws SQLException {
        in.nextLine();
        // user input of the book Title
        System.out.print("Enter a specific book title:  ");
        String userbookTitle = in.nextLine();
        System.out.print("Enter a specific group name:  ");
        String usergroupName = in.nextLine();
        
        String query = "SELECT * FROM publishers NATURAL JOIN books NATURAL JOIN writinggroups where bookTitle = ? AND groupName = ?"; // sql statement
        PreparedStatement pstmt = conn.prepareStatement(query); 
        pstmt.clearParameters(); // clearing the parameters
        pstmt.setString(1, userbookTitle); // assigning the where condition with the user input
        pstmt.setString(2, usergroupName); // assigning the where condition with the user input
        ResultSet rs = pstmt.executeQuery(); // retrieving data
        int i=1;
        if (rs.next()) {
            System.out.printf("    "+"%-30s%-20s%-20s%-25s%-20s%-20s%-20s%-25s%-50s%-20s%-20s\n","Book Title", "Year Published", "No. of Pages", "Group Name",  "Head Writer", "Year Formed", "Subject", "Publisher Name","Publisher Adrress", "Publisher Phone", "Publisher Email");
            // do while loop to print the data from the books, publishers and writingGroups
            do {
                // retrieve by column name
                String groupName = rs.getString("groupName");
                String publisherName = rs.getString("publisherName");
                String headWriter = rs.getString("headWriter");
                String yearFormed = rs.getString("yearFormed");
                String subject = rs.getString("subject");
                String bookTitle = rs.getString("bookTitle");
                String yearPublished = rs.getString("yearPublished");
                String numberPages = rs.getString("numberPages");
                String publisherAddress = rs.getString("publisherAddress");
                String publisherPhone = rs.getString("publisherPhone");
                String publisherEmail = rs.getString("publisherEmail");
                // display values
                System.out.printf("%-4d%-30s%-20s%-20s%-25s%-20s%-20s%-20s%-25s%-50s%-20s%-20s\n",(i++),bookTitle, yearPublished, numberPages, groupName, headWriter, yearFormed, subject, publisherName, publisherAddress, publisherPhone, publisherEmail);
            } while (rs.next());
        } else {
            System.out.println("No data found");
        }
        rs.close();
        pstmt.close();
    }
    /**
     * method to insert a new book into the database
     * @param conn Connection
     */
    public static void insertBook(Connection conn) {
        try {
            String query = "INSERT INTO books(groupName, bookTitle, publisherName, yearPublished, numberPages) VALUES (?, ?, ?, ?, ?)"; // sql statement
            PreparedStatement pstmt = conn.prepareStatement(query);
            in.nextLine();
            int i=1;
            System.out.print("Enter Group name: ");
            String usergroupName = in.nextLine(); // gets groupName from user
            if (usergroupName.equals("")) {
                System.out.println("Group name missing");
                return;
            }
            System.out.print("Enter publisher name: ");
            String userpublisherName = in.nextLine(); // gets publisherName from user
            if (userpublisherName.equals("")) {
                System.out.println("Publisher name missing");
                return;
            }
            System.out.print("Enter book title: ");
            String userbookTitle = in.nextLine(); // gets bookTitle from user
            System.out.print("Enter year published: ");
            if (!in.hasNextInt()) {
                System.out.println("year published can only be integers!");
                in.nextLine();
                return;
            }
            int useryearPublished = in.nextInt(); // gets the year book was published from user
            System.out.print("Enter number of pages: ");
            if (!in.hasNextInt()) {
                System.out.println("number of pages can only be integers!");
                in.nextLine();
                return;
            }
            int usernumberPages = in.nextInt(); // gets the number of pages in the book frmo user
            // assigns the ? with the associated user inputs
            pstmt.setString(1, usergroupName);
            pstmt.setString(2, userbookTitle);
            pstmt.setString(3, userpublisherName);
            pstmt.setInt(4, useryearPublished);
            pstmt.setInt(5, usernumberPages);
            pstmt.executeUpdate();
            // if the book was successfully added to the database,it prints all the data related to the book which was added
            String sql = "SELECT * FROM books WHERE bookTitle = ? AND groupName = ?"; // sql statament
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userbookTitle); // assigning the where condition with the book Title
            pstmt.setString(2, usergroupName); // assigning the where condition with the groupName
            ResultSet rs = pstmt.executeQuery();
            System.out.printf("    "+BOOKS_FORMAT, "Group Name", "Book Title", "Publisher Name", "Year Published", "Num. of Pages");
            // while loop prints all the data of the particular book that was added to the database
            while (rs.next()) {
                // retrieve by column name
                String groupName = rs.getString("groupName");
                String bookTitle = rs.getString("bookTitle");
                String publisherName = rs.getString("publisherName");
                String yearPublished = rs.getString("yearPublished");
                String numberPages = rs.getString("numberPages");
                // display values
                System.out.printf("%-4d"+BOOKS_FORMAT,(i++) ,groupName, bookTitle, publisherName, yearPublished, numberPages);
            }
            System.out.println("Book inserted.");
            rs.close();
        } catch (SQLException se) {
            if (se.getMessage().contains("caused a violation of foreign key constraint 'BOOKS_WRITINGGROUPS_FK'")) {
                System.out.println("The group name you entered does not exist. Try a different group name.");
            } else if (se.getMessage().contains("caused a violation of foreign key constraint 'BOOKS_PUBLISHERS_FK'")) {
                System.out.println("The publisher name you entered does not exist. Try a different publisher name.");
            } else if (se.getMessage().contains("The statement was aborted because it would have caused a duplicate key value in a unique or primary key constraint or unique index identified by 'BOOKS_PK' defined on 'BOOKS'.")) {
                System.out.println("There is already a row in the table with those primary key column (group name, book title) values.");
            } else if (se.getMessage().contains("The statement was aborted because it would have caused a duplicate key value in a unique or primary key constraint or unique index identified by 'BOOKS_CK' defined on 'BOOKS'.")) {
                System.out.println("There is already a row in the table with those candidate key column (book title, publisher name) values.");
            } else {
                System.out.println(se.getMessage());
            }
        }
    }
    /**
     * method to remove a single book specified by the user
     * @param conn Connection
     * @throws SQLException 
     */
    public static void removeSpecificBook(Connection conn) throws SQLException {
        in.nextLine();
        // gets the book Title of the book which that user whishes to delete from the database
        int i=1;
        System.out.print("Enter the book title:  ");
        String userbooktitle = in.nextLine();
        System.out.print("Enter the group name:  ");
        String usergroupName = in.nextLine();
        Statement stmt=conn.createStatement();
        String query = "DELETE FROM books WHERE bookTitle = ? and groupName = ?"; // sql statement
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.clearParameters(); // clearing the parameters
        pstmt.setString(1, userbooktitle); // assigning the where condition with the user input
        pstmt.setString(2, usergroupName); // assigning the where condition with the user input
        int success = pstmt.executeUpdate();
        // if the book gets deleted successfully, it prints all the book Titles without the deleted book Title
        if (success >= 1) {
            System.out.println();
            displayAllBooks(conn);
			System.out.println("\nBook Removed!");
        }
        // if the book title entered by the user does not exist, it prints unsuccessful
        else {
            System.out.printf("%s written by %s does not exist.\n", userbooktitle, usergroupName);
        }
        pstmt.close();
    }
    /**
     * method to insert a new publisher and update all book published by one publisher to be published by the new publisher
     * @param conn
     * @throws SQLException 
     */
    public static void insertPublisher(Connection conn) throws SQLException {
        String userpublisherName = " "; // publiser name
        String userreplacepublisherName = " "; // publisher to be replaced with
        Statement stmt = conn.createStatement();
        String query = "";
        String sql = null;
        ResultSet rs=null;
        PreparedStatement pstmt = null;
        int i=1;
        try {
            query = "INSERT INTO publishers(publisherName, publisherAddress, publisherPhone, publisherEmail) VALUES (?, ?, ?, ?)"; // sql statement
            pstmt = conn.prepareStatement(query);
            in.nextLine();
            System.out.print("Enter publisher name: ");
            userpublisherName = in.nextLine(); // publisher's name
            System.out.print("Enter publisher address: "); 
            String userpublisherAddress = in.nextLine(); // publisher's address
            System.out.print("Enter publisher phone: ");
            String userpublisherPhone = in.nextLine(); // publisher's phone number
            System.out.print("Enter publisher email: ");
            String userpublisherEmail = in.nextLine(); // publisher's email address
            // assigns the ? with the associated user inputs
            pstmt.setString(1, userpublisherName);
            pstmt.setString(2, userpublisherAddress);
            pstmt.setString(3, userpublisherPhone);
            pstmt.setString(4, userpublisherEmail);
            pstmt.executeUpdate();
            // if the query successfully runs
            sql = "SELECT * FROM publishers"; // sql statement
            rs = stmt.executeQuery(sql);
            System.out.printf("    "+PUBLISHERS_FORMAT, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
            while (rs.next()) {
                // retrieve by column name
                String publisherName = rs.getString("publisherName");
                String publisherAddress = rs.getString("publisherAddress");
                String publisherPhone = rs.getString("publisherPhone");
                String publisherEmail = rs.getString("publisherEmail");
                // display values
                System.out.printf("%-4d"+PUBLISHERS_FORMAT,(i++) ,publisherName, publisherAddress, publisherPhone, publisherEmail);
            }
            System.out.println("publisher inserted.");
            rs.close();  
        } catch (SQLIntegrityConstraintViolationException constraintEx) {
            // Primary key constraint violation!
            System.out.println("That Publisher already exists in the database");
            return;
        }
        i=1;
        query = "UPDATE books SET publisherName = ? WHERE publisherName = ?"; // sql statement
        pstmt = conn.prepareStatement(query);
        System.out.print("\nEnter publisher name to replace with: ");
        userreplacepublisherName = in.nextLine(); // publisher to be replaced with
        // assigns the ? with the associated user inputs
        pstmt.setString(1, userpublisherName);
        pstmt.setString(2, userreplacepublisherName);
        int success = pstmt.executeUpdate();
        if (success >= 1) {
            sql = "SELECT * FROM books"; // sql statement
            rs = stmt.executeQuery(sql);
            System.out.printf("    "+BOOKS_FORMAT, "Group Name", "Book Title", "Publisher Name", "Year Published", "Num. of Pages");
            while (rs.next()) {
                // retrieve by column name
                String groupName = rs.getString("groupName");
                String bookTitle = rs.getString("bookTitle");
                String publisherName = rs.getString("publisherName");
                String yearPublished = rs.getString("yearPublished");
                String numberPages = rs.getString("numberPages");
                // display values
                System.out.printf("%-4d"+BOOKS_FORMAT,(i++) ,groupName, bookTitle, publisherName, yearPublished, numberPages);
            }
            System.out.println("Publisher replaced.");
            // Deletes the publisher name which exists in the publishers table
            query = "DELETE FROM publishers WHERE publisherName = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userreplacepublisherName);
            pstmt.executeUpdate();
            System.out.println("Publisher: " + userreplacepublisherName + " removed.");  
            rs.close();
            pstmt.close();
        } else {
            // Deletes the publisher name added by the user if the publisher name that the user wants to replace it with doesn't exist
            System.out.println("Unsuccessful in replacing, deleting the new publisher.");
            query = "DELETE FROM publishers WHERE publisherName = ?";//sql statement
            pstmt = conn.prepareStatement(query);
            // assigns the ? with the associated user inputs
            pstmt.setString(1, userpublisherName);
            pstmt.executeUpdate();
            pstmt.close();
        }
    }

    /**
     * Method to perform the input validation 
     * @return the user choice after input validation
     */
    public static int checkChoice(int min, int max) {
        System.out.print("Enter choice: ");
        int choice = 0;
        boolean repeat = true;
        while(repeat) {
            if (in.hasNextInt()) {
                choice = in.nextInt();
                if (choice < min || choice > max) {
                    System.out.println("Invalid choice.");
                    System.out.print("Choose again: ");
                } else {
                    repeat = false;
                }
            } else {
                in.next();
                System.out.println("Invalid choice.");
                System.out.print("Choose again: ");
            }
        }
        System.out.println();
        return choice;
    }
}