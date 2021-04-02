import javax.swing.ImageIcon;

public class WhitePlayer extends Player{
    private int myColor = -1;
    private ImageIcon myIcon = whiteIcon;
    private ImageIcon yourIcon = blackIcon;

    public WhitePlayer(String name) {
        super(name);
        // TODO Auto-generated constructor stub
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