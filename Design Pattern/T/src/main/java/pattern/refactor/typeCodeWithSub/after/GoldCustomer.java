package pattern.refactor.typeCodeWithSub.after;

import pattern.refactor.typeCodeWithSub.CustomerType;

import static pattern.refactor.typeCodeWithSub.CustomerType.GOLD;

public class GoldCustomer extends Customer {

    public GoldCustomer(String customerName) {
        super(customerName);
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
