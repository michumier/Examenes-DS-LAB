package slides.layout.types;

import slides.ui.Canvas;

public class NormalLayout implements LayoutType
{
    @Override
    public String toString()
    {
        return "Normal";
    }

    @Override
    public void drawLayout(Canvas canvas) {
        canvas.drawLine("┌─────────────────┐");
        canvas.drawLine("│                 │");
        canvas.drawLine("├─────────────────│");
        canvas.drawLine("        ...        ");
        canvas.drawLine("│                 │");
        canvas.drawLine("└─────────────────┘");

    }
    

}
