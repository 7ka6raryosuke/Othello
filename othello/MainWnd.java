//MainWnd.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class MainWnd extends JFrame{
    
    MainWnd() {
        setTitle("オセロ");
        //windoweventのリスナーを追加
        addWindowListener(new AppCloser());
        //最小サイズの設定
        setMinimumSize(new Dimension(300,300));
        //スタートボタン
        JButton startButton = new JButton("始める");
        startButton.setPreferredSize(new Dimension(220,40));
        startButton.setHorizontalAlignment(JButton.CENTER);

        JLabel titlLabel = new JLabel("オセロ");
        titlLabel.setPreferredSize(new Dimension(220,40));
        titlLabel.setHorizontalAlignment(JLabel.CENTER);

        startButton.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent e){
                new Othello();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(titlLabel,BorderLayout.NORTH);
        panel.add(startButton,BorderLayout.CENTER);

        add(panel);
        
        //パックして表示
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWnd();
    }
}