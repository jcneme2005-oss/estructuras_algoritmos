public class ejercicio7_2 {

    public static int sumarDigitos(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "El número debe ser mayor o igual que cero.");
        }

        /*
         * Caso base: si n tiene un solo dígito, ese dígito es la suma.
         * También cubre n == 0, cuya suma de dígitos es 0.
         */
        if (n < 10) {
            return n;
        }

        /*
         * n % 10 extrae el dígito de las unidades.
         * n / 10 usa división entera para descartar ese dígito.
         *
         * La llamada recursiva procesa los dígitos restantes. Cuando retorna,
         * cada llamada suma el dígito que había extraído. Por ejemplo:
         * 123 -> 3 + (2 + 1).
         */
        return (n % 10) + sumarDigitos(n / 10);
    }

    public static void main(String[] args) {
        int[] casos = {7, 0, 12345, 10050};
        int[] resultadosEsperados = {7, 0, 15, 6};

        for (int i = 0; i < casos.length; i++) {
            long inicio = System.nanoTime();
            int resultado = sumarDigitos(casos[i]);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println("sumarDigitos(" + casos[i] + ") = "
                    + resultado + " | Tiempo: " + tiempoTranscurrido + " ns");

            if (resultado != resultadosEsperados[i]) {
                throw new AssertionError(
                        "Resultado incorrecto para n = " + casos[i]);
            }
        }
    }
}