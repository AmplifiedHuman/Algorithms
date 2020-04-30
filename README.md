# Algorithms Portfolio
This repository contains documentation and code that I have written for my Algorithms course COMP20290.

## Algorithms List
Algorithm | Description
--|--
[Russian Peasant Multiplication Algorithm](#russian-peasant-multiplication-algorithm) | In the Russian peasant method, the powers of two in the decomposition of the multiplicand are found by writing it on the left and progressively halving the left column, discarding any remainder, until the value is 1 (or −1, in which case the eventual sum is negated), while doubling the right column as before. Lines with even numbers on the left column are struck out, and the remaining numbers on the right are added together. - [Wikipedia](https://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication)
3Sum A & B | In computational complexity theory, the 3SUM problem asks if a given set of n real numbers contains three elements that sum to zero. - [Wikipedia](https://en.wikipedia.org/wiki/3SUM)
Nth Fibonacci Number | In mathematics, the Fibonacci numbers, commonly denoted F<sub>n</sub>, form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 to 1. This section contains different approaches including the iterative approach, recursive approach, and the memoization approach. - [Wikipedia](https://en.wikipedia.org/wiki/Fibonacci)
Tower of Hanoi | The Tower of Hanoi is a mathematical game or puzzle. It consists of three rods, and a number of disks of different sizes, which can slide onto any rod. The puzzle starts with the disks in a neat stack in ascending order of size on one rod, the smallest at the top, thus making a conical shape. The objective of the puzzle is to move the entire stack to another rod - [Wikipedia](https://en.wikipedia.org/wiki/Tower_of_Hanoi)
Bubble Sort | Bubble Sort, sometimes referred to as sinking sort, is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements and swaps them if they are in the wrong order. The pass through the list is repeated until the list is sorted. The algorithm, which is a comparison sort, is named for the way smaller or larger elements "bubble" to the top of the list. - [Wikipedia](https://en.wikipedia.org/wiki/Bubble_sort)
Selection Sort | Selection Sort divides the input list into two parts: a sorted sublist of items which is built up from left to right at the front (left) of the list, and a sublist of the remaining unsorted items that occupy the rest of the list. Initially, the sorted sublist is empty, and the unsorted sublist is the entire input list. The algorithm proceeds by finding the smallest element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element (putting it in sorted order), and moving the sublist boundaries one element to the right. - [Wikipedia](https://en.wikipedia.org/wiki/Selection_sort)
Insertion Sort | Insertion Sort iterates, consuming one input element each repetition, and growing a sorted output list. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there. It repeats until no input elements remain. - [Wikipedia](https://en.wikipedia.org/wiki/Insertion_sort)
Shell Sort | Shell Sort starts by sorting pairs of elements far apart from each other, then progressively reducing the gap between elements to be compared. Starting with far apart elements, it can move some out-of-place elements into position faster than a simple nearest neighbor exchange. The running time of Shell Sort is heavily dependent on the gap sequence it uses. - [Wikipedia](https://en.wikipedia.org/wiki/Shellsort)
Bogo Sort | Bogo Sort is a highly inefficient sorting algorithm based on the generate and test paradigm. The function successively generates permutations of its input until it finds one that is sorted. - [Wikipedia](https://en.wikipedia.org/wiki/Bogosort)
Merge Sort | Merge Sort divides the unsorted list into n sublists, each containing one element (a list of one element is considered sorted). It repeatedly merges sublists to produce new sorted sublists until there is only one sublist remaining to get the final sorted list - [Wikipedia](https://en.wikipedia.org/wiki/Merge_sort)
Quick Sort | Quicksort is a divide-and-conquer algorithm. It works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively. This can be done in-place, requiring small additional amounts of memory to perform the sorting. - [Wikipedia](https://en.wikipedia.org/wiki/Quicksort)
Brute Force Substring Search | The Brute Force algorithm compares the pattern to the text, one character at a time, until unmatching characters are found.
KMP Search | The Knuth–Morris–Pratt string-searching algorithm (or KMP algorithm) searches for occurrences of a "word" W within a main "text string" S by employing the observation that when a mismatch occurs, the word itself embodies sufficient information to determine where the next match could begin, thus bypassing re-examination of previously matched characters. - [Wikipedia](https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm)
Run Length Encoding | Run-length encoding (RLE) is a form of lossless data compression in which runs of data (sequences in which the same data value occurs in many consecutive data elements) are stored as a single data value and count, rather than as the original run. This is most useful on data that contains many such runs. - [Wikipedia](https://en.wikipedia.org/wiki/Run-length_encoding)
Huffman Encoding | Huffman coding uses a specific method for choosing the representation for each symbol, resulting in a prefix code (sometimes called "prefix-free codes", that is, the bit string representing some particular symbol is never a prefix of the bit string representing any other symbol). The output from Huffman's algorithm can be viewed as a variable-length code table for encoding a source symbol (such as a character in a file). The algorithm derives this table from the estimated probability or frequency of occurrence (weight) for each possible value of the source symbol. - [Wikipedia](https://en.wikipedia.org/wiki/Huffman_coding)

## Analysis

### Russian Peasant Multiplication Algorithm
How the algorithm works is fairly simple and can be illustrated using an example.  
  
#### Example: 85 x 18
Power | Binary Digit | First Number | Second Number
--|--|--|--
2<sup>0</sup> | 1 | 85 | 18
2<sup>1</sup> | 0 | 42 | 36
2<sup>2</sup> | 1 | 21 | 72
2<sup>3</sup> | 0 | 10 | 144
2<sup>4</sup> | 1 | 5 | 288
2<sup>5</sup> | 0 | 2 | 576
2<sup>6</sup> | 1 | 1 | 1152

We can see that by halving the first number, we will essentially get the binary representation of the first number in reverse (read from bottom up), in this example 18 = 1010101<sub>2</sub>.
In the algorithm, we only keep track of odd numbers, which is essentially the 1s in the binary representation, hence 85 x 18 can be simplified to  
(2<sup>0</sup> + 2<sup>2</sup> + 2<sup>4</sup> + 2<sup>6</sup>) * 18 = 1 * 18 + 4 * 18 + 16 * 18 + 64 * 18
  
#### Runtime
Attribute | Performance
--|--
Overall Runtime | O(log n), since we are halving the first number repeatedly
Worst Case | O(log n)
Best Case | O(log n)

#### Performance

![RussianPeasantGraph](/src/graphs/RussianPeasantGraph.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1_KWGXFESAdKmmsv-7X7g6ot7kqFwDrlZDvbGtQFJGfg/edit?usp=sharing "Graph Link")

From the graph, we can see that there's a huge spike at the very front, that is most likely due to a heavyweight initialization process. The time to complete it is included in the first measurement. Subsequent calls proceeded down the fast path where the state was already initialized.

Overall, we can see that the runtime of the algorithm grows very slowly relative to the input (even approaching 2<sup>31</sup> - 1) and resembles O(log n) growth which is very close to constant time O(1) performance.

### 3Sum Algorithm
#### 3SumA
For 3SumA it works by using three nested for loops. The performance suffers as the runtime is O(n<sup>3</sup>).  

##### Runtime of 3SumA  
Attribute | Performance
--|--
Overall Runtime | O(n<sup>3</sup>)

##### Performance of 3SumA

![3SumAGraph](/src/graphs/3SumA.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1oHA2O2ycLp78JbFN_iGmzpGkTrE2-W9NtBgp_TVLw24/edit?usp=sharing "Graph Link")

From the graph, we can see that for 3SumA the graph fits almost perfectly with a n<sup>3</sup> polynomial, so this further
consolidates our previous asymptotic analysis result.

#### 3SumB
For 3SumB, the array is sorted (O(n log n)) first and there are two for loops and a binary search. The overall runtime is O(n<sup>2</sup> log n).

##### Runtime of 3SumB 
Attribute | Performance
--|--
Overall Runtime | O(n<sup>2</sup> log n)

##### Performance of 3SumB

![3SumBGraph](/src/graphs/3SumB.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1oHA2O2ycLp78JbFN_iGmzpGkTrE2-W9NtBgp_TVLw24/edit?usp=sharing "Graph Link")

From the graph, we can see that for 3SumB the graph also looks like a quadratic graph, however it's not possible to fit a n<sup>2</sup> log n line on google sheets unfortunately.

#### 3Sum Comparison

![3SumA_BGraph](/src/graphs/3SumA_B.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1oHA2O2ycLp78JbFN_iGmzpGkTrE2-W9NtBgp_TVLw24/edit?usp=sharing "Graph Link")

If we compare both 3SumA and 3SumB we can see that there's a massive difference in terms of performance. 3SumB performed much better than 3SumA, because O(n<sup>2</sup> log n) behaves like O(n<sup>2</sup>) if the input is large enough. Effectively speaking we are comparing O(n<sup>2</sup>) against O(n<sup>3</sup>), as the latter has a higher degree, it will perform much worse than its counterpart.

### Nth Fibonacci Number Algorithm
#### Recursive Approach
The common recursive approach is clean and elegant but it suffers in terms of performance due a lot of redundant and repetitive calculations. The runtime complexity for this approach would be O(2<sup>n</sup>) since we are almost making twice the number of calls of the previous level, and there are n-levels. So, if we solve 1 + 2 + 4 + 8 + ... and there are n terms, we can use the [Geometric Series Sum formula](https://en.wikipedia.org/wiki/Geometric_series#Formula) to get the value 2<sup>n</sup> - 1. Hence, we can conclude that the runtime is exponential (O(2<sup>n</sup>)). 

The space complexity will be O(n) because the deepest level is the nth level. The first call is completed before the second call hence the maximum stack space the algorithm can occupy is n frames. The best case is O(1) when the input is 0 or 1.

##### Runtime of Recursive Approach
Attribute | Performance
--|--
Overall Runtime | O(2<sup>n</sup>)
Worst Case | O(2<sup>n</sup>)
Best Case | O(1)
Space Complexity | O(n)

##### Performance of Recursive Approach
![Fibonacci_Recursive](/src/graphs/Fibonacci_Recursive.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1ghyVCJWkgRQJNJfwR7eqcm_3EkmPnlMrbv5TFEhdTSo/edit?usp=sharing "Graph Link")

From the graph, we can see that for the recursive approach the graph fits almost perfectly with an exponential function, so this further consolidates our previous asymptotic analysis result.

#### Iterative Approach
The runtime complexity of the iterative approach is clearly linear O(n), as the loop runs from 2 to n, which means it's O(n) time. Note that for this approach, the space complexity O(1) since it does not take up any extra space. The best case is O(1) which is similar to the recursive approach.

##### Runtime of Iterative Approach 
Attribute | Performance
--|--
Overall Runtime | O(n)
Worst Case | O(n)
Best Case | O(1)
Space Complexity | O(1)

##### Performance of Iterative Approach
![Fibonacci_Iterative](/src/graphs/Fibonacci_Iterative.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1ghyVCJWkgRQJNJfwR7eqcm_3EkmPnlMrbv5TFEhdTSo/edit?usp=sharing "Graph Link")

From the graph, we can see that for the iterative approach the graph fits displayed a linear pattern. So, we can conclude the runtime is indeed linear.

#### Memoization Approach
In computing, memoization or memoisation is an optimization technique used primarily to speed up computer programs by storing the results of expensive function calls and returning the cached result when the same inputs occur again. For this particular problem, we can use an auxiliary array (or any reasonable data structure) for caching previously computed answers. By doing this, we truncate the other branch of the execution tree to obtain linear performance O(n). 

It works by first checking the array for previously computed answers, if so, return the answer directly. Or else, it computes the answer and stores it in the array for future use. This is also an excellent example of dynamic programming, where the idea is to simply store the results of subproblems, so that we do not have to re-compute them when needed later.

##### Runtime of Memoization Approach 
Attribute | Performance
--|--
Overall Runtime | O(n)
Worst Case | O(n)
Best Case | O(1)
Space Complexity | O(n)

##### Memoization Approach
![Fibonacci_Memoization](/src/graphs/Fibonacci_Memoization.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1ghyVCJWkgRQJNJfwR7eqcm_3EkmPnlMrbv5TFEhdTSo/edit?usp=sharing "Graph Link")

From the graph, we can see that for the memoization approach the graph fits displayed a sublinear pattern. There are spikes at when the input is small but it stabilises as a linear line when n is after 18. 

#### Fibonacci Comparison
![Fibonacci_Comparison](/src/graphs/Fibonacci_Comparison.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1ghyVCJWkgRQJNJfwR7eqcm_3EkmPnlMrbv5TFEhdTSo/edit?usp=sharing "Graph Link")

If we compare all the approaches, we can see that there's a massive difference in terms of performance. The iterative and the memoization approach performed much better than the recursive approach since we are comparing two O(n) algorithms against O(2<sup>n</sup>), as the latter has a higher degree, it will perform much worse than its counterparts.

### Tower of Hanoi Algorithm
This problem can be elegantly solved using recursion. 
The algorithm consists of the following steps:  
1. Move n - 1 disks to the mid tower
2. Move the last disk to the destination
3. Move the n - 1 from the mid tower to the destination

There is a total of 2 recursive calls with (n - 1) disks as the input, so similar to the Fibonacci recursive algorithm, we can see that the tree height is n and we are making twice the number of calls of the previous level. Hence the runtime is exponential (O(2<sup>n</sup>)). The space complexity will be O(n) because the deepest level is the nth level. The first call is completed before the second call hence the maximum stack space the algorithm can occupy is n frames. The best case is O(1) when the input is 1.
  
#### Runtime
Attribute | Performance
--|--
Overall Runtime | O(2<sup>n</sup>)
Worst Case | O(2<sup>n</sup>)
Best Case | O(1)
Space Complexity | O(n)

#### Performance

![TowerOfHanoiGraph](/src/graphs/Tower_of_Hanoi.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1pINJeaU_bLvGYepocz3DMri5iCqDiT0IfOk2azq-Whs/edit?usp=sharing "Graph Link")

From the graph, we can see that the graph fits almost perfectly with an exponential function, so the runtime (order of growth) of the algorithm is O(2<sup>n</sup>).

### Bubble Sort
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

### Brute Force Substring Search
The brute-force algorithm works by starting at the beginning of the string and compare each character of your pattern against the subsequent characters in the string.
Once the algorithm finishes checking the first pattern then increment the pointer to the next character in the string and start the process again. This implementation is designed to stop on the first occurence of the pattern.

The Brute Force algorithm has the worst case of O(NW) where N is the length of the input text and W is the length of the search pattern. The best case is O(N + W) when there is no backup during the search.

#### Runtime
Attribute | Performance
--|--
Worst Case | O(NW)
Best Case | O(N + W)
Space Complexity | O(1)

#### Performance

![BruteForceSearch](/src/graphs/Brute_Force_Search.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1HenaccKrhxz3L9JKX1EPfWJ87_le9FmdZpILWAapYhw/edit?usp=sharing "Graph Link")

From the graph, we can see that the running time increases proportionally when the input grows larger.

### KMP Search
KMP improves upon this by taking advantage of prior knowledge about the text being searched. The key idea then behind KMP is that if we already have matched the first x characters of the pattern and then we encounter a mismatch, we don’t necessarily have to move to the very next character in the search text. We might be able to take advantage of our knowledge of the pattern we’re looking for and the characters we’ve already encountered in the search text to jump ahead a little.

KMP has two efficiencies by taking advantage of prior knowledge:
 - We can skip some iterations for which we know no match is possible and
 - We can also skip some iterations in the inner loop
 
Steps:
 - **Preprocess Pattern** - the search pattern needs to be preprocessed to create a longest prefix suffix array to aid the search process.
 - **Searching** - we use the constructed LPS array to search for the pattern and determine how much steps do we need to jump back in the pattern array if a mismatch occurs.

#### Runtime
Attribute | Performance
--|--
Worst Case | O(N + W)
Best Case | O(N + W)
Space Complexity | O(W), for storing LPS array

#### Performance

![KMPSearch](/src/graphs/KMP_Search.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1HenaccKrhxz3L9JKX1EPfWJ87_le9FmdZpILWAapYhw/edit?usp=sharing "Graph Link")

From the graph, we can see that the running time increases proportionally when the input grows larger.

### Searching Comparison

![Searching Comparison](/src/graphs/Searching_Comparison.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1HenaccKrhxz3L9JKX1EPfWJ87_le9FmdZpILWAapYhw/edit?usp=sharing "Graph Link")

We can see the KMP algorithm performs better than the Brute Force algorithm by a small margin. This is due to the constant factor of the KMP algorithm is much larger than the constant factor of the Brute Force algorithm. The value of n has to be awfully big, and the number of sub-optimal partial matches has to be very large, for the KMP algorithm to win over the blazingly fast naive algorithm.

KMP will only be a superior choice if we are searching for large strings and the LPS table is reusable for every search, as we would need to amortise away the huge cost of building the table by doing lots of searches using that table.

### Run Length Encoding
In RLE, the characters in the input string must first be looped over. An int counter is used to count the number of times the same character has been shown in a row. When a different character occurs, the counter value and the previous character is outputed. We continue till the end of the list. A small optimisation is added by not outputing the 1 counts.

#### Runtime
Attribute | Performance
--|--
Worst Case | O(n)
Best Case | O(n)
Space Complexity | O(1)

#### Performance

Name | Type | Input Size | Compressed Size | Compression Ratio
--|--|--|--|--
4runs.bin | Binary | 40	| 32 | 0.8
abra.txt | Text |	104 |	456 | 4.38
mobydick.txt | Text	| 9531704	| 38698936 | 4.06
q32x48.bin | Bitmap | 1536 | 1144 | 0.74
q64x96.bin | Bitmap	| 6144 | 2296 | 0.37

It is apparent that Run Length Encoding performs poorly on ASCII text as the resulting file ended up being larger than the original file by nearly 4 times (Compression Ratio > 1). 

In the provided implementation of RunLength.java, 8 bits are used to store the counts. It alternates between 1 and 0 for each entry and the count is fixed to 8 bits so it pads smaller counts with 0 values. So for ASCII text, as there are not a lot of runs of 0 and 1, we ended up wasting a lot of bits on short runs.

Hence Run Length Encoding is widely used for bitmaps because this input data is more likely to have long runs of repeated data (i.e. pixels).


### Huffman Encoding
This is the documentation of the final assignment of the module where we build our own utility for compressing and decompressing files.

The construction of a Huffman tree can be illustrated with an example:
If we are given the phrase **"There is no place like home"**, without double quotes, we can first construct the frequency table by counting each character occurence in the phrase.

#### Frequency Table

Character | Frequency
-- | --
e | 5
space | 5
h | 2
i | 2
o | 2
l | 2
T | 1
r | 1
s | 1
n | 1
p | 1
a | 1
c | 1
k | 1
m | 1

#### Huffman Tree Construction

We can then construct the Huffman Tree by selecting the smallest two nodes and merging them, until there is one root left.
Note that: if two nodes have equal weight, it doesn't matter which one we choose as the produced code will still adhere to the Huffman Tree Rules.

![Huffman Tree](/src/graphs/Huffman_Tree.png)

#### Encoding Table
After constructing the tree, we can just create the codes for each character by tracing a path from root to leaf node (aggregating the bits along the way). 

Character | Frequency | Encoding
-- | -- | --
e | 5 | 00
space | 5 | 01
h | 2 | 1000
i | 2 | 1010
o | 2 | 1011
l | 2 | 11000
T | 1 | 11001
r | 1 | 11010
s | 1 | 11011
n | 1 | 10010
p | 1 | 10011
a | 1 | 11110
c | 1 | 11111
k | 1 | 11100
m | 1 | 11101

Average bits per character can be calculated as follows: ((5 * 2) + (3 * 2 * 4) + (2 * 5) + (9 * 5)) / 27 = 3.3 bits per character, which is better than 4 bits.

#### Get Started
Run `javac Huffman.java` in the src directory, then use the appropiate arguments for different operation methods:  

Read from stdin and output to stdout: 
`java Huffman [compress|decompress] < inputfile`  

Read from input file and output to output file: 
`java Huffman [compress|decompress] [inputfile] [outputfile]`  

Run benchmark for provided input files: 
`java Huffman benchmark`  

#### Runtime
Building the Huffman Tree would take O(k log k) time where k is the size of the char set. (example: k = 256 (max) for ascii)

In many cases, time complexity is not very important in the choice of algorithm here, since k here is the number of symbols in the alphabet, which is typically a very small number (compared to the length of the message to be encoded); whereas complexity analysis concerns the behavior when k grows to be very large.

For the compressing operation, it would take O(n + k log k) where n is the size of the text and k log k is the time to build the Huffman Tree, note that the k log k part is offen negligible since the char set size is usually small.

Similar to the compressing operation, decompression would also take O(n + k log k) time since we need to reconstruct the Huffman Tree again and decode each individual character in the input.


#### Compression Analysis
Note:
The compressed files can be found under src/compressed_files/ and has the file name "filename_compressed.*" where * is the extension of the respective file and filename is the corresponding input file name.

The input file of my choosing is bee.txt which contains the entire bee movie script.

I have written a method called generateCompressionAnalysis() in Huffman.java which generates the data Huffman.txt, by compressing and decompressing the input files and recording the time taken and also the size difference.

If we format the data into tabular form, we would get the following result:

File	| Input Size | Compressed Size | Compression Ratio | Compression Time |	Decompression Time | Decompressed Size
-- | -- | -- | -- | -- | -- | --
q32x48.bin | 1536 | 816 | 0.53125 | 212243 | 40709 | 1536
medTale.txt	| 45056 |	23912 |	0.530717 | 6846695 | 2174448 | 45056
genomeVirus.txt | 50008 | 12576 | 0.25148 | 896080 | 270402 | 50008
bee.txt	| 401112 | 231976 | 0.578332 | 14536900 | 2107232 | 401112
mobydick.txt | 9531704 | 5341208 | 0.560362 | 113272887 | 46806047 | 9531704

If we plot the compression time and decompression time relative to the input size, we would obtain the following graph:
![Huffman Compression Decompression Graph](/src/graphs/Huffman_Compress_Decompress.png)

[Graph Link](https://docs.google.com/spreadsheets/d/18CtPVsTnGmuOjtCjKfd8xp5nqEoQ8kqKwdQVQFFS7to/edit?usp=sharing "Graph Link")

We can see that both the compression and decompression time grows linearly when the input increases which is the expected result since the algorithm is effectively O(n) since the character set is not large.

Another interesting observation is that the algorithm achieved extraordinary performance when the character set is small (genomeVirus.txt) as the file is shrinked to almost 25% of its original size. 

Besides, for normal text files with english text, the compression ratio is normally around 50-60% since the english language contains quite a lot of redundacies.
