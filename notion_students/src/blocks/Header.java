package blocks;

import java.io.PrintWriter;

import document.*;

public class Header implements Block 
{
    private String text;

    public Header(String text) 
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
        writer.println("\t<h2>" + getText() + "</h2>");
    }

    @Override
    public void generateMarkdown(PrintWriter writer) 
    {
        writer.println("\n## " + getText());
    }
}
