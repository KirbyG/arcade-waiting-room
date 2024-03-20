class Data
{
    private int difficulty;
    private Button[] buttons;
    private Tower[] rings;
    private boolean solve = false;
    public Data()
    {
        difficulty = 5;
        buttons = new Button[4];
        int height = c.h / 2 + (c.rh * difficulty + 100) / 2 + c.pw + c.gtom + c.box / 2;
        buttons[0] = new Button(c.w / 2 - (c.box + c.sep), height, c.box, c.box, "-");
        buttons[1] = new Button(c.w / 2, height, c.box, c.box, "" + difficulty);
        buttons[2] = new Button(c.w / 2 + c.box + c.sep, height, c.box, c.box, "+");
        buttons[3] = new Button(c.w / 2, height + c.box + c.sep, c.box * 3 + c.sep * 2, c.box, "solve");
        rings = new Tower[3];
        for (int i = 0; i < 3; i++)
        {
            rings[i] = new Tower();
        }
        solve = false;
    }
    public void load()
    {
        for (int i = 0; i < difficulty; i++)
        {
            rings[0].add(difficulty - i);
        }
    }
    public void solveTime()
    {
        solve = true;
    }
    public boolean solve()
    {
        return solve;
    }
    public void setDifficulty(int d)
    {
        difficulty = d;
    }
    public int getDifficulty()
    {
        return difficulty;
    }
    public Button[] getButtons()
    {
        return buttons;
    }
    public Tower[] rings()
    {
        return rings;
    }
}