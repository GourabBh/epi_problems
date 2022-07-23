package dev.gourab.epi.ds.arrays;

import java.util.HashMap;
import java.util.Map;

public class MaxShareProfit {

    private static Map<Integer, Float> maxProfit(float[] arr, int n) {
        Map<Integer, Float> result = new HashMap<>();
        float maxProfit = 0.0f;
        int i, j, frequency = 0;
        for (i = 0; i < n - 1; i++) {
            for (j = i + 1; j < n; j++) {
                if ((arr[j] - arr[i]) > maxProfit) {
                    maxProfit = arr[j] - arr[i];
                }
                frequency++;
            }
        }
        result.put(frequency, maxProfit);
        return result;
    }

    public static void main(String[] args) {
        float[] input = { 0.5f, 1.3f, 2.7f, 2.1f, 3.7f, 4.6f, 0.78f, 5.78f };
        Map<Integer, Float> result = maxProfit(input, input.length);
        for (Map.Entry<Integer, Float> entry : result.entrySet()) {
            System.out.println("Maximum profit: " + entry.getValue());
            System.out.println("Execution time : " + entry.getKey() + " times.");
        }
    }
}
