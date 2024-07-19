package project_2;

import java.util.Comparator;
import java.util.List;

/**
 * Project 2
 * @author Kelvin Njenga
 * Date: September 12th 2023
 * The OrderedList class is the utility class containing methods for checking whether a list is in strictly ascending order.
 * It provides two overloaded methods for checking order based on Comparable or custom Comparator.
 */

public class OrderedList {

    // Check if a List is sorted using Comparable
    public static <T extends Comparable<T>> boolean checkSorted(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    // Check if a List is sorted using a custom Comparator
    public static <T> boolean checkSorted(List<T> list, Comparator<T> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
