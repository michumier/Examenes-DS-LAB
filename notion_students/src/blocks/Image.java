package blocks;

import java.io.PrintWriter;

import document.*;

public class Image implements Block 
{
    private String url;

    public Image(String url) 
    {
        this.url = url;
    }

    public String getURL() 
    {
        return url;
    }

    @Override
    public void generateHTML(PrintWriter writer) 
    {
        writer.println("\t<img src=\"" + getURL() + "\">");
    }

    @Override
    public void generateMarkdown(PrintWriter writer) 
    {
        writer.println("![image](" + getURL() + ")");
    }
}
