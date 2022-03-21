package com.alvachien.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.alvachien.product.Product;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RewardByDiscountServiceTest {

    private RewardByDiscountService service = null;
    private List<Product> smallOrder = null;
    private List<Product> bigOrder = null;

    RewardByDiscountServiceTest() {
        System.out.println("Entering Constructor");
    }

    @BeforeAll
    void setUpAll() {
        System.out.println("Entering BeforeAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Entering BeforeEach");

        service = new RewardByDiscountService();
        service.setNeededPoints(100);
        service.setPrecentage(0.1);

        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        smallOrder = Collections.singletonList(smallDecaf);

        Product bigDecaf = new Product(2, "Big Decaf", 2.49);
        Product bigLatte = new Product(3, "Big Latte", 2.99);
        Product bigTea = new Product(4, "Big Tea", 2.99);
        Product espresso = new Product(5, "Espresso", 2.99);

        bigOrder = Arrays.asList(bigDecaf, bigLatte, bigTea, espresso);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Entering AfterEach");

        service = null;
    }

    @AfterAll
    void tearDownAll() {
        System.out.println("Entering AfterAll");
    }

    @Test
    @Order(1)
    void setNeededPoints() {
        System.out.println("Entering setNeededPoints");

        //RewardByDiscountService service = new RewardByDiscountService();
        // service.setNeededPoints(100);
        assertEquals(100, service.getNeededPoints());
    }

    @Test
    @Order(3)
    void setPrecentageForPoints() {
        System.out.println("Entering setPrecentageForPoints");

        //RewardByDiscountService service = new RewardByDiscountService();
        // service.setPrecentage(0.1);
        assertEquals(0.1, service.getPrecentage());
    }
    
    @Test
    @Order(2)
    void zeroCustomerPoints() {
        System.out.println("Entering zeroCustomerPoints");

        //RewardByDiscountService service = new RewardByDiscountService();
        // service.setPrecentage(0.1);
        // service.setNeededPoints(100);

        // Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        // List<Product> order = Collections.singletonList(smallDecaf);

        RewardInformation info = service.applyReward(smallOrder, 0);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    @Test
    void enoughCustomerPointsForDiscountInBigOrder() {
        RewardInformation info = service.applyReward(bigOrder, 200);

        assertEquals(1.14, info.getDiscount(), 0.01);
        assertEquals(100, info.getPointsRedeemed());
    }
}
