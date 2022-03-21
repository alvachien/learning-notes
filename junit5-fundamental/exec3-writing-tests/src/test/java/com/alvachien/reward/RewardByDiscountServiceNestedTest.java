package com.alvachien.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.alvachien.product.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Reward by Discount \uD83D\uDE02")
public class RewardByDiscountServiceNestedTest {
    private RewardByDiscountService service = null;

    @DisplayName("Intialize precent 10% and need points 100")
    @BeforeEach
    void setUp() {
        service = new RewardByDiscountService();
        service.setPrecentage(0.1);
        service.setNeededPoints(100);
    }

    @DisplayName("Then the precent shall be 10%")
    @Test
    void checkPercentage() {
        assertEquals(0.1, service.getPrecentage());
    }

    @DisplayName("Then the points shall be 100")
    @Test
    void checkNeedPoints() {
        assertEquals(100, service.getNeededPoints());
    }

    @DisplayName("Given there is a small order")
    @Nested
    class SmallOrder {
        private List<Product> smallOrder = null;

        @BeforeEach
        void setUp() {
            Product smallDecaf = new Product(1, "Small Decaf", 1.99);
            smallOrder = Collections.singletonList(smallDecaf);    
        }

        @DisplayName("Given the customer has zero points")
        @Nested
        class ZeroPoints {
            private RewardInformation info = null;
    
            @DisplayName("When the reward is applied")
            @BeforeEach
            void setUp() {
                info = service.applyReward(smallOrder, 0);
            }
    
            @DisplayName("Discount shall be zero")
            @Test
            void checkDiscount() {
                assertEquals(0, info.getDiscount());
            }
    
            @DisplayName("The points redeemd shall be zero")
            @Test
            void checkPointsRedeemed() {
                assertEquals(0, info.getPointsRedeemed());
            }
        }
    }

    @DisplayName("Given there is a big order")
    @Nested
    class BigOrder {
        private List<Product> bigOrder = null;

        @BeforeEach
        void setUp() {
            Product bigDecaf = new Product(2, "Big Decaf", 2.49);
            Product bigLatte = new Product(3, "Big Latte", 2.99);
            Product bigTea = new Product(4, "Big Tea", 2.99);
            Product espresso = new Product(5, "Espresso", 2.99);
    
            bigOrder = Arrays.asList(bigDecaf, bigLatte, bigTea, espresso);    
        }

        @DisplayName("When customer have enough points, then reward shall be applied")
        @Test
        void enoughCustomerPointsForDiscountInBigOrder() {
            RewardInformation info = service.applyReward(bigOrder, 200);
    
            assertEquals(1.14, info.getDiscount(), 0.01);
            assertEquals(100, info.getPointsRedeemed());
        }    
    }
}
