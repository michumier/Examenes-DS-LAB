package blocks;

import java.io.PrintWriter;

import document.*;

public class Checkbox implements Block 
{
    private String text;
    private boolean status;

    public Checkbox(String text, boolean status) 
    {
        this.text = text;
        this.status = status;
    }

    public String getText() 
    {
        return text;
    }

    public boolean getStatus() 
    {
        return status;
    }

    @Override
    public void generateHTML(PrintWriter writer) 
    {
        writer.println("\t<p><input type=\"checkbox\"> " + getText() + "</p>");
    }

    @Override
    public void generateMarkdown(PrintWriter writer) 
    {
        writer.print(getStatus() ? "[x] " : "[ ] ");
        writer.println(getText());
    }
}
