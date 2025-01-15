package slides.model;


import slides.content.Placeholder;
import slides.layout.LayoutManager;
import slides.ui.Canvas;

public class Slide 
{
    private LayoutManager layout;

    public Slide(LayoutManager layout)
    {
        if (layout == null)
            throw new NullPointerException("¡Se necesita un layout para crear la diapositiva!");
        this.layout = layout;
    }
    
    public LayoutManager getLayout()
    {
    	return layout;
    }
 
    public void show(Canvas canvas)
    {
        layout.drawLayout(canvas);
    }

    // NOTA: Téngase en cuenta que aunque, por ahora y para simplificar la
    //       implementación, los tres tipos de layout se limitan a mostrar por
    //       pantalla los distintos marcadores y la posición que ocupa cada uno
    //       como una cadena de texto, en un caso real esto no sería así, y 
    //       tendrían implementaciones muy distintas. Es lo que se está
    //       tratando de simular con esa especie de representación de la
    //       diapositiva que se imita en modo texto. El bucle que va a 
    //       continuación de ella dibujaría efectivamente en un caso real el
    //       contenido de cada marcador de posición, para los que a su vez
    //       previamente habría calculado su posición, dimensiones, forma de
    //       ajustarse al redimensionar la pantalla, etcétera).
    //
    //       (Esto mismo habría de tenerse en cuenta a la hora de la 
    //       exportación: en la mayoría de los casos no tendrá nada que ver la
    //       forma de generar los distintos formatos dependiendo del tipo de
    //       layout de la diapositiva.)
    //       
   

    public void edit(Canvas canvas)
    {
        for (Placeholder each : layout.getPlaceholders()) {
            each.edit(canvas);
        }
    }
    
    @Override
    public String toString()
    {
        return String.format("Diapositiva con %d marcadores de posición%n  => %s",
                layout.getPlaceholders().size(), layout);
    }

    public Slide clone() {
        return new Slide(layout);
    }
}