package pattern.refactor.factoryMethod.after;

import pattern.refactor.typeCodeWithSub.CustomerType;

import static pattern.refactor.typeCodeWithSub.CustomerType.SILVER;

public class SilverCustomer extends Customer {

    private SilverCustomer(String customerName) {
        super(customerName);
    }

    public static Customer create(String customerName) {
        return new SilverCustomer(customerName);
    }

    @Override
    public CustomerType getCustomerGrade() {
        return SILVER;
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
