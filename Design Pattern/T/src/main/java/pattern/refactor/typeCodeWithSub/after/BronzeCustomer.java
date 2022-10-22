package pattern.refactor.typeCodeWithSub.after;

import pattern.refactor.typeCodeWithSub.CustomerType;

import static pattern.refactor.typeCodeWithSub.CustomerType.BRONZE;

public class BronzeCustomer extends Customer {

    public BronzeCustomer(String customerName) {
        super(customerName);
    }

    @Override
    public CustomerType getCustomerGrade() {
        return BRONZE;
    }

    @Override
    public int calcPrice(int price) {
        return price;
    }

    @Override
    public int calcBonusPoint(int price) {
        return bonusPoint += price * 0.01;
    }
}
