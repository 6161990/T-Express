package pattern.refactor.factoryMethod.after;

import pattern.refactor.typeCodeWithSub.CustomerType;

import static pattern.refactor.typeCodeWithSub.CustomerType.GOLD;

public class GoldCustomer extends Customer {

    private GoldCustomer(String customerName) {
        super(customerName);
    }

    public static Customer create(String customerName) {
        return new GoldCustomer(customerName);
    }

    @Override
    public CustomerType getCustomerGrade() {
        return GOLD;
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
