package pattern.refactor.classificationCode.after;

public class Item {
    public static final int TYPECODE_BOOK = ItemType.BOOK.getTypeCode();
    public static final int TYPECODE_DVD = ItemType.DVD.getTypeCode();
    public static final int TYPECODE_SOFTWARE = ItemType.SOFTWARE.getTypeCode();

    private final ItemType itemType;
    private final String title;
    private final int price;

    public Item(ItemType itemType, String title, int price) {
        this.itemType = itemType;
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return itemType.getTypeCode() +
                ", " + title +
                ", " + price ;
    }
}
