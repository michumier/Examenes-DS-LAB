package blocks;

import java.io.PrintWriter;

import document.*;

public class Separator implements Block 
{
    @Override
    public void generateHTML(PrintWriter writer) 
    {
        writer.println("\t<hr>");
    }

    @Override
    public void generateMarkdown(PrintWriter writer) 
    {
        writer.println("\n---");
    }
}
