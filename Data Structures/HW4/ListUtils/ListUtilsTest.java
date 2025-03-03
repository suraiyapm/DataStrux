//(not my code, copied from assignment to test and debug)

// DO NOT MODIFY THIS FILE.

import java.util.ArrayList;
import java.util.List;

public class ListUtilsTest {
    public static void main(String[] args) {
        testGetInitials();
        testSwapFirstAndLast();
        testFirstN();
        testLastN();
        testIsSorted();
        testPrintSorted();
    }

    private static void testGetInitials() {
        System.out.println("Testing getInitials");

        List<String> list1 = List.of();
        System.out.println(ListUtils.getInitials(list1)); //

        List<String> list2 = List.of("one");
        System.out.println(ListUtils.getInitials(list2)); // o

        List<String> list3 = List.of("one", "two", "three");
        System.out.println(ListUtils.getInitials(list3)); // ott

        System.out.println();
    }

    private static void testSwapFirstAndLast() {
        System.out.println("Testing swapFirstAndLast");

        List<String> list1 = new ArrayList<>(List.of("one"));
        ListUtils.swapFirstAndLast(list1);
        System.out.println(list1); // [one]

        List<String> list2 = new ArrayList<>(List.of("one", "two"));
        ListUtils.swapFirstAndLast(list2);
        System.out.println(list2); // [two, one]

        List<String> list3 = new ArrayList<>(List.of("one", "two", "three"));
        ListUtils.swapFirstAndLast(list3);
        System.out.println(list3); // [three, two, one]

        List<String> list4 = new ArrayList<>(List.of("one", "two", "three", "four"));
        ListUtils.swapFirstAndLast(list4);
        System.out.println(list4); // [four, two, three, one]

        System.out.println();
    }

    private static void testFirstN() {
        System.out.println("testing firstN");

        List<Integer> list1 = List.of();
        System.out.println(ListUtils.firstN(list1, 0)); // []

        List<Integer> list2 = List.of(5);
        System.out.println(ListUtils.firstN(list2, 0)); // []
        System.out.println(ListUtils.firstN(list2, 1)); // [5]

        List<Integer> list3 = List.of(6, 4, 7);
        System.out.println(ListUtils.firstN(list3, 0)); // []
        System.out.println(ListUtils.firstN(list3, 1)); // [6]
        System.out.println(ListUtils.firstN(list3, 2)); // [6, 4]
        System.out.println(ListUtils.firstN(list3, 3)); // [6, 4, 7]

        List<Integer> list4 = List.of(10, 6, 4, 7);
        System.out.println(ListUtils.firstN(list4, 3)); // [10, 6, 4]
        System.out.println(ListUtils.firstN(list4, 4)); // [10, 6, 4, 7]

        System.out.println();
    }

    private static void testLastN() {
        System.out.println("testing lastN");

        List<Integer> list1 = List.of();
        System.out.println(ListUtils.lastN(list1, 0)); // []

        List<Integer> list2 = List.of(5);
        System.out.println(ListUtils.lastN(list2, 0)); // []
        System.out.println(ListUtils.lastN(list2, 1)); // [5]

        List<Integer> list3 = List.of(6, 4, 7);
        System.out.println(ListUtils.lastN(list3, 0)); // []
        System.out.println(ListUtils.lastN(list3, 1)); // [7]
        System.out.println(ListUtils.lastN(list3, 2)); // [4, 7]
        System.out.println(ListUtils.lastN(list3, 3)); // [6, 4, 7]

        List<Integer> list4 = List.of(10, 6, 4, 7);
        System.out.println(ListUtils.lastN(list4, 3)); // [6, 4, 7]
        System.out.println(ListUtils.lastN(list4, 4)); // [10, 6, 4, 7]

        System.out.println();
    }

    private static void testIsSorted() {
        System.out.println("Testing isSorted");

        System.out.println(ListUtils.isSorted(List.of())); // true
        System.out.println(ListUtils.isSorted(List.of(5.5))); // true
        System.out.println(ListUtils.isSorted(List.of(5.5, 5.9))); // true
        System.out.println(ListUtils.isSorted(List.of(5.5, 5.1))); // false
        System.out.println(ListUtils.isSorted(List.of(5.5, 6.5, 6.5))); // true
        System.out.println(ListUtils.isSorted(List.of(5.5, 6.5, 6.1))); // false

        System.out.println();
    }

    private static void testPrintSorted() {
        System.out.println("Testing printSorted");

        ListUtils.printSorted(List.of()); //
        ListUtils.printSorted(List.of("one")); // one
        ListUtils.printSorted(List.of("one", "two")); // one two
        ListUtils.printSorted(List.of("one", "two", "three")); // one three two
        ListUtils.printSorted(List.of("one", "two", "three", "four")); // four one three two
        ListUtils.printSorted(List.of("a", "b", "a", "a")); // a a a b

        System.out.println();
    }
}