package com.alvachien;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import com.alvachien.reward.RewardByConversionService;
import com.alvachien.reward.RewardInformation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@TestWithErrorHandler
public class RewardByConversionWithExtensionTest3 {

    @Nested
    class OneNestedTes {
        @TestWithErrorHandler
        void changeAmount(RewardByConversionService service) {
            service.setAmount(-20);
            assertEquals(-20, service.getAmount());
        }
    }
   
    @Test
    void changeAmount(RewardByConversionService service) {
        System.out.println("Test changeAmount");
        service.setAmount(20);
        assertEquals(20, service.getAmount());
    }

    @Test
    void rewardNotAppliedEmptyOrder(RewardByConversionService service) {
        System.out.println("Test rewardNotAppliedEmptyOrder");

        RewardInformation info = service.applyReward(new ArrayList<>(), 500);
        assertEquals(0, info.getPointsRedeemed());
        assertEquals(0, info.getDiscount());
    }

}