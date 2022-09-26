package pattern.refactor.classificationCode.after;

public class Item {
    public static final int TYPECODE_BOOK = ItemType.BOOK.getTypeCode();
    public static final int TYPECODE_DVD = ItemType.DVD.getTypeCode();
    public static final int TYPECODE_SOFTWARE = ItemType.SOFTWARE.getTypeCode();

    private final int typeCode;
    private final String title;
    private final int price;

    public Item(int typeCode, String title, int price) {
        this.typeCode = typeCode;
        this.title = title;
        this.price = price;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return typeCode +
                ", " + title +
                ", " + price ;
    }
}
