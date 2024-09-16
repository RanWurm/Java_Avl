# AVL Tree Implementation in Java

## Overview
This Java class implements an AVL tree, a self-balancing binary search tree. Each node maintains a balance factor to ensure that the tree remains approximately balanced, resulting in operations such as insertions, deletions, and lookups to be efficient.

## Features
- **Dynamic Balancing:** Automatically maintains balancing during insertions and deletions.
- **Traversal:** In-order traversal to display elements in a sorted manner.
- **Insertion:** Insert elements while maintaining the tree's balance.
- **Utility Functions:** Methods to check tree balance, set and get various properties of the tree nodes.

## Class Details

### Properties
- `rootVal`: Value of the node.
- `height`: Height of the node.
- `balanceCoEfficient`: Balance factor of the node, calculated as the height difference between the left and right subtrees.
- `leftAvlSubTree`: Reference to the left child.
- `rightAvlSubTree`: Reference to the right child.
- `parent`: Reference to the parent node.

### Constructor
```java
public Avl(int val) {
    this.rootVal = val;
}
```

### Methods
- **Setter and Getter Methods:** For setting and getting node properties.
- **`insert(int val)`:** Inserts a new value into the tree and rebalances it if necessary.
- **`inOrder()`:** Performs an in-order traversal of the tree and prints node values.
- **`isInTree(int val)`:** Checks if a value is present in the tree.
- **Balancing Methods:** `LL()`, `RR()`, `LR()`, `RL()` for performing rotations to maintain tree balance.
- **`setBalanceCoEfficient()`:** Updates the balance coefficient based on the heights of the left and right subtrees.
- **`cloneTree(Avl root)`:** Creates a deep copy of the subtree rooted at `root`.

## Usage
To utilize this AVL tree implementation, instantiate the `Avl` class and use the `insert` method to add elements. You can check the tree structure by performing an in-order traversal.

```java
public static void main(String[] args) {
    Avl tree = new Avl(10);
    tree.insert(5);
    tree.insert(15);
    tree.insert(3);
    tree.insert(7);
    tree.inOrder();
}
```

## Testing
- **Rotation Tests:** Implementations for right-right (RR), left-left (LL), left-right (LR), and right-left (RL) rotations are provided and can be tested to ensure the tree maintains balance as expected.

## Contributions
Contributions are welcome. Please fork the repository and submit a pull request with your suggested changes.

## License
Specify your license or let others know that the code is available under an open license.
