package org.masteryourself.tutorial.algorithm.tree.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>description : BSTTree
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2023/5/27 15:05
 */
public class BSTTree {

    BSTNode root;

    /**
     * 查找 key 对应的值
     */
    public Object get(int key) {
        return doGet(root, key);
    }

    /**
     * 求最小值：一直向左查找
     */
    public Object min() {
        return min(root);
    }

    /**
     * 求最大值：一直向右查找
     */
    public Object max() {
        return max(root);
    }

    /**
     * 存在就更新
     * 不存在就新增(只会往叶子节点插入)
     */
    public void put(int key, Object value) {
        doPut(root, key, value);
    }

    /**
     * 查找前驱节点
     */
    public Object predecessor(int key) {
        BSTNode point = root;
        BSTNode ancestorFromLeft = null;
        // 1. 先查找到当前节点
        while (point != null) {
            if (key < point.key) {
                point = point.left;
            } else if (key > point.key) {
                ancestorFromLeft = point;
                point = point.right;
            } else {
                break;
            }
        }
        if (point == null) {
            return null;
        }
        // 2. 如果有左子节点, 则查找左子节点的最大值
        if (point.left != null) {
            return max(point.left);
        }
        // 没有左子节点, 返回从右而来的祖先节点
        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    /**
     * 查找后继节点
     */
    public Object successor(int key) {
        BSTNode point = root;
        BSTNode ancestorFromRight = null;
        // 1. 先查找到当前节点
        while (point != null) {
            if (key < point.key) {
                ancestorFromRight = point;
                point = point.left;
            } else if (key > point.key) {
                point = point.right;
            } else {
                break;
            }
        }
        if (point == null) {
            return null;
        }
        // 2. 如果有右子节点, 则查找右子节点的最小值
        if (point.right != null) {
            return min(point.right);
        }
        // 没有右子节点, 返回从右而来的祖先节点
        return ancestorFromRight != null ? ancestorFromRight.value : null;
    }

    /**
     * 要删除某节点（称为 D），必须先找到被删除节点的父节点，这里称为 P
     * 情况一：删除节点只有右孩子，将右孩子托孤给 P
     * 情况二：删除节点只有左孩子，将左孩子托孤给 P
     * 情况三：删除节点没有孩子，将 null 托孤给 P
     * 情况四：删除节点有左右孩子，将 D 的后继节点 C 托孤给 P
     * - 如果 D C 相邻, 只需要将 C 托孤给 P
     * - 如果 D C 不相邻, 先处理 C 的孩子节点给 C 的父节点，然后将 C 托孤给 P
     */
    public Object delete(int key) {
        BSTNode parent = null;
        BSTNode deleted = root;
        // 1. 先查找到当前节点
        while (deleted != null) {
            if (key < deleted.key) {
                parent = deleted;
                deleted = deleted.left;
            } else if (key > deleted.key) {
                parent = deleted;
                deleted = deleted.right;
            } else {
                break;
            }
        }
        if (deleted == null) {
            return null;
        }
        // 2. 删除节点
        // 2.1 删除节点只有右孩子
        if (deleted.left == null) {
            shift(parent, deleted, deleted.right);
        }
        // 2.2 删除节点只有左孩子
        else if (deleted.right == null) {
            shift(parent, deleted, deleted.left);
        }
        // 2.3 删除节点有左右孩子
        else {
            // 1. 查找后继节点（这里表明肯定有左右孩子节点，所以直接查找左孩子节点最小值即可）
            BSTNode cP = deleted;
            BSTNode c = deleted.right;
            while (c.left != null) {
                cP = c;
                c = c.left;
            }
            // 2. 如果 D C 不相邻, 先处理 C 的孩子节点给 C 的父节点
            if (deleted != cP) {
                shift(cP, c, c.right);
                c.right = cP.right;
            }
            // 3. 最后处理父节点
            shift(parent, deleted, c);
            c.left = deleted.left;
            c.right = deleted.right;
        }
        return deleted.value;
    }

    /**
     * 查找大于某个 key 的所有值
     */
    public List<Object> less(int key) {
        List<Object> result = new ArrayList<>();
        // 使用中序遍历：它是按照从小到大排序
        Stack<BSTNode> stack = new Stack<>();
        BSTNode point = root;
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                stack.push(point);
                point = point.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                point = pop.right;
            }
        }
        return result;
    }

    /**
     * 查找小于某个 key 的所有值
     */
    public List<Object> greater(int key) {
        List<Object> result = new ArrayList<>();
        // 使用反向中序遍历：它是按照从大到小排序
        Stack<BSTNode> stack = new Stack<>();
        BSTNode point = root;
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                stack.push(point);
                point = point.right;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                point = pop.left;
            }
        }
        return result;
    }

    /**
     * 查找两者 key 之间的所有值
     */
    public List<Object> between(int key1, int key2) {
        List<Object> result = new ArrayList<>();
        // 使用中序遍历：它是按照从小到大排序
        Stack<BSTNode> stack = new Stack<>();
        BSTNode point = root;
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                stack.push(point);
                point = point.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                point = pop.right;
            }
        }
        return result;
    }

    /**
     * 托孤方法: 这个只会更改 parent 节点引用, 不会更改 child 节点引用
     *
     * @param parent  被删除节点的父亲
     * @param deleted 被删除节点
     * @param child   被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        }
        // 删除的是左节点
        else if (deleted == parent.left) {
            parent.left = child;
        }
        // 删除的是右节点
        else {
            parent.right = child;
        }
    }

    private void doPut(BSTNode node, int key, Object value) {
        BSTNode parent = null;
        BSTNode point = node;
        while (point != null) {
            parent = point;
            if (key < point.key) {
                point = point.left;
            } else if (key > point.key) {
                point = point.right;
            } else {
                // 找到了更新, 注意这里是 return
                point.value = value;
                return;
            }
        }
        // parent 为 null, 说明是空节点
        if (parent == null) {
            root = new BSTNode(key, value);
        }
        // parent 不为 null, 说明没有找到, 此时需要判断是插入左侧还是右侧
        else if (parent.key > key) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }
    }

    public Object min(BSTNode node) {
        BSTNode point = node;
        while (point.left != null) {
            point = point.left;
        }
        return point.value;
    }

    public Object max(BSTNode node) {
        BSTNode point = node;
        while (point.right != null) {
            point = point.right;
        }
        return point.value;
    }

    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            // 向左查找
            return doGet(node.left, key);
        } else if (key > node.key) {
            // 向右查找
            return doGet(node.right, key);
        } else {
            return node.value;
        }
    }

}
