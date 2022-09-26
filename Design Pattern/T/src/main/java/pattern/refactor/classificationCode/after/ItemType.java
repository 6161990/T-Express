package pattern.refactor.classificationCode.after;

public class ItemType {
    private int typeCode;

    public static final ItemType BOOK = new ItemType(0);
    public static final ItemType DVD = new ItemType(1);
    public static final ItemType SOFTWARE = new ItemType(2);

    public ItemType(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public ItemType getItemType(int typeCode){
        switch (typeCode){
            case 0 : return BOOK;
            case 1 : return DVD;
            case 2 : return SOFTWARE;
            default : return null;
        }
    }
}
