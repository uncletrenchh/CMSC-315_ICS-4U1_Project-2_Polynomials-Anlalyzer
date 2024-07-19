package project_2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project 2
 * @author Kelvin Njenga
 * Date: September 12th 2023
 * This is the Main class that contains the main method and handles the program's execution.
 * It allows the user to select an input file, read polynomials, display them, and check if they are in sorted order using both strong and weak orders.
 */

public class Main {

    public static void main(String[] args) {
        try {
            // Use JFileChooser to select the input file
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();

                // Read polynomials from the file and create Polynomial objects
                List<Polynomial> polynomials = readPolynomialsFromFile(fileName);

                // Display the polynomials using toString()
                System.out.println("Polynomials:");
                for (Polynomial polynomial : polynomials) {
                    System.out.println(polynomial.toString());
                }

             // Check for strictly strong order (both coefficients and exponents)
                boolean strictlyStrongOrder = true;
                for (int i = 1; i < polynomials.size(); i++) {
                    if (polynomials.get(i).compareTo(polynomials.get(i - 1)) <= 0) {
                        strictlyStrongOrder = false;
                        break;
                    }
                }

                // Check for weak order in coefficients
                boolean weakOrderCoefficients = true;
                for (int i = 1; i < polynomials.size(); i++) {
                    if (polynomials.get(i).compareToCoefficients(polynomials.get(i - 1)) < 0) {
                        weakOrderCoefficients = false;
                        break;
                    }
                }

                // Check for weak order in exponents
                boolean weakOrderExponents = true;
                for (int i = 1; i < polynomials.size(); i++) {
                    if (polynomials.get(i).compareToExponents(polynomials.get(i - 1)) < 0) {
                        weakOrderExponents = false;
                        break;
                    }
                }

             // Display the results
                if (strictlyStrongOrder) {
                    System.out.println("The list is sorted according to strictly strong order.");
                } else if (weakOrderCoefficients && weakOrderExponents) {
                    System.out.println("The list is sorted according to weak order in coefficients and exponents.");
                } else if (weakOrderCoefficients) {
                    System.out.println("The list is sorted according to weak order in coefficients.");
                } else if (weakOrderExponents) {
                    System.out.println("The list is sorted according to weak order in exponents.");
                } else {
                    System.out.println("The list is not sorted according to any order.");
                }            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        } catch (InvalidPolynomialSyntax e) {
            JOptionPane.showMessageDialog(null, "Invalid Polynomial Syntax: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Read polynomials from the input file and return a list of Polynomial objects
    private static List<Polynomial> readPolynomialsFromFile(String fileName) throws IOException {
        List<Polynomial> polynomials = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                polynomials.add(new Polynomial(line));
            }
        }
        return polynomials;
    }
}
