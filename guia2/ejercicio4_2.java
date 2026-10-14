public class ejercicio4_2 {

    public static long potencia(int base, int exponente) {
        // Los exponentes negativos no se procesan en esta implementación.
        if (exponente < 0) {
            throw new IllegalArgumentException(
                    "El exponente debe ser mayor o igual que cero.");
        }

        /*
         * Caso base: cualquier base elevada a 0 es 1.
         * Este valor detiene la recursión y permite resolver
         * las multiplicaciones pendientes al retornar.
         */
        if (exponente == 0) {
            return 1;
        }

        /*
         * Reduce el exponente en cada llamada para acercarse al caso base.
         * Al retornar, cada llamada multiplica la base por el resultado
         * de la llamada siguiente. Por ejemplo, 2^3 se resuelve como
         * 2 * (2 * (2 * 1)).
         */
        return base * potencia(base, exponente - 1);
    }

    public static void main(String[] args) {
        int[][] casos = {
            {5, 0},
            {5, 1},
            {0, 5},
            {2, 3},
            {3, 4}
        };

        for (int[] caso : casos) {
            int base = caso[0];
            int exponente = caso[1];

            long inicio = System.nanoTime();
            long resultado = potencia(base, exponente);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println(base + "^" + exponente + " = " + resultado
                    + " | Tiempo: " + tiempoTranscurrido + " ns");
        }
    }
}