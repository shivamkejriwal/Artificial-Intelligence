package pathfind;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;



public class Agent {

    private int x,y;
    private int goalX,goalY;
    private int gridWidth,gridHeight;
    private Dimension dim;

    public Agent(int locX,int locY, int size) {
    	this.x = locX;
        this.y = locY;
        dim = new Dimension(size,size);
    }
    
    public Agent(int locX,int locY, int width, int height) {
    	this.x = locX;
        this.y = locY;
        dim = new Dimension(width,height);
    }

    public void setGoal(int x,int y){
    	goalX = x;
    	goalY = y;
    }
    
    public void setGoal(Point goal){
    	goalX = goal.getX();
    	goalY = goal.getY();
    }
    
    public Point getLocation(){
    	return new Point(x,y);
    }

    public void move(int x,int y) {
        this.x = x;
        this.y = y;
    }

    
    
   
	
	private Comparator<Point> getComparator(){
		
		 class SortByManhattanDistance implements Comparator<Point>{ 
				public int compare(Point a, Point b) {
					//System.out.println("Compare:"+a.toString()+","+b.toString());
					int distA = (int)Math.abs(a.x-goalX)+Math.abs(a.y-goalY);
					int distB = (int)Math.abs(b.x-goalX)+Math.abs(b.y-goalY);
					if(distA < distB)
			            return -1;
			        else if(distA > distB)
			            return 1;
			        else
			            return 0;
				}
			}
		
		return new SortByManhattanDistance();
		
	}
	
    public Stack<Point> moveTowardsGoal(boolean[][] grid, boolean returnPath){
    	gridHeight	= grid[0].length;
    	gridWidth	= grid.length;
       	Point Start	= new Point(x,y);
    	Point Goal	= new Point(goalX,goalY);
    	//System.out.println("inside pathfind:Start:"+Start.toString()+"Goal:"+Goal.toString());
    	PriorityQueue<Point> queue	= new PriorityQueue<Point>(11,getComparator());
    	HashMap<Point,Point> parent	= new HashMap<Point,Point>();
    	queue.offer(Start);
    	while(!queue.isEmpty()){
    		if(queue.peek() == null)break;
    		if(queue.peek().equals(Goal))break;
    		Point curr = (Point)queue.poll();
    		if(grid[curr.x][curr.y])break;
    		
    		grid[curr.x][curr.y]		= true;
    		ArrayList<Point> neighbors	= getNeighbors(curr);
    		for(Point neighbor : neighbors){
    			queue.offer(neighbor);
    			parent.put(neighbor, curr);
    		}
    	}
    	Point parentnNode = queue.poll();
    	Stack<Point>stack = new Stack<Point>();
    	while(parentnNode != null){
            if(!parentnNode.equals(Start))stack.push(parentnNode);
            parentnNode = parent.get(parentnNode);
        }
    	if(stack.isEmpty())return null;
    	if(!returnPath){
    		parentnNode	= stack.pop();
    		move(parentnNode.x,parentnNode.y);
    		return null;
    	}
    	return stack;
    }
    
    private ArrayList<Point> getNeighbors(Point curr) {
    	ArrayList<Point> neighbors = new ArrayList<Point>();
    	if(isValidMove(curr.x+1,curr.y))
    		neighbors.add(new Point(curr.x+1,curr.y));
		if(isValidMove(curr.x,curr.y+1))
			neighbors.add(new Point(curr.x,curr.y+1));
		if(isValidMove(curr.x-1,curr.y))
			neighbors.add(new Point(curr.x-1,curr.y));
		if(isValidMove(curr.x,curr.y-1))
			neighbors.add(new Point(curr.x,curr.y-1));
		return neighbors;
	}

    public boolean isValidMove(int xLoc,int yLoc){
		if(xLoc<0 || xLoc >= gridWidth)return false;
		if(yLoc<0 || yLoc >= gridHeight)return false;
		return true;
	}
    
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth(){
    	return (int)dim.getWidth();
    }
    
    public int getHeight(){
    	return (int)dim.getHeight();
    }
    
    public Dimension getDimension() {
        return dim;
    }
    
    public Shape getShape(boolean isRectangle){
    	if(isRectangle)
    		return new Rectangle(x,y,getWidth(),getHeight());
    	else
    		return new Ellipse2D.Double(x,y,getWidth(),getHeight());
    }
    
    public Rectangle getFrame(){
    	return new Rectangle(x,y,getWidth(),getHeight());
    }
    
    public boolean isCollision(int xLoc,int yLoc){
    	if(xLoc<x || xLoc>x+getWidth())return false;
    	if(yLoc<y || yLoc>y+getHeight())return false;
    	return true;
    }
}
