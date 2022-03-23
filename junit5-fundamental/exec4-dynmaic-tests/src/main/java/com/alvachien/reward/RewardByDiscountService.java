package com.alvachien.reward;

import java.util.List;
import com.alvachien.product.Product;

public class RewardByDiscountService extends RewardService {
    private double  precentage;

    @Override
    public RewardInformation applyReward(List<Product> order, long customerPoints) {
        RewardInformation rewardInformation = new RewardInformation();

        if (customerPoints >= neededPoints)     {
            double orderTotal = calculateTotal(order);
            double discount = orderTotal * precentage;
            rewardInformation = new RewardInformation(neededPoints, discount);
        }

        return rewardInformation;
    }

    public double getPrecentage() { return this.precentage; }
    public void setPrecentage(double precentage) {
        if (precentage > 0) {
            this.precentage = precentage;
        } else {
            this.precentage = 1;
        }
    }
    
}
