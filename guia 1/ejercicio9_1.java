public class ejercicio9_1 {

    public static int encontrarMayor(int[][] matriz) {
        /*
         * Se usa el primer elemento como valor inicial para que el algoritmo
         * también funcione si todos los números son negativos. Si se iniciara
         * en 0, podría devolver 0 aunque ese valor no estuviera en la matriz.
         * Se asume que la matriz contiene al menos una celda.
         */
        int mayor = matriz[0][0];

        // i recorre las filas de la matriz.
        for (int i = 0; i < matriz.length; i++) {
            // j recorre las columnas de la fila actual.
            for (int j = 0; j < matriz[i].length; j++) {
                // Si la celda actual supera al mayor encontrado, lo actualiza.
                if (matriz[i][j] > mayor) {
                    mayor = matriz[i][j];
                }
            }
        }

        return mayor;
    }

    public static void main(String[] args) {
        int[][] matriz = {
            {12, 5, 8},
            {21, 3, 14},
            {7, 19, 10}
        };

        long inicio = System.nanoTime();
        int mayor = encontrarMayor(matriz);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Valor mayor: " + mayor);
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}