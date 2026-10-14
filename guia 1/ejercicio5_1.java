public class ejercicio5_1 {

    /**
     * Cuenta cuántas veces aparece un valor en el arreglo.
     *
     * El recorrido no se interrumpe al encontrar una coincidencia porque
     * todavía podría haber más apariciones en los elementos restantes.
     *
     * @param vector arreglo que se va a recorrer
     * @param valorBuscado valor cuyas apariciones se cuentan
     * @return cantidad de veces que aparece el valor en el arreglo
     */
    public static int contarOcurrencias(int[] vector, int valorBuscado) {
        // Acumula el total de coincidencias encontradas.
        int contador = 0;

        // Recorre cada posición para contar todas las apariciones.
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == valorBuscado) {
                // Suma una coincidencia cuando los valores son iguales.
                contador++;
            }
        }

        return contador;
    }

    public static void main(String[] args) {
        int[] vector = {3, 7, 3, 2, 3, 9};
        int valorBuscado = 3;

        long inicio = System.nanoTime();
        int cantidad = contarOcurrencias(vector, valorBuscado);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("El valor " + valorBuscado + " aparece "
                + cantidad + " veces.");
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}