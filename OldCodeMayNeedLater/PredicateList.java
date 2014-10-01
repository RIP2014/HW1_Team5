import java.util.Collection;
import java.util.ArrayList;

public class PredicateList {

    private ArrayList<String> predicates; 

    public PredicateList() {
        this.predicates = new ArrayList<String>();
    }

    public PredicateList(Collection<Predicate> predicates) {
        this();
        String[] predicatesArray = predicates.toArray(new String[0]);
        for ( String predicate : predicatesArray ) {
            this.predicates.add(predicate);    
        }

    }

    public boolean add(String predicate) {
        return this.predicates.add(predicate);
    }

    public boolean add(String[] predicateArray) {
        String predicate = buildPredicate(predicateArray);
        return this.add(predicate);
    }

    public boolean contains(String predicate) {
        return this.predicates.contains(predicate);
    }

    public boolean contains(String[] predicateArray) {
        String predicate = buildPredicate(predicateArray);
        return this.contains(predicate);

    }

    public boolean remove(String predicate) {
        return this.predicates.remove(predicate);
    }

    public boolean remove(String[] predicateArray) {
        String predicate = buildPredicate(predicateArray);
        return this.remove(predicate);
    }

    private String buildPredicate(String[] predicateArray) {
        StringBuilder predicate = new StringBuilder();
        for ( String arg : predicateArray ) {
            predicate.append(arg);
        }

        return predicate.toString();
    }
}
