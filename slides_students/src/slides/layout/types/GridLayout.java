package slides.layout.types;

import slides.ui.Canvas;

public class GridLayout implements LayoutType
{
    @Override
    public String toString()
    {
        return "Grid";
    }

    @Override
    public void drawLayout(Canvas canvas) {
        canvas.drawLine("┌────┬────┬─   ───┐");
        canvas.drawLine("│    │    │       │");
        canvas.drawLine("├────┴────┼─   ───┤");
        canvas.drawLine("             ...  ");
        canvas.drawLine("├─────────┤       │");
        canvas.drawLine("└─────────┴─   ───┘");        

    }

}
