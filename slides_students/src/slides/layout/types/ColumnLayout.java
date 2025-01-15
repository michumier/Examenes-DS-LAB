package slides.layout.types;

import slides.ui.Canvas;

public class ColumnLayout implements LayoutType
{
    @Override
    public String toString()
    {
        return "Column";
    }

    @Override
    public void drawLayout(Canvas canvas) {
        canvas.drawLine("┌────┬────┬─   ───┐");
        canvas.drawLine("│    │    │       │");
        canvas.drawLine("│    │    │  ...  │");
        canvas.drawLine("│    │    │       │");
        canvas.drawLine("│    │    │       │");
        canvas.drawLine("└────┴────┴─   ───┘");        

    }

}
