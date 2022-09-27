package pattern.refactor.typeCodeWithSub.after;

public class GoldCustomer extends Customer {

    public GoldCustomer(String customerName) {
        super(customerName);
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
