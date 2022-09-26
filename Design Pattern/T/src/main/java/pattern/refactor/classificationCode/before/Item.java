package pattern.refactor.classificationCode.before;

public class Item {
    public static final int TYPECODE_BOOK = 0;
    public static final int TYPECODE_DVD = 1;
    public static final int TYPECODE_SOFTWARE = 2;

    private final int typeCode;
    private final String title;
    private final int price;

    public Item(int typeCode, String title, int price) {
        this.typeCode = typeCode;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return typeCode +
                ", " + title +
                ", " + price ;
    }
}
