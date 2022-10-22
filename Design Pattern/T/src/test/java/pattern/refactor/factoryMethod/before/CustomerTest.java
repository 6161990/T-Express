package pattern.refactor.factoryMethod.before;

import org.junit.jupiter.api.Test;
import pattern.refactor.typeCodeWithSub.before.Customer;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void before() {
        pattern.refactor.typeCodeWithSub.before.Customer bronzeCustomer = new pattern.refactor.typeCodeWithSub.before.Customer(pattern.refactor.typeCodeWithSub.before.Customer.BRONZE_CUSTOMER, "James");
        pattern.refactor.typeCodeWithSub.before.Customer silverCustomer = new pattern.refactor.typeCodeWithSub.before.Customer(pattern.refactor.typeCodeWithSub.before.Customer.SILVER_CUSTOMER, "Soo");
        pattern.refactor.typeCodeWithSub.before.Customer goldCustomer = new pattern.refactor.typeCodeWithSub.before.Customer(Customer.GOLD_CUSTOMER, "Amil");

        int price = 10000;

        assertThat(bronzeCustomer.getCustomerGrade()).isEqualTo("BRONZE");
        assertThat(silverCustomer.getCustomerGrade()).isEqualTo("SILVER");
        assertThat(goldCustomer.getCustomerGrade()).isEqualTo("GOLD");

        assertThat(bronzeCustomer.calcPrice(price)).isEqualTo(10000);
        assertThat(bronzeCustomer.calcBonusPoint(price)).isEqualTo(100);

        assertThat(silverCustomer.calcPrice(price)).isEqualTo(9500);
        assertThat(silverCustomer.calcBonusPoint(price)).isEqualTo(500);

        assertThat(goldCustomer.calcPrice(price)).isEqualTo(9000);
        assertThat(goldCustomer.calcBonusPoint(price)).isEqualTo(1000);
    }

}