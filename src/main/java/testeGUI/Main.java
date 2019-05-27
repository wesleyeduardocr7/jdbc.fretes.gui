
package testeGUI;

/**
 *
 * @author WtiDev
 */
public class Main {
        
    public static void main(String[] args) {
        
        Splash splash = new Splash(3000);
        splash.showSplashAndExit();
        GUI gui = new GUI();
        gui.setVisible(true);
        
    }
        
}
