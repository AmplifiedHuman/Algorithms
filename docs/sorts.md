## Sorts
### Bubble Sort
#### Source Code
 - [Sorter.java](../../master/src/Sorter.java)

In each iteration of Bubble Sort, we swap each element pair if the first element is bigger than the second element. At the nth iteration, it's guaranteed that the nth largest element is in their correct place. We can see that there are two for loops where the indices of the second for loop depends on the first for loop, so the runtime complexity is quadratic. 

From a geometrical standpoint, for each outer loop iteration, the inner loop iteration count decreases by 1, so it resembles a triangular shape. We know that the area of the triangle is can be calculated by 1/2 * base * height, as the base and the height are the same, the runtime complexity can be calculated as follows O(n<sup>2</sup>/2) = O(n<sup>2</sup>). 

For this specific implementation, the worst case is O(n<sup>2</sup>) but can be shortened to O(n) if we terminate the method as soon as we encounter a 0-swap iteration (array already sorted).
  
#### Runtime
Attribute | Performance
--|--
Overall Runtime | O(n<sup>2</sup>)
Worst Case | O(n<sup>2</sup>)
Best Case | O(n<sup>2</sup>)
Space Complexity | O(1)
Stable | Yes, can be made stable

#### Performance

![BubbleSortGraph](/src/graphs/Bubble_Sort.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1K42GfhnHgZ4xDVeytyaHNN5rqhDBLk0ozXtFv1bUDzE/edit?usp=sharing "Graph Link")

From the graph, we can see that the graph fits almost perfectly with a quadratic polynomial, so the runtime (order of growth) of the algorithm is indeed O(n<sup>2</sup>).

### Selection Sort
#### Source Code
 - [Sorter.java](../../master/src/Sorter.java)

For each iteration of Selection Sort, we find the i-th smallest element in the array and swaps it with the current i-th element. Similar to Bubble Sort as there are two for loops and the iteration of the inner for loop decreases by one for each iteration of the outer loop, we can simply the sum of n + (n - 1) + (n - 2) + ... 3 + 2 + 1 using the sum from [1 to N formula](https://en.wikipedia.org/wiki/1_%2B_2_%2B_3_%2B_4_%2B_%E2%8B%AF) to get n * (n + 1) / 2, so the runtime complexity is O(n<sup>2</sup>).

#### Runtime
Attribute | Performance
--|--
Overall Runtime | O(n<sup>2</sup>)
Worst Case | O(n<sup>2</sup>)
Best Case | O(n<sup>2</sup>)
Space Complexity | O(1)
Stable | Yes, can be made stable

#### Performance

![SelectionSortGraph](/src/graphs/Selection_Sort.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1K42GfhnHgZ4xDVeytyaHNN5rqhDBLk0ozXtFv1bUDzE/edit?usp=sharing "Graph Link")

From the graph
, we can see that the graph fits almost perfectly with a quadratic polynomial, so the runtime (order of growth) of the algorithm is indeed O(n<sup>2</sup>).

### Insertion Sort
#### Source Code
 - [Sorter.java](../../master/src/Sorter.java)

At each iteration, insertion sort takes the next i-th element and inserts it to its appropriate place in the sorted section of the array. In the worst case, the element would need to be placed at the start of the array if it's the smallest element we have encountered so far. Hence, we can see that the runtime complexity will be O(n<sup>2</sup>) as for each element the worst case is O(n). 

An interesting observation is the fact that if the provided array is in descending order it will be considered the worst case since each element would need to be moved to the start of the array for each iteration. Insertion sort only takes one iteration to complete if the array is already sorted and performs very well on partially sorted small arrays.

#### Runtime
Attribute | Performance
--|--
Overall Runtime | O(n<sup>2</sup>)
Worst Case | O(n<sup>2</sup>)
Best Case | O(n)
Space Complexity | O(1)
Stable | Yes, can be made stable

#### Performance

![InsertionSortGraph](/src/graphs/Insertion_Sort.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1K42GfhnHgZ4xDVeytyaHNN5rqhDBLk0ozXtFv1bUDzE/edit?usp=sharing "Graph Link")

From the graph, we can see that the graph fits almost perfectly with a quadratic polynomial, so the runtime (order of growth) of the algorithm is indeed O(n<sup>2</sup>).

### Shell Sort
#### Source Code
 - [Sorter.java](../../master/src/Sorter.java)

Shell sort is a subtle improvement of insertion sort, instead of moving one position at a time, we make jumps to move the smaller elements to the front of the array faster. The sequence used for this implementation of Shell Sort is the Knuth Sequence where the kth element in the sequence is defined as (3<sup>k</sup>-1)/2 and bounded by N/3 (1, 4, 13, 40, 121, 364, ...). We can obtain linearithmic performance O(n log n) for the best case and O(n<sup>3/2</sup>) for the worst case.
#### Runtime
Attribute | Performance
--|--
Worst Case | O(n<sup>3/2</sup>)
Best Case | O(n log n)
Space Complexity | O(1)
Stable | No

#### Performance

![ShellSortGraph](/src/graphs/Shell_Sort.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1K42GfhnHgZ4xDVeytyaHNN5rqhDBLk0ozXtFv1bUDzE/edit?usp=sharing "Graph Link")

From the graph, we can see that the algorithm performs very well and grows almost linearly even when the input is quite large. However, the performance is inconsistent as there are multiple spikes in the graph.

### Bogo Sort
#### Source Code
 - [Sorter.java](../../master/src/Sorter.java)

Bogo-sort is equivalent to repeatedly throwing a deck of cards in the air, picking them up at random, and then testing whether they are in order. It performs extremely badly and should not be used at all.
#### Runtime
Attribute | Performance
--|--
Average Case | O(n * n!)
Worst Case | O(∞), might go on forever
Best Case | O(n), array already sorted
Space Complexity | O(1)
Stable | No

#### Performance

![BogoSortGraph](/src/graphs/Bogo_Sort.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1K42GfhnHgZ4xDVeytyaHNN5rqhDBLk0ozXtFv1bUDzE/edit?usp=sharing "Graph Link")

From the graph, we can see that the algorithm is inconsistent as it relies on probability, as the input size grows, and when n > 13 it will take an extraordinary amount of time to compute as the factorial function grows even faster than an exponential algorithm.

### Elementary Sorts Comparison

![SlowSortsGraph](/src/graphs/Slow_Sorts.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1L8M2HLUyXZ69qncev7MBSP7mjc3p310YiYvOXW1R_n0/edit?usp=sharing "Graph Link")

When we compare the all the elementary sorts listed above, we can see that bubble sort performs the worst, as it requires kxn comparisons, where k is the maximum distance between an entry's starting position and its ending position, which is usually larger than n/2 for uniformly distributed initial values (assuming random entries). 

Insertion Sort performs better than Selection Sort since the best case for Insertion Sort is O(n) and for each i-th iteration, there can be less than i comparison whereas for Selection Sort, it is guaranteed that there will be exactly (n - i) comparisons for the i-th iteration. 

Shell Sort performs the best among all elementary sorting algorithms as it is more asymptotically efficient than the other algorithms by making jumps (reducing the number of swaps). 

### Merge Sort
#### Source Code
 - [Sorter.java](../../master/src/Sorter.java)

Merge Sort uses the divide and conquer approach to partition the array into halves, recursively sorting each half and finally merging both sorted arrays to form the final sorted array. As the merging operation takes O(n) time when merging both halves, and for each level i, the runtime will be O(i * (n / i)) = O(n), so the amount of work remains O(n) for each level. As we are halving the array for each level until the they eventually reach 1, there will be log<sub>2</sub>n levels. So, combining the the work done on each level, and the number of levels, the runtime of the algorithm will be O(n log n). 

The main disadvantage of merge sort is that it takes up O(n) space for auxiliary arrays, so it's not in place. 

A cutoff for small subarrays is added, and small subarrays are sorted using insertion sort for increase efficiency. A check is also added to check if the last element in the first sorted array is less than or equal to the first element in the second sorted array if so, just add the entire second array in without the need for comparisons.

#### Runtime
Attribute | Performance
--|--
Average Case | O(n log n)
Worst Case | O(n log n)
Best Case | O(n log n), array already sorted
Space Complexity | O(n)
Stable | Yes, depends on the merging operation

#### Performance

![MergeSortGraph](/src/graphs/Merge_Sort.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1K42GfhnHgZ4xDVeytyaHNN5rqhDBLk0ozXtFv1bUDzE/edit?usp=sharing "Graph Link")

From the graph, we can see that the algorithm performs very well and grows almost linearly even when the input is quite large. So, it is an efficient algorithm and is used in many practical applications.

### Quick Sort
#### Source Code
 - [Sorter.java](../../master/src/Sorter.java)

Quick Sort also uses the divide and conquer approach and uses pivot elements to recursively partition the array into two sections and joining them together after sorting each half recursively. So, like merge sort, it will achieve O(n log n) time for the best case.

The main disadvantage of Quick Sort is its performance relies on the pivot selection algorithm. For example, if the pivot element is always the first element and the input is a sorted array, each partition process will only partition one element, and there will be n levels, so by using a similar analysis technique like the Selection Sort we would get the worst case runtime which is O(n<sup>2</sup>).

The above problem can be easily avoided by selecting pivots at random. And on average, its runtime will be O(log n). Quick Sort is an in-place algorithm but however it does not guarantee stability.

#### Runtime
Attribute | Performance
--|--
Average Case | O(n log n)
Worst Case | O(n<sup>2</sup>)
Best Case | O(n log n)
Space Complexity | O(1)
Stable | No, achievable but impractical

#### Performance

![QuickSortGraph](/src/graphs/Quick_Sort.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1K42GfhnHgZ4xDVeytyaHNN5rqhDBLk0ozXtFv1bUDzE/edit?usp=sharing "Graph Link")

From the graph, we can see that the algorithm performs very well and grows almost linearly even when the input is quite large. So, it is an efficient algorithm and is used in many practical applications.

#### Enhanced Quick Sort
Similar to the Merge Sort implementation, a cutoff is added for small subarrays and insertion sort is used to handle them. The array is first shuffled first using the [Fisher-Yates Shuffle](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle) algorithm. It also uses median-of-three partitioning and the pivot item is selected as the median between the first element, the last element, and the middle element (decided using integer division of n/2). 

#### Performance

![EnhancedQuickSortGraph](/src/graphs/Enhanced_Quick_Sort.png)

The enhanced quick sort algorithm performs slightly better than the normal quick sort algorithm as shown in the graph.

### Effecient Sorts Comparison

![FastSortsGraph](/src/graphs/Fast_Sorts.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1L8M2HLUyXZ69qncev7MBSP7mjc3p310YiYvOXW1R_n0/edit?usp=sharing "Graph Link")

When we compare all the efficient sorts listed above, we can see that the enhanced Quick Sort algorithm performs the best out of the 3 algorithms. Quick Sort often outperforms Merge Sort as,

> Typically, quicksort is significantly faster in practice than other O(n log n) algorithms, because its inner loop can be efficiently implemented on most architectures, and in most real-world data, it is possible to make design choices that minimize the probability of requiring quadratic time. Additionally, quicksort tends to make excellent usage of the memory hierarchy, taking perfect advantage of virtual memory and available caches. Although quicksort is not an in-place sort and uses auxiliary memory, it is very well suited to modern computer architectures.
-[Algorithm Design Manual](https://books.google.com.my/books?id=7XUSn0IKQEgC&lpg=PR1&dq=The+algorithm+design+manual.&pg=PA129&redir_esc=y#v=onepage&q=When%20faced%20with%20algorithms%20of%20the&f=false)

### All Sorts Comparison

![AllSortsGraph](/src/graphs/All_Sorts.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1L8M2HLUyXZ69qncev7MBSP7mjc3p310YiYvOXW1R_n0/edit?usp=sharing "Graph Link")

If we compare all the sorting algorithms above, we can see that the linearithmic (O(n log n)) algorithms outperform the slower O(n<sup>2</sup>) algorithms by a lot. So, it is clear that asymptotic analysis can save us a lot of time by estimating how the algorithm will scale as the input grows.