import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

final public class AppCloser extends WindowAdapter{

    @Override
    public void windowClosing(final WindowEvent e){
        System.exit(0);
    }
}
