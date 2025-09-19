import java.util.ArrayList;
// Name: Luca Sambat
// Computing ID: aqc8qt
// Homework Name: HW 13 Sorting
// Resources used (if applicable):https://www.geeksforgeeks.org/merge-sort/, https://www.geeksforgeeks.org/quick-sort-algorithm/

/* Author: Briana Morrison, Hunter Platt */

public class SortingAlgorithms {

	/*
	 * Swaps the elements and indices i and j in list
	 * */
	private static <T> void swap(ArrayList<T> list, int i, int j) {
		if (i < 0 || i >= list.size())
			return;
		if (j < 0 || j >= list.size())
			return;
		T temp = list.get(i);
		list.set(i,list.get(j));
		list.set(j, temp);
	}
	
	// ####################
	// ## INSERTION SORT ## ----------------------------------------------------------------------
	// ####################
	// ## IMPORTANT: the code we've given you below has a small bug!
	// ##   You will need to look at this code and/or test, and fix the bug.
	// ####################
	/**
	 * Usually a slow sorting algorithm. Insertion sort. 
	 * @param list - An array of items
	 */
	public static <T extends Comparable<T>> void insertionSort(ArrayList<T> list) {
		for (int i = 1; i < list.size(); i++) {
			T val = list.get(i); //current element
			int j = i - 1;
			while (j >= 0 && val.compareTo(list.get(j)) < 0) { //was not examining the first element in the list.
				list.set(j+1, list.get(j));
				j--;
			}
			list.set(j+1, val);
		}
//		System.out.println(list);
	}

	//=================================================================================


	// ################
	// ## MERGE SORT ## ----------------------------------------------------------------------
	// ################	
	/**
	 * Fully recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * 
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void mergeSort(ArrayList<T> list) {
		mergeSort(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void mergeSort(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		if (i<j){
			int middle=(i+(j-1))/2; //find the middle of the array
			mergeSort(list,i,middle); //recursive call on left
			mergeSort(list,middle+1,j); //recursive call on right
			merge(list,i,middle,j); //merge everything together
		}
	}
	
	/**
	 * Merge method for Merge Sort algorithm.
	 * Your mergeSort algorithm will call this method as appropriate to do the merging.
	 * @param list - An arrayList of items
	 * @param i - lower bound index
	 * @param mid - middle index
	 * @param j - upper bound index 
	 */
	public static<T extends Comparable<T>> void merge(ArrayList<T> list, int i, int mid, int j) {
		//TODO: write the body of this method
		int leftsize=mid-i+1;
		int rightsize=j-mid;
//		System.out.println(leftsize+" "+rightsize);
		ArrayList<T> left=new ArrayList<>(leftsize);
		ArrayList<T> right =new ArrayList<>(rightsize);
		for (int l=0;l<leftsize;l++){	//copy elements of each subarray into the left and right.
			left.add(list.get(i + l));
		}
		for (int r=0;r<rightsize;r++){
			right.add(list.get(mid + 1 + r));
		}
		int leftindex=0;
		int rightindex=0;
		int index=i;
		while (leftindex<leftsize&&rightindex<rightsize){
			if (left.get(leftindex).compareTo(right.get(rightindex))<=0){ //determine which item is bigger from left/right subarray
				list.set(index,left.get(leftindex));
				leftindex++;
			} else {
				list.set(index,right.get(rightindex));
				rightindex++;
			}
			index++; //move up
		}
		while (leftindex<leftsize){ //readding any last items from the array that may not have been added
			list.set(index,left.get(leftindex));
			leftindex++;
			index++;
		}
		while (rightindex<rightsize){ //readding from right array
			list.set(index,right.get(rightindex));
			rightindex++;
			index++;
		}
//		System.out.println(list);
	}

	//=================================================================================

	// #######################
	// ## HYBRID MERGE SORT ## ----------------------------------------------------------------------
	// #######################
	/**
	 * Hybrid recursive Merge sort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * For this implementation, when the size of the portion of the arrayList
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the arrayList.
	 *
	 *
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void mergeSortHybrid(ArrayList<T> list) {
		mergeSortHybrid(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void mergeSortHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
		if (i<j){
			if (j-i+1<100){
				insertionSort(list);
			} else {
				int middle=(i+(j-1))/2;
				mergeSort(list,i,middle);
				mergeSort(list,middle+1,j);
				merge(list,i,middle,j);
			}
		}
	}

	//=================================================================================
	
	// ###############
	// ## QUICKSORT ## ----------------------------------------------------------------------
	// ###############	
	/**
	 * Fully recursive Quicksort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like. 
	 * 
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void quickSort(ArrayList<T> list) {
		quickSort(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void quickSort(ArrayList<T> list, int i, int j) {
		if (i<j){ //if i==j, the parts are sorted.
			int p=partition(list,i,j); //otherwise create the partition (sorting items)
			quickSort(list,i,p-1); //recursive call on left subarray
			quickSort(list,p+1,j); //and right subarray
		}
	}
	
	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An arrayList of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partition(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		T pivot=list.get(j); //find the pivot for the Lomuto method (at the end of the array)
		int low=i-1; //the marker
		for (int index=i;index<j;index++){ //loops j-i times, since can only access i to j elements.
			if (list.get(index).compareTo(pivot)<0){ //if the item at the iterator is less than the pivot
				low++; //increment marker
				swap(list,low,index); //swap item at marker and item at iterator
			}
		}
		swap(list,low+1,j); //once done looping, place the pivot in the correct place.
		return low+1;// be sure to return the right value and not 0
	}
	
	//=================================================================================

        // ######################
	// ## HYBRID QUICKSORT ## ----------------------------------------------------------------------
	// ######################
	/**
	 * Hybrid Quicksort and associated helper method.
	 * The second method below provides the portion of the arrayList
	 * (i.e., index i to j inclusive) that we want to sort.
	 * >>> Use any partition scheme that you like.
	 * For this implementation, when the size of the portion of the arrayList
	 * to be sorted is less than 100 items, call insertionSort method to
	 * sort that chunk of the arrayList.
	 *
	 * @param list - An arrayList of items
	 */
	public static<T extends Comparable<T>> void quickSortHybrid(ArrayList<T> list) {
		quickSortHybrid(list, 0, list.size() - 1);
	}
	public static<T extends Comparable<T>> void quickSortHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		// When the size of array to be sorted is < 100, call insertionSort rather than recurse
		if (i<j){ //if i==j, the parts are sorted.
			if (j-i+1<100){
				insertionSort(list);
			} else {
				int p=partitionHybrid(list,i,j); //otherwise create the partition (sorting items)
				quickSort(list,i,p-1); //recursive call on left subarray
				quickSort(list,p+1,j); //and right subarray
			}
		}
	}

	/**
	 * Partition method for Quicksort - Use any valid partition algorithm that you like.
	 * Your quickSort algorithm will call this method as appropriate to do the partitioning.
	 * @param list - An arrayList of items
	 * @param i - lower bound
	 * @param j - upper bound
	 */
	public static<T extends Comparable<T>> int partitionHybrid(ArrayList<T> list, int i, int j) {
		//TODO: write the body of this method
		T pivot=list.get(j); //find the pivot for the Lomuto method (at the end of the array)
		int low=i-1; //the marker
		for (int index=i;index<j;index++){ //loops j-i times, since can only access i to j elements.
			if (list.get(index).compareTo(pivot)<0){ //if the item at the iterator is less than the pivot
				low++; //increment marker
				swap(list,low,index); //swap item at marker and item at iterator
			}
		}
		swap(list,low+1,j); //once done looping, place the pivot in the correct place.
		return low+1; // be sure to return the right value and not 0
	}

}