### Russian Peasant Multiplication Algorithm
#### Source Code
 - [RussianPeasant.java](../../master/src/RussianPeasant.java)

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
