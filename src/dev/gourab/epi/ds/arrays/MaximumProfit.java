package dev.gourab.epi.ds.arrays;

public class MaximumProfit {

    private float[] prices;
    private float maxProfit;

    MaximumProfit(float[] prices) {
        this.prices = prices;
    }

    public void setPrices(float[] prices) {
        this.prices = prices;
    }

    public float[] getPrices() {
        return prices;
    }

    public void setMaxProfit(float maxProfit) {
        this.maxProfit = maxProfit;
    }

    public float getMaxProfit() {
        return maxProfit;
    }

    class BuySell {
        private int buyTime, sellTime, minTime, maxTime;

        // Encapsulation
        BuySell(int buyTime, int sellTime, int minTime, int maxTime) {
            this.buyTime = buyTime;
            this.sellTime = sellTime;
            this.minTime = minTime;
            this.maxTime = maxTime;
        }
    }

    private float findMaxProfit() {
        if (prices == null && prices.length == 0) {
            return 0;
        }
        BuySell result = findMaxProfit(0, prices.length - 1);
        return prices[result.sellTime] - prices[result.buyTime];
    }

    private BuySell findMaxProfit(int low, int high) {
        if (low >= high) {
            return new BuySell(low, low, low, low);
        }
        int mid = low + (high - low) / 2;
        BuySell left = findMaxProfit(low, mid);
        BuySell right = findMaxProfit(mid + 1, high);
        return combine(left, right);
    }

    private BuySell combine(BuySell left, BuySell right) {
        // case 1: left
        float maxProfit = prices[left.sellTime] - prices[left.buyTime];
        int bestBuyTime = left.buyTime;
        int bestSellTime = left.sellTime;
        // case 2: right
        float case2Profit = prices[right.sellTime] - prices[right.buyTime];
        if (case2Profit > maxProfit) {
            maxProfit = case2Profit;
            bestBuyTime = right.buyTime;
            bestSellTime = right.sellTime;
        }
        // case 3: left and right
        float case3profit = prices[right.maxTime] - prices[left.minTime];
        if (case3profit > maxProfit) {
            maxProfit = case3profit;
            bestBuyTime = left.minTime;
            bestSellTime = right.maxTime;
        }
        // update min and max
        int newMinTime = (prices[left.minTime] < prices[right.minTime]) ? left.minTime : right.minTime;
        int newMaxTime = (prices[left.maxTime] > prices[right.maxTime]) ? left.maxTime : right.maxTime;

        return new BuySell(bestBuyTime, bestSellTime, newMinTime, newMaxTime);
    }

    public static void main(String[] args) {
        float[] input = { 0.5f, 1.3f, 2.7f, 2.1f, 3.7f, 4.6f, 0.78f, 5.78f };
        MaximumProfit maximumProfit = new MaximumProfit(input);
        // Concept of OOP
        maximumProfit.setMaxProfit(maximumProfit.findMaxProfit());
        System.out.println("Maximum Profit: " + maximumProfit.getMaxProfit());
    }

}
