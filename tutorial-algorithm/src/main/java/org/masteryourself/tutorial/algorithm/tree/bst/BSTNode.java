package org.masteryourself.tutorial.algorithm.tree.bst;

import java.util.ArrayList;

/**
 * <p>description : BSTNode
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/27 15:04
 */
public class BSTNode {

    // 若希望任意类型作为 key, 则后续可以将其设计为 Comparable 接口
    int key;
    Object value;
    BSTNode left;
    BSTNode right;

    public BSTNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

}
