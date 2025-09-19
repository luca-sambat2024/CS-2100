
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		/* Read in size and which algorithm to run */
		Scanner in = new Scanner(System.in);
		System.out.print("Enter size:  ");
		int size = in.nextInt();
		System.out.print("Enter sort:  ");
		int whichSort = in.nextInt();
		in.close();

		/* Make an array to sort. Fill with random numbers */
		final ArrayList<Integer> list = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++)
			list.add( (int) (Math.random() * 3 * size));

		/* Make copies to sort */
		ArrayList<Integer> mergeSortList = (ArrayList<Integer>) list.clone();
		ArrayList<Integer> mergeHybridList = (ArrayList<Integer>)list.clone();
		ArrayList<Integer> quickSortList = (ArrayList<Integer>)list.clone();
		ArrayList<Integer> quickHybridList = (ArrayList<Integer>)list.clone();
		ArrayList<Integer> insertionSortList = (ArrayList<Integer>)list.clone();

		if (whichSort == 1) {		// insertion sort
			System.out.print("Sorting using insertion sort...");
//			System.out.println("\ninput: " + Arrays.toString(insertionSortList));
			// start timing
			long startTime = System.currentTimeMillis();
			SortingAlgorithms.insertionSort(insertionSortList);
			long timing = System.currentTimeMillis()-startTime;
//			System.out.println("sorted:" + Arrays.toString(insertionSortList));
			System.out.println("Done...checking if sorted correctly...");
			checkSorted(list, insertionSortList);
			System.out.println("It took " + timing + " ms to complete the sort");
			System.out.println("DONE");
		}

		else if (whichSort == 2) {   // recursive merge
			System.out.print("Sorting using recursive mergesort...");
			// start timing
			long startTime = System.currentTimeMillis();
//			System.out.println(mergeSortList);
			SortingAlgorithms.mergeSort(mergeSortList);
//			System.out.println(mergeSortList);
			long timing = System.currentTimeMillis()-startTime;
			System.out.println("Done...checking if sorted correctly...");
			checkSorted(list, mergeSortList);
			System.out.println("It took " + timing + " ms to complete the sort");
			System.out.println("DONE");
		}

		else if (whichSort == 3) {  // hybrid merge
			System.out.print("Sorting using hybrid mergesort...");
			// start timing
//			System.out.println(mergeHybridList);
			long startTime = System.currentTimeMillis();
			SortingAlgorithms.mergeSortHybrid(mergeHybridList);
			long timing = System.currentTimeMillis()-startTime;
			System.out.println("Done...checking if sorted correctly...");
			checkSorted(list, mergeHybridList);
			System.out.println("It took " + timing + " ms to complete the sort");
			System.out.println("DONE");
		}
		else if (whichSort == 4) {  // recursive quick
			System.out.print("Sorting using recursive quicksort...");
			// start timing
			long startTime = System.currentTimeMillis();
//			System.out.println(quickSortList);
			SortingAlgorithms.quickSort(quickSortList);
//			System.out.println(quickSortList);
			long timing = System.currentTimeMillis()-startTime;
			System.out.println("Done...checking if sorted correctly...");
			checkSorted(list, quickSortList);
			System.out.println("It took " + timing + " ms to complete the sort");
			System.out.println("DONE");
		}
		else if (whichSort == 5) { // hybrid quick
			System.out.print("Sorting using hybrid quicksort...");
			// start timing
//			System.out.println(quickHybridList);
			long startTime = System.currentTimeMillis();
			SortingAlgorithms.quickSortHybrid(quickHybridList);
			long timing = System.currentTimeMillis()-startTime;
			System.out.println("Done...checking if sorted correctly...");
			checkSorted(list, quickHybridList);
			System.out.println("It took " + timing + " ms to complete the sort");
			System.out.println("DONE");
		}
		else
			System.out.println("invalid selection");
	}

	public static <T extends Comparable<T>> boolean checkSorted(ArrayList<T> orig, ArrayList<T> sorted) {
		/* Make sure size is the same */
		if (orig.size() != sorted.size()) {
			System.out.println("ERROR...original list and sorted list have different lengths...");
			return false;
		}

		/* Make sure new array is sorted */
		for (int i = 1; i < sorted.size(); i++) {
			if (sorted.get(i).compareTo(sorted.get(i - 1)) < 0) {
				System.out.println("ERROR...the sorted list does not appear to be correctly sorted...");
				return false;
			}
		}
		
		// Make sure the same elements are in each array using binary search.
		// This works because we already know two arrays are same length and 
		//    the list named sorted is correctly sorted.
		boolean ok = true;
		for (int i = 0; i < orig.size(); i++) {
			int inSortedIndex = Collections.binarySearch(sorted, orig.get(i));
			if (inSortedIndex < 0) {
				ok = false;
				break;
			}
		}
		if (!ok ) {
			System.out.println(
					"ERROR...The sorted list does not contain the same elements that the original list does...");
			return false;
		}
		
		return true;
	}
}
