package medida;

public abstract class Medida {

	public static final int ANCHO = 500;
	public static final int LARGO = 500;
	public static final int BORDE = 50;

	public static final int ANCHO_VENTANA = 800; 
	public static final int LARGO_VENTANA = 600;
	
	public static final int SIZE_MATRIZ = 50;
	public static final int FPS = 25; //FRAMES_PER_SECOND
	public static final int SKIP_TICKS = 1000/FPS;
	
	public static final int SIZE = ANCHO/SIZE_MATRIZ;

	public static final int NORTE = -1; // Norte y sur deben ser opuestos
	public static final int SUR = 1;
	public static final int OESTE = -2; // Oeste y Este deben ser opuestos
	public static final int ESTE = 2;

}
