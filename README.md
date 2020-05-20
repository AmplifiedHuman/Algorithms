# Algorithms Portfolio
This repository contains documentation and code that I have written for my Algorithms course COMP20290.

## Interactive Demo
To run the interactive demo, run [Main.java](../master/src/Main.java).

Note that: 3SumA & B, RLE, and Huffman Encoding are not available and can only be run through the terminal.

## Algorithms List
Algorithm | Description
--|--
[Russian Peasant Multiplication Algorithm](../master/docs/russian_peasant.md) | In the Russian peasant method, the powers of two in the decomposition of the multiplicand are found by writing it on the left and progressively halving the left column, discarding any remainder, until the value is 1 (or −1, in which case the eventual sum is negated), while doubling the right column as before. Lines with even numbers on the left column are struck out, and the remaining numbers on the right are added together. - [Wikipedia](https://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication)
[3Sum A & B](../master/docs/three_sum.md) | In computational complexity theory, the 3SUM problem asks if a given set
 of n real
 numbers contains three elements that sum to zero. - [Wikipedia](https://en.wikipedia.org/wiki/3SUM)
[Nth Fibonacci Number](../master/docs/fibo.md) | In mathematics, the Fibonacci numbers, commonly denoted F<sub>n</sub>, 
form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 to 1. This section contains different approaches including the iterative approach, recursive approach, and the memoization approach. - [Wikipedia](https://en.wikipedia.org/wiki/Fibonacci)
[Tower of Hanoi](../master/docs/hanoi.md) | The Tower of Hanoi is a mathematical game or puzzle. It consists of three
 rods, and a number of disks of different sizes, which can slide onto any rod. The puzzle starts with the disks in a neat stack in ascending order of size on one rod, the smallest at the top, thus making a conical shape. The objective of the puzzle is to move the entire stack to another rod - [Wikipedia](https://en.wikipedia.org/wiki/Tower_of_Hanoi)
[Bubble Sort](../master/docs/sorts.md#bubble-sort) | Bubble Sort, sometimes referred to as sinking sort, is a simple
 sorting algorithm that repeatedly steps through the list, compares adjacent elements and swaps them if they are in the wrong order. The pass through the list is repeated until the list is sorted. The algorithm, which is a comparison sort, is named for the way smaller or larger elements "bubble" to the top of the list. - [Wikipedia](https://en.wikipedia.org/wiki/Bubble_sort)
[Selection Sort](../master/docs/sorts.md#selection-sort) | Selection Sort divides the input list into two parts: a sorted sublist of items which is built up from left to right at the front (left) of the list, and a sublist of the remaining unsorted items that occupy the rest of the list. Initially, the sorted sublist is empty, and the unsorted sublist is the entire input list. The algorithm proceeds by finding the smallest element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element (putting it in sorted order), and moving the sublist boundaries one element to the right. - [Wikipedia](https://en.wikipedia.org/wiki/Selection_sort)
[Insertion Sort](../master/docs/sorts.md#insertion-sort) | Insertion Sort iterates, consuming one input element each repetition, and growing a sorted output list. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there. It repeats until no input elements remain. - [Wikipedia](https://en.wikipedia.org/wiki/Insertion_sort)
[Shell Sort](../master/docs/sorts.md#shell-sort) | Shell Sort starts by sorting pairs of elements far apart from each other, then progressively reducing the gap between elements to be compared. Starting with far apart elements, it can move some out-of-place elements into position faster than a simple nearest neighbor exchange. The running time of Shell Sort is heavily dependent on the gap sequence it uses. - [Wikipedia](https://en.wikipedia.org/wiki/Shellsort)
[Bogo Sort](../master/docs/sorts.md#bogo-sort) | Bogo Sort is a highly inefficient sorting algorithm based on the generate and test paradigm. The function successively generates permutations of its input until it finds one that is sorted. - [Wikipedia](https://en.wikipedia.org/wiki/Bogosort)
[Merge Sort](../master/docs/sorts.md#merge-sort) | Merge Sort divides the unsorted list into n sublists, each containing one element (a list of one element is considered sorted). It repeatedly merges sublists to produce new sorted sublists until there is only one sublist remaining to get the final sorted list - [Wikipedia](https://en.wikipedia.org/wiki/Merge_sort)
[Quick Sort](../master/docs/sorts.md#quick-sort) | Quicksort is a divide-and-conquer algorithm. It works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot. The sub-arrays are then sorted recursively. This can be done in-place, requiring small additional amounts of memory to perform the sorting. - [Wikipedia](https://en.wikipedia.org/wiki/Quicksort)
[Brute Force Substring Search](../master/docs/brute_force_search.md) | The Brute Force algorithm compares the pattern to
 the text, one character at a time, until unmatching characters are found.
[KMP Search](../master/docs/kmp.md) | The Knuth–Morris–Pratt string-searching algorithm (or KMP algorithm) searches for
 occurrences of a "word" W within a main "text string" S by employing the observation that when a mismatch occurs, the word itself embodies sufficient information to determine where the next match could begin, thus bypassing re-examination of previously matched characters. - [Wikipedia](https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm)
[Run Length Encoding](../master/docs/rle.md) | Run-length encoding (RLE) is a form of lossless data compression in
 which runs of data (sequences in which the same data value occurs in many consecutive data elements) are stored as a single data value and count, rather than as the original run. This is most useful on data that contains many such runs. - [Wikipedia](https://en.wikipedia.org/wiki/Run-length_encoding)
[Huffman Encoding](../master/docs/huffman.md) | Huffman coding uses a specific method for choosing the representation
 for each symbol, resulting in a prefix code (sometimes called "prefix-free codes", that is, the bit string representing some particular symbol is never a prefix of the bit string representing any other symbol). The output from Huffman's algorithm can be viewed as a variable-length code table for encoding a source symbol (such as a character in a file). The algorithm derives this table from the estimated probability or frequency of occurrence (weight) for each possible value of the source symbol. - [Wikipedia](https://en.wikipedia.org/wiki/Huffman_coding)
