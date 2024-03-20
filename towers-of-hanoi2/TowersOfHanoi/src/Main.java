class Main
{
    private static Data data = new Data();
    private static Input input = new Input();
    private static Output output = new Output(input);
    public static void main(String[] args) throws InterruptedException
    {
        output.redraw(data);
        int click = -1;
        boolean indicator = true;
        while (indicator)
        {
            Thread.sleep(0);
            if (input.mouse())
            {
                input.reset();
                if (inRange(data.getButtons()[0]))
                {
                    data.setDifficulty(data.getDifficulty() - 1);
                    if (data.getDifficulty() < 1)
                    {
                        data.setDifficulty(1);
                    }
                    data.getButtons()[1].setTxt("" + data.getDifficulty());
                }
                else
                {
                    if (inRange(data.getButtons()[2]))
                    {
                        data.setDifficulty(data.getDifficulty() + 1);
                        if (data.getDifficulty() > 20)
                        {
                            data.setDifficulty(20);
                        }
                        data.getButtons()[1].setTxt("" + data.getDifficulty());
                    }
                    else
                    {
                        if (inRange(data.getButtons()[3]))
                        {
                            indicator = false;
                        }
                    }
                }
                output.redraw(data);
            }
        }
        data.solveTime();
        data.load();
        output.redraw(data);
        move(0, data.getDifficulty(), 2);
    }
    public static void move(int ss, int d, int es) throws InterruptedException
    {
        int os = -1;
        for (int i = 0; i < 3; i++)
        {
            if (ss != i && es != i)
            {
                os = i;
            }
        }
        if (d == 1)
        {
            data.rings()[es].add(data.rings()[ss].top());
            data.rings()[ss].remove();
            output.redraw(data);
            Thread.sleep(1);
        }
        else
        {
            move(ss, d - 1, os);
            move(ss, 1, es);
            move(os, d - 1, es);
        }
    }
    public static boolean inRange(Button b)
    {
        int x = input.mx();
        int y = input.my();
        int xLeft = b.x() - b.w() / 2;
        int xRight = b.x() + b.w() / 2;
        int yTop = b.y() - b.h() / 2;
        int yBottom = b.y() + b.h() / 2;
        return xLeft < x && xRight > x && yTop < y && yBottom > y; //8mj8jnjbuhbuyhojbiughnbuhbiuhbiuhbiuhbiubuhjlbuhbuljbnjluhjn hkjb
    }
}