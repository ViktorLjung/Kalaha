package ai;
import ai.Node.*;
import ai.AIClient;
import kalaha.GameState;

public class MinMax {
    // Player, the player number of the AI-bot
    private int m_Player;
    // Other Player
    private int m_OtherPlayer;
    
    // Our first node, the node containing the board state when we first start 
    // searching for our move.
    private ai.Node m_RootNode;
    
    // Some variables to use when a finished state is reached.
    private int WIN = 1000000;
    private int LOSS = -1000000;
    
    //Initialize bestMove to an invalid move
    public int m_BestMove = -1;
    long startT;
    int MaxTime = 5000;
    
    boolean aborted = false;
    
    public MinMax(int player, GameState currentBoardState)
    {
        //First we initialize our variables
        m_Player = player;
        m_OtherPlayer = 2 / player;
        m_RootNode = new ai.Node();
        m_RootNode.m_GameState = currentBoardState;
    }
    
    public int GetNextMove() {
      //itterative deepening
        startT = System.currentTimeMillis();
        int itDepth = 1;
        
        int bestmove = -1;
        
        while(System.currentTimeMillis() - startT < MaxTime) {
            //If we have time left, increese the mamimum depth and search again
            itDepth++;
            System.out.println("Depth: " + itDepth);
            RecursiveNodeSearch(m_Player, 0, itDepth, m_RootNode);
            
            if(!aborted) {
                //If the search was not aborted we have a new best move
                bestmove = m_BestMove;
            }
        }
        return bestmove;
    }
    
    // Player, is the current player for this node
    // depth, how deep we har in the tree
    // maxDepth, the maximum depth we can go until we return
    // node, the current node
    public int RecursiveNodeSearch(int player, int depth, int maxDepth, Node node)
    {
        if(System.currentTimeMillis() - startT >= MaxTime)
        {
            aborted = true;
            return 0;
        }
        
        //We keep on calling this recursive method as long as we haven't reached the maximum depth
        //This makes it so that we start returning when we reach the bottom, aka depth first
        if(depth < maxDepth) {
            //If maxDepth has not been reached, expand the node tree
            for(int i = 1; i <= 6; i++){
                //Create a new child
                Node child = new Node();
                //Clone the gamestate to the new child
                child.m_GameState = node.m_GameState.clone();
                //Check if the move is possible
                if(child.m_GameState.moveIsPossible(i)) {
                    //If out theoretical move is possible, do it and add this as a legitimate child for the parent node
                    child.m_GameState.makeMove(i);
                    node.AddChild(child, i);
                }
            }
            
            int bestScore = -777;
            // Initialize bestScore to the worst possible move for both players
            
            //Now we have made up to 6 new child nodes
            // Call the recursive method again for all the valid children
            
            boolean firstSet = false;
            for(int i = 1; i <= 6; i++) {
                if(node.getChild(i) == null){
                    continue;
                }
                
                if(!firstSet) {
                    bestScore = RecursiveNodeSearch(node.getChild(i).m_GameState.getNextPlayer(), depth + 1, maxDepth, node.getChild(i));
                    
                    if(node == m_RootNode) {
                            m_BestMove = i;
                        }
                    firstSet = true;
                    continue;
                }
                
                int score = RecursiveNodeSearch(node.getChild(i).m_GameState.getNextPlayer(), depth + 1, maxDepth, node.getChild(i));
                if(System.currentTimeMillis() - startT >= MaxTime)
                {
                    aborted = true;
                    return 0;
                }
                
                if(m_Player == player) {
                    if(score > bestScore) {
                        bestScore = score;
                        
                        if(node == m_RootNode) {
                            m_BestMove = i;
                        }
                    }
                } else { // other player wants negative score
                    if(score < bestScore) {
                        bestScore = score;
                        if(node == m_RootNode) {
                            m_BestMove = i;
                        }
                    }
                }
            }
            return bestScore;
        }
        
        return node.getScore(m_Player);
    }
}
