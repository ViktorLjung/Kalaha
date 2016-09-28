package ai;
import kalaha.GameState;
import java.util.*;

public class Node {
    Vector<Node> m_Children;
    GameState m_GameState;
    
    public Node(Node parent) {
        m_Children = new Vector<Node>(6);
    }
    
    void AddChild(Node child, int index) {
        m_Children.setElementAt(child, index);
    }
}
