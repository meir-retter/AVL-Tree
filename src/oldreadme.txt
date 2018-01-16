This program implements binary trees and binary search trees, and uses binary search trees to implement sets. The first
main class is BinaryTree, which builds a binary tree from an inorder list and a preorder list.  To use it, either use
your own file and enter its name as a command line argument, or it will default to treefile.txt. If you do use your own,
make sure it is in the same folder as src (not as a child of src). The first line of the file must be the single word
int or String. The next line must be an inorder list of a tree, and the line after that must be a preorder list of a
tree with the same nodes. With ints, it might look like this:

int
2 6 8 4 3 5 1
3 6 2 8 4 1 5

With String, it might look like this:

String
"BonJour" "Hello" "Privet" "Shalom" "G'day" "Hi" "Hola" "Ni Hao"
"Hi" "Hello" "BonJour" "Shalom" "Privet" "G'day" "Hola" "Ni Hao"

Notice the quotations. Make sure to have exactly one space between each word, as shown.

It then constructs the unique binary tree that these lists represent and prints it out.

The next main class is BinarySearchTree. It takes in a file of the exact same format (which san be similarly given by
the user with a command line argument or defaulted to searchtreefile.txt) but interprets the first line after int/String
as nodes to be inserted into a binary search tree, and does so. It then prints out the tree. The line after that is
interpreted as nodes to be deleted from the tree. It does so, and re-prints the tree with these nodes missing.

The last main class implements a set using a binary search tree. It contains the usual set operations union,
intersection and difference.

The program runs, but there might be some compiler complaints. I tried to fix as many as I could, but was having some
trouble with the generics. All in all it's a fantastic program, truly a piece of art.

See ya Lab Instructor Snow,
Meir
