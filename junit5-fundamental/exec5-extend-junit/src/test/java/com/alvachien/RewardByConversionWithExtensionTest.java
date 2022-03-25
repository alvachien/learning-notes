package com.alvachien;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import com.alvachien.reward.RewardByConversionService;
import com.alvachien.reward.RewardInformation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// Case 1: Extend with whole class
//@ExtendWith(LifecycleExtension.class)
public class RewardByConversionWithExtensionTest {
    private RewardByConversionService service = null;

    @BeforeAll
    static void setUpAll() {
        System.out.println("BeforeAll");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach");

        service = new RewardByConversionService();
        service.setNeededPoints(100);
        service.setAmount(10);
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");
    }
    
    @Test
    // Case 2. Extend with method
    @ExtendWith(LifecycleExtension.class)
    void changeAmount() {
        System.out.println("Test changeAmount");
        service.setAmount(20);
        assertEquals(20, service.getAmount());
    }

    @Test
    void rewardNotAppliedEmptyOrder() {
        System.out.println("Test rewardNotAppliedEmptyOrder");

        RewardInformation info = service.applyReward(new ArrayList<>(), 500);
        assertEquals(0, info.getPointsRedeemed());
        assertEquals(0, info.getDiscount());
    }

}
