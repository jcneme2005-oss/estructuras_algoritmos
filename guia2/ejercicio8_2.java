public class ejercicio8_2{

    public static String invertirString(String cadena) {
        // Si la referencia es null, no hay una cadena que invertir.
        if (cadena == null) {
            return null;
        }

        /*
         * Caso base: una cadena vacía o de un solo carácter ya está
         * invertida. Este caso también detiene la recursión.
         */
        if (cadena.length() <= 1) {
            return cadena;
        }

        /*
         * charAt(0) obtiene el primer carácter.
         * substring(1) crea la parte restante, sin ese primer carácter.
         * La llamada recursiva invierte esa parte; al desapilar, se añade
         * el primer carácter al final, reconstruyendo la cadena al revés.
         * Por ejemplo, "hola" se transforma en "aloh".
         */
        return invertirString(cadena.substring(1)) + cadena.charAt(0);
    }

    public static void main(String[] args) {
        String[] casos = {
            "",
            "A",
            "hola",
            "estructuras",
            "reconocer",
            "Java 2026"
        };

        for (String cadena : casos) {
            long inicio = System.nanoTime();
            String invertida = invertirString(cadena);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println("\"" + cadena + "\" -> \"" + invertida
                    + "\" | Tiempo: " + tiempoTranscurrido + " ns");
        }
    }
}