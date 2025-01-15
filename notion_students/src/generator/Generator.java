package generator;

import java.io.PrintWriter;
import java.util.List;

import document.Block;

public interface Generator {

	public void generate(PrintWriter writer, List<Block> block);
}
