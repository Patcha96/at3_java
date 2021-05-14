package sample;

/**
 * Tree Node object made for storing string data inside a binary search tree.
 */
public class TreeNode
{
    private String element;
    private TreeNode left;
    private TreeNode Right;
    private int height;

    /**
     * Tree Node constructor
     * @param _element string element stored in the tree node.
     */
    public TreeNode(String _element)
    {
        element = _element;
        left = null;
        Right = null;
        height = 1;
    }

    /**
     * Returns string element in tree node.
     * @return String element.
     */
    public String getElement()
    {
        return element;
    }

    /**
     * Sets string element in tree node.
     * @param _element String element.
     */
    public void setElement(String _element)
    {
        element = _element;
    }

    /**
     * Gets Tree Node on the left of current tree node.
     * @return Left TreeNode.
     */
    public TreeNode getLeft()
    {
        return left;
    }

    /**
     * Sets Tree Node on the left of current tree node.
     * @param _left TreeNode.
     */
    public void setLeft(TreeNode _left)
    {
        left = _left;
    }

    /**
     * Gets Tree Node on the right of current tree node.
     * @return Right TreeNode.
     */
    public TreeNode getRight()
    {
        return Right;
    }

    /**
     * Sets Tree Node on the right of current tree node.
     * @param _right TreeNode.
     */
    public void setRight(TreeNode _right)
    {
        Right = _right;
    }

    /**
     * Gets height of current tree node.
     * @return height int.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Sets height of current tree node.
     * @param _height int.
     */
    public void setHeight(int _height)
    {
        height = _height;
    }
}