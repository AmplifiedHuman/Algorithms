### 3Sum Algorithm
#### 3SumA
##### Source Code
 - [ThreeSumA.java](../../master/src/ThreeSumA.java)

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
##### Source Code
 - [ThreeSumB.java](../master/src/ThreeSumB.java)

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