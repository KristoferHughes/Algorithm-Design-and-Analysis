
package AlgorithmsHW;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * Kristofer Hughes
 */
public class SortingAlgorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestMergeSort();
        System.out.println();
        TestInsertionSort();
        System.out.println();
        TestQuickSort();
    }
    
    
    /* Sorts array using recursive merge sort algorithm */

   public static void mergeSort(int[] arr){
        if(arr == null){
            return;
        }
 
        if(arr.length > 1){
            int mid = arr.length / 2;
 
            // Create a sub array of left side values
            int[] leftArr = new int[mid];
            for(int i = 0; i < mid; i++){
                leftArr[i] = arr[i];
            }
             
            // Create a sub array of right side values
            int[] rightArr = new int[arr.length - mid];
            for(int i = mid; i < arr.length; i++){
                rightArr[i - mid] = arr[i];
            }
            
            mergeSort(leftArr);
            mergeSort(rightArr);
 
            int i = 0, j = 0, k = 0;
 
            // Merge the left and right sub arrays
            while(i < leftArr.length && j < rightArr.length){
                if(leftArr[i] < rightArr[j]){
                    arr[k] = leftArr[i];
                    i++;
                }
                else{
                    arr[k] = rightArr[j];
                    j++;
                }
                k++;
            }
            
            // Get the remaining values
            while(i < leftArr.length){
                arr[k] = leftArr[i];
                i++;
                k++;
            }
            while(j < rightArr.length){
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }
    }
    
    /* Sorts array using insertion sort algorithm */
    public static void insertionSort(int arr[]){ 
        int arrLength = arr.length;
        
        for (int pos = 1; pos < arrLength; ++pos) {
            int currentValue = arr[pos]; 
            int i = pos - 1; 
  
    
            while (i >= 0 && arr[i] > currentValue) {
                arr[i + 1] = arr[i]; 
                i--; 
            }
            // Move the current value to the right position.
            arr[i + 1] = currentValue; 
        } 
    } 
    
    
    public static void quickSort(int arr[], int start, int end){ 
        if (start < end){
            int partitionIndex = partition(arr, start, end); 
  
            
            quickSort(arr, start, partitionIndex - 1); 
            quickSort(arr, partitionIndex+1, end); 
        } 
    }
        
    /* Helper function for quickSort */
    private static int partition(int arr[], int start, int end) 
    { 
        int pivotValue = arr[end];  
        int i = (start - 1);
        for (int j = start; j < end; j++) 
        { 
            
            if (arr[j] < pivotValue) 
            { 
                i++; 
  
                // swap
                int tmp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = tmp; 
            } 
        } 
  
        // swap 
        int tmp = arr[i + 1]; 
        arr[i + 1] = arr[end]; 
        arr[end] = tmp; 
  
        return i + 1; 
    } 
                        
    /*
     * Helper/Utility Methods 
     */
    
    
    private static int[] readFile(String filename, int length){
        int[] array = new int[length];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            
            for(int i = 0; i < length; i++){
                String line;
                if((line = br.readLine()) == null) break;
                
                array[i] = Integer.parseInt(line);
            }
            
        } catch(IOException ioe) {
            System.out.print("Error: " + ioe.getMessage());
        }
        
        return array;
    }
    
    private static void printArray(int[] arr){
        for (int i = 1; i < arr.length; ++i) { 
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
    
    private static void TestMergeSort(){
        /* Array of Size 10 */

        System.out.println("Result for Merge Sort of array of size 10");
        
        System.out.println("Using Random data");
        int[] arrayToSort10 = readFile("SortingTestFiles/10_Random.txt", 10);      
        mergeSort(arrayToSort10);
        printArray(arrayToSort10);        

        System.out.println("Using Sorted data");
        arrayToSort10 = readFile("SortingTestFiles/10_Sorted.txt", 10);
        mergeSort(arrayToSort10);
        printArray(arrayToSort10);
        
        System.out.println("Using Reverse data");
        arrayToSort10 = readFile("SortingTestFiles/10_Reverse.txt", 10);
        mergeSort(arrayToSort10);
        printArray(arrayToSort10);

        /* Array of Size 100 */
        
        System.out.println();
        System.out.println("Result for Merge Sort of array of size 100");
        
        System.out.println("Using Random data");
        int[] arrayToSort100 = readFile("SortingTestFiles/100_Random.txt", 100);      
        mergeSort(arrayToSort100);
        printArray(arrayToSort100);        

        System.out.println("Using Sorted data");
        arrayToSort100 = readFile("SortingTestFiles/100_Sorted.txt", 100);
        mergeSort(arrayToSort100);
        printArray(arrayToSort100);
        
        System.out.println("Using Reverse data");
        arrayToSort100 = readFile("SortingTestFiles/100_Reverse.txt", 100);
        mergeSort(arrayToSort100);
        printArray(arrayToSort100);
        
        /* Array of Size 1000 */
        
        System.out.println();
        System.out.println("Result for Merge Sort of array of size 1000");
        
        System.out.println("Using Random data");
        int[] arrayToSort1000 = readFile("SortingTestFiles/1000_Random.txt", 1000);      
        mergeSort(arrayToSort1000);
        printArray(arrayToSort1000);        

        System.out.println("Using Sorted data");
        arrayToSort1000 = readFile("SortingTestFiles/1000_Sorted.txt", 1000);
        mergeSort(arrayToSort1000);
        printArray(arrayToSort1000);
        
        System.out.println("Using Reverse data");
        arrayToSort1000 = readFile("SortingTestFiles/1000_Reverse.txt", 1000);
        mergeSort(arrayToSort1000);
        printArray(arrayToSort1000);

                        
    }
 
 
    private static void TestInsertionSort(){
        /* Array of Size 10 */

        System.out.println("Result for Insertion sort of array of size 10");
        
        System.out.println("Using Random data");
        int[] arrayToSort10 = readFile("SortingTestFiles/10_Random.txt", 10);      
        insertionSort(arrayToSort10);
        printArray(arrayToSort10);        

        System.out.println("Using Sorted data");
        arrayToSort10 = readFile("SortingTestFiles/10_Sorted.txt", 10);
        insertionSort(arrayToSort10);
        printArray(arrayToSort10);
        
        System.out.println("Using Reverse data");
        arrayToSort10 = readFile("SortingTestFiles/10_Reverse.txt", 10);
        insertionSort(arrayToSort10);
        printArray(arrayToSort10);

        /* Array of Size 100 */
        System.out.println();
        System.out.println("Result for Insertion sort of array of size 100");
        
        System.out.println("Using Random data");
        int[] arrayToSort100 = readFile("SortingTestFiles/100_Random.txt", 100);      
        insertionSort(arrayToSort100);
        printArray(arrayToSort100);        

        System.out.println("Using Sorted data");
        arrayToSort100 = readFile("SortingTestFiles/100_Sorted.txt", 100);
        insertionSort(arrayToSort100);
        printArray(arrayToSort100);
        
        System.out.println("Using Reverse data");
        arrayToSort100 = readFile("SortingTestFiles/100_Reverse.txt", 100);
        insertionSort(arrayToSort100);
        printArray(arrayToSort100);
        
        /* Array of Size 1000 */
        System.out.println();
        System.out.println("Result for Insertion sort of array of size 1000");
        
        System.out.println("Using Random data");
        int[] arrayToSort1000 = readFile("SortingTestFiles/1000_Random.txt", 1000);      
        insertionSort(arrayToSort1000);
        printArray(arrayToSort1000);        

        System.out.println("Using Sorted data");
        arrayToSort1000 = readFile("SortingTestFiles/1000_Sorted.txt", 1000);
        insertionSort(arrayToSort1000);
        printArray(arrayToSort1000);
        
        System.out.println("Using Reverse data");
        arrayToSort1000 = readFile("SortingTestFiles/1000_Reverse.txt", 1000);
        insertionSort(arrayToSort1000);
        printArray(arrayToSort1000);

                        
    }
 

    private static void TestQuickSort(){
        /* Array of Size 10 */

        System.out.println("Result for Quick sort of array of size 10");
        
        System.out.println("Using Random data");
        int[] arrayToSort10 = readFile("SortingTestFiles/10_Random.txt", 10);      
        quickSort(arrayToSort10, 0, 10-1);
        printArray(arrayToSort10);        

        System.out.println("Using Sorted data");
        arrayToSort10 = readFile("SortingTestFiles/10_Sorted.txt", 10);
        quickSort(arrayToSort10, 0, 10-1);
        printArray(arrayToSort10);
        
        System.out.println("Using Reverse data");
        arrayToSort10 = readFile("SortingTestFiles/10_Reverse.txt", 10);
        quickSort(arrayToSort10, 0, 10-1);
        printArray(arrayToSort10);

        /* Array of Size 100 */
        System.out.println();
        System.out.println("Result for Quick sort of array of size 100");
        
        System.out.println("Using Random data");
        int[] arrayToSort100 = readFile("SortingTestFiles/100_Random.txt", 100);      
        quickSort(arrayToSort100, 0, 100-1);
        printArray(arrayToSort100);        

        System.out.println("Using Sorted data");
        arrayToSort100 = readFile("SortingTestFiles/100_Sorted.txt", 100);
        quickSort(arrayToSort100, 0, 100-1);
        printArray(arrayToSort100);
        
        System.out.println("Using Reverse data");
        arrayToSort100 = readFile("SortingTestFiles/100_Reverse.txt", 100);
        quickSort(arrayToSort100, 0, 100-1);
        printArray(arrayToSort100);
        
        /* Array of Size 1000 */
        System.out.println();
        System.out.println("Result for Quick sort of array of size 1000");
        
        System.out.println("Using Random data");
        int[] arrayToSort1000 = readFile("SortingTestFiles/1000_Random.txt", 1000);      
        quickSort(arrayToSort1000, 0, 1000-1);
        printArray(arrayToSort1000);        

        System.out.println("Using Sorted data");
        arrayToSort1000 = readFile("SortingTestFiles/1000_Sorted.txt", 1000);
        quickSort(arrayToSort1000, 0, 1000-1);
        printArray(arrayToSort1000);
        
        System.out.println("Using Reverse data");
        arrayToSort1000 = readFile("SortingTestFiles/1000_Reverse.txt", 1000);
        quickSort(arrayToSort1000, 0, 1000-1);
        printArray(arrayToSort1000);

                        
    }
     
}
