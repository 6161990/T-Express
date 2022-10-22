package pattern.refactor.factoryMethod.after;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pattern.refactor.typeCodeWithSub.CustomerType.*;

class CustomerTest {

    @Test
    void after() {
        Customer silverCustomer = SilverCustomer.create("Soo");
        Customer bronzeCustomer = BronzeCustomer.create( "James");
        Customer goldCustomer = GoldCustomer.create("Amil");

        int price = 10000;

        assertThat(bronzeCustomer.getCustomerGrade()).isEqualTo(BRONZE);
        assertThat(silverCustomer.getCustomerGrade()).isEqualTo(SILVER);
        assertThat(goldCustomer.getCustomerGrade()).isEqualTo(GOLD);

        assertThat(bronzeCustomer.calcPrice(price)).isEqualTo(10000);
        assertThat(bronzeCustomer.calcBonusPoint(price)).isEqualTo(100);

        assertThat(silverCustomer.calcPrice(price)).isEqualTo(9500);
        assertThat(silverCustomer.calcBonusPoint(price)).isEqualTo(500);

        assertThat(goldCustomer.calcPrice(price)).isEqualTo(9000);
        assertThat(goldCustomer.calcBonusPoint(price)).isEqualTo(1000);
    }

}