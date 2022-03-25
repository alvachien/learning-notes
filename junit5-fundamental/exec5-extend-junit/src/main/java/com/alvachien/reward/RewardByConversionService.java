package com.alvachien.reward;

import java.util.List;

import com.alvachien.product.Product;

public class RewardByConversionService extends RewardService {
    private double amount;
    
    @Override
    public RewardInformation applyReward(List<Product> order, long customerPoints ) {
        RewardInformation rewardInformation = new RewardInformation();
        if (customerPoints >= neededPoints) {
            double orderTotal = calculateTotal(order);
            if (orderTotal > amount) {
                rewardInformation = new RewardInformation(neededPoints, amount);
            }
        }
        return rewardInformation;
    }

    public double getAmount() { return this.amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
