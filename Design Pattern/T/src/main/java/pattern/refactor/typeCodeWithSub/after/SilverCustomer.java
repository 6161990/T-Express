package pattern.refactor.typeCodeWithSub.after;

public class SilverCustomer extends Customer {

    public SilverCustomer(String customerName) {
        super(customerName);
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
