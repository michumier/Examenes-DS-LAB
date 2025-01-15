package blocks;

import java.io.PrintWriter;

import document.*;

public class Text implements Block 
{
    private String text;

    public Text(String text) 
    {
        this.text = text;
    }

    public String getText() 
    {
        return text;
    }

    @Override
    public void generateHTML(PrintWriter writer) 
    {
        writer.println("\t<p>" + getText() + "</p>");

    }

    @Override
    public void generateMarkdown(PrintWriter writer) 
    {
        writer.println(getText());

    }
}
