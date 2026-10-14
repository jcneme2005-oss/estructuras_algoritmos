import java.util.Arrays;

public class ejercicio2_1 {
    public static boolean contains(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (int value : arr) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] vector = {5, 3, 9, -2, 7}; // ejemplo de vector
        int buscar = 3; // variable que contiene el valor a buscar

        System.out.println("Vector: " + Arrays.toString(vector));
        boolean encontrado = contains(vector, buscar);

        if (encontrado) {
            System.out.println("Encontrado: " + buscar);
        } else {
            System.out.println("No encontrado: " + buscar);
        }
    }
}