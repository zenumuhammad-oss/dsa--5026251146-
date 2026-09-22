package unguided;

public abstract class washservice implements billable {
    protected String id;
    protected int days;

    public washservice(String id, int days, int units) {
        if (days <= 1 || days > 30) {
            throw new IllegalArgumentException("days must be between 1 and 30.");
        }
        this.id = id;
        this.days = days;
    }

    public int calculateCharge() {
        int cost;
        if (days <= 10) {
            cost = days * 1500;
        } else {
            cost = (10 * 1500) + ((days - 10) * 1000);
        }
        cost += 2000; // setup cost per complete copy
        return cost;
    }

    public String getId() {
        return id;
    }

    public int getdays() {
        return days;
    }

    public String label() {
        return "Print";
    }

    // Overload calculateCharge(int copies) as requested in the instructions
    public int calculatecharge(int units) {
        if (units <= 0 || units > 10) {
            throw new IllegalArgumentException("Units must be between 1 and 10.");
        }
        return units * calculateCharge();
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}