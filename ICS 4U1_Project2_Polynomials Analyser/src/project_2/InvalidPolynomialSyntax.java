package project_2;

/**
 * Project 2
 * @author Kelvin Njenga
 * Date: September 12th 2023
 * The InvalidPolynomialSyntax class defines a custom unchecked exception that is thrown when the supplied polynomial string contains improper coefficients or exponents and an error message shown.
 */
public class InvalidPolynomialSyntax extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9092865406589487108L;

	public InvalidPolynomialSyntax(String message) {
        super(message);
    }
}

