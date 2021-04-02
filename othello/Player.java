import javax.swing.ImageIcon;
import java.awt.Image;

public class Player{
    private String name;
    //ImageIconのサイズを調整
    ImageIcon ablackIcon = new ImageIcon("black.jpg");
    Image img = ablackIcon.getImage();
    Image newBlack = img.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
    ImageIcon awhiteIcon = new ImageIcon("white.jpg");
    Image image = awhiteIcon.getImage();
    Image newWhite = image.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
    //サイズ調整されたIcon
    ImageIcon blackIcon = new ImageIcon(newBlack);
    ImageIcon whiteIcon = new ImageIcon(newWhite);
    private int myColor;
    private ImageIcon myIcon;
    private ImageIcon yourIcon;

    public Player(String name){
        this.name = name;
    }

    public int getMyColor(){
        return this.myColor;
    }

    public ImageIcon getMyIcon(){
        return this.myIcon;
    }
    public ImageIcon getYourIcon(){
        return this.yourIcon;
    }

}
