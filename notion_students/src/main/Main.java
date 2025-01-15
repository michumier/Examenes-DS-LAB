package main;

import java.io.FileWriter;
import java.io.PrintWriter;

import blocks.Checkbox;
import blocks.Header;
import blocks.Image;
import blocks.Link;
import blocks.Separator;
import blocks.Text;
import document.Document;
import generator.HTMLGenerator;

public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        Document doc = new Document(new HTMLGenerator());

        createTestDocument(doc);

        PrintWriter htmlFile = new PrintWriter(new FileWriter("html_page.html"));
        doc.generate(htmlFile);
        htmlFile.close();

        Document doc2 = new Document(new HTMLGenerator());

        createTestDocument(doc2);
        
        PrintWriter mdFile = new PrintWriter(new FileWriter("md_page.md"));
        doc2.generate(mdFile);
        mdFile.close();
    }	

    private static void createTestDocument(Document doc) 
    {
        doc.addBlock(new Header("Historia de Roma"));
        doc.addBlock(new Image("./images/Coliseo.jpg"));
        doc.addBlock(new Header("Capitulo 1. La República"));
        doc.addBlock(new Text("Lorem ipsum Republicum..."));
        doc.addBlock(new Separator());
        doc.addBlock(new Header("Capitulo 2. El Imperio"));
        doc.addBlock(new Text("Lorem ipsum Imperium..."));
        doc.addBlock(new Separator());
        doc.addBlock(new Checkbox("Marcar como favorito", true));
        doc.addBlock(new Link("Ver más de la historia de Roma en Wikipedia", "https://es.wikipedia.org/wiki/Roma"));
    }
}
