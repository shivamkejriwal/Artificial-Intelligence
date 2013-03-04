package pathfind;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Pathfind extends JFrame{

	public Pathfind(int width,int height) {
        add(new Board(width,height));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, width);
        setLocationRelativeTo(null);
        setTitle("Path Find");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Pathfind(400,300);
    }
}
