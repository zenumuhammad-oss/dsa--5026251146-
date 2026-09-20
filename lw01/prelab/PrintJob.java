public abstract class PrintJob implements Chargeable {
    protected String id;
    protected int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0 || pages > 100) {
            throw new IllegalArgumentException("Pages must be between 1 and 100.");
        }
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    public String label() {
        return "Print";
    }

    // Overload calculateCharge(int copies) as requested in the instructions
    public int calculateCharge(int copies) {
        if (copies <= 0 || copies > 10) {
            throw new IllegalArgumentException("Copies must be between 1 and 10.");
        }
        return copies * calculateCharge();
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}