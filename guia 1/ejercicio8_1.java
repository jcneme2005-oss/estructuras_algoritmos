public class ejercicio8_1 {

    // Guarda el número de celdas procesadas para mostrarlo desde main.
    private static int contadorOperaciones;

    public static int sumarMatriz(int[][] matriz) {
        int suma = 0;
        contadorOperaciones = 0;

        // matriz.length indica cuántas filas tiene la matriz.
        for (int i = 0; i < matriz.length; i++) {

            // matriz[i].length indica cuántas columnas tiene esta fila.
            for (int j = 0; j < matriz[i].length; j++) {

                // Acumula el valor de la celda actual.
                suma += matriz[i][j];

                // Registra una operación por cada celda visitada.
                contadorOperaciones++;
            }
        }

        return suma;
    }

    public static void main(String[] args) {
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        long inicio = System.nanoTime();
        int resultado = sumarMatriz(matriz);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Suma de la matriz: " + resultado);
        System.out.println("Celdas visitadas: " + contadorOperaciones);
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}