package com.alvachien;

import com.alvachien.product.Product;
import com.alvachien.reward.RewardByGiftService;
import com.alvachien.reward.RewardInformation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class RewardByGiftServiceParameterizedTest {
    private RewardByGiftService service = null;

    // Case 1. without TestInfo
    // @BeforeEach
    // void setUp() {
    //     System.out.println("Entering setUp (BeforeEach)");
    //     service = new RewardByGiftService();
    //     service.setNeededPoints(100);
    //     System.out.println("Leaving setUp (BeforeEach)");
    // }

    // Case 2. With product info
    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("Entering setUp (BeforeEach): " + testInfo.getDisplayName());
        service = new RewardByGiftService();
        service.setNeededPoints(100);
    }

    // Case 1. No names
    //@ParameterizedTest
    // @ValueSource(longs = { 1, 2, 3, 4 })
    // void discountShouldBeApplied(long productId) {
    //     service.setGiftProductId(productId);

    //     RewardInformation info = service.applyReward(getSampleOrder(), 200);

    //     assertTrue(info.getDiscount() > 0);
    // }

    // Case 2. Diffent name for each test
    // @ParameterizedTest(name = "Test #{index}: productId={0}")
    // @ValueSource(longs = { 1, 2, 3, 4 })
    // void discountShouldBeApplied(long productId) {
    //     service.setGiftProductId(productId);

    //     RewardInformation info = service.applyReward(getSampleOrder(), 200);

    //     assertTrue(info.getDiscount() > 0);
    // }

    // Case 3. Use additional info
    @ParameterizedTest(name = "Test #{index}: productId={0}")
    @ValueSource(longs = { 1, 2, 3, 4 })
    void discountShouldBeApplied(long productId, TestInfo testInfo, TestReporter testReporter) {
        System.out.println("Display name : " + testInfo.getDisplayName());
        testReporter.publishEntry("ProductID", String.valueOf(productId));
        service.setGiftProductId(productId);

        RewardInformation info = service.applyReward(getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);
    }


    // Case 1. for all enum values
    // @ParameterizedTest
    // @EnumSource(SpecialProductsEnum.class)
    // void discountShouldBeAppliedEnumSource(SpecialProductsEnum product) {
    //     service.setGiftProductId(product.getProductId());
    //     RewardInformation info = service.applyReward(getSampleOrder(), 200);

    //     assertTrue(info.getDiscount() > 0);
    // }

    // Case 2, only part of enum values
    @ParameterizedTest
    @EnumSource(value = SpecialProductsEnum.class, names = { "BIG_TEA", "BIG_LATTE" })
    void discountShouldBeAppliedEnumSource(SpecialProductsEnum product) {
        service.setGiftProductId(product.getProductId());
        RewardInformation info = service.applyReward(getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);
    }

    // Method source can occept only 1 parameter
    @ParameterizedTest
    @MethodSource("productIds")
    void discountShouldBeAppliedMethodSource(long productId) {
        service.setGiftProductId(productId);

        RewardInformation info = service.applyReward(getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);        
    }

    @ParameterizedTest
    @MethodSource("productIdsCustomerPoints")
    void discountShouldBeAppliedMethodSourceMultipleParams(long productId, long customerPoints) {
        service.setGiftProductId(productId);

        RewardInformation info = service.applyReward(getSampleOrder(), customerPoints);

        assertTrue(info.getDiscount() > 0);        
    }

    private static LongStream productIds() {
        return LongStream.range(1, 6);
    }

    static Stream<Arguments> productIdsCustomerPoints() {
        return productIds().mapToObj(productId -> Arguments.of(productId, 100 * productId));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/product-point-data.csv")
    void discountShouldBeAppliedCsvFileSource(long productId, long customerPoints) {
        service.setGiftProductId(productId);

        RewardInformation info = service.applyReward(getSampleOrder(), customerPoints);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @ArgumentsSource(ProductIdArgumentsProvider.class)
    void discountShouldBeAppliedArgumentsSource(long productId, long customerPoints) {
        service.setGiftProductId(productId);
        RewardInformation info = service.applyReward(getSampleOrder(), customerPoints);

        assertTrue(info.getDiscount() > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1; Small Decaf; 1.99", "2; Big Decaf; 2.49"})
    void discountShouldBeAppliedCustomConverter(
        @ConvertWith(ProductArgumentConverter.class) Product product) {
        System.out.println("Testing product" + product.getName());
        
        service.setGiftProductId(product.getId());
        RewardInformation info = service.applyReward(getSampleOrder(), 200);

        assertTrue(info.getDiscount() > 0);
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
