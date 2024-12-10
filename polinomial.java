package examples.behaviours;

import examples.behaviours.OperacionesMatrices;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class PolynomialRegressionAgent extends Agent {

    private double[] x;
    private double[] y;

    protected void setup() {
        dataset();

        addBehaviour(new OneShotBehaviour() {
            public void action() {

                System.out.println("Modelo Lineal:");
                double[] betaLineal = fitPolynomial(1);
                Modelo(betaLineal);

                System.out.println("Modelo Cuadrático:");
                double[] betaCuadratico = fitPolynomial(2);
                Modelo(betaCuadratico);

                System.out.println("Modelo Cúbico:");
                double[] betaCubico = fitPolynomial(3);
                Modelo(betaCubico);
            }
        });
    }


    private void dataset() {
        this.x = new double[]{108, 115, 106, 97, 95, 91, 97, 83, 83, 78, 54, 73, 67, 56, 53, 61, 115, 81, 78, 30, 45, 99, 32, 25, 28, 90, 89};
        this.y = new double[]{95, 96, 95, 97, 93, 94, 95, 93, 92, 86, 73, 67, 80, 65, 69, 77, 96, 87, 89, 60, 63, 95, 61, 55, 56, 94, 93};
    }

    private double[] fitPolynomial(int grado) {
        int n = x.length;
        int m = grado + 1;
        double[][] X = new double[n][m];
        double[] Y = y.clone();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                X[i][j] = Math.pow(x[i], j);
            }
        }

        double[][] XtX = OperacionesMatrices.multiplicar(OperacionesMatrices.transponer(X), X);
        double[] Xty = OperacionesMatrices.matrizVector(OperacionesMatrices.transponer(X), Y);

        double [][] XtXInversa = OperacionesMatrices.matrizInversa(XtX);

        return OperacionesMatrices.matrizVector(XtXInversa, Xty);
    }

    private void Modelo (double[] beta) {
        StringBuilder ecuacion = new StringBuilder("y = ");
        for (int i = 0; i < beta.length; i++) {
            if (i > 0) ecuacion.append(" + ");
            ecuacion.append(beta[i]).append(" * x^").append(i);
        }
        System.out.println(ecuacion.toString());
    }
}
