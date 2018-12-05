package graph.depth_first_search;

/**
 * @author beyondlov1
 * @date 2018/12/05
 */
public class Linked {

    private static boolean[] isVisited;

    public static void main(String[] args) {

        //生成gragh
        AdjLinkedVex[] linkedVexes = new AdjLinkedVex[10];
        for (int i = 0; i < linkedVexes.length; i++) {
            linkedVexes[i] = new AdjLinkedVex(i,null);
        }
        for (int i = 0; i < linkedVexes.length; i++) {
            for (int j = 0; j < linkedVexes.length; j++) {
                if (i!=j){
                    linkedVexes[i].add(new AdjLinkedEdge(j,null));
                }
            }
        }
        System.out.println("---测试是否生成成功---");
        //测试是否生成成功
        for (int i = 0; i < linkedVexes.length; i++) {
            AdjLinkedEdge edge = linkedVexes[i].firstEdge;
            while (edge!=null){
                System.out.print(edge.adjVexIndex+" ");
                edge = edge.next;
            }
            System.out.println();
        }
        LinkedGraph linkedGraph = new LinkedGraph(linkedVexes);

        //遍历graph
        System.out.println("---遍历graph---");
        isVisited = new boolean[linkedGraph.linkedVexes.length];
        depthFirstSearchTraverse(linkedGraph);
    }

    private static void depthFirstSearchTraverse(LinkedGraph graph){
        for (int i = 0; i < graph.linkedVexes.length; i++) {
            if (!isVisited[i]){
                depthFirstSearch(graph,i);
            }
        }
    }

    private static void depthFirstSearch(LinkedGraph graph,int i){
        isVisited[i] = true;
        System.out.print(graph.linkedVexes[i].data+" ");
        AdjLinkedEdge edge = graph.linkedVexes[i].firstEdge;
        for (int j = 0; j < graph.linkedVexes[i].size(); j++) {
            if (!isVisited[j]){
                depthFirstSearch(graph, j);
            }
            if (edge!=null){
                edge = edge.next;
            }else {
                break;
            }
        }
    }
}

class AdjLinkedVex {
    Integer data;
    AdjLinkedEdge firstEdge;
    private int size;
    public AdjLinkedVex(Integer data,AdjLinkedEdge firstEdge){
        this.data = data;
        this.firstEdge = firstEdge;
    }

    public void add(AdjLinkedEdge adjLinkedEdge){
        if (firstEdge == null){
            firstEdge = adjLinkedEdge;
        }else {
            getEndEdge(firstEdge).next = adjLinkedEdge;
        }
    }

    public int size(){
        if (firstEdge == null){
            return 0;
        }
        AdjLinkedEdge edge = firstEdge;
        while(edge.next!=null){
            size++;
            edge = edge.next;
        }
        size++;
        return size;
    }

    private AdjLinkedEdge getEndEdge(AdjLinkedEdge edge){
        if (edge.next!=null){
            return getEndEdge(edge.next);
        }else{
            return edge;
        }
    }
}
class AdjLinkedEdge {
    int adjVexIndex;
    AdjLinkedEdge next;
    public AdjLinkedEdge(int adjVexIndex, AdjLinkedEdge next){
        this.adjVexIndex = adjVexIndex;
        this.next = next;
    }
}
class LinkedGraph {
    AdjLinkedVex[] linkedVexes;
    public LinkedGraph(AdjLinkedVex[] linkedVexes){
        this.linkedVexes = linkedVexes;
    }
}

