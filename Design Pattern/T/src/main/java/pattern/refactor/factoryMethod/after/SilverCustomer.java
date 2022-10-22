package pattern.refactor.factoryMethod.after;

public class SilverCustomer extends Customer {

    private SilverCustomer(String customerName) {
        super(customerName);
    }

    public static Customer create(String customerName) {
        return new SilverCustomer(customerName);
    }

    @Override
    public String getCustomerGrade() {
        return "SILVER";
    }

    @Override
    public int calcPrice(int price) {
        return price -(int)(price * 0.05);
    }

    @Override
    public int calcBonusPoint(int price) {
        return bonusPoint += price * 0.05;
    }
}
