package org.masteryourself.tutorial.algorithm.tree.bst;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>description : BSTTreeTest
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/27 15:12
 */
class BSTTreeTest {

    public BSTTree createTree() {
        /*
                     4
                   /   \
                  2     6
                 / \   / \
                1   3 5   7
         */
        BSTNode n1 = new BSTNode(1, "张无忌");
        BSTNode n3 = new BSTNode(3, "宋青书");
        BSTNode n2 = new BSTNode(2, "周芷若", n1, n3);
        BSTNode n5 = new BSTNode(5, "说不得");
        BSTNode n7 = new BSTNode(7, "殷离");
        BSTNode n6 = new BSTNode(6, "赵敏", n5, n7);
        BSTNode root = new BSTNode(4, "小昭", n2, n6);
        BSTTree tree = new BSTTree();
        tree.root = root;
        return tree;
    }

    @Test
    void get() {
        BSTTree tree = createTree();
        assertEquals("张无忌", tree.get(1));
        assertEquals("周芷若", tree.get(2));
        assertEquals("宋青书", tree.get(3));
        assertEquals("小昭", tree.get(4));
        assertEquals("说不得", tree.get(5));
        assertEquals("赵敏", tree.get(6));
        assertEquals("殷离", tree.get(7));
        assertNull(tree.get(8));
    }

    @Test
    public void minMax() {
        BSTTree tree = createTree();
        assertEquals("张无忌", tree.min());
        assertEquals("殷离", tree.max());
    }

    @Test
    public void put() {
        BSTTree tree = new BSTTree();
        tree.put(4, new Object());
        tree.put(2, new Object());
        tree.put(6, new Object());
        tree.put(1, new Object());
        tree.put(3, new Object());
        tree.put(7, new Object());
        tree.put(5, new Object());
        assertTrue(isSameTree(createTree().root, tree.root));
        tree.put(1, "教主张无忌");
        assertEquals("教主张无忌", tree.get(1));
    }

    @Test
    public void predecessor() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n3 = new BSTNode(3, 3);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);

        BSTNode n5 = new BSTNode(5, 5);
        BSTNode n6 = new BSTNode(6, 6, n5, null);
        BSTNode n8 = new BSTNode(8, 8);
        BSTNode n7 = new BSTNode(7, 7, n6, n8);
        BSTNode root = new BSTNode(4, 4, n2, n7);

        BSTTree tree = new BSTTree();
        tree.root = root;

        assertNull(tree.predecessor(1));
        assertEquals(1, tree.predecessor(2));
        assertEquals(2, tree.predecessor(3));
        assertEquals(3, tree.predecessor(4));
        assertEquals(4, tree.predecessor(5));
        assertEquals(5, tree.predecessor(6));
        assertEquals(6, tree.predecessor(7));
        assertEquals(7, tree.predecessor(8));
    }

    @Test
    public void successor() {
        /*
                     5
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     \
                      4
         */
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n4 = new BSTNode(4, 4);
        BSTNode n3 = new BSTNode(3, 3, null, n4);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);

        BSTNode n6 = new BSTNode(6, 6);
        BSTNode n8 = new BSTNode(8, 8);
        BSTNode n7 = new BSTNode(7, 7, n6, n8);
        BSTNode root = new BSTNode(5, 5, n2, n7);

        BSTTree tree = new BSTTree();
        tree.root = root;

        assertEquals(2, tree.successor(1));
        assertEquals(3, tree.successor(2));
        assertEquals(4, tree.successor(3));
        assertEquals(5, tree.successor(4));
        assertEquals(6, tree.successor(5));
        assertEquals(7, tree.successor(6));
        assertEquals(8, tree.successor(7));
        assertNull(tree.successor(8));
    }

    @Test
    @DisplayName("删除叶子节点")
    public void delete1() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n3 = new BSTNode(3, 3);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);

        BSTNode n5 = new BSTNode(5, 5);
        BSTNode n6 = new BSTNode(6, 6, n5, null);
        BSTNode n8 = new BSTNode(8, 8);
        BSTNode n7 = new BSTNode(7, 7, n6, n8);
        BSTNode root1 = new BSTNode(4, 4, n2, n7);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(1, tree1.delete(1));
        assertEquals(3, tree1.delete(3));
        assertEquals(5, tree1.delete(5));
        assertEquals(8, tree1.delete(8));


        /*
                     4
                   /   \
                  2     7
                       /
                      6
         */
        BSTNode x2 = new BSTNode(2, 2);
        BSTNode x6 = new BSTNode(6, 6);
        BSTNode x7 = new BSTNode(7, 7, x6, null);
        BSTNode root2 = new BSTNode(4, 4, x2, x7);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除只有一个孩子节点")
    public void delete2() {
        /*
                     4
                   /   \
                  2     7
                 / \   /
                1   3 6
                     /
                    5
         */
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n3 = new BSTNode(3, 3);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);

        BSTNode n5 = new BSTNode(5, 5);
        BSTNode n6 = new BSTNode(6, 6, n5, null);
        BSTNode n7 = new BSTNode(7, 7, n6, null);
        BSTNode root1 = new BSTNode(4, 4, n2, n7);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(7, tree1.delete(7));


        /*
                     4
                   /   \
                  2     6
                 / \   /
                1   3 5
         */
        BSTNode x1 = new BSTNode(1, 1);
        BSTNode x3 = new BSTNode(3, 3);
        BSTNode x2 = new BSTNode(2, 2, x1, x3);
        BSTNode x5 = new BSTNode(5, 5);
        BSTNode x6 = new BSTNode(6, 6, x5, null);
        BSTNode root2 = new BSTNode(4, 4, x2, x6);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继不邻")
    public void delete3() {
        /*
                      4
                   /     \
                  2      7
                 / \   /   \
                1   3 5     8
                       \
                        6
         */
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n3 = new BSTNode(3, 3);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);

        BSTNode n6 = new BSTNode(6, 6);
        BSTNode n5 = new BSTNode(5, 5, null, n6);
        BSTNode n8 = new BSTNode(8, 8);
        BSTNode n7 = new BSTNode(7, 7, n5, n8);
        BSTNode root1 = new BSTNode(4, 4, n2, n7);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(4, tree1.delete(4));


        /*
                      5
                   /     \
                  2      7
                 / \   /   \
                1   3 6     8

         */
        BSTNode x1 = new BSTNode(1, 1);
        BSTNode x3 = new BSTNode(3, 3);
        BSTNode x2 = new BSTNode(2, 2, x1, x3);

        BSTNode x6 = new BSTNode(6, 6);
        BSTNode x8 = new BSTNode(8, 8);
        BSTNode x7 = new BSTNode(7, 7, x6, x8);
        BSTNode root2 = new BSTNode(5, 5, x2, x7);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继相邻")
    public void delete4() {
        /*
                     4
                   /   \
                  2     5
                 / \     \
                1   3     6

         */
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n3 = new BSTNode(3, 3);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);

        BSTNode n6 = new BSTNode(6, 6);
        BSTNode n5 = new BSTNode(5, 5, null, n6);
        BSTNode root1 = new BSTNode(4, 4, n2, n5);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(4, tree1.delete(4));


        /*
                     5
                   /  \
                  2    6
                 / \
                1   3

         */
        BSTNode x1 = new BSTNode(1, 1);
        BSTNode x3 = new BSTNode(3, 3);
        BSTNode x2 = new BSTNode(2, 2, x1, x3);

        BSTNode x6 = new BSTNode(6, 6);
        BSTNode root2 = new BSTNode(5, 5, x2, x6);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    public void compare() {
        /*
                 4
               /   \
              2     6
             / \   / \
            1   3 5   7
         */
        BSTNode n1 = new BSTNode(1, 1);
        BSTNode n3 = new BSTNode(3, 3);
        BSTNode n2 = new BSTNode(2, 2, n1, n3);

        BSTNode n5 = new BSTNode(5, 5);
        BSTNode n7 = new BSTNode(7, 7);
        BSTNode n6 = new BSTNode(6, 6, n5, n7);
        BSTNode root = new BSTNode(4, 4, n2, n6);

        BSTTree tree = new BSTTree();
        tree.root = root;

        assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), tree.less(6));
        assertIterableEquals(Arrays.asList(7), tree.greater(6));
        assertIterableEquals(Arrays.asList(3, 4, 5), tree.between(3, 5));
    }

    static boolean isSameTree(BSTNode tree1, BSTNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.key != tree2.key) {
            return false;
        }
        return isSameTree(tree1.left, tree2.left) && isSameTree(tree1.right, tree2.right);
    }

}