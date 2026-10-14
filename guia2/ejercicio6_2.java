public class ejercicio6_2 {

    public static int contarDigitos(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El número debe ser mayor o igual que cero.");
        }

        /*
         * Caso base: cualquier número entre 0 y 9 tiene un dígito.
         * Detiene la recursión. También hace que contarDigitos(0) retorne 1.
         */
        if (n < 10) {
            return 1;
        }

        /*
         * La división entera entre 10 elimina el último dígito decimal.
         * Por ejemplo, 12345 / 10 da 1234.
         *
         * Cada llamada suma 1 al resultado al regresar. Así, el 1 acumulado
         * por cada llamada cuenta un dígito: para 12345 se obtiene
         * 1 + 1 + 1 + 1 + 1 al desapilar las llamadas.
         */
        return 1 + contarDigitos(n / 10);
    }

    public static void main(String[] args) {
        int[] casos = {7, 0, 12345, 10050};

        for (int n : casos) {
            long inicio = System.nanoTime();
            int cantidad = contarDigitos(n);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println("contarDigitos(" + n + ") = " + cantidad
                    + " | Tiempo: " + tiempoTranscurrido + " ns");
        }
    }
}