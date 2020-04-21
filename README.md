# Algorithms Portfolio
This repository contains documentation and code that I have written for my Algorithms course COMP20290.

## Algorithms List
Algorithm | Description
--|--
Russian Peasant Multiplication Algorithm | In the Russian peasant method, the powers of two in the decomposition of the multiplicand are found by writing it on the left and progressively halving the left column, discarding any remainder, until the value is 1 (or −1, in which case the eventual sum is negated), while doubling the right column as before. Lines with even numbers on the left column are struck out, and the remaining numbers on the right are added together.
3Sum A & B | In computational complexity theory, the 3SUM problem asks if a given set of n real numbers contains three elements that sum to zero.


## Analysis

### Russian Peasant Multiplication Algorithm
How the algorithm works is fairly simple and can be illustrated using an example.  
  
**Example: 85 x 18**
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
  
**Runtime**
Attribute | Performance
--|--
Overall Runtime | O(log n), since we are halving the first number repeatedly
Worst Case | O(log n)
Best Case | O(log n)

**Performance Analysis**

![RussianPeasantGraph](/src/graphs/RussianPeasantGraph.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1_KWGXFESAdKmmsv-7X7g6ot7kqFwDrlZDvbGtQFJGfg/edit?usp=sharing "Graph Link")

From the graph, we can see that there's a huge spike at the very front, that is most likely due to a heavyweight initialization process. The time to complete it is included in the first measurement. Subsequent calls proceed down the fast path where the state is already initialized.    
Overall, we can see that the runtime of the algorithm grows very slowly relative to the input (even approaching 2<sup>31</sup> - 1) and ressembles O(log n) growth which is very close to constant time O(1) performance.

### 3Sum Algorithm
For 3SumA it works by using three nested for loops. The performance suffers as the runtime is O(n<sup>3</sup>).  

For 3SumB, the array is sorted (O(n log n)) first and there are two for loops and a binary search. The overall runtime is O(n<sup>2</sup> log n).

**Runtime of 3SumA**  
Attribute | Performance
--|--
Overall Runtime | O(n<sup>3</sup>)

**Runtime of 3SumB**  
Attribute | Performance
--|--
Overall Runtime | O(n<sup>2</sup> log n)

**Performance Analysis**

![3SumAGraph](/src/graphs/3SumA.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1oHA2O2ycLp78JbFN_iGmzpGkTrE2-W9NtBgp_TVLw24/edit?usp=sharing "Graph Link")

From the graph, we can see that for 3SumA the graph fits almost perfectly with a n<sup>3</sup> polynomial, so this further
consolidates our previous asymptotic analysis result.

![3SumAGraph](/src/graphs/3SumB.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1oHA2O2ycLp78JbFN_iGmzpGkTrE2-W9NtBgp_TVLw24/edit?usp=sharing "Graph Link")

From the graph, we can see that for 3SumB the graph also looks like a quadratic graph, however it's not possible to fit a n<sup>2</sup> log n line on google sheets unfortunately.

![3SumA](/src/graphs/3SumA_B.png)

[Graph Link](https://docs.google.com/spreadsheets/d/1oHA2O2ycLp78JbFN_iGmzpGkTrE2-W9NtBgp_TVLw24/edit?usp=sharing "Graph Link")

If we compare both 3SumA and 3SumB we can see that there's a massive difference in terms of performance. 3SumB performs much better than 3SumA, because O(n<sup>2</sup> log n) behaves like O(n<sup>2</sup>) if the input is large enough. Effectively speaking we are comparing O(n<sup>2</sup>) against O(n<sup>3</sup>), as the latter has a higher degree, it will perform much worse than it's counterpart.
