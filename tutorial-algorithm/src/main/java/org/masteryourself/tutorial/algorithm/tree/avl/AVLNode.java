package org.masteryourself.tutorial.algorithm.tree.avl;

/**
 * <p>description : AVLNode
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/29 16:05
 */
public class AVLNode {

    int key;
    Object value;
    AVLNode left;
    AVLNode right;
    int height = 1;

    public AVLNode(int key) {
        this.key = key;
    }

    public AVLNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

}
