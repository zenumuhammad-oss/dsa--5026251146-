public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public String label() {
        return "Colour";
    }

    @Override
    public int calculateCharge() {
        // ColourPrint: first 10 pages at IDR 1,500 each; every page beyond 10 at IDR 1,000; add IDR 2,000 setup per complete copy.
        int cost = 0;
        if (pages <= 10) {
            cost = pages * 1500;
        } else {
            cost = (10 * 1500) + ((pages - 10) * 1000);
        }
        cost += 2000; // setup cost per complete copy
        return cost;
    }
}