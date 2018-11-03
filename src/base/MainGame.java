package base;

public class MainGame {

	public static void main(String[] args) {
		Sala sala = new Sala();
		new Thread (sala).start();
	}
}
