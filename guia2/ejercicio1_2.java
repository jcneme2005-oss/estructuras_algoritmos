public class ejercicio1_2{

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El factorial no está definido para números negativos.");
        }

        /*
         * Caso base: 0! y 1! valen 1.
         * Detener aquí la recursión evita seguir llamando al método
         * cuando ya se alcanzó el resultado más pequeño necesario.
         */
        if (n == 0 || n == 1) {
            return 1;
        }

        /*
         * Reduce el problema: para calcular n!, calcula n * (n - 1)!.
         * Cada llamada recibe un valor menor y se acerca al caso base.
         */
        return n * factorial(n - 1);

        /*
         * Si se reemplazara n - 1 por n + 1, el valor crecería y no
         * alcanzaría el caso base; las llamadas podrían agotar la pila
         * y producir StackOverflowError.
         *
         * Si se quitara solo el caso base, la validación n < 0 de arriba
         * haría que la recursión descendente terminara con
         * IllegalArgumentException. Sin ninguna condición de parada,
         * podría producirse StackOverflowError.
         */
    }

    public static void main(String[] args) {
        int[] casos = {0, 1, 5, -2};

        for (int n : casos) {
            long inicio = System.nanoTime();

            try {
                long resultado = factorial(n);
                long tiempo = System.nanoTime() - inicio;

                System.out.println("factorial(" + n + ") = " + resultado
                        + " | Tiempo: " + tiempo + " ns");
            } catch (IllegalArgumentException excepcion) {
                long tiempo = System.nanoTime() - inicio;

                System.out.println("factorial(" + n + ") lanzó "
                        + excepcion.getClass().getSimpleName()
                        + " | Tiempo: " + tiempo + " ns");
            }
        }
    }
}