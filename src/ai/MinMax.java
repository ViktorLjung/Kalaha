package ai;
import ai.Node.*;

public class MinMax {
    // Player är vilken spelarnummer vår bot har.
    private int m_Player;
    // Other Player
    private int m_OtherPlayer;
    
    // maxDepth är hur långt ner trädet kommer att söka.
    private int m_maxDepth;
    
    public MinMax(int player, int maxDepth)
    {
        // Först initializerar vi våra variabler.
        m_Player = player;
        m_OtherPlayer = 2 / player;
        m_maxDepth = maxDepth;
        
        //Skicka in våran node som håller vårat current board state till vår
        //Rekursiva funktion.
        int d = RecursiveNodeSearch(m_Player, m_maxDepth);
        System.out.println(d);
    }
    
    // Player är den spelare som skall göra moven från detta board state.
    // depth är vilken nivå i trädet vi söker på just nu.
    public int RecursiveNodeSearch(int player, int depth)
    {
        //Vi kallar sedan denna funktion så länge vi inte har kommit till botten.
        if(depth < m_maxDepth) {
            RecursiveNodeSearch(player, depth-1);
        }
        
        
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
