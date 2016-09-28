package ai;
import ai.Node.*;
import ai.AIClient;

public class MinMax {
    // Player är vilken spelarnummer vår bot har.
    private int m_Player;
    // maxDepth är hur långt ner trädet kommer att söka.
    private int m_maxDepth;
    
    public MinMax(int player, int maxDepth)
    {
        // Först initializerar vi våra variabler.
        m_Player = player;
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
               System.out.println(depth);

        
        return depth;
    }
}
