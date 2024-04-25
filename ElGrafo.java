import java.util.*;

class ElGrafo {
    private int vertices; 
    private ArrayList<ArrayList<int[]>> array; 

    ElGrafo(int vertices) {
        this.vertices = vertices;
        array = new ArrayList<>();
        for (int i=0; i<vertices; ++i)
            array.add(new ArrayList<>());
    }

    void addAristas(int vectorI, int vectorF, int peso) {
        array.get(vectorI).add(new int[]{vectorF, peso});
        array.get(vectorF).add(new int[]{vectorI, peso}); 
    }

    void printGrafo(int numGrafo) {
        System.out.println("Grafo " + numGrafo + ": ");
        int[][] matriz = new int[vertices][vertices];
        for (int i = 0; i < vertices; ++i) {
            for (int j = 0; j <vertices; j++) {
                matriz[i][j] = 0;  
            }
        }

        for(int i = 0; i < vertices; i++){
            for(int[] adyacente : array.get(i)){
                matriz[i][adyacente[0]] = adyacente[1];
            }
        }

        for (int i = 0; i < vertices; i++){
            for (int j = 0; j <vertices; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        int inicio = 0;
        int fin = 2;
        int pesoMenosPesado = recorridoPeso(inicio, fin);
        System.out.println("El camino menos pesado entre el vértice " + inicio + " y el vértice " + fin + " es de peso: " + pesoMenosPesado);

        System.out.println();
    }

    int recorridoPeso(int inicio, int fin) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{inicio, 0});

        boolean[] visitado = new boolean[vertices];
        int[] distancias = new int[vertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[inicio] = 0;

        while (!pq.isEmpty()) {
            int[] nodoPeso = pq.poll();
            int nodo = nodoPeso[0];
            int peso = nodoPeso[1];
            if (visitado[nodo]) continue;
            visitado[nodo] = true;

            for (int[] vecino : array.get(nodo)) {
                int v = vecino[0];
                int pesoVecino = vecino[1];
                if (!visitado[v] && distancias[nodo] + pesoVecino < distancias[v]) {
                    distancias[v] = distancias[nodo] + pesoVecino;
                    pq.offer(new int[]{v, distancias[v]});
                }
            }
        }

        return distancias[fin];
    }

    public static void main(String[] args) {
        ElGrafo[] grafo = new ElGrafo[5];
        grafo[0] = new ElGrafo(4);
        grafo[1] = new ElGrafo(4);
        grafo[2] = new ElGrafo(3);
        grafo[3] = new ElGrafo(4);
        grafo[4] = new ElGrafo(5);

        //Grafo 1
        grafo[0].addAristas(0, 1, 7);
        grafo[0].addAristas(1, 2, 4);
        grafo[0].addAristas(1, 3, 1);
        grafo[0].addAristas(2, 3, 3);
        grafo[0].addAristas(3, 3, 3);

        //Grafo 2
        grafo[1].addAristas(0, 1, 1);
        grafo[1].addAristas(1, 2, 1);
        grafo[1].addAristas(2, 3, 5);

        //Grafo 3
        grafo[2].addAristas(0, 1, 8);
        grafo[2].addAristas(1, 2, 5);
        grafo[2].addAristas(2, 0, 3);

        //Grafo 4
        grafo[3].addAristas(0, 1, 10);
        grafo[3].addAristas(0, 2, 10);
        grafo[3].addAristas(0, 3, 3);
        grafo[3].addAristas(2, 1, 8);
        grafo[3].addAristas(1, 3, 3);

        //Grafo 5
        grafo[4].addAristas(1, 0, 6);
        grafo[4].addAristas(1, 2, 1);
        grafo[4].addAristas(1, 3, 11);
        grafo[4].addAristas(1, 4, 7);
        grafo[4].addAristas(0, 3, 2);
        grafo[4].addAristas(0, 4, 8);
        grafo[4].addAristas(3, 2, 2);
        grafo[4].addAristas(4, 2, 1);

        for (int i = 0; i < grafo.length; i++){
            grafo[i].printGrafo(i);
        }
    }
}
