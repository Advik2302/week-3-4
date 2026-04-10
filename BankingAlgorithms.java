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

        public Client(String id, int riskScore, double accountBalance) {
            this.id = id;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return id + "(" + riskScore + ")";
        }
    }

    public static void problem2() {
        System.out.println("--- Problem 2: Client Risk Score Ranking ---");
        Client[] clients = {
                new Client("C", 80, 1000),
                new Client("A", 20, 5000),
                new Client("B", 50, 2000),
                new Client("D", 80, 1500)
        };

        // Bubble Sort (asc)
        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        int n = bubbleArr.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (bubbleArr[j].riskScore > bubbleArr[j + 1].riskScore) {
                    Client temp = bubbleArr[j];
                    bubbleArr[j] = bubbleArr[j + 1];
                    bubbleArr[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("Bubble (asc): " + Arrays.toString(bubbleArr) + " // Swaps: " + swaps);

        // Insertion Sort (desc riskScore + accountBalance desc)
        Client[] insertArr = Arrays.copyOf(clients, clients.length);
        for (int i = 1; i < insertArr.length; i++) {
            Client key = insertArr[i];
            int j = i - 1;
            while (j >= 0 && (insertArr[j].riskScore < key.riskScore || 
                 (insertArr[j].riskScore == key.riskScore && insertArr[j].accountBalance < key.accountBalance))) {
                insertArr[j + 1] = insertArr[j];
                j--;
            }
            insertArr[j + 1] = key;
        }
        System.out.println("Insertion (desc): " + Arrays.toString(insertArr));
        
        System.out.print("Top 3 risks: ");
        for(int i = 0; i < Math.min(3, insertArr.length); i++) {
            System.out.print(insertArr[i] + " ");
        }
        System.out.println("\n");
    }

    // --- Problem 3: Historical Trade Volume Analysis ---
    static class Trade {
        String id;
        int volume;

        public Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id.replace("trade", "") + ":" + volume;
        }
    }

    public static void problem3() {
        System.out.println("--- Problem 3: Historical Trade Volume Analysis ---");
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort (asc)
        Trade[] mergeArr = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("MergeSort: " + Arrays.toString(mergeArr));

        // Quick Sort (desc)
        Trade[] quickArr = Arrays.copyOf(trades, trades.length);
        quickSortDesc(quickArr, 0, quickArr.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickArr));

        // Merge two sorted lists
        Trade[] morning = {new Trade("m1", 200), new Trade("m2", 400)};
        Trade[] afternoon = {new Trade("a1", 100), new Trade("a2", 300), new Trade("a3", 600)};
        Trade[] merged = new Trade[morning.length + afternoon.length];
        int m = 0, a = 0, k = 0, totalVol = 0;
        while(m < morning.length && a < afternoon.length) {
            if (morning[m].volume <= afternoon[a].volume) merged[k++] = morning[m++];
            else merged[k++] = afternoon[a++];
        }
        while(m < morning.length) merged[k++] = morning[m++];
        while(a < afternoon.length) merged[k++] = afternoon[a++];

        for (Trade t : merged) totalVol += t.volume;
        System.out.println("Merged total volume: " + totalVol + "\n");
    }

    private static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    private static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDesc(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }
    private static int partitionDesc(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // --- Problem 4: Portfolio Return Sorting ---
    static class Asset {
        String name;
        double returnRate;
        double volatility;

        public Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }
        @Override
        public String toString() {
            return name + ":" + (int)returnRate + "%";
        }
    }
