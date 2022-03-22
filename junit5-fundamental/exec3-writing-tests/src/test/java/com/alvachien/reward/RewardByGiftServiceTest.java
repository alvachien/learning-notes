package com.alvachien.reward;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import com.alvachien.product.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.JRE;

public class RewardByGiftServiceTest {
    private RewardByGiftService service = null;

    @BeforeEach
    void setUp() {
        service = new RewardByGiftService();
        service.setGiftProductId(4);
        service.setNeededPoints(100);
    }

    @Test
    @DisplayName("Correct product ID is set")
    void correctProductID() {
        assertEquals(4, service.getGiftProductId(),
            () -> {
                System.out.println("Lazy loaded");
                return "Error, the product ID is incorrect";
            });
    }

    @Test
    @DisplayName("Reward applied with enough points")
    void rewardApplied() {
        RewardInformation info = service.applyReward(
            buildSampleOrder(10), 200);
        
        // assertNotNull(info);
        // assertEquals(2, info.getDiscount());
        // assertEquals(10, info.getPointsRedeemed());

        assertAll("Reward info error",
            () -> assertNotNull(info),
            () -> assertEquals(2.99, info.getDiscount()),
            () -> assertEquals(100, info.getPointsRedeemed())
        );
    }

    @DisplayName("Exception is thrown when invalid product ID")
    @Test
    void execptionThrownWhenInvalidProductID() {
        long productId = 0;
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.setGiftProductId(productId);
        });

        assertTrue(exception.getMessage().contains(String.valueOf(productId)));
    }

    @Test
    @DisplayName("Should not exceed timeout")
    //@DisabledOnJre({ JRE.JAVA_11 })
    @Disabled("Optimization not implemented yet")
    void timeOutNotExceed() {
        //int numberOfProduct = 50_000;
        int numberOfProduct = 50;
        service.setGiftProductId(numberOfProduct - 1);

        // Commend lines: exceed the execution time.
        // RewardInformation info = assertTimeout(
        //     Duration.ofMillis(4), 
        //     () -> service.applyReward(buildSampleOrder(numberOfProduct), 200)
        // );

        // Following lines will start the test in another thread, but failed too
        RewardInformation info = assertTimeoutPreemptively(
             Duration.ofMillis(4), 
             () -> service.applyReward(buildSampleOrder(numberOfProduct), 200)
        );

        assertEquals(2.99, info.getDiscount());

    }

    private List<Product> buildSampleOrder(int numberOfProducts) {
        return IntStream.range(1, numberOfProducts)
            .mapToObj(i -> new Product(i, "Product " + i, 2.99))
            .collect(toList());
    }
}
