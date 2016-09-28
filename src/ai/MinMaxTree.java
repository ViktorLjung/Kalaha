/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD
/*
package ai;
import ai.AIClient.*;
import kalaha.*;
=======
package ai;

import kalaha.GameState;

>>>>>>> origin/master


class Node {
    Node[] m_Children;
    GameState m_State = null;
} 

public class MinMaxTree {
    private int m_depthLevel = 0;
    private Node m_RootNode;
    private int m_Player;
    
    public MinMaxTree(int depthLevel, GameState currentState, int player)
    {
        m_depthLevel = depthLevel;
        m_RootNode = new Node();
<<<<<<< HEAD
        m_RootNode.m_Children = new Node[6];
=======
>>>>>>> origin/master
        m_RootNode.m_State = currentState;
        m_Player = player;
        
        //Root node = First game state
        //Create 6 children with the possible moves
        //recursivly create new children with possible moves
        //When done go back and calculate the score.
    }
    
<<<<<<< HEAD
    public int SearchNextMove() 
    {
        int BestMove = -1;
                //System.out.println("New search for move -------");

        BestMove = EvaluateMove(5, m_RootNode);
        return BestMove;
    }
    
    int EvaluateMove(int level, Node node)
    {
        int BestScore = 0;
        int ReturnScore = 0;
        int BestMove = -1000000;
=======
    public int SearchNextMove(Node parent) 
    {
        int BestMove = -1;
        
        BestMove = EvaluateMove(5, parent);
        
        return BestMove;
    }
    
   public int EvaluateMove(int level, Node node)
    {
        int BestScore = 0;
        int ReturnScore = 0;
        int BestMove = -1;
>>>>>>> origin/master
        
        level--;
        for(int i = 0; i < 6; i++)
        {
<<<<<<< HEAD
            
            //Make sure the move we wanna try is valid
            if(!node.m_State.moveIsPossible(i+1)){
                continue;
            }
            
            //Create a new node where we make some move.
            Node cNode = new Node();
            cNode.m_Children = new Node[6];
            cNode.m_State = node.m_State.clone();
            cNode.m_State.makeMove(i+1);
            
            //Save the child so we can access it later.
            node.m_Children[i] = cNode;

            if(level > 0) {
                //Evaluate this childs nodes.
                //System.out.println("Level: " + level + "  -  Child: " + i );
                ReturnScore = EvaluateMove(level-1, node.m_Children[i]);
                if(BestScore < ReturnScore) {
                    BestScore = ReturnScore;
                    BestMove = i+1;
                }
            }
            else {
                System.out.println(node.m_Children[i].m_State.getScore(m_Player) + ":" + node.m_Children[i].m_State.getScore(2/m_Player));
                return node.m_Children[i].m_State.getScore(m_Player) - node.m_Children[i].m_State.getScore(2/m_Player);
            }
        }
        
        return BestMove;
    }
}
*/
=======
            //Make sure the move we wanna try is valid
            if(!node.m_State.moveIsPossible(i)){
                continue;
            }
            Node cNode = new Node();
            cNode.m_State = node.m_State.clone();
            cNode.m_State.makeMove(i);
            
            node.m_Children[i] = cNode;
            
            if(level > 0) {
                ReturnScore = EvaluateMove(level-1, node.m_Children[i]);
                //node.m_State.
            }
        }
        
        return BestScore;
    }
}
>>>>>>> origin/master
