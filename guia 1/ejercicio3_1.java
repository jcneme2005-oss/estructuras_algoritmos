import java.util.Objects;

public class ejercicio3_1 {
    public static int buscarBinario(int[] datosOrdenados, int valorBuscado) {
        Objects.requireNonNull(datosOrdenados, "datosOrdenados no puede ser null");
        if (datosOrdenados.length == 0) {
            return -1;
        }

        int bajo = 0;
        int alto = datosOrdenados.length - 1;

        while (bajo <= alto) {
            int medio = bajo + (alto - bajo) / 2;
            System.out.printf("bajo=%d(%d) alto=%d(%d) medio=%d(%d)%n",
                    bajo, datosOrdenados[bajo],
                    alto, datosOrdenados[alto],
                    medio, datosOrdenados[medio]);

            if (datosOrdenados[medio] == valorBuscado) {
                return medio;
            } else if (valorBuscado < datosOrdenados[medio]) {
                alto = medio - 1;
            } else {
                bajo = medio + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] datos = {3, 7, 15, 22, 45, 63, 78, 91};

        System.out.println("Caso: buscar 45");
        int res45 = buscarBinario(datos, 45);
        System.out.println("Resultado: " + res45);
        System.out.println();

        System.out.println("Caso: buscar 50");
        int res50 = buscarBinario(datos, 50);
        System.out.println("Resultado: " + res50);
    }
}



