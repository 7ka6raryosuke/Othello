import java.util.stream.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Image;


public class Othello extends JFrame implements ActionListener{
    //フィールド
    //ボタン8x8
    private JButton[][] buttonArray = new JButton[8][8];
    int banmen[][] =  // 盤面の石の色(1:黒:1-白:0:なし)
        {{0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, -1, 0, 0, 0},
	    {0, 0, 0, -1, 1, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0}};
    //ボタンのIcon
    ImageIcon boardIcon, blackIcon, whiteIcon;
    private int turn=-1;//ターンを入れ替える
    //プレイヤーのインスタンスを作成
    Player blackPlayer = new BlackPlayer("黒");
    Player whitePlayer = new WhitePlayer("白");
    JLabel lbl = new JLabel("白の番です");

    //オセロメソッド
    Othello() {
        setTitle("オセロ");
        addWindowListener(new AppCloser());
        setPreferredSize(new Dimension(500,540));
        
        JPanel mainPanel = new JPanel();
        JPanel panel = new JPanel();
        JPanel myturn = new JPanel();
        myturn.setBackground(Color.MAGENTA);
        JButton pass = new JButton("pass");
        pass.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent e){
                changeTurn();
            }
        });
        myturn.add(lbl);
        myturn.add(pass);
        ImageIcon boardIcon = new ImageIcon("board.jpg");
        Image img = boardIcon.getImage();
        Image newBoard = img.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newBoardIcon = new ImageIcon(newBoard);

        //8x8の盤面をボタンで作成
        this.buttonArray = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttonArray[i][j] = new JButton(newBoardIcon);
                buttonArray[i][j].setPreferredSize(new Dimension(55,55));;
                buttonArray[i][j].setBounds(i * 45, j * 45, 45, 45);
                int theBnum = i*8+j;
                buttonArray[i][j].setActionCommand(Integer.toString(theBnum));
                buttonArray[i][j].addActionListener(this);
                panel.add(buttonArray[i][j]);
            }
        }
        
        //初期配置にオセロを配置
        buttonArray[3][3].setIcon(blackPlayer.getMyIcon());
        buttonArray[3][4].setIcon(whitePlayer.getMyIcon());
        buttonArray[4][3].setIcon(whitePlayer.getMyIcon());
        buttonArray[4][4].setIcon(blackPlayer.getMyIcon());
        
        add(panel);
        add(myturn,BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Othello();
    }

    //ターンを変える
    public void changeTurn(){
        turn = -1*turn;
        if(turn==1){
            lbl.setText("黒の番です");
        }else{
            lbl.setText("白の番です");
        }
    }

    //ボタンがクリックされたときの処理インナークラス
    public void actionPerformed(ActionEvent e){
        JButton theButton = (JButton) e.getSource();
        String str = theButton.getActionCommand();
        int arrayIndex = Integer.parseInt(str);
        int x = arrayIndex%8;
        int y = arrayIndex/8;
        if(banmen[y][x]==0){
            if(turn==blackPlayer.getMyColor()){
                if(JudgeButton(x, y,blackPlayer)){
                    theButton.setIcon(blackPlayer.getMyIcon());
                    banmen[y][x]=blackPlayer.getMyColor();
                    changeTurn();
                }
            }else{
                if(JudgeButton(x,y,whitePlayer)){
                    theButton.setIcon(whitePlayer.getMyIcon());
                    banmen[y][x]=whitePlayer.getMyColor();
                    changeTurn();
                }
            }
        }
        judgeWin();  
    }
    

    //石を置くことができるか判定(戻り値はboolean, 引数に　ボタンのx,yの値)
    boolean JudgeButton(int x, int y,Player xPlayer){
        boolean flag = false;
        for(int i = -1;i<=1;i++){
            for(int j = -1;j<=1;j++){
                if(x+i<8&&x+i>=0&&y+j<8&&y+j>=0){
                    int flipCount = flipNum(y, x, j, i);
                    if(flipCount>0){
                            for(int dy=j, dx=i, k=0; k<flipCount; dy+=j, dx+=i,k++){                               
                                banmen[y+dy][x+dx]=xPlayer.getMyColor();
                                buttonArray[y+dy][x+dx].setIcon(xPlayer.getMyIcon());
                                System.out.println(k);
                            }
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }
    
    //ひっくり返すことができる石の数をカウントする
    int flipNum(int y, int x, int j, int i){
        int count = 0;
        int dx;
        int dy;
        for(dx = i, dy = j;;dx+=i,dy+=j){
            if(x+dx<8&&x+dx>=0&&y+dy<8&&y+dy>=0){
                if(banmen[y+dy][x+dx]==turn){
                    break;
                }else if(banmen[y+dy][x+dx]==turn*-1){
                    count++;
                }else{
                    count=0;
                    break;
                }
            }else{
                count = 0;
                break;
            } 
        }   
        return count;
    }

    //盤面が全て埋まった時に勝敗をlblに表示する(機能してない)
    void judgeWin(){
        int blackCount = 0;
        int whiteCount = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(buttonArray[i][j].equals(whitePlayer.getMyIcon())){
                    whiteCount+=1;
                    System.out.println(whiteCount);
                }else if(buttonArray[i][j].equals(blackPlayer.getMyIcon())){
                    blackCount+=1;
                    System.out.println(blackCount);
                }else{
                    whiteCount=0;
                    blackCount=0;
                    return;
                }
            }
        }
        if(blackCount>whiteCount){
            lbl.setText("黒の勝ち");
        }else if(whiteCount>blackCount){
            lbl.setText("白の勝ち");
        }else{
            lbl.setText("引き分け");
        }
    }
}  


