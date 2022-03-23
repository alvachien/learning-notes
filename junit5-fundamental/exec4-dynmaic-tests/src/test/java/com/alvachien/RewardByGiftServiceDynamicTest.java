package com.alvachien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.alvachien.product.Product;
import com.alvachien.reward.RewardByGiftService;
import com.alvachien.reward.RewardInformation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

public class RewardByGiftServiceDynamicTest {

    private RewardByGiftService service = null;

    @BeforeEach
    void setUp() {
        System.out.println("Entering setUp");

        service = new RewardByGiftService();
        service.setNeededPoints(100);        
    }

    // Case 1. use collection.
    // @TestFactory
    // Collection<DynamicTest> dynamicTestsFromCollection() {
    //     return Arrays.asList(
    //         dynamicTest("1st Dynamic Test", 
    //             () -> assertEquals(1, 1)
    //         ),

    //         dynamicTest("2nd Dynamic Test",
    //             () -> assertEquals(1, 1)
    //         )
    //     );
    // }

    // Case 2. use Iterator.
    // @TestFactory
    // Iterator<DynamicTest> dynamicTestsFromCollection() {
    //     return Arrays.asList(
    //         dynamicTest("1st Dynamic Test", 
    //             () -> assertEquals(1, 1)
    //         ),

    //         dynamicTest("2nd Dynamic Test",
    //             () -> assertEquals(1, 1)
    //         )
    //     ).iterator();
    // }

    // Case 3. Use Stream (from number stream)
    // @TestFactory
    // Stream<DynamicTest> giftProductNotInOrderRewardNotApplied() {
    //     return getStreamOfRandomNumber()
    //         .limit(5)
    //         .mapToObj(randomId -> 
    //             dynamicTest("Test Product ID" + randomId, 
    //                 () -> {
    //                     service.setGiftProductId(randomId);
    //                     RewardInformation info = service.applyReward(getSampleOrder(), 200);

    //                     assertEquals(0, info.getDiscount());
    //                     assertEquals(0, info.getPointsRedeemed());
    //                 }
    //             )
    //         );
    // }

    // Case 4. Use DynamicTest.stream
    @TestFactory
    Stream<DynamicTest> giftProductNotInOrderRewardNotApplied() {
        Iterator<Long> inputGenerator = getStreamOfRandomNumber().limit(5).iterator();
        Function<Long, String> displayNameGenerator = randomId -> "Testing Product ID" + randomId;

        ThrowingConsumer<Long> testExecutorThrowingConsumer = randomId -> {
            service.setGiftProductId(randomId);
            RewardInformation info = service.applyReward(getSampleOrder(), 200);

            assertEquals(0, info.getDiscount());
            assertEquals(0, info.getPointsRedeemed());
        };

        return DynamicTest.stream(
            inputGenerator, 
            displayNameGenerator, 
            testExecutorThrowingConsumer);
        // return getStreamOfRandomNumber()
        //     .limit(5)
        //     .mapToObj(randomId -> 
        //         dynamicTest("Test Product ID" + randomId, 
        //             () -> {
        //                 service.setGiftProductId(randomId);
        //                 RewardInformation info = service.applyReward(getSampleOrder(), 200);

        //                 assertEquals(0, info.getDiscount());
        //                 assertEquals(0, info.getPointsRedeemed());
        //             }
        //         )
        //     );
    }

    // Case 5. Test Factory with Test container 
    // @TestFactory
    // Stream<DynamicContainer> dynamicTestsWithContainer() {
    //     return LongStream.range(1, 6)
    //         .mapToObj(productId -> dynamicContainer(
    //             "Container for ID" + productId, 
    //             Stream.of(
    //                 dynamicTest("Valid Id", () -> assertTrue(productId > 0)),
    //                 dynamicContainer("Test", Stream.of(
    //                     dynamicTest("Discount applied", () -> {
    //                         service.setGiftProductId(productId);

    //                         RewardInformation info = service.applyReward(getSampleOrder(), 200);
    //                         assertTrue(info.getDiscount() > 0);
    //                     })
    //                     )
    //                 )
    //             )));
    // }
    @TestFactory
    Stream<DynamicContainer> dynamicTestsWithContainers() {
        return LongStream.range(1, 6)
                .mapToObj(productId -> dynamicContainer(
                        "Container for ID " + productId,
                        Stream.of(
                                dynamicTest("Valid Id", () -> assertTrue(productId > 0)),
                                dynamicContainer("Test", Stream.of(
                                    dynamicTest("Discount applied", () -> {
                                        service.setGiftProductId(productId);
                                        RewardInformation info = service.applyReward(
                                                getSampleOrder(), 200);

                                        assertTrue(info.getDiscount() > 0);
                                    })
                                ))
                        )
                ));
    }

    private LongStream getStreamOfRandomNumber() {
        Random r = new Random();
        return r.longs(1000, 2000);
    }

    public List<Product> getSampleOrder() {
        Product smallDecaf = new Product(1, "Small Decaf", 1.99);
        Product bigDecaf = new Product(2, "Big Decaf", 2.49);
        Product bigLatte = new Product(3, "Big Latte", 2.99);
        Product bigTea = new Product(4, "Big Tea", 2.99);
        Product espresso = new Product(5, "Espresso", 2.99);

        return Arrays.asList(smallDecaf, bigDecaf, bigLatte, bigTea, espresso);
    }
}
