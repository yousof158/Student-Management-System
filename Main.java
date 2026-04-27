
import java.util.Scanner;

class BinarySearchArray {
    private long[] array;
    private int nElems;
    private int comparisons;
    private int swaps;

    public BinarySearchArray(int max) {
        array = new long[max];
        nElems = 0;
        comparisons = 0;
        swaps = 0;
    }

    public int size() {
        return nElems;
    }

    // Recursive Binary Search
    private int binarySearch(long searchKey, int lowerBound, int upperBound) {
        comparisons++; // one comparison per call
        if (lowerBound > upperBound)
            return -1;

        int mid = (lowerBound + upperBound) / 2;

        if (array[mid] == searchKey)
            return mid;
        else if (array[mid] < searchKey)
            return binarySearch(searchKey, mid + 1, upperBound);
        else
            return binarySearch(searchKey, lowerBound, mid - 1);
    }

    public int find(long value) {
        return binarySearch(value, 0, nElems - 1);
    }

    public boolean insert(long value) {
        if (nElems == array.length)
            return false;

        int j;
        for (j = 0; j < nElems; j++) {
            comparisons++;
            if (array[j] > value)
                break;
        }

        for (int k = nElems; k > j; k--) {
            array[k] = array[k - 1];
            swaps++;
        }

        array[j] = value;
        nElems++;
        return true;
    }

    public boolean delete(long value) {
        int pos = find(value);
        if (pos == -1) {
            comparisons++;
            return false;
        } else {
            for (int j = pos; j < nElems - 1; j++) {
                array[j] = array[j + 1];
                swaps++;
            }
            nElems--;
            return true;
        }
    }

    public void display() {
        for (int j = 0; j < nElems; j++)
            System.out.print(array[j] + " ");
        System.out.println();
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of elements to insert: ");
        int n = s.nextInt();
        BinarySearchArray arr = new BinarySearchArray(n + 10); // extra space

        System.out.println("\nPlease insert " + n + " elements:");
        for (int i = 0; i < n; i++) {
            System.out.print("Insert element no. " + (i + 1) + ": ");
            long value = s.nextLong();
            arr.insert(value);
        }

        System.out.println("\nArray after insertion (sorted):");
        arr.display();

        System.out.print("\nEnter a value to search for: ");
        long valueToSearch = s.nextLong();
        int index = arr.find(valueToSearch);
        if (index != -1)
            System.out.println("Value found at index: " + index);
        else
            System.out.println("Value not found.");

        while (true) {
            System.out.print("\nDo you want to delete a value? (yes/no): ");
            String answer = s.next().toLowerCase();
            if (!answer.equals("yes"))
                break;

            System.out.print("Enter the value to delete: ");
            long valueToDelete = s.nextLong();
            boolean deleted = arr.delete(valueToDelete);
            System.out.println(deleted ? "Value deleted successfully." : "Value not found. Deletion failed.");

            System.out.println("\nCurrent array:");
            arr.display();
        }

        System.out.println("\nTotal comparisons: " + arr.getComparisons());
        System.out.println("Total swaps: " + arr.getSwaps());

        s.close();
    }
}
