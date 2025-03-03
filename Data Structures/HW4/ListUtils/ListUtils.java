import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class ListUtils  {
    public static String getInitials(List<String> x) {
        String s = "";
        for (String z : x) {
            s+=z.charAt(0);
        }
        return s;
    }
    public static void swapFirstAndLast(List<String> x) {
        String temp = x.get(0);
        x.set(0, x.get(x.size()-1));
        x.set(x.size()-1, temp);
    }
    public static List<Integer> firstN(List<Integer> x, int n) {
        List<Integer> list = new ArrayList<>(n);
        for(int i=0; i<n-1; i++) {
            list.add(x.get(i));
        }
        return list;
    }
    public static List<Integer> lastN(List<Integer> x, int n) {
        List<Integer> list = new ArrayList<>(n);
        for(int i=x.size()-1; i>x.size()-n; i--) {
            list.add(x.get(i));
        }
        return list;
    }
    public static boolean isSorted(List<Double> x) {

    }

}