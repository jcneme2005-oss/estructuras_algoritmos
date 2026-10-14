public class ejercicio2_2 {

    /**
     * Suma los enteros desde n hasta 1 mediante recursión.
     *
     * @param n límite superior de la suma; debe ser mayor o igual que cero
     * @return la suma desde n hasta 1, o 0 si n es 0
     * @throws IllegalArgumentException si n es negativo
     */
    public static int sumarHastaUno(int n) {
        /*
         * Rechaza los valores negativos antes de hacer llamadas recursivas.
         * Así, el método informa del argumento inválido en vez de seguir
         * decrementándolo hasta agotar la pila.
         */
        if (n < 0) {
            throw new IllegalArgumentException(
                    "n debe ser mayor o igual que cero.");
        }

        /*
         * Caso base: detener la recursión cuando n llega a 0.
         * La suma restante es 0 y no se necesita otra llamada.
         */
        if (n == 0) {
            return 0;
        }

        /*
         * Paso recursivo: suma n al resultado de sumar los valores menores.
         * n - 1 acerca cada llamada al caso base.
         *
         * Sin el caso base, y sin otra condición de parada, las llamadas
         * no terminarían y podrían provocar StackOverflowError. Si se
         * conserva la validación de negativos, omitir el caso base hace
         * que la recursión descendente termine en IllegalArgumentException
         * al llegar a -1.
         */
        return n + sumarHastaUno(n - 1);
    }

    public static void main(String[] args) {
        int[] casos = {0, 1, 5};

        for (int n : casos) {
            long inicio = System.nanoTime();
            int resultado = sumarHastaUno(n);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println("sumarHastaUno(" + n + ") = " + resultado
                    + " | Tiempo: " + tiempoTranscurrido + " ns");

            if (n == 5 && resultado != 15) {
                throw new AssertionError("La suma hasta 5 debería ser 15.");
            }
        }

        System.out.println("Verificación para n = 5: correcta.");
    }
}