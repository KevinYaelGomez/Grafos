import java.util.*;

class ElGrafo {
    private int vertices; 
    private ArrayList<ArrayList<Integer>> array; 

    ElGrafo(int vertices) {
        this.vertices = vertices;
        array = new ArrayList<>();
        for (int i=0; i<vertices; ++i)
            array.add(new ArrayList<>());
    }

    void addAristas(int vectorI, int vectorF) {
        array.get(vectorI).add(vectorF);
        array.get(vectorF).add(vectorI); 
    }

    void printGrafo(int numGrafo) {
        System.out.println("Grafo " + numGrafo + ": ");
        int[][] matriz = new int[vertices][vertices];
        for (int i = 0; i < vertices; ++i) {
            for (int j = 0; j <vertices; j++) {
                if (array.get(i).contains(j)) {
                    matriz[i][j] = 1;
                } else {
                    matriz[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < vertices; i++){
            for (int j = 0; j <vertices; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ElGrafo[] grafo = new ElGrafo[5];
        grafo[0] = new ElGrafo(4);
        grafo[1] = new ElGrafo(4);
        grafo[2] = new ElGrafo(3);
        grafo[3] = new ElGrafo(4);
        grafo[4] = new ElGrafo(5);

        //Grafo 1
        grafo[0].addAristas(0, 1);
        grafo[0].addAristas(1, 2);
        grafo[0].addAristas(1, 3);
        grafo[0].addAristas(2, 3);
        grafo[0].addAristas(3, 3);

        //Grafo 2
        grafo[1].addAristas(0, 1);
        grafo[1].addAristas(1, 2);
        grafo[1].addAristas(2, 3);

        //Grafo 3
        grafo[2].addAristas(0, 1);
        grafo[2].addAristas(1, 2);
        grafo[2].addAristas(2, 0);

        //Grafo 4
        grafo[3].addAristas(0, 1);
        grafo[3].addAristas(0, 2);
        grafo[3].addAristas(0, 3);
        grafo[3].addAristas(2, 1);
        grafo[3].addAristas(1, 3);

        //Grafo 5
        grafo[4].addAristas(1, 0);
        grafo[4].addAristas(1, 2);
        grafo[4].addAristas(1, 3);
        grafo[4].addAristas(1, 4);
        grafo[4].addAristas(0, 3);
        grafo[4].addAristas(0, 4);
        grafo[4].addAristas(3, 2);
        grafo[4].addAristas(4, 2);

        for (int i = 0; i < grafo.length; i++){
            grafo[i].printGrafo(i);
        }
    }
}
