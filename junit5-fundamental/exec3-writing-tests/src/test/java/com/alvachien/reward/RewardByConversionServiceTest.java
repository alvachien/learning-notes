package com.alvachien.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.Arrays;
import java.util.List;

import com.alvachien.product.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RewardByConversionServiceTest implements TestHelper {
    private RewardByConversionService service;

    @BeforeEach
    public void setUp() {
        System.out.println("Entering RewardByConversionServiceTest.setUp");

        service = new RewardByConversionService();
        service.setAmount(10);
        service.setNeededPoints(100);
    }

    @Test
    @DisplayName("Correct amount is set")
    void correctAmount() {
        assertEquals(10, service.getAmount());
    }


    @Test
    @DisplayName("When empty order and zero points no rewards should be applied")
    void emptyOrderZeroPoints() {
        RewardInformation info = service.applyReward(getEmptyOrder(), 0);
        
        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    @Test
    @DisplayName("When not enough points no rewads should be applied")
    void noEnoughPoints() {
        RewardInformation info = service.applyReward(getSampleOrder(), 10);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    @Test
    @DisplayName("When empty order and enough points no rewards should be applied")
    void emptyOrderEnoughPoints() {
        RewardInformation info = service.applyReward(getEmptyOrder(), 200);

        assertEquals(0, info.getDiscount());
        
        // Case 1. using assumeTrue
        //assumeTrue("1".equals(System.getenv("TEST_POINTS")));
        // Only execute the next assert if the above assumption is valid
        // assertEquals(0, info.getPointsRedeemed());

        // Case 2. Use assumingThat
        assumingThat("1".equals(System.getenv("TEST_POINTS")), () -> {
            assertEquals(0, info.getPointsRedeemed());
        });
    }

    @Override
    public RewardService getRewardService() {
        return this.service;
    }
}
