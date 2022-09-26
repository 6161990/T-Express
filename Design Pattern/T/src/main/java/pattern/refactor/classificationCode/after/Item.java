package pattern.refactor.classificationCode.after;

public class Item {

    private final ItemType itemType;
    private final String title;
    private final int price;

    public Item(ItemType itemType, String title, int price) {
        this.itemType = itemType;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return itemType.getTypeCode() +
                ", " + title +
                ", " + price ;
    }
}
