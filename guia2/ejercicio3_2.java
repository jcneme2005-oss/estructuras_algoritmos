public class ejercicio3_2 {

    public static int multiplicar(int a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException(
                    "El segundo número debe ser mayor o igual que cero.");
        }

        /*
         * Caso base: multiplicar a por cero da cero.
         * Este resultado detiene la recursión.
         */
        if (b == 0) {
            return 0;
        }

        /*
         * Reduce b para acercarse al caso base.
         * Al llegar al caso base, las llamadas pendientes se resuelven
         * al desapilarse: cada una suma a al resultado recibido.
         * Por ejemplo, para b = 3: a + (a + (a + 0)).
         */
        return a + multiplicar(a, b - 1);
    }

    public static void main(String[] args) {
        int[][] casos = {
            {5, 0},
            {5, 1},
            {0, 5},
            {4, 3},
            {5, -2}
        };

        for (int[] caso : casos) {
            int a = caso[0];
            int b = caso[1];

            long inicio = System.nanoTime();

            try {
                int resultado = multiplicar(a, b);
                long tiempo = System.nanoTime() - inicio;

                System.out.println(a + " * " + b + " = " + resultado
                        + " | Tiempo: " + tiempo + " ns");
            } catch (IllegalArgumentException excepcion) {
                long tiempo = System.nanoTime() - inicio;

                System.out.println(a + " * " + b + " lanzó "
                        + excepcion.getClass().getSimpleName()
                        + " | Tiempo: " + tiempo + " ns");
            }
        }
    }
}