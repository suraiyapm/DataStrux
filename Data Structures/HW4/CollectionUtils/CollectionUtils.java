import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

public class CollectionUtils {

    public static double average(Collection<Integer> x) {
        double sum=0;
        for (int z : x) {
            sum+=z;
        }
        return sum/(x.size());
    }
    public static int maxLength(Collection<String> x) {
        int max = 0;
        for(String z : x) {
            if (z.length()>max) max=z.length();
        }
        return max;
    }
    public static boolean containsDuplicate(Collection<Integer> x) {
        Set<Integer> intSet = new HashSet<>(x);
        //create copy check length? :D

        return !(x.size()==intSet.size());
    }
}