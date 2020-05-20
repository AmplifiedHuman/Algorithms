### Nth Fibonacci Number Algorithm
#### Source Code
 - [Fibonacci.java](../../master/src/Fibonacci.java)

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