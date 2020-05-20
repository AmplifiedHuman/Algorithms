
### Tower of Hanoi Algorithm
#### Source Code
 - [TowerOfHanoi.java](../../master/src/TowerOfHanoi.java)

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