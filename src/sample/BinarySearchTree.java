package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree object.
 */
public class BinarySearchTree
{

    /**
     * List of staff
     */
    private ArrayList<String> staff;
    /**
     * Tree Node generic object called Root
     */
    private TreeNode root;

    /**
     * Binary Search Tree Constructor
     */
    public BinarySearchTree()
    {
        root = null;
        staff = new ArrayList<String>();
    }

    /**
     * Returns the current tree root.
     * @return current Root.
     */
    public TreeNode getRoot()
    {
        return root;
    }

    /**
     * Sets the current tree root.
     * @param _root current.
     */
    public void setRoot(TreeNode _root)
    {
        root = _root;
    }

    /**
     * Inserts the data passed to this function into the tree
     * @param data String element.
     */
    public void insert(String data)
    {
        root = insert(data, root);
    }

    /**
     * Deletes the TreeNode containing data passed to this function.
     * @param data String element.
     */
    public void delete(String data)
    {
        root = delete(root, data);
    }

    /**
     * Returns the element found at bottom left of the tree.
     * @return Lowest string element.
     */
    public String findMin()
    {
        return elementAt(findMin(root));
    }

    /**
     * Searches the tree for a branch containing passed data.
     * @param data String element.
     * @return String element.
     */
    public String find(String data)
    {
        return elementAt(find(data, root));
    }

    /**
     * Returns the data element of passed TreeNode object.
     * @param branch TreeNode.
     * @return String element.
     */
    private String elementAt(TreeNode branch)
    {
        if (branch == null)
        {
            return null;
        }
        else
        {
            /*System.out.println(branch.getElement() + " found.");*/
            return branch.getElement();
        }

    }

    /**
     * Finds the tree node containing the data object passed, starting from the TreeNode passed to this function
     * and returns the TreeNode containing said data.
     * @param data String element.
     * @param branch TreeNode.
     * @return TreeNode.
     */
    private TreeNode find(String data, TreeNode branch)
    {
        while (branch != null)
        {
            if (data.compareTo(branch.getElement()) < 0)
            {
                branch = branch.getLeft();
            }
            else if (data.compareTo(branch.getElement()) > 0)
            {
                branch = branch.getRight();
            }
            else
            {
                return branch;
            }
        }
        /*System.out.println(data + " not found.");*/
        return null;
    }


    /**
     * Finds bottom left node starting from the TreeNode provided
     * @param branch TreeNode.
     * @return TreeNode.
     */
    private TreeNode findMin(TreeNode branch)
    {
        if (branch != null)
        {
            while (branch.getLeft() != null)
            {
                branch = branch.getLeft();
            }
        }
        return branch;
    }

    /**
     * Adds each node from the tree to the staff list recursively, alphabetically arranged
     * @param node TreeNode.
     */
    public void inOrder(TreeNode node)
    {
        if (node == null)
        {
            return;
        }
        inOrder(node.getLeft());
        staff.add(node.getElement().toString());
        inOrder(node.getRight());
    }

    /**
     * Inserts the data into the tree, alphabetically arranged and balanced.
     * @param data String element.
     * @param branch TreeNode.
     * @return TreeNode.
     */
    public TreeNode insert(String data, TreeNode branch)
    {
        if (branch == null)
        {
            branch = new TreeNode(data);
            staff.add(branch.getElement().toString());
        }
        else if (data.compareTo(branch.getElement()) < 0)
        {
            branch.setLeft(insert(data, branch.getLeft()));
            branch.setHeight(updateHeight(branch));
            branch = balanceNode(branch, data);
        }
        else if (data.compareTo(branch.getElement()) > 0)
        {
            branch.setRight(insert(data, branch.getRight()));
            branch.setHeight(updateHeight(branch));
            branch = balanceNode(branch, data);
        }
/*        else
        {
            System.out.println(data + " already exists.");
        }*/

        return branch;
    }

    /**
     * Updates the height of the com.company.TreeNode
     * @param node TreeNode.
     * @return height int.
     */
    public int updateHeight(TreeNode node)
    {
        if (node.getLeft() != null && node.getRight() != null)
        {
            return 1 + Math.max(node.getLeft().getHeight(), node.getRight().getHeight());
        }
        else if (node.getLeft() != null && node.getRight() == null)
        {
            return node.getLeft().getHeight() + 1;
        }
        else if (node.getLeft() == null && node.getRight() != null)
        {
            return node.getRight().getHeight() + 1;
        }
        else
        {
            return 1;
        }

    }

    /**
     * Balances the TreeNode if the tree height on the left of the tree is higher than the right and vice-versa, by rotating its nodes.
     * @param node TreeNode.
     * @param data String element.
     * @return TreeNode.
     */
    public TreeNode balanceNode(TreeNode node, String data)
    {
        int balance = getBalance(node);

        if (balance > 1)
        {
            if (getBalance(node.getLeft()) >= 0)
            {
                node = rightRotate(node);
            }
            else
            {
                node.setLeft(leftRotate(node.getLeft()));
                node = rightRotate(node);
            }
        }
        else if (balance < -1)
        {
            if (getBalance(node.getRight()) <= 0)
            {
                node = leftRotate(node);
            }
            else
            {
                node.setRight(rightRotate(node.getRight()));
                node = leftRotate(node);
            }
        }
        return node;
    }

    /**
     * Checks whether the tree is balanced.
     * @param branch TreeNode.
     * @return height int.
     */
    private int getBalance(TreeNode branch)
    {
        int height = 0;
        int left = 0;
        int right = 0;
        if (branch != null)
        {
            if (branch.getLeft() != null)
                left = branch.getLeft().getHeight();
            if (branch.getRight() != null)
                right = branch.getRight().getHeight();
            height = left - right;
        }
        return height;
    }

    /**
     * Rotates TreeNode passed down(height) and to the left of finalNode.
     * @param currentNode TreeNode.
     * @return TreeNode.
     */
    public TreeNode leftRotate(TreeNode currentNode)
    {
        TreeNode finalNode = currentNode.getRight();
        TreeNode subTree = finalNode.getLeft();

        finalNode.setLeft(currentNode);
        currentNode.setRight(subTree);

        currentNode.setHeight(updateHeight(currentNode));
        finalNode.setHeight(updateHeight(finalNode));

        return finalNode;
    }

    /**
     * Rotates TreeNode passed down(height) and to the right of finalNode.
     * @param currentNode TreeNode.
     * @return TreeNode.
     */
    public TreeNode rightRotate(TreeNode currentNode)
    {
        TreeNode finalNode = currentNode.getLeft();
        TreeNode subTree = finalNode.getRight();

        finalNode.setRight(currentNode);
        currentNode.setLeft(subTree);

        currentNode.setHeight(updateHeight(currentNode));
        finalNode.setHeight(updateHeight(finalNode));

        return finalNode;
    }

    /**
     * Recursively moves through the tree to find the node to be deleted.
     * When it finds the node it tests to see if the node has zero, one or two children.
     * If the node has one or zero children it deletes the node.
     * If the node has two children it finds the minimum node of the right sub-tree which it sets as a temp node, then
     * sets the current node's data equal to the temp node's.
     * Recursively moves through the current node's sub-tree and deletes the minimum node.
     * @param branch TreeNode.
     * @param data String element.
     * @return TreeNode.
     */
    public TreeNode delete(TreeNode branch, String data)
    {
        //If Current Node is null.
        if (branch == null)
        {
            /*System.out.println("Can't delete " + data + " as it has not been found.");*/
            return branch;
        }

        //Else iterate down (left or right).
        else if (data.compareTo(branch.getElement()) < 0)
        {
            branch.setLeft(delete(branch.getLeft(), data));
        }
        else if (data.compareTo(branch.getElement()) > 0)
        {
            branch.setRight(delete(branch.getRight(), data));
        }
        //Else Node has been found.
        else
        {
            //Case 1: No Children.
            if (branch.getLeft() == null && branch.getRight() == null)
                branch = null;

                //Case 2: One Child.
            else if (branch.getLeft() == null)
                branch = branch.getRight();
            else if (branch.getRight() == null)
                branch = branch.getLeft();
                //Case 3: Two Children.
            else
            {
                TreeNode temp = findMin(branch.getRight());
                branch.setElement(temp.getElement());
                branch.setRight(delete(branch.getRight(), temp.getElement()));
            }
        }
        //Set the new height & balance.
        if (branch != null)
        {
            branch.setHeight(updateHeight(branch));
            branch = balanceNode(branch, data);
        }
        return branch;
    }

    /**
     * Returns the amount of staff in the staff list
     * @return amount of staff.
     */
    public int countStaff()
    {
        return staff.size();
    }

    /**
     * Returns the staff name at passed staff list index.
     * @param i index.
     * @return staff name at i index.
     */
    public String elementStaff(int i)
    {
        return staff.get(i);
    }

    /**
     * Adds to the staff list.
     * @param _name staff name.
     */
    public void addStaff(String _name)
    {
        staff.add(_name);
    }

    /**
     * Clears the staff list.
     */
    public void ClearStaff()
    {
        staff.clear();
    }

    /**
     * Returns staff list as ArrayList object.
     * @return staff list.
     */
    public ArrayList getStaffList()
    {
        return staff;
    }
}
