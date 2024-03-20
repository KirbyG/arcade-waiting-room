import java.util.ArrayList;
class Tower
{
    private ArrayList<Integer> stack;
    public Tower()
    {
        stack = new ArrayList<Integer>();
    }
    public void add(int addition)
    {
        stack.add(addition);
    }
    public void remove()
    {
        stack.remove(stack.size() - 1);
    }
    public int top()
    {
        return stack.get(stack.size() - 1);
    }
    public ArrayList<Integer> stack()
    {
        return stack;
    }
}