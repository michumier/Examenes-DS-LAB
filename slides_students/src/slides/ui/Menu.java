package slides.ui;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import slides.content.*;
import slides.layout.*;
import slides.layout.types.ColumnLayout;
import slides.layout.types.GridLayout;
import slides.layout.types.LayoutType;
import slides.layout.types.NormalLayout;
import slides.model.*;

/*
	Un menú en modo texto que representa la interfaz de usuario de la aplicación.
 */
public class Menu 
{
	// la presentación (el modelo)
    private Presentation presentation;
    // el objeto que usarán el resto de clases de la aplicación para leer y escribir
    private Canvas canvas;
    private PrintWriter out;
    private BufferedReader in;
    // si se está ejecutando en modo interactivo (leyendo y escribiendo en la
    // consola) o desde un fichero que simula la entrada de usuario
    private boolean interactive = false;
    private boolean exit = false;
    private Map<String, SlidePrototype> masterLayouts = new HashMap<>();
    
    public Map<String, SlidePrototype> getMasterLayouts() {
        return masterLayouts;
    }

    public void setMasterLayouts(Map<String, SlidePrototype> masterLayouts) {
        this.masterLayouts = masterLayouts;
    }

    public Menu(Presentation presentation) 
    {
        this(presentation, new InputStreamReader(System.in), 
                new OutputStreamWriter(System.out));
        canvas = new Canvas();
        interactive = true;
        initializeMasterLayouts();
    }

    public Menu(Presentation presentation, Reader input, Writer output)
    {
        this.presentation = presentation;
        this.in = new BufferedReader(input);
        // ¡ojo!, que aunque se cree indicando 'autoflush' = true, esto sólo
        // funciona con los métodos println, printf y format, ¡pero no con print!
        // (si se utiliza print habría que llamar igualmente a out.flush() a
        // continuación)
        this.out = new PrintWriter(output, true);
        canvas = new Canvas(this.in, this.out);
        initializeMasterLayouts();
    }

    private void initializeMasterLayouts() {
        masterLayouts.put("Normal", new SlidePrototype(new LayoutManager(new NormalLayout())));
        masterLayouts.put("Columnas", new SlidePrototype(new LayoutManager(new ColumnLayout())));
        masterLayouts.put("Grid", new SlidePrototype(new LayoutManager(new GridLayout())));
    }

    //-- Menú principal

    public void run()
    {
        showOptions();
        while (!exit) {
            out.printf("> ");
            String option = askUser().toLowerCase();
            try {
                if (option.equals("s") || option.equals("siguiente")) {
                    nextSlide();
                } else if (option.equals("a") || option.equals("anterior")) {
                    previousSlide();
                } else if (option.equals("b") || option.equals("blanco")) {
                    newBlankSlide();
                } else if (option.equals("n") || option.equals("nueva")) {
                    newSlideFromMaster();
                } else if (option.equals("e") || option.equals("editar")) {
                    edit();
                } else if (option.equals("m") || option.equals("maestra")) {
                    // crea una nueva diapositiva maestra a partir de la actual
                    newMasterLayout();
                } else if (option.equals("p") || option.equals("presentación")) {
                    // muestra todas las diapositivas, del tirón
                    showAll();
                } else if (option.equals("ayuda")) {
                    showOptions();
                } else if (option.equals("salir")) {
                    exit();
                } else if (option.isEmpty()) {
                    // no hacer nada
                } else if (option.startsWith(";")) {
                    // por si se quieren poner comentarios en un fichero de entrada
                } else {
                    out.println("¡Acción desconocida!");
                    showOptions();
                }
            } catch (MenuException e) {
                out.println(e.getMessage());
            }
        }    
    }

    private void showOptions()
    {
        out.println("Acciones: [S]iguiente - [A]nterior - Nueva diapositiva en [B]lanco");
        out.println("          [N]ueva a partir de maestra - Convertir en [M]aestra");
        out.println("          [E]ditar - [P]resentación - Salir");
        out.println("------------------------------------------------------------------");
    }
    
    /*-----------------------------------------------------------------------*/

  
    private void showCurrentSlide()
    {
        if (presentation.isEmpty())
            return;
        showSlideNumber();
        presentation.currentSlide().show(canvas);
    }

    private void showSlideNumber()
    {
        canvas.drawLine(String.format("Diapositiva %d de %d", 
                presentation.currentSlideNumber(), presentation.getSlides().size()));
    }

    private void nextSlide()
    {
        if (!presentation.hasNext()) {
            out.println("No hay más diapositivas que mostrar");
            return;
        }
        presentation.nextSlide();
        showCurrentSlide();
    }

    private void previousSlide()
    {
        if (!presentation.hasPrevious()) {
            out.println("Estás en la primera diapositiva");
            return;
        }
        presentation.previousSlide();
        showCurrentSlide();
    }

    private void edit()
    {
        if (presentation.isEmpty()) {
            out.println("Aún no has añadido ninguna diapositiva");
            return;
        }
        presentation.currentSlide().edit(canvas);
        showCurrentSlide();                
    }

    private void showAll()
    {
        for (Slide slide : presentation.getSlides()) {
            slide.show(canvas);
        }
    }


    /*------------------------------------------------------------------------

        CREACIÓN DE UNA NUEVA DIAPOSITIVA EN BLANCO

     ------------------------------------------------------------------------*/

    private void newBlankSlide() 
    {
        // en primer lugar, el usuario elige el tipo de disposición de la diapositiva
        LayoutManager layout = new LayoutManager(selectLayout());
        // a continuación, cuántos marcadores de posición tendrá esta diapositiva,
        // indicando para cada uno:
        // - la posición que ocupan en el layout elegido (que simularemos simplemente
        //   introduciendo un nombre descriptivo para cada uno, como "CENTER", "TOP",
        //   "BOTTOM_LEFT" o similares)
        // - el tipo de marcador (de texto, lista de viñetas o imagen)
        selectPlaceholders(layout);
        // se crea la nueva diapositiva a partir del diseño creado
        Slide slide = new Slide(layout);
        // y se pide al usuario que rellene el contenido (sus marcadores)
        slide.edit(canvas);
        // ya sólo queda añadir la diapositiva a la presentación y mostrarla
        presentation.addSlide(slide);
        showCurrentSlide();
    }

    private LayoutType selectLayout()
    {
        out.println("Elige cómo se dispondrán los elementos de esta diapositiva:");
        out.printf("  => [N]ormal, [C]olumnas o [G]rid: ");
        do {
            String option = askUser().toLowerCase();
            if (option.equals("n") || option.equals("normal")) {
                return new NormalLayout();
            } else if (option.equals("c") || option.equals("columnas")) {
                return new ColumnLayout();
            } else if (option.equals("g") || option.equals("grid")) {
                return new GridLayout();
            } else {
                out.println("Tipo de layout desconocido, vuelve a intentarlo:");
                out.printf("  => [N]ormal, [C]olumnas o [G]rid: ");
            }
        } while (true);
    }

    private void selectPlaceholders(LayoutManager layout)
    {
        out.println("Ahora, los marcadores de posición (en blanco para terminar):");
        do {
            out.printf(" - Posición en el diseño: ");
            String position = askUser();
            if (position.isEmpty()) 
                return;
            out.printf(" - Tipo de marcador ([T]ítulo, [L]ista de viñetas o [I]magen): ");
            Placeholder placeholder = selectPlaceholderType();
            layout.addPlaceholder(position, placeholder);
        } while (true);        
    }

    private Placeholder selectPlaceholderType()
    {
        do {
            String option = askUser().toLowerCase();
            if (option.equals("t") || option.equals("título")) {
                return new Title();
            } else if (option.equals("l") || option.equals("lista")) {
                return new Bullets();
            } else if (option.equals("i") || option.equals("imagen")) {
                return new Image();
            } else {
                out.println("Tipo de marcador desconocido; debe ser uno de entre los siquientes:");
                out.println("  => [T]ítulo, [L]ista de viñetas o [I]magen");
            }
        } while (true);
    }

    /*------------------------------------------------------------------------

        DISEÑOS DE DIAPOSITIVAS MAESTRAS (MASTER LAYOUTS)

     ------------------------------------------------------------------------*/

    /*
     * Crea un nuevo diseño de diapositiva a partir de la diapositiva actual.
     */
    private void newMasterLayout()
    {
        if (presentation.isEmpty()) {
            out.println("Aún no has añadido ninguna diapositiva");
            return;
        }
        out.printf("Introduce el nombre del nuevo diseño maestro: ");
        String name = askUser();
        SlidePrototype prototype = new SlidePrototype(presentation.currentSlide().getLayout());
        masterLayouts.put(name, prototype);
        out.println("Nuevo diseño maestro añadido: " + name);
    }

    /*
     * Crea una nueva diapositiva a partir de un diseño existente.
     */
    private void newSlideFromMaster()
    {
        if (masterLayouts.isEmpty()) {
            out.println("No hay diseños maestros disponibles");
            return;
        }
        out.println("Elige un diseño maestro:");
        for (String name : masterLayouts.keySet()) {
            out.println("  => " + name);
        }
        String name = askUser();
        SlidePrototype prototype = masterLayouts.get(name);
        if (prototype == null) {
            out.println("Diseño maestro desconocido");
            return;
        }
        Slide slide = prototype.clone();
        slide.edit(canvas);
        presentation.addSlide(slide);
        showCurrentSlide();
    }
    
    
    //-- Métodos auxiliares

    /*
     * Lee una línea de la entrada, realizando pequeñas tareas adicionales, para
     * evitar repetirlas en todos los sitios del menú donde se pregunta algo al 
     * usuario. Para ello resto de código de esta clase de la interfaz de usuario
     * debería pedir los datos siempre a través de este método en vez de llamar
     * directamente a 'in.readLine()'. También se encarga de capturar los posibles
     * errores de entrada/salida (que no se deberían producir si se está leyendo
     * de la entrada estándar).
     */
    private String askUser() {
        try {
            String userInput = in.readLine();
            if (userInput == null) {
            	exit = true;
            	throw new MenuException("No hay más datos en la entrada");
            }
            // si no está en modo interactivo, sino leyendo la entrada de usuario
            // de un fichero, escribe lo que acaba de leer la salida
			if (!interactive) {
				out.println(userInput);
			}
            return userInput;            
        } catch (IOException e) {
        	throw new MenuException("Se produjo un error al pedir datos desde la interfaz de usuario");
        }
    }

    /*
     * Sale del menú principal, devolviendo el control al programa principal que
     * debería ser quien hubiera llamado el método 'run' de esta clase. En caso
     * de que se esté escribiendo la salida del programa en un fichero en vez de
     * en la consola, lo cierra.
     */
    private void exit()
    {
        exit = true;
        if (!interactive) {
            out.close();
        }
        out.println("¡Adiós!");
    }
}