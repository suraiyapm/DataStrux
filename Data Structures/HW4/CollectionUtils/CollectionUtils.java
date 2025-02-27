import java.util.Collection;
import java.util.Set;
public class CollectionUtils {

    public double average(Collection<Integer> x) {
        double sum=0;
        for (int z : x) {
            sum+=z;
        }
        return sum/(x.size());
    }
    public int maxLength(Collection<String> x) {
        int max = 0;
        for(String z : x) {
            if (z.length()>max) max=z.length();
        }
        return max;
    }
    public boolean containsDuplicate(Collection<Integer> x) {
        Set<Integer> intSet = new Set<>;
//create copy check length? :D
    }
}