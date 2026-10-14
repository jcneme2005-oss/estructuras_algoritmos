public class ejercicio10_2 {

    public int buscar(int[] arreglo, int objetivo) {
        // Inicia la búsqueda en el primer elemento.
        return buscarRecursivo(arreglo, objetivo, 0);
    }

    private int buscarRecursivo(int[] arreglo, int objetivo, int indice) {
        /*
         * Caso base de parada por límite: se agotaron los elementos.
         * Se comprueba antes de acceder al arreglo[indice] para no salir
         * de los límites del arreglo. -1 indica que no se encontró el objetivo.
         */
        if (indice >= arreglo.length) {
            return -1;
        }

        /*
         * Caso base de éxito: el elemento actual coincide con el objetivo.
         * Se devuelve su índice.
         */
        if (arreglo[indice] == objetivo) {
            return indice;
        }

        /*
         * Avanza a la siguiente posición. El parámetro indice + 1 cumple
         * el mismo rol que i++ en un bucle for tradicional: controla el
         * avance de la búsqueda en cada paso recursivo.
         */
        return buscarRecursivo(arreglo, objetivo, indice + 1);
    }

    public static void main(String[] args) {
        ejercicio10_2 buscador = new ejercicio10_2();
        int[] arreglo = {10, 20, 30, 40, 50};

        int[][] arreglosDePrueba = {
            arreglo, arreglo, arreglo, arreglo, {}
        };
        int[] objetivos = {10, 30, 50, 99, 7};
        String[] descripciones = {
            "elemento al inicio",
            "elemento en el medio",
            "elemento al final",
            "elemento inexistente",
            "arreglo vacío"
        };

        for (int i = 0; i < objetivos.length; i++) {
            long inicio = System.nanoTime();
            int resultado = buscador.buscar(arreglosDePrueba[i], objetivos[i]);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println(descripciones[i] + ": índice " + resultado
                    + " | Tiempo: " + tiempoTranscurrido + " ns");
        }
    }
}