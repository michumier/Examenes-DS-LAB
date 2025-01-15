package document;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import generator.Generator;

public class Document 
{
    private List<Block> blocks;
    
    public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	private Generator generator;
    

    public Document(Generator generator) {
		this.generator = generator;
	}

	public void addBlock(Block block) 
    {
        blocks.add(block);
    }

    public int size() 
    {
        return blocks.size();
    }

    public Block getBlock(int index) 
    {
        return blocks.get(index);
    }

    public void generate(PrintWriter writer) {
    	generator.generate(writer, blocks);
    }

}
