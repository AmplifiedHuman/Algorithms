### KMP Search
#### Source Code
 - [KMPSearch.java](../../master/src/KMPSearch.java)

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
