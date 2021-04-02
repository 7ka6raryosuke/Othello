import javax.swing.ImageIcon;

public class BlackPlayer extends Player{
    private int myColor = 1;
    private ImageIcon myIcon = blackIcon;
    private ImageIcon yourIcon = whiteIcon;

    public BlackPlayer(String name) {
        super(name);
        // TODO Auto-generated
    }
    public int getMyColor(){
        return myColor;
    }

    public ImageIcon getMyIcon(){
        return this.myIcon;
    }

    public ImageIcon getYourIcon(){
        return this.yourIcon;
    }
}
