import java.util.HashSet;
import java.util.Set;

public class ejercicio4_1 {

    /**
     * Busca valores repetidos comparando cada elemento con los que aparecen
     * después de él.
     *
     * @param vector arreglo de enteros que se va a analizar
     * @return true si encuentra un valor repetido; false en caso contrario
     */
    public static boolean tieneDuplicadosAnidado(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            /*
             * Empieza en i + 1 para no comparar el elemento consigo mismo
             * y evitar repetir comparaciones que ya se hicieron.
             */
            for (int j = i + 1; j < vector.length; j++) {
                if (vector[i] == vector[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Busca valores repetidos guardando los elementos vistos en un HashSet.
     *
     * @param vector arreglo de enteros que se va a analizar
     * @return true si encuentra un valor repetido; false en caso contrario
     */
    public static boolean tieneDuplicadosHashSet(int[] vector) {
        Set<Integer> vistos = new HashSet<>();

        for (int valor : vector) {
            /*
             * add(valor) devuelve true si el valor no estaba y lo agrega.
             * Devuelve false si ya existía, indicando que encontramos
             * un duplicado. HashSet usa el hash del valor para localizarlo
             * y comprobar si ya forma parte del conjunto.
             */
            if (!vistos.add(valor)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Consumo de memoria RAM:
     * El método anidado usa espacio auxiliar O(1), porque solo necesita
     * índices y variables, sin importar el tamaño del vector.
     *
     * HashSet usa espacio auxiliar O(n) en el peor caso: si todos los valores
     * son distintos, guarda hasta n elementos. También necesita memoria para
     * su tabla hash y sus referencias. En Java, los int se representan como
     * Integer dentro del conjunto, lo que añade memoria. La cantidad exacta
     * depende de la JVM y de su implementación.
     */
    public static void main(String[] args) {
        int[] vector = {4, 8, 15, 16, 23, 42, 8};

        long inicioAnidado = System.nanoTime();
        boolean duplicadosAnidado = tieneDuplicadosAnidado(vector);
        long tiempoAnidado = System.nanoTime() - inicioAnidado;

        long inicioHashSet = System.nanoTime();
        boolean duplicadosHashSet = tieneDuplicadosHashSet(vector);
        long tiempoHashSet = System.nanoTime() - inicioHashSet;

        System.out.println("Anidado: " + duplicadosAnidado
                + " (" + tiempoAnidado + " ns)");
        System.out.println("HashSet: " + duplicadosHashSet
                + " (" + tiempoHashSet + " ns)");
    }
}