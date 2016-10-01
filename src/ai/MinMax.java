package ai;
import ai.Node.*;
import ai.AIClient;
import kalaha.GameState;

public class MinMax {
    // Player är vilken spelarnummer vår bot har.
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
    
    public MinMax(int player, GameState currentBoardState)
    {
        // Först initializerar vi våra variabler.
        m_Player = player;
        m_OtherPlayer = 2 / player;
        m_RootNode = new ai.Node();
        m_RootNode.m_GameState = currentBoardState;
        
        //Skicka in våran node som håller vårat current board state till vår
        //Rekursiva funktion.
        
        //Test github :)
    }
    
    public int GetNextMove() {
      //  System.out.println("---------------------");
      //  System.out.println("Starting new tree");
      //  System.out.println("---------------------");
      
      //itterative deepening?
        startT = System.currentTimeMillis();
        int itDepth = 1;
        while(System.currentTimeMillis() - startT < 1000) {
            itDepth++;
            System.out.println("Depth: " + itDepth);
        
            RecursiveNodeSearch(m_Player, 0, itDepth, m_RootNode);
            
        }
        return m_BestMove;
    }
    
    
    
    // Player är den spelare som skall göra moven från detta board state.
    // depth är vilken nivå i trädet vi söker på just nu.
    public int RecursiveNodeSearch(int player, int depth, int maxDepth, Node node)
    {
        //Vi kallar sedan denna funktion så länge vi inte har kommit till vårt max depth.
        //Detta gör att vi kommer hamna längst ner i vårt träd, sedan börja rekusivt att ta det bästa resultatet här nerifrån.
       // System.out.println("Max: " + m_maxDepth + " - depth: " + depth);
        if(depth < maxDepth) {
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
                   // System.out.println("Possible move: " + i);
                    node.AddChild(child, i);
                }
                else {
                    //Annars skriver vi att det var ett illegal move.
                    //System.out.println("Illegal move: " + i + " at depth: " + depth);
                }
            }
            
            int bestScore = -777;
            // Initialize bestScore to the worst possible move for both players
            
            
            //Nu har vi gjort upp mot 6 nya child nodes.
            // Kalla denna rekursiva funktion igen för alla barnen.
            
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
                //System.out.println("Node: " + i + " Depth: " + depth + " Score: " + score);
                
            }
           // System.out.println("Best score: " + bestScore + " Best move: " + m_BestMove);
            return bestScore;
        }
        
        return node.getScore(m_Player);
    }
}
