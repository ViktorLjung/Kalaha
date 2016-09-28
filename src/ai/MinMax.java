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
        if(depth < m_maxDepth) {
            RecursiveNodeSearch(player, depth+1, node);
        }
        System.out.println(depth);

        
        return depth;
    }
    
    // Returns the child node that gives the most score to the current player
    public Node GetBestMove(Node node) 
    {
        int bestScore = -100000;
        int bestIndex = -1;
        for(int i = 0; i < 6; i++) {
            int score = node.getScore(node.m_GameState.getNextPlayer());
            
            if(score > bestScore) {
                bestScore = score;
                bestIndex = i;
            }
        }
        
        return node.m_Children.elementAt(bestIndex);
    }
}
