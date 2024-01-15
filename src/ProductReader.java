import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProductReader {

    public static void main(String[] args) {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            try {
                displayProductFile(selectedFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void displayProductFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            // Assuming the first line contains column headers
            String headers = scanner.nextLine();
            System.out.println("ID      Name           Description               Cost");
            System.out.println("=====================================================");
            System.out.println(headers); // Print the first line under the header

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Adjust the split to handle commas in a CSV format
                String[] parts = line.split(",\\s*");

                // Check if the array has enough elements before accessing
                if (parts.length >= 3) {
                    // Assuming your Product class has id, name, description, and cost fields
                    String id = parts.length > 0 ? parts[0] : "";
                    String name = parts.length > 1 ? parts[1] : "";
                    String description = parts.length > 2 ? parts[2] : "";
                    String cost = parts.length > 3 ? parts[3] : "Missing Cost";

                    // Manually format the output with spaces between the id and other columns
                    String formattedLine = String.format("%s\t%-15s%-25s%-10s", id, name, description, cost);
                    System.out.println(formattedLine);
                } else {
                    // Handle the case where the line doesn't have enough elements
                    System.out.println("Invalid data format: " + line);
                }
            }
        }
    }
}
