package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import java.util.Random;

public class AlgoritmoGeneticoAgent extends Agent {
    private static final int TAMANO_POBLACION = 20;
    private static final int NUMERO_GENERACIONES = 100;
    private static final float TASA_MUTACION = 0.1f;

    private Random rand = new Random();

    @Override
    protected void setup() {
        System.out.println("Agente " + getLocalName() + " iniciado.");
        addBehaviour(new AlgoritmoGeneticoBehaviour());
    }

    private class AlgoritmoGeneticoBehaviour extends Behaviour {
        private boolean terminado = false;

        @Override
        public void action() {

            float[] x = {1, 2, 3, 4, 5};
            float[] y = {2, 4, 6, 8, 10};

            Individuo[] poblacion = new Individuo[TAMANO_POBLACION];
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                poblacion[i] = new Individuo();
            }

            Individuo mejor = poblacion[0];

            for (int generacion = 0; generacion < NUMERO_GENERACIONES; generacion++) {
                for (Individuo ind : poblacion) {
                    ind.calcularFitness(x, y);
                }

                for (Individuo ind : poblacion) {
                    if (ind.fitness > mejor.fitness) {
                        mejor = ind;
                    }
                }

                Individuo[] nuevaPoblacion = new Individuo[TAMANO_POBLACION];
                for (int i = 0; i < TAMANO_POBLACION; i++) {
                    Individuo padre1 = poblacion[rand.nextInt(TAMANO_POBLACION)];
                    Individuo padre2 = poblacion[rand.nextInt(TAMANO_POBLACION)];
                    Individuo hijo = Individuo.crossover(padre1, padre2);
                    hijo.mutar(TASA_MUTACION);
                    nuevaPoblacion[i] = hijo;
                }
                poblacion = nuevaPoblacion;

                System.out.println("Generación " + generacion + " Mejor MSE: " + (-mejor.fitness));
            }

            System.out.println("Mejor individuo encontrado: b0 = " + mejor.b0 + ", b1 = " + mejor.b1 + ", Fitness = " + (-mejor.fitness));
            terminado = true;
        }

        @Override
        public boolean done() {
            return terminado;
        }
    }
}
