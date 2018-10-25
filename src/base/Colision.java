package base;

import GameObjects.*;

public class Colision {

	public static void colision(int x, int y, Snake viboritaQueSemueve) {
		Snake viboritaALaQueChocan;
		int objetoChocado = Escenario.matriz[x][y];

		if (objetoChocado != 0 && objetoChocado != -1 && objetoChocado != 99) { // si es !=0 y no es un item, ni tampoco
																				// el cuerpo de una viborita, choque de
																				// cabezas y mato a las 2
			viboritaALaQueChocan = Escenario.viboritas.get(Escenario.matriz[x][y] - 1);
			viboritaALaQueChocan.muerto = true;
			viboritaQueSemueve.muerto = true;

		}

		if (objetoChocado == 99) // sino, choco contra un cuerpo y tengo que matarla a ella sola (no importa si
									// es su propio cuerpo o de otra vibora)
			viboritaQueSemueve.muerto = true;

	}

	public static boolean colisiona(Snake snake, Fruta item) {
		// colision de fruta
		if (snake.getPosition().equals(item.getPosition())) {
			item.setItem();
			snake.crecer();
			return true;
		}
		
		return false;
	}

	public static boolean colisiona(Snake snake, Snake snake2) {
		// colision de cabezas
		if (snake.getPosition().equals(snake2.getPosition())) {
			//agregue las direcciones, verificar que las dir para determinar quienes mueren
			snake.muerto = true;
			snake2.muerto = true;
			return true;
		}
		
		for(Cuerpo c: snake2.getCuerpos()) {
			if(snake.getPosition().equals(c.getPosition())) {
				snake.muerto=true;
				return true;
			}
		}
		
		//ahorrar y preguntar si la snake2 choca con algun cuerpo? 
		
		return false;
	}


}
