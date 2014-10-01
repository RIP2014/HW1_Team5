
public class Predicate {

    private String id;

    public Predicate(String[] args) {

        StringBuilder id = new StringBuilder();
        for (String arg : args) {
            id.append(arg);
        }
        this.id = id.toString();

    }

    public String getId() {
        return this.id; 
    }
        

}
