package task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SplayTreeTest {

    @Test
    void insertTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 5);
        root = tree.insert(root, 15);

        assertEquals(15, root.key);
        assertEquals(10, root.left.key);
        assertEquals(5, root.left.left.key);
    }

    @Test
    void deleteTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 5);
        root = tree.insert(root, 15);
        root = tree.deleteKey(root, 5);

        assertEquals(10, root.key);
        assertEquals(15, root.right.key);
    }

    @Test
    void findTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 5);
        root = tree.insert(root, 15);

        root = tree.search(root, 10);

        assertEquals(10, root.key);
        assertEquals(15, root.right.key);
        assertEquals(5, root.left.key);
    }

    @Test
    void sameValue() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 5);
        root = tree.insert(root, 5);

        assertEquals(5, root.key);
        assertNull(root.left);
    }

    @Test
    void insertTestWithLeftNode() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 9);
        root = tree.insert(root, 8);

        assertEquals(8, root.key);
        assertEquals(9, root.right.key);
        assertEquals(10, root.right.right.key);
    }

    @Test
    void deleteNullRoot() {
        SplayTree tree = new SplayTree();
        Node root = null;
        tree.deleteKey(root, 2);

        assertNull(root);
    }

    @Test
    void deleteRoot() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 3);
        root = tree.insert(root, 8);
        root = tree.insert(root, 4);

        root = tree.deleteKey(root, 3);
        root = tree.deleteKey(root, 8);
        root = tree.deleteKey(root, 4);

        assertNull(root);
    }

    @Test
    void insertingTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 50);
        root = tree.insert(root, 20);
        root = tree.insert(root, 10);
        root = tree.insert(root, 30);
        root = tree.insert(root, 25);

        assertEquals(25, root.key);
        assertEquals(20, root.left.key);
        assertEquals(30, root.right.key);
        assertEquals(10, root.left.left.key);
        assertEquals(50, root.right.right.key);
    }

    @Test
    void deletingTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 50);
        root = tree.insert(root, 20);
        root = tree.insert(root, 10);
        root = tree.insert(root, 30);
        root = tree.insert(root, 25);

        root = tree.deleteKey(root, 26);

        assertEquals(30, root.key);
        assertEquals(25, root.left.key);
        assertEquals(50, root.right.key);
        assertEquals(20, root.left.left.key);
        assertEquals(10, root.left.left.left.key);
    }

    @Test
    void searchNotExistingNode() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 50);
        root = tree.insert(root, 20);
        root = tree.insert(root, 10);
        root = tree.insert(root, 30);
        root = tree.insert(root, 25);

        root = tree.search(root, 100);

        assertEquals(50, root.key);
        assertEquals(30, root.left.key);
        assertEquals(25, root.left.left.key);
        assertEquals(20, root.left.left.left.key);
        assertEquals(10, root.left.left.left.left.key);
    }

    @Test
    void leftRotate() {
        SplayTree tree = new SplayTree();
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.right = new Node(8);
        root.left.right.right = new Node(9);

        root = tree.splay(root, 7);

        assertEquals(8, root.key);
        assertEquals(5, root.left.key);
        assertEquals(10, root.right.key);
        assertEquals(9, root.right.left.key);
    }

    @Test
    void rightRotate() {
        SplayTree tree = new SplayTree();
        Node root = new Node(10);
        root.right = new Node(15);
        root.right.left = new Node(12);

        root = tree.splay(root, 14);

        assertEquals(12, root.key);
        assertEquals(10, root.left.key);
        assertEquals(15, root.right.key);
    }


}