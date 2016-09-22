/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import kalaha.GameState;



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
        m_RootNode.m_State = currentState;
        m_Player = player;
        
        //Root node = First game state
        //Create 6 children with the possible moves
        //recursivly create new children with possible moves
        //When done go back and calculate the score.
    }
    
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
        
        level--;
        for(int i = 0; i < 6; i++)
        {
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
