package pattern.refactor.factoryMethod.after;

public class GoldCustomer extends Customer {

    public GoldCustomer(String customerName) {
        super(customerName);
    }

    public static Customer create(String customerName) {
        return new GoldCustomer(customerName);
    }

    @Override
    public String getCustomerGrade() {
        return "GOLD";
    }

    @Override
    public int calcPrice(int price) {
        return price -(int)(price * 0.1);
    }

    @Override
    public int calcBonusPoint(int price) {
        return bonusPoint += price * 0.1;
    }
}
