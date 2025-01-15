package blocks;

import java.io.PrintWriter;

import document.*;

public class Link implements Block 
{
    private String description, url;

    public Link(String description, String url) 
    {
        this.description = description;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getURL() {
        return url;
    }

    @Override
    public void generateHTML(PrintWriter writer) {
        writer.println("\t<a href=\"" + getURL() + "\">" + getDescription() + "</a>");

    }

    @Override
    public void generateMarkdown(PrintWriter writer) {
        writer.println("[" + getDescription() + "](" + getURL() + ")");

    }
}
