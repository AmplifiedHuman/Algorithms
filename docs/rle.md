### Run Length Encoding
#### Source Code
 - [RLE.java](../../master/src/RLE.java)

In RLE, the characters in the input string must first be looped over. An int counter is used to count the number of times the same character has been shown in a row. When a different character occurs, the counter value and the previous character is outputted. We continue till the end of the list. A small optimisation is added by not outputting the 1 counts.

#### Runtime
Attribute | Performance
--|--
Worst Case | O(n)
Best Case | O(n)
Space Complexity | O(1)

#### Performance

Name | Type | Input Size | Compressed Size | Compression Ratio
--|--|--|--|--
[4runs.bin](../../master/src/input_files/4runs.bin) | Binary | 40	| 32 | 0.8
[abra.txt](../../master/src/input_files/abra.txt) | Text |	104 |	456 | 4.38
[mobydick.txt](../../master/src/input_files/mobydick.txt) | Text	| 9531704	| 38698936 | 4.06
[q32x48.bin](../../master/src/input_files/q32x48.bin) | Bitmap | 1536 | 1144 | 0.74
[q64x96.bin](../../master/src/input_files/q64x96.bin) | Bitmap	| 6144 | 2296 | 0.37

It is apparent that Run Length Encoding performs poorly on ASCII text as the resulting file ended up being larger than the original file by nearly 4 times (Compression Ratio > 1). 

In the provided implementation of RunLength.java, 8 bits are used to store the counts. It alternates between 1 and 0 for each entry and the count is fixed to 8 bits so it pads smaller counts with 0 values. So, for ASCII text, as there are not a lot of runs of 0 and 1, we ended up wasting a lot of bits on short runs.

Hence Run Length Encoding is widely used for bitmaps because this input data is more likely to have long runs of repeated data (i.e. pixels).