class Button
{
    private int x;
    private int y;
    private int w;
    private int h;
    private String txt;
    public Button(int x, int y, int w, int h, String txt)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.txt = txt;
    }
    public int x()
    {
        return x;
    }
    public int y()
    {
        return y;
    }
    public int w()
    {
        return w;
    }
    public int h()
    {
        return h;
    }
    public String txt()
    {
        return txt;
    }
    public void setTxt(String txt)
    {
        this.txt = txt;
    }
}