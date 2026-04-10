import java.util.*;

public class BankingAlgorithms {

    // --- Problem 1: Transaction Fee Sorting ---
    static class Transaction {
        String id;
        double fee;
        String timestamp;

        public Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    public static void problem1() {
        System.out.println("--- Problem 1: Transaction Fee Sorting ---");
        Transaction[] txns = {
                new Transaction("id1", 10.5, "10:00"),
                new Transaction("id2", 25.0, "09:30"),
                new Transaction("id3", 5.0, "10:15"),
                new Transaction("id4", 60.0, "11:00")
        };

        // Bubble Sort: asc by fee
        Transaction[] bubbleArr = Arrays.copyOf(txns, txns.length);
        int n = bubbleArr.length;
        int passes = 0, swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (bubbleArr[j].fee > bubbleArr[j + 1].fee) {
                    Transaction temp = bubbleArr[j];
                    bubbleArr[j] = bubbleArr[j + 1];
                    bubbleArr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        System.out.println("BubbleSort (fees): " + Arrays.toString(bubbleArr) + " // passes: " + passes + ", swaps: " + swaps);

        // Insertion Sort: fee asc, then ts asc
        Transaction[] insertArr = Arrays.copyOf(txns, txns.length);
        for (int i = 1; i < insertArr.length; i++) {
            Transaction key = insertArr[i];
            int j = i - 1;
            while (j >= 0 && (insertArr[j].fee > key.fee || 
                 (insertArr[j].fee == key.fee && insertArr[j].timestamp.compareTo(key.timestamp) > 0))) {
                insertArr[j + 1] = insertArr[j];
                j--;
            }
            insertArr[j + 1] = key;
        }
        System.out.println("InsertionSort (fee+ts): " + Arrays.toString(insertArr));

        System.out.print("High-fee outliers (> $50): ");
        boolean foundOutlier = false;
        for (Transaction t : txns) {
            if (t.fee > 50) {
                System.out.print(t.id + " ");
                foundOutlier = true;
            }
        }
        if (!foundOutlier) System.out.print("none");
        System.out.println("\n");
    }

    // --- Problem 2: Client Risk Score Ranking ---
    static class Client {
        String id;
        int riskScore;
        double accountBalance;

