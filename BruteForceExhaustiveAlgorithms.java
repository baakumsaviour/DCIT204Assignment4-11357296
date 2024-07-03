import java.util.Scanner;

public class BruteForceExhaustiveAlgorithms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an algorithm to run:");
        System.out.println("1. Brute Force Maximum Subarray Sum");
        System.out.println("2. Exhaustive Knapsack Problem");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                runBruteForceMaxSubarraySum();
                break;
            case 2:
                runExhaustiveKnapsack();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void runBruteForceMaxSubarraySum() {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        long startTime = System.nanoTime();
        int maxSum = bruteForceMaxSubarraySum(array);
        long endTime = System.nanoTime();

        System.out.println("Maximum Subarray Sum: " + maxSum);
        System.out.println("Execution Time (ns): " + (endTime - startTime));
    }

    private static int bruteForceMaxSubarraySum(int[] array) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += array[k];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    private static void runExhaustiveKnapsack() {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 5;

        long startTime = System.nanoTime();
        int maxProfit = exhaustiveKnapsack(weights, values, capacity);
        long endTime = System.nanoTime();

        System.out.println("Maximum Profit: " + maxProfit);
        System.out.println("Execution Time (ns): " + (endTime - startTime));
    }

    private static int exhaustiveKnapsack(int[] weights, int[] values, int capacity) {
        return knapsackRecursive(weights, values, capacity, weights.length - 1);
    }

    private static int knapsackRecursive(int[] weights, int[] values, int capacity, int n) {
        if (n < 0 || capacity == 0) {
            return 0;
        }
        if (weights[n] > capacity) {
            return knapsackRecursive(weights, values, capacity, n - 1);
        } else {
            int include = values[n] + knapsackRecursive(weights, values, capacity - weights[n], n - 1);
            int exclude = knapsackRecursive(weights, values, capacity, n - 1);
            return Math.max(include, exclude);
        }
    }
}
