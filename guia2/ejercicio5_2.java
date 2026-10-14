public class ejercicio5_2 {

    public static void contarRegresivamente(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El número debe ser mayor o igual que cero.");
        }

        if (n == 0) {
            System.out.println(0);

            // El método es void: return; termina esta llamada sin devolver un valor.
            return;
        }

        /*
         * Imprimir antes de la llamada recursiva muestra los valores en orden
         * descendente: n, n - 1, ..., 0.
         * Si se imprime después de la llamada, la instrucción espera a que
         * terminen las llamadas siguientes y los valores salen en orden
         * ascendente durante el retorno.
         */
        System.out.println(n);
        contarRegresivamente(n - 1);
    }

    public static void main(String[] args) {
        int[] casos = {5, 0, -2};

        for (int n : casos) {
            System.out.println("Prueba con n = " + n);
            long inicio = System.nanoTime();

            try {
                contarRegresivamente(n);
                long tiempo = System.nanoTime() - inicio;
                System.out.println("Tiempo: " + tiempo + " ns");
            } catch (IllegalArgumentException excepcion) {
                long tiempo = System.nanoTime() - inicio;
                System.out.println("Error: " + excepcion.getMessage());
                System.out.println("Tiempo: " + tiempo + " ns");
            }
        }
    }
}