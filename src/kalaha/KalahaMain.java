package kalaha;

import server.*;
import client.*;
import ai.*;

/**
 * Start point for the Kalaha application.
 * 
 * @author Johan Hagelbäck
 */
public class KalahaMain
{

    /**
     * Version number.
     */
    public static String VERSION = "1.6";
    
    /**
     * Default port to start server at.
     */
    public static int port = 10101;
    
    /**
     * Starts the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        KalahaMain main = new KalahaMain();
    }
    
    /**
     * Starts the Kalaha GUI and sserver.
     */
    public KalahaMain()
    {
        try
        {
            KalahaServer server = KalahaServer.getInstance();
            server.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
