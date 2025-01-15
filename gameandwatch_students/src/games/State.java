package games;

public interface State {
	
	void initialize();
	void checkFinished();
	void draw();
	void gameFinished();
	void restart();

}
