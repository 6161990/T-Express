package pattern.refactor.typeCodeWithSub.before;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void before() {
        Customer bronzeCustomer = new Customer(Customer.BRONZE_CUSTOMER, "James");
        Customer silverCustomer = new Customer(Customer.SILVER_CUSTOMER, "Soo");
        Customer goldCustomer = new Customer(Customer.GOLD_CUSTOMER, "Amil");

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