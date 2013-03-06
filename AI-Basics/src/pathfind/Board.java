package pathfind;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;



@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener{

	private Timer timer;
    private Agent agent;
    private Agent goal;
    ArrayList<Point> path;
    private int BoardWidth,BoardHeight;

    public Board(int width,int height) {

        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        setSize(width, height);
        BoardWidth	= getSize().width;
        BoardHeight	= getSize().height;
        agent 		= new Agent(10,10,5);
        goal  		= new Agent(BoardWidth/2,BoardHeight-10,5);
        agent.setGridSize(BoardWidth, BoardHeight);
        goal.setGridSize(BoardWidth, BoardHeight);
        path		= new ArrayList<Point>();
        timer 		= new Timer(1, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        if(foundGoal()){
        	String msg = "Found Goal";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.black);
            g.setFont(small);
            g.drawString(msg,(BoardWidth-metr.stringWidth(msg))/2,BoardHeight/2);
        }
        else{
        	
	        g2d.setColor(Color.RED);//make goal red
	        g2d.drawOval(goal.getX(), goal.getY(), goal.getWidth(), goal.getHeight());
	        g2d.fillOval(goal.getX(), goal.getY(), goal.getWidth(), goal.getHeight()); 
        
            g2d.setColor(Color.green);
            for(int i=0;i<path.size();i++){
            	Point moves = (Point)path.get(i);
            	g2d.drawOval(moves.x, moves.y, goal.getWidth(), goal.getHeight());
                g2d.fillOval(moves.x, moves.y, goal.getWidth(), goal.getHeight());
            }
            
            g2d.setColor(Color.BLUE);//make agent blue
            g2d.drawOval(agent.getX(), agent.getY(), agent.getWidth(), agent.getHeight());
            g2d.fillOval(agent.getX(), agent.getY(), agent.getWidth(), agent.getHeight());
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    private boolean foundGoal() {
		return agent.getFrame().intersects(goal.getFrame());
	}


	public void actionPerformed(ActionEvent e) {
		//goal.move((goal.getX()+1)%BoardWidth, (goal.getY()+1)% BoardHeight);
		//goal.move((goal.getX()+1)%BoardWidth, goal.getY());
		//goal.move(goal.getX(), (goal.getY()+1)% BoardHeight);
        //boolean[][] grid = new boolean[BoardWidth][BoardHeight];
        
		//goal.setGoal(agent.getLocation());
        agent.setGoal(goal.getLocation());
        if(!foundGoal())
    	{
        	
        	//goal.moveTowardsGoal(new boolean[BoardWidth][BoardHeight],false);	
    		path.add(agent.getLocation());
    		agent.moveTowardsGoal(new boolean[BoardWidth][BoardHeight],false);
    	}
        repaint();  
		
    }

}
