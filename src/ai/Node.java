package ai;
import kalaha.GameState;
import java.util.*;

public class Node {
    Vector<Node> m_Children;
    GameState m_GameState;
    
    public Node() {
        m_Children = new Vector<Node>(6);
        for(int i = 0; i < 6; i++) {
            m_Children.add(null);
        }
        
    }
    
    public void AddChild(Node child, int index) {
        m_Children.setElementAt(child, index-1);
    }
    
    public int getScore(int player) {
        return m_GameState.getScore(player);
    }
    
    public Node getChild(int index) {
        Node node = m_Children.get(index-1);
        
        if(node == null) {
            //System.out.print("Child " + index + " is invalid.");
        }
        
        return node;
    }
    
    
}
