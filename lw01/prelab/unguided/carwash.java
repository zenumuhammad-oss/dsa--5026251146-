package unguided;

public class carwash extends washservice {

    public carwash(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public String label() {
        return "mono";
    }

    @Override
    public int calculatecharge() {
        int cost = 0;
        if (days <= 3) {
            cost = days * 35000;
        } else {
            cost = (3 * 35000) + ((days - 3) * 25000);
        }
        cost += 15000; 
        return cost;
    }
}
