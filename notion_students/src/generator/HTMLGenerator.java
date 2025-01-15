package generator;

import java.io.PrintWriter;
import java.util.List;

import document.Block;

public class HTMLGenerator implements Generator{

	@Override
	public void generate(PrintWriter writer, List<Block> block) {
		
		writer.println("<!DOCTYPE html>");
        writer.println("<head>");
        writer.println("  <meta charset=\"UTF-8\">");
        writer.println("  <style type=\"text/css\">");
        writer.println("    body { font-family: Calibri; margin-left: 5em; }");
        writer.println("  </style>");
        writer.println("</head>");
        writer.println("<body>");
        
        for(Block b : block) {
        	b.generateHTML(writer);

        }
				
		writer.println("</body>");
        writer.println("</html>");
	}

}
