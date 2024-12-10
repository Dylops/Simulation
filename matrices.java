package examples.behaviours;

public class OperacionesMatrices {
    public static double[][] transponer(double[][] matrix) {
        int filas = matrix.length;
        int columnas = matrix[0].length;
        double[][] transpuesta = new double[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                transpuesta[j][i] = matrix[i][j];
            }
        }
        return transpuesta;
    }

    public static double[][] multiplicar(double[][] matrix1, double[][] matrix2) {
        int filas1 = matrix1.length;
        int columnas1 = matrix1[0].length;
        int columnas2 = matrix2[0].length;
        double[][] result = new double[filas1][columnas2];

        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j < columnas2; j++) {
                for (int k = 0; k < columnas1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static double[] matrizVector(double[][] matrix, double[] vector) {
        int filas = matrix.length;
        double[] resultado = new double[filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < vector.length; j++) {
                resultado[i] += matrix[i][j] * vector[j];
            }
        }
        return resultado;
    }

    public static double[][] matrizInversa(double[][] matrix) {
        int n = matrix.length;
        double[][] matrizAumentada = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizAumentada[i][j] = matrix[i][j];
            }
            matrizAumentada[i][i + n] = 1;
        }

        for (int i = 0; i < n; i++) {
            double diagonal = matrizAumentada[i][i];
            for (int j = 0; j < 2 * n; j++) {
                matrizAumentada[i][j] /= diagonal;
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matrizAumentada[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        matrizAumentada[k][j] -= factor * matrizAumentada[i][j];
                    }
                }
            }
        }
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa[i][j] = matrizAumentada[i][j + n];
            }
        }

        return inversa;
    }
}

