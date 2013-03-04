package pathfind;



public class Point {
	public int x,y;

	public Point(){
		this.x=0;
		this.y=0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Point other){
		if(other == null)return false;
		return (x == other.x && y == other.y);
	}
	
	public boolean isValid(int width,int height){
		if(x<0 || x>width)return false;
		if(y<0 || y>height)return false;
		return true;
	}
	
	public double compare(Point a, Point b){
		return Math.pow(((b.x-a.x)^2)+((b.y-a.y)^2), 1/2);
	}

	@Override
	public String toString() {
		return "(X:"+x+", Y:"+y+")";
	}
	
	/*
	public static class SortByManhattanDistance implements Comparator<Point>{ 
		public int compare(Point a, Point b) { 
			return (int)Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
		} 
	}
	
	public static Comparator<Point> getComparator(Point start, Point goal){
		return new SortByManhattanDistance();
		
	}
	*/
}
