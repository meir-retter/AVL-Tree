This program implements an AVL tree. You can either use your own file and enter its name as a command line argument, or
it will default to treefile.txt. If you do use your own, make sure it is in the same folder as src (not as a child of
src). The program then interprets the first line after int/String as nodes to be inserted into an AVL tree, and does so.
It then prints out the tree. The line after that is interpreted as nodes to be deleted from the tree. It does so, and
re-prints the tree with these nodes missing. All of this is done while maintaining the AVL-tree property, which is that
the absolute difference in heights between any node's right and left children must be at most 1.

With ints, an example input file might look like this:

int
2 6 8 4 3 5 1
3 6 2 8 4 1 5

With String, it might look like this:

String
"BonJour" "Hello" "Privet" "Shalom" "G'day" "Hi" "Hola" "Ni Hao"
"Hi" "Hello" "BonJour" "Shalom" "Privet" "G'day" "Hola" "Ni Hao"

Notice the quotations. Make sure to have exactly one space between each word, as shown.

4 test files are provided:

AVLtestfile1.txt
AVLtestfile2.txt
AVLtestfile3.txt, and
AVLtestfile4.txt.

The first is of the int variety, while the other three contain Strings. I tried to give a variety of tests, including
deletion of the root, insertion in ascending order, and insertion in random order. One file even contains words
randomly mashed out from my keyboard.

To run the program, run the class AVLTree, which contains the main() method. To adjust the name of the input file to be
read, either enter it as a command line argument as mentioned previously or simply change it (seventh line of the main()
method).