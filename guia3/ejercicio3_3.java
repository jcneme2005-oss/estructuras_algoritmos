import java.util.Arrays;

public class ejercicio3_3 {

	private static long comparaciones;
	private static long desplazamientos;

	public static void insertionSort(int[] array) {
		comparaciones = 0;
		desplazamientos = 0;

		for (int i = 1; i < array.length; i++) {
			// Guarda el elemento que se insertará en su posición ordenada.
			int clave = array[i];
			int j = i - 1;

			while (j >= 0 && array[j] > clave) {
				comparaciones++;

				/*
				 * Desplaza el elemento una posición a la derecha para abrir
				 * espacio para clave. Cada ejecución hace una escritura real
				 * en el arreglo, por eso desplazamientos mide los movimientos
				 * efectuados, no solo los que podrían haber sido necesarios.
				 */
				array[j + 1] = array[j];
				desplazamientos++;
				j--;
			}

			// Cuenta la última comparación fallida si j aún apunta al arreglo.
			if (j >= 0) {
				comparaciones++;
			}

			/*
			 * Inserta clave en el hueco que dejaron los desplazamientos.
			 * Esta escritura final coloca la clave; desplazamientos cuenta
			 * solo los movimientos de elementos hechos dentro del while.
			 */
			array[j + 1] = clave;
		}
	}

	public static void main(String[] args) {
		int[] numeros = {1, 2, 3, 5, 4, 6, 7};

		long inicio = System.nanoTime();
		insertionSort(numeros);
		long tiempoTranscurrido = System.nanoTime() - inicio;

		System.out.println("Vector ordenado: " + Arrays.toString(numeros));
		System.out.println("Comparaciones: " + comparaciones);
		System.out.println("Desplazamientos: " + desplazamientos);
		System.out.println("Tiempo de ejecución: "
				+ tiempoTranscurrido + " ns");
	}
}
