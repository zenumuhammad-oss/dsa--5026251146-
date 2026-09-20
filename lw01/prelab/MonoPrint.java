public class MonoPrint extends PrintJob {

    public MonoPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public String label() {
        return "Mono";
    }

    @Override
    public int calculateCharge() {
        // MonoPrint: IDR 500 per page
        return pages * 500;
    }
}