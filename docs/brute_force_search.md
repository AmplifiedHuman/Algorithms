### Brute Force Substring Search
#### Source Code
 - [BruteForceSearch.java](../../master/src/BruteForceSearch.java)

The brute-force algorithm works by starting at the beginning of the string and compare each character of your pattern against the subsequent characters in the string.
Once the algorithm finishes checking the first pattern then increment the pointer to the next character in the string and start the process again. This implementation is designed to stop on the first occurrence of the pattern.

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