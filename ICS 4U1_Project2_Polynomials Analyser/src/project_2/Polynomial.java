package project_2;

import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Project 2
 * @author Kelvin Njenga
 * Date: September 12th 2023
 * The Polynomial class represents an individual polynomial and defines methods for comparing, iterating, and converting polynomials to strings.
 * It implements the Comparable and Iterable interfaces.
 */

public class Polynomial implements Iterable<Polynomial.Term>, Comparable<Polynomial> {

    // Static nested class representing a term in the polynomial
    public static class Term {
        double coefficient;
        int exponent;

        public Term(double coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }

        @Override
        public String toString() {
            if (exponent == 0) {
                return String.format("%.1f", coefficient);
            } else if (exponent == 1) {
                return String.format("%.1fx", coefficient);
            } else {
                return String.format("%.1fx^%d", coefficient, exponent);
            }
        }
    }

    private Node head;
    private int size;

    private class Node {
        Term term;
        Node next;

        Node(Term term) {
            this.term = term;
        }
    }

    // Constructor that accepts a string to define a polynomial
    public Polynomial(String polynomialString) {
        head = null;
        setSize(0);
        StringTokenizer tokenizer = new StringTokenizer(polynomialString);
        while (tokenizer.hasMoreTokens()) {
            double coefficient = Double.parseDouble(tokenizer.nextToken());
            int exponent = Integer.parseInt(tokenizer.nextToken());
            addTerm(coefficient, exponent);
        }
    }

    // Compare two polynomials using compareTo method
    public int compareTo(Polynomial other) {
        // Implement comparison logic
        Iterator<Term> thisIterator = this.iterator();
        Iterator<Term> otherIterator = other.iterator();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            Term thisTerm = thisIterator.next();
            Term otherTerm = otherIterator.next();

            // Compare exponents
            if (thisTerm.exponent < otherTerm.exponent) {
                return -1;
            } else if (thisTerm.exponent > otherTerm.exponent) {
                return 1;
            }

            // Compare coefficients even if exponents are equal
            if (thisTerm.coefficient < otherTerm.coefficient) {
                return -1; // Weak order by coefficient
            } else if (thisTerm.coefficient > otherTerm.coefficient) {
                return 1; // Strong order
            }
        }

        // Handle cases where one polynomial is a prefix of the other
        if (thisIterator.hasNext()) {
            return 1; // Other polynomial is shorter
        } else if (otherIterator.hasNext()) {
            return -1; // This polynomial is shorter
        }

        return 0; // Polynomials are equal
    }

    // Iterator method to iterate across polynomial terms
    @Override
    public Iterator<Term> iterator() {
        return new Iterator<Term>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Term next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                Term term = current.term;
                current = current.next;
                return term;
            }
        };
    }

    // Add a new term to the polynomial
    private void addTerm(double coefficient, int exponent) {
        Term term = new Term(coefficient, exponent);
        if (head == null || exponent > head.term.exponent) {
            Node newNode = new Node(term);
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && exponent < current.next.term.exponent) {
                current = current.next;
            }
            Node newNode = new Node(term);
            newNode.next = current.next;
            current.next = newNode;
        }
        setSize(getSize() + 1);
    }

    // Convert polynomial to a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Term> iterator = this.iterator();
        while (iterator.hasNext()) {
            Term term = iterator.next();
            if (term.coefficient != 0.0) {
                if (sb.length() > 0) {
                    sb.append(" + ");
                }
                sb.append(term.toString());
            }
        }
        return sb.toString();
    }

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public int compareToExponents(Polynomial other) {
	    Iterator<Term> thisIterator = this.iterator();
	    Iterator<Term> otherIterator = other.iterator();

	    while (thisIterator.hasNext() && otherIterator.hasNext()) {
	        Term thisTerm = thisIterator.next();
	        Term otherTerm = otherIterator.next();

	        if (thisTerm.exponent < otherTerm.exponent) {
	            return -1; // Weak order by exponents only
	        } else if (thisTerm.exponent > otherTerm.exponent) {
	            return 1; // Weak order by exponents only
	        }
	    }

	    return compareToCoefficients(other); // Compare coefficients if exponents are equal
	}

	public int compareToCoefficients(Polynomial other) {
	    Iterator<Term> thisIterator = this.iterator();
	    Iterator<Term> otherIterator = other.iterator();

	    while (thisIterator.hasNext() && otherIterator.hasNext()) {
	        Term thisTerm = thisIterator.next();
	        Term otherTerm = otherIterator.next();

	        if (thisTerm.coefficient < otherTerm.coefficient) {
	            return -1; // Weak order by coefficients
	        } else if (thisTerm.coefficient > otherTerm.coefficient) {
	            return 1; // Weak order by coefficients
	        }
	    }

	    return 0; // Weak order by coefficients
	}



}
