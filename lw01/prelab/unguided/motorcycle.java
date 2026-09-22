package unguided;

public class motorcycle extends washservice {

    public motorcycle(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public String label() {
        return "mono";
    }

    @Override
    public int calculatecharge() {

          int cost = 0;
        if (days <= 10) {
            cost = days * 1500;
        }
        cost += 5000; 
        return cost;
    }
}