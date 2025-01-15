package slides.layout;

import java.util.*;

import slides.content.Placeholder;
import slides.layout.types.LayoutType;
import slides.ui.Canvas;

public class LayoutManager
{
    // asocia los marcadores de posición de esta disposición con la posición
    // que ocupan en el mismo (simulada aquí mediante una simple cadena de texto
    // como "CENTER", "TOP", "BOTTOM_LEFT" o similares; en un caso real sería
    // un objeto con toda la información necesaria para cada tipo de layout)
    private Map<Placeholder, String> positions = new LinkedHashMap<>();
    // los marcadores de posición (títulos, listas, imágenes...) de este layout
    private List<Placeholder> placeholders = new ArrayList<>();

    private LayoutType type;
    
    public LayoutManager(LayoutType type)
    {
        this.type = type;
    }

    public LayoutType getType()
    {
        return type;
    }

    public void addPlaceholder(String position, Placeholder placeholder) 
    {
        placeholders.add(placeholder);
        positions.put(placeholder, position);
    }

    public List<Placeholder> getPlaceholders()
    {
        return Collections.unmodifiableList(placeholders);
    }

    public String getPosition(Placeholder placeholder)
    {
        if (!positions.containsKey(placeholder))
            throw new IllegalArgumentException("Este layout no contiene ese marcador: " + placeholder);
        return positions.get(placeholder);
    }

    @Override
    public String toString()
    {
        return type.toString();

        
    }

    public void drawLayout(Canvas canvas) {
        type.drawLayout(canvas);
        for (Placeholder each : placeholders) {
            canvas.drawLine("Dibujando en: " + getPosition(each) + "...");
            each.paint(canvas);
        }
    }
}