package document;

import java.io.PrintWriter;

public interface Block 
{
    public void generateHTML(PrintWriter writer);
    public void generateMarkdown(PrintWriter writer);
}
