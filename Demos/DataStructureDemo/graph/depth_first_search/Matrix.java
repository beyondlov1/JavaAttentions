package graph.depth_first_search;

/**
 * @author beyondlov1
 * @date 2018/12/05
 */
public class Matrix {

    private static boolean[] isVisited;

    public static void main(String[] args) {
        //邻接矩阵
        AdjMatrixVex[] vexes = new AdjMatrixVex[10];
        int[][] arcs = new int[vexes.length][vexes.length];
        for (int i = 0; i < 10; i++) {
            vexes[i] = new AdjMatrixVex(100-i);
        }

        for (int i = 0; i < vexes.length; i++) {
            for (int j = 0; j < vexes.length; j++) {
                if (j%2==0){
                    arcs[i][j] = 0;
                }else {
                    arcs[i][j] = 1;
                }
            }
        }

        MatrixGraph graph = new MatrixGraph(vexes,arcs);
        isVisited = new boolean[graph.vexs.length];
        depthFirstSearchTraverse(vexes,graph);
    }

    private static void depthFirstSearchTraverse(AdjMatrixVex[] vexes, MatrixGraph graph){
        for (int i = 0; i < vexes.length; i++) {
            if (!isVisited[i]){
                depthFirstSearch(graph,i);
            }
        }
    }

    private static void depthFirstSearch(MatrixGraph graph, int i) {
        isVisited[i] = true;
        AdjMatrixVex vex = graph.vexs[i];
        System.out.print(vex.getData()+" ");
        for (int j = 0; j < graph.arcs[i].length; j++) {
            if (graph.arcs[i][j] == 1){
                if (!isVisited[j]){
                    depthFirstSearch(graph,j);
                }
            }
        }
    }

}

class AdjMatrixVex {
    private Integer data;
    public AdjMatrixVex(Integer data){
        this.data = data;
    }
    Integer getData(){
        return data;
    }
}
class MatrixGraph {
    AdjMatrixVex[] vexs;
    int[][] arcs;

    public MatrixGraph(AdjMatrixVex[] vexs, int[][] arcs) {
        this.vexs = vexs;
        this.arcs = arcs;
    }
}