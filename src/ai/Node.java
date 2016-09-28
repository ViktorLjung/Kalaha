package ai;
import kalaha.GameState;
import java.util.*;

public class Node {
    Vector<Node> m_Children;
    GameState m_GameState;
    
    public Node() {
        m_Children = new Vector<Node>(6);
    }
    
    public void AddChild(Node child, int index) {
        m_Children.setElementAt(child, index);
    }
    
    public int getScore(int player) {
        return m_GameState.getScore(player);
    }
    
    public Node getChild(int index) {
        Node node = m_Children.get(index);
        
        if(node == null) {
            System.out.print("Child " + index + "is invalid");
        }
        
        return node;
    }
    
    
}
