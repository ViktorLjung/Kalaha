package ai;
import ai.Node.*;
import ai.AIClient;
import kalaha.GameState;

public class MinMax {
    // Player är vilken spelarnummer vår bot har.
    private int m_Player;
    // Other Player
    private int m_OtherPlayer;
    
    // maxDepth är hur långt ner trädet kommer att söka.
    private int m_maxDepth;
    
    // Our first node, the node containing the board state when we first start 
    // searching for our move.
    private ai.Node m_RootNode;
    
    // Some variables to use when a finished state is reached.
    private int WIN = 1000000;
    private int LOSS = -1000000;
    
    public MinMax(int player, int maxDepth, GameState currentBoardState)
    {
        // Först initializerar vi våra variabler.
        m_Player = player;
        m_OtherPlayer = 2 / player;
        m_maxDepth = maxDepth;
        m_RootNode = new ai.Node();
        m_RootNode.m_GameState = currentBoardState;
        
        //Skicka in våran node som håller vårat current board state till vår
        //Rekursiva funktion.
        int d = RecursiveNodeSearch(m_Player, 0, m_RootNode);
        System.out.println(d);
    }
    
    // Player är den spelare som skall göra moven från detta board state.
    // depth är vilken nivå i trädet vi söker på just nu.
    public int RecursiveNodeSearch(int player, int depth, Node node)
    {
        //Vi kallar sedan denna funktion så länge vi inte har kommit till vårt max depth.
        //Detta gör att vi kommer hamna längst ner i vårt träd, sedan börja rekusivt att ta det bästa resultatet här nerifrån.
        System.out.println("Max: " + m_maxDepth + " - depth: " + depth);
        if(depth < m_maxDepth) {
            
            //Vi har ännu inte nått vårat sista barn, så gör 6 nya nodes som är olika moves som görs till våra nya barn.
            for(int i = 1; i <= 6; i++){
                // Skapa ett nytt barn som vi kan använda för att kolla om vårt nya move är bättre.
                Node child = new Node();
                //Clona över vårat current gamestate till vårt nya barn.
                child.m_GameState = node.m_GameState.clone();
                //Kolla om vårt move är möjligt.
                if(child.m_GameState.moveIsPossible(i)) {
                    // Om vårt teoretiska move är möjligt så gör det och adda detta som ett legitimt barn till vår parent node.
                    child.m_GameState.makeMove(i);
                    System.out.println("Possible move: " + i);
                    node.AddChild(child, i);
                }
                else {
                    //Annars skriver vi att det var ett illegal move.
                    System.out.println("Illegal move: " + i);
                }
            }
            //Nu har vi gjort upp mot 6 nya child nodes.
            // Kalla denna rekursiva funktion igen för alla barnen.
        }
        
        // När vi är på vårt maxDepth, alltså sista nivån i trädet som vi skall söka till
        // så skall vi kolla hur bra detta board state är och om det är det bästa skicka vidare resultaten.
        
        System.out.println(depth);

        
        return depth;
    }
    
    // Returns the child node that gives the most score to the current player
    public int GetBestChild(Node node) 
    {
        int bestScore = -LOSS;
        for(int i = 0; i < 6; i++) {
            if(node.getChild(i) != null) {
             int score = node.getScore(node.getChild(i).getScore(node.m_GameState.getNextPlayer()));
            
            if(score > bestScore) {
                bestScore = score;
                }
            }
        }
        return bestScore;
    }
}
