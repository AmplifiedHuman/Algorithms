### Huffman Encoding
#### Source Code
 - [Huffman.java](../../master/src/Huffman.java)

This is the documentation of the final assignment of the module where we build our own utility for compressing and decompressing files.

The construction of a Huffman tree can be illustrated with an example:
If we are given the phrase **"There is no place like home"**, without double quotes, we can first construct the frequency table by counting each character occurrence in the phrase.

PDF form of example: [huffman_example.pdf](../../master/huffman_example.pdf) 
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

Note that: When we are picking the two smallest nodes, there might exist multiple nodes with the same weight. The Huffman algorithm does not specify the method for choosing the correct node in these kinds of cases. So, no matter which node we pick as long as **their weight are equal** it will still encode the message with the optimal number of bits among prefix-free codes. 

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

Average bits per character can be calculated as follows: ((5 * 2) + (3 * 2 * 4) + (2 * 5) + (9 * 5)) / 27 = 3.3 bits per character, which is better than 4 bits. We are taking advantage of the fact that each character has different probabilities (occurrences), so we can assign shorter codes to more frequent characters to save space, in contrast to RLE which uses a fix number of bits to represent the count.

#### Get Started
Run `javac Huffman.java` in the src directory, then use the appropriate arguments for different operation methods:  

Read from stdin and output to stdout:

`java Huffman [compress|decompress] < inputfile`  

Read from input file and output to output file: 

`java Huffman [compress|decompress] [inputfile] [outputfile]`  

Run benchmark for provided input files: 

`java Huffman benchmark`  

#### Runtime
Building the Huffman Tree would take O(k log k) time where k is the size of the char set. (example: k = 256 (max) for ascii)

In many cases, time complexity is not very important in the choice of algorithm here, since k here is the number of symbols in the alphabet, which is typically a very small number (compared to the length of the message to be encoded); whereas complexity analysis concerns the behavior when k grows to be very large.

For the compressing operation, it would take O(n + k log k) where n is the size of the text and k log k is the time to build the Huffman Tree, note that the k log k part is often negligible since the char set size is usually small.

Similar to the compressing operation, decompression would also take O(n + k log k) time since we need to reconstruct the Huffman Tree again and decode each individual character in the input.


#### Compression Analysis
Note:
The compressed files can be found under [src/compressed_files/](../../master/src/compressed_files/) and has the file name "filename_compressed.*" where * is the extension of the respective file and filename is the corresponding input file name.

The input file of my choosing is [bee.txt](../../master/src/input_files/bee.txt) which contains the entire bee movie script.

I have written a method called generateCompressionAnalysis() in [Huffman.java](../../master/src/Huffman.java) which generates the data [Huffman.txt](../../master/src/compressed_files/Huffman.txt), by compressing and decompressing the input files and recording the time taken and also recording the size difference.

If we format the data into tabular form, we would get the following result:

File	| Input Size | Compressed Size | Compression Ratio | Compression Time |	Decompression Time | Decompressed Size
-- | -- | -- | -- | -- | -- | --
[q32x48.bin](../../master/src/input_files/q32x48.bin) | 1536 | 816 | 0.53125 | 212243 | 40709 | 1536
[medTale.txt](../../master/src/input_files/medTale.txt)	| 45056 |	23912 |	0.530717 | 6846695 | 2174448 | 45056
[genomeVirus.txt](../../master/src/input_files/genomeVirus.txt) | 50008 | 12576 | 0.25148 | 896080 | 270402 | 50008
[bee.txt](../../master/src/input_files/bee.txt)	| 401112 | 231976 | 0.578332 | 14536900 | 2107232 | 401112
[mobydick.txt](../../master/src/input_files/mobydick.txt) | 9531704 | 5341208 | 0.560362 | 113272887 | 46806047 | 9531704

If we plot the compression time and decompression time relative to the input size, we would obtain the following graph:
![Huffman Compression Decompression Graph](/src/graphs/Huffman_Compress_Decompress.png)

[Graph Link](https://docs.google.com/spreadsheets/d/18CtPVsTnGmuOjtCjKfd8xp5nqEoQ8kqKwdQVQFFS7to/edit?usp=sharing "Graph Link")

We can see that both the compression and decompression time grows linearly when the input increases which is the expected result since the algorithm is effectively O(n) since the character set is not large.

Another interesting observation is that the algorithm achieved extraordinary performance when the character set is small ([genomeVirus.txt](../../master/src/input_files/genomeVirus.txt)) and the file is shrinked to almost 25% of its original size. 

Besides, for normal text files with English text, the compression ratio is normally around 50-60% since the English language contains quite a lot of redundancies.

##### So, what happens if we compress a file repeatedly? (is there an end to this?)

To prove that a universal algorithm **does not exist**, which means we will eventually reach a point where the file could no longer be compressed any further (compression ratio >= 1), I wrote the method repeatedCompression() in [Huffman.java](../../master/src/Huffman.java) to prove that my hypothesis is indeed correct.

The chosen file is bee.txt in this instance, but it can be done to any file.

The results are outputted to [repeated_compression.txt](../../master/src/compressed_files/repeated_compression.txt), and can be summarised as follows:
Count |	Original Bits	| Compressed Bits	| Compression Ratio
--|--|--|--
1	| 401112 | 231976 | 0.578332
2	| 231976 | 231832	| 0.999379
3	| 231832 | 234376 | 1.010973

With just 3 iterations, we are no longer able to shrink the file any further, as the compression ratio reaches 1. Also note that with each iteration the compression ratio increases very minimally (diminishing returns).

#### Comparison against RLE
I have written a modified version of RunLength.java which is named [ModifiedRLE.java](../../master/src/ModifiedRLE.java), to easily generate data for RLE (with identical format as the previous analysis) and can be found at [RLE.txt](../../master/src/compressed_files/RLE.txt). The RLE compressed files will have the file name (name_RLE_compressed.*) where * is the extension of the respective file and filename is the corresponding input file name.

##### RLE
File	| Input Size | Compressed Size | Compression Ratio | Compression Time |	Decompression Time | Decompressed Size
-- | -- | -- | -- | -- | -- | --
[q32x48.bin](../../master/src/input_files/q32x48.bin) | 1536 | 1144 | 0.744792 | 157464 | 213198 | 1536
[medTale.txt](../../master/src/input_files/medTale.txt) | 45056 | 182520 | 4.050959 | 5900426 | 5033866 | 45056
[genomeVirus.txt](../../master/src/input_files/genomeVirus.txt) | 50008 | 223632 | 4.471924 | 1785293 | 2347047 | 50008
[bee.txt](../../master/src/input_files/bee.txt)	| 401112 | 1615848 | 4.028421 | 7766411 | 11706178 | 401112
[mobydick.txt](../../master/src/input_files/mobydick.txt) | 9531704 | 38698936 | 4.060023 | 96692046 | 144633228 | 9531704

If we compare the RLE data to the Huffman data, we can see that Huffman has higher compression ratio overall. RLE only works on binary files (Bit Maps) is literally useless for ASCII text compression.

##### Why did the Huffman Algorithm perform better on [q32x48.bin](../../master/src/input_files/q32x48.bin) even though RLE is optimised for binary files?

The Huffman algorithm can work properly for any 8-bit value in each 8-bit character, and hence can be applied to **any byte stream**. In the case of the binary bit map file, Huffman compression essentially discovers a lot of 00000000 and 11111111 characters, so those runs are encoded with a smaller encoding. In the case of RLE, although it is optimised for long runs the count will still take up 8 bits which contributes to a lot of wasted space.