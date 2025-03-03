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
//        List<Integer> list = new ArrayList<>(n);
//        for(int i=0; i<n; i++) {
//            list.add(x.get(i));
//        }
//        return list;
        return x.subList(0, n);
    }
    public static List<Integer> lastN(List<Integer> x, int n) {
        //returning last N #s*
//        List<Integer> list = new ArrayList<>(n);
//        for(int i=x.size()-1; i>x.size()-n; i--) {
//            list.add(x.get(i));
//        }
//        return list;
        //SUBLIST goddangit we learnt that-(JCF doc slides: List<E>
        return x.subList(x.size()-n, x.size());
    }
    public static boolean isSorted(List<Double> x) {
        if (x.size()<=1||x==null) return true;
        double temp = x.get(0);
        for (int i=1; i<x.size(); i++) {
            if (x.get(i)<temp) return false;
            temp=x.get(i);
        }
        return true;
    }
    public static void printSorted(List<String> s) {
        if (s.size()==0) return;
        List<String> x = new ArrayList<>(s);
        x.sort(Comparator.naturalOrder());
//        for (String y : x) {
//            System.out.print(y + " ");
//        }
//        System.out.println();
//        for(int i=0; i<x.size()-1; i++) {
//            if (i==x.size()-1) {
//                System.out.println(x.get(i));
//            } else {
//                System.out.print(x.get(i) + " ");
//            }
//        }
        for (int i=0; i<x.size(); i++) {
            System.out.print(x.get(i));
            if (i<x.size()-1) System.out.print(" ");
        }
        System.out.println();
    }

}