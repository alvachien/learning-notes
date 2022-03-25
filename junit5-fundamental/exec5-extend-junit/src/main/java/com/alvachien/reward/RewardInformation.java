package com.alvachien.reward;

public class RewardInformation {
    private long pointsRedeemed;
    private double discount;

    public RewardInformation() { }

    public RewardInformation(long pointsRedeemed, double discount) {
        this.pointsRedeemed = pointsRedeemed;
        this.discount = discount;
    }

    public long getPointsRedeemed() { return pointsRedeemed; }
    public void setPointsRedeemed(long pointsRedeemed) { this.pointsRedeemed = pointsRedeemed; }
    public double getDiscount() { return this.discount; }
    public void setDiscount(double discount) { this.discount = discount; }
}
