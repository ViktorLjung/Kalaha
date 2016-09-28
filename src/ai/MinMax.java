package ai;
import ai.AIClient.*;
import kalaha.*;


class Node {
    
    vector<Node> Children;
    
    GameState m_State = null;
}

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
        
    }
    
    // Player är den spelare som skall göra moven från detta board state.
    // depth är vilken nivå i trädet vi söker på just nu.
    public int RecursiveNodeSearch(int player, int depth)
    {
        
        return 
    }
}
