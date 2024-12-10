package examples.behaviours;

import java.util.Random;

public class Individuo {
    public float b0;
    public float b1;
    public float fitness;

    private static Random rand = new Random();


    public Individuo() {
        this.b0 = rand.nextFloat() * 10 - 5;
        this.b1 = rand.nextFloat() * 10 - 5;
    }

    public Individuo(float b0, float b1) {
        this.b0 = b0;
        this.b1 = b1;
    }

    public float calcularFitness(float[] x, float[] y) {
        float mse = 0;
        for (int i = 0; i < x.length; i++) {
            float predicted = b0 + b1 * x[i];
            mse += Math.pow(predicted - y[i], 2);
        }
        this.fitness = -mse / x.length;
        return this.fitness;
    }

    public static Individuo crossover(Individuo padre1, Individuo padre2) {
        float nuevoB0 = (padre1.b0 + padre2.b0) / 2;
        float nuevoB1 = (padre1.b1 + padre2.b1) / 2;
        return new Individuo(nuevoB0, nuevoB1);
    }

    public void mutar(float tasaMutacion) {
        if (rand.nextFloat() < tasaMutacion) {
            this.b0 += rand.nextFloat() * 2 - 1;
        }
        if (rand.nextFloat() < tasaMutacion) {
            this.b1 += rand.nextFloat() * 2 - 1;
        }
    }
}
