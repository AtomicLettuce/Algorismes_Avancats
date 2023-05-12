package P4;

import P4.Controlador.Controlador;
import P4.Interficies.InterficieComunicacio;
import P4.Model.Aresta;
import P4.Model.Graf;
import P4.Model.Node;
import P4.Model.SAX.MeuSax;
import P4.Vista.Vista;

public class Main implements InterficieComunicacio {
    private Vista vista;
    private Graf g;

    private Controlador controlador = new Controlador();
    public static boolean CONTINUAR=true;
    public static void main(String[] args) {
        new Main().inici();
    }


    public void inici() {
        //new Mesurament().mesura();
        g=new Graf();
        vista = new Vista("mondongo", this,g);
        MeuSax sax=new MeuSax("mapes/grafobase.ltim",this,g);
        sax.llegir();
        vista.repaint();


        //Exemple de com troba es cami minim
        /*Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        nodeA.ponArista(new Aresta(nodeB, 10));
        nodeA.ponArista(new Aresta(nodeC, 15));

        nodeB.ponArista(new Aresta(nodeD, 12));
        nodeB.ponArista(new Aresta(nodeF, 15));

        nodeC.ponArista(new Aresta(nodeE, 10));

        nodeD.ponArista(new Aresta(nodeE, 2));
        nodeD.ponArista(new Aresta(nodeF, 1));

        nodeF.ponArista(new Aresta(nodeE, 5));

        Graf graph = new Graf();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        Controlador controlador = new Controlador();
        graph = controlador.getCamino(nodeA, nodeE, graph);
        */
    }
    Node sortida;
    Node desti;
    @Override
    public void comunicacio(String instruccio) {

        switch (instruccio) {
            case "stop":
                System.out.println("Aturant...");
                // Aturar tots els fils i aturar el programa
                CONTINUAR = false;
                vista.actualitzar();
                break;
            case "Actualitzar":
                // Tornar a pintar la GUI
                vista.actualitzar();
                break;
            case "play":
                // Envia l'ordre de començar
                controlador.getCamino(g);
                System.out.println("");
                for(int i = 0; i<g.getCami().getNodes().size(); i++){
                    System.out.println(g.getCami().getNodes().get(i).toString());
                }
                vista.actualitzar();
                break;
            case "reset":
                // Envia l'ordre de reinici
                break;
        }
        // Format Origen:'n'
        // on n és índex de node
        if(instruccio.startsWith("Origen:")){
            int n = Integer.parseInt(instruccio.split(":")[1]);
            g.setInici(g.getNode(n));
            vista.actualitzar();

            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
        }
        // Format Desti:'n'
        // on n és índex de node
        else if(instruccio.startsWith("Desti:")){
            int n = Integer.parseInt(instruccio.split(":")[1]);
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]
            g.setDesti(g.getNode(n));
            vista.actualitzar();
            /*Graf graf = controlador.getCamino(g);
            for(int i = 0; i<graf.getNodes().size(); i++){
                System.out.println(graf.getNode(i).toString());
            }*/
        }  else if(instruccio.startsWith("Intermig:")){
            int n = Integer.parseInt(instruccio.split(":")[1]);
            Node node=g.getNode(n);
            g.addNodeIntermig(node);
            g.setIntermig(g.getNode(n));
            vista.actualitzar();
            // [IMPLEMENTAR][IMPLEMENTAR][IMPLEMENTAR]

        }

    }
}