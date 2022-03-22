package com.alvachien.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class RewardByGiftServiceRepeatTest implements TestHelper {
    private RewardByGiftService service = null;

    @BeforeEach
    public void setUp() {
        service = new RewardByGiftService();
        service.setNeededPoints(100);
        System.out.println("setUp called");
    }

    // Case 1. Use RepeatedTest directly
    //@RepeatedTest(5)
    // Case 2. use RepeatedTest with value and name
    @RepeatedTest(value = 5, name = "{displayName} -> {currentRepetition}/{totalRepetitions}")
    @DisplayName("When gift product is not in order, reward should not be applied")
    //void giftProductInOrder() {
    // Case 3. Get repetition info.
    void giftProductInOrder() {        
        service.setGiftProductId(getRandomProductIdNotInOrder());

        RewardInformation info = service.applyReward(getSampleOrder(), 200);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    // Case 3. Get repetition info.
    @RepeatedTest(value = 5, name = "{displayName} -> {currentRepetition}/{totalRepetitions}")
    @DisplayName("When gift product is not in order, reward should not be applied")
    void giftProductInOrder2(RepetitionInfo repetitionInfo) {
        long productId = repetitionInfo.getCurrentRepetition() + 1000;
        System.out.println(productId);
        service.setGiftProductId(productId);

        RewardInformation info = service.applyReward(getSampleOrder(), 200);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    @Override
    public RewardService getRewardService() {
        return this.service;
    }

    private long getRandomProductIdNotInOrder() {
        Random r = new Random();
        return r.longs(1000, 2000)
            .findFirst().getAsLong();
    }
}
