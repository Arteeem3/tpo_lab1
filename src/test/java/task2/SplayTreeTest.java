package task2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SplayTreeTest {

    private Integer[] treeToArray(Node root) {
        if (root == null) return new Integer[0];

        List<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr != null) {
                list.add(curr.key);
                queue.add(curr.left);
                queue.add(curr.right);
            } else {
                list.add(null);
            }
        }

        while (!list.isEmpty() && list.get(list.size() - 1) == null) {
            list.remove(list.size() - 1);
        }

        return list.toArray(new Integer[0]);
    }

    @Test
    void deleteNullRoot() {
        SplayTree tree = new SplayTree();
        Node root = null;
        root = tree.deleteKey(root, 2);

        Integer[] expected = {};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void sameValue() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 5);
        root = tree.insert(root, 5);

        Integer[] expected = {5};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void insertTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 5);
        root = tree.insert(root, 15);

        Integer[] expected = {15, 10, null, 5};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void rightRotate() {
        SplayTree tree = new SplayTree();
        Node root = new Node(10);
        root.right = new Node(15);
        root.right.left = new Node(12);

        root = tree.splay(root, 14);

        Integer[] expected = {12, 10, 15};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void leftRotate() {
        SplayTree tree = new SplayTree();
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.right = new Node(8);
        root.left.right.right = new Node(9);

        root = tree.splay(root, 7);

        Integer[] expected = {8, 5, 10, null, null, 9};
        assertArrayEquals(expected, treeToArray(root));
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

        Integer[] expected = {25, 20, 30, 10, null, null, 50};
        assertArrayEquals(expected, treeToArray(root));
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

        Integer[] expected = {50, 30, null, 25, null, 20, null, 10};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void findTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 5);
        root = tree.insert(root, 15);

        root = tree.search(root, 10);

        Integer[] expected = {10, 5, 15};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void deleteTest() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 5);
        root = tree.insert(root, 15);
        root = tree.deleteKey(root, 5);

        Integer[] expected = {10, null, 15};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void deleteTest2() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 5);
        root = tree.insert(root, 15);
        root = tree.deleteKey(root, 10);

        Integer[] expected = {5, null, 15};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void deleteTest3() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 50);
        root = tree.insert(root, 20);
        root = tree.insert(root, 10);
        root = tree.insert(root, 30);
        root = tree.insert(root, 25);

        root = tree.deleteKey(root, 26);

        Integer[] expected = {30, 25, 50, 20, null, null, null, 10};
        assertArrayEquals(expected, treeToArray(root));
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

        Integer[] expected = {};
        assertArrayEquals(expected, treeToArray(root));
    }

    @Test
    void insertTestWithLeftNode() {
        SplayTree tree = new SplayTree();
        Node root = null;

        root = tree.insert(root, 10);
        root = tree.insert(root, 9);
        root = tree.insert(root, 8);

        Integer[] expected = {8, null, 9, null, 10};
        assertArrayEquals(expected, treeToArray(root));
    }
}