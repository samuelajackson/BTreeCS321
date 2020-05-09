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
 this class requires input from the user

 ArraySet implements the SimpleSet interface, which defines methods for a
 basic set collection. ArraySet, as the name implies, manages set elements
 in an array. A set only contains one unique instance of an element, so
 ArraySet only adds an element if it is not already in the array. Removing
 an element will only succeed if the element is found. Attempts to remove
 an element not in the set results in an ElementNotFoundException being
 thrown.

 As the underlying array capacity may not be the same size as the number of
 elements in the set, the array is maintained with the first element at 
 index 0 and with no gaps between elements. The next available index is
 used to indicate the current size of the set and to recognize when the
 array is not large enough to hold a new element.

 When the array capacity needs to be increased, a new array is created, 
 twice the capacity of the old array, and all elements are copied into
 the new array. The reverse is not true, however. As elements are removed,
 smaller arrays do not replace larger arrays. If memory use ever becomes 
 an issue, it may be worth modifying ArraySet to shrink when there is 
 excessive unused capacity.

 SetTester confirms correct operation of all SimpleSet methods for change
 scenarios involving sets from zero to three elements after add and
 remove changes. It is configured to use the ArraySet implementation of
 SimpleSet.
 

TESTING:

 SetTester was the primary mechanism for testing ArraySet. SetTester was
 written before ArraySet, so test-driven development helped ensure that
 all ArraySet functionality was being tested from the start.

 Scenarios being tested by SetTester include:
   a new empty set
   adding the first element to an empty set
   removing the element from a one element set
   adding a duplicate element to a set with one element
   adding a second element to a set with one element
   removing each of the two elements in a two element set
   adding a third element to a two element set
   removing each of the three elements from a three element set
 
 Additional scenarios would be beneficial, such as adding duplicates to
 sets with multiple elements, but time did not permit more extensive
 testing.

 Not all tests are currently passing, but work is still underway to fix
 remaining bugs.


DISCUSSION:
 
 Test-driven development was a new idea, for me, and I was a little
 skeptical that it would make a difference, but it did help me catch a
 couple of bugs early on having to do with my rear index and with
 expanding the array capacity that I would have missed. Since ArraySet
 still isn't bug free, I'm glad I don't have those problems in there at
 the same time!

 Working with shifting elements is also a challenge. I think I have the
 idea down, but implementing it can still be difficult. I'm starting to
 see why everyone suggests drawing pictures of the processes before
 coding, to make sure the order of statements actually makes sense.

 I haven't made extensive use of the Eclipse debugger, yet, but I think I 
 need to start using it to figure out my remaining bugs. Reading the code
 over and over isn't helping, anymore, and I don't want to fill my code
 with print statements I'll just have to rip out, again, later.

 