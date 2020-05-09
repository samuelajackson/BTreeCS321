# BTreeCS321
Final project for CS321 spring 2020

****************
* GeneBankBTree
* CS 321
* 05/08/2020
* Sam Jackson, Terran Dykes, Alex Guy
**************** 

OVERVIEW:

 Our BTree is an implementation of the BTree data structure using
 BTreeNodes. BTrees are constructed from strings of gene data
 and can be stored and searched through.


INCLUDED FILES:

 TreeObject.java - source file
 BTreeNode.java - source file
 BTree.java - source file
 GeneBankCreateBTree.java - source file
 GeneBankSearch - source file
 README - this file


BUILDING AND RUNNING:

 From the directory containing all source files, compile both 
 driver classes as well as their dependents using:
 $ javac *.java

 To create a new GeneBankBTree, run the command:
 $ java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence length>
   {<cache size>} {<debug level>}
 
 To search within that BTree, run the command:
 $ java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>]
   [<debug level>]

 Expect relevant output in the console; a dump file will be created if the optional
 debug value is set to 1. 

PROGRAM DESIGN:

 Our program has two driver classes; the first, GeneBankCreateBTree,
 reads the gene data from an appropriate given file and parses the
 information. It then stores each string of gene indicators as a long
 integer, encoding each letter as two bits of information for efficient
 storage. The long is then stored in a BTree (if any given string is
 already in the BTree, its frequency is incremented). Functionality of
 this class requires input from the user that decides the degree of the
 BTree as well as the length of the strings of data.
 
 The second driver class, GeneBankSearch, searches through an existing
 Btree for the frequencies of strings of a given length. 
 
 BTreeNode contains the bulk of BTree management functionality. It keeps
 track of the keys in each node as well as the connections to children nodes
 and its parent node. It also contains the logic for adding keys as well
 as splitting when necessary. In addition, BTreeNode contains the method
 for traversing the tree in an ordered manner, visiting each node and
 transferring its information to the dump file when debug mode is set to 1.
 
 Because so much functionality relating to the BTree is in the BTreeNode,
 the BTree class itself only contains a few methods. Among those is the
 search method that searches the BTree for a given long. Another, the 
 writeBinaryFile method, creates a binary file from the BTree. The TreeObject
 class simply manages information about the objects we are passing into
 the BTree, in our case longs. This class's toString method translates
 longs back into a string of gene indicators.
 

TESTING:

 We had initially planned on more rigorous testing, but due to current
 circumstance we were unable to do so. Thus, all testing was done
 by hand either locally or on the Onyx server. This was done by comparing
 actual output to expected results.
 
 This was made easier by the dump files produced by GeneBankCreateBTree.
 These files gave us specific data to be compared to a given sample. If
 given more time, this program would benefit from specific unit tests.


DISCUSSION:
 
 This project faced a lot of hurdles, not the least of which was the current
 pandemic. Because team members were not able to meet in person, all coordination
 had to occur via email. This lead to a slower pace, as many methods in the program
 rely on information in other classes that were being worked on by different team
 members.
 
 On top of that, two team members had to move during the project, which limited
 time spent on the project and further interfered with communication. Furthermore,
 although a cache was introduced to the project, we were unable to properly link
 it as we didn't fully understand how to implement it into both driver classes.
 
 Despite these difficulties, we worked as a team to the best of our abilities
 and coordinated to finish the project on time.

 