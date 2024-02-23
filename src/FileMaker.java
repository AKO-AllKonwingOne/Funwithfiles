import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileMaker {
    private String fileName = "data.txt";
    private File file;

    public FileMaker() {
        createFile();
        updateFileContents();
        System.out.println("File contents:");
        displayFileContents();
    }

    public void updateFileContents() {
        Scanner scanner = new Scanner(System.in);
        FileWriter fileWriter;
        while (true) {
            System.out.print("Would you like to overwrite existing content? (yes/no): ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
                System.out.print("Please enter 'yes' or 'no': ");
                response = scanner.nextLine();
            }
            else {
                try {
                    fileWriter = new FileWriter(file, response.equalsIgnoreCase("yes"));
                    break;
                }
                catch (IOException e) {
                    System.out.println("Error occurred while accessing the file.");
                    return;
                }
            }
        }

        try {
            System.out.print("Enter text to add to the file: ");
            fileWriter.write(scanner.nextLine());
            fileWriter.close();
            System.out.println("Text successfully written to the file.");
        }
        catch (IOException e) {
            System.out.println("Error occurred while writing to the file.");
        }
    }

    public void displayFileContents() {
        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();
        }
        catch (IOException e) {
            System.out.println("Error occurred while accessing the file.");
        }
    }

    public void createFile() {
        file = new File(fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            }
            else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("Error occurred while creating the file.");
        }
    }
}