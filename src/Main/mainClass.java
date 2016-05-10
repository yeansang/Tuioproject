package Main;

import TUIO.*;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.*;




public class mainClass extends JFrame implements TuioListener {
	
	
	private static final long serialVersionUID = 1L;
	
	int ScreenX = 640;
	int ScreenY = 480;
	
	int reX0 = 0;
	int reY0 = 0;
	int reX1 = 0;
	int reY1 = 0;
	int reX2 = 0;
	int reY2 = 0;
	int reX3 = 0;
	int reY3 = 0;
	int reX4 = 0;
	int reY4 = 0;
	
	int Ang0 = 0;
	int speed0 = 0;
	int Ang1 = 0;
	int speed1 = 0;
	
	float angX1 = 0;
	float angY1 = 0;
	int angX0 = 0;
	int angY0 = 0;
	
	int srtX0 = 0;
	int srtY0 = 0;
	
	int endX0 = 0;
	int endY0 = 0;
	
	long time0 = 0;
	long time1 = 0;
	
	boolean saveData = false;
	boolean strEnd = false;
	
	boolean right = false;
	boolean left = false;
	boolean go = false;
	boolean back = false;

	
	String out;
	
	JTextField jtextfiled = new JTextField();
	
	Main.Com commu0 = new Main.Com(1);
	//Main.Com commu1 = new Main.Com(2);
	
	
	
	
	
	/*class drawFinger(int x, int y) extends JFrame {
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.fillRect(x, y, 20, 20);
		}
	}
	*/
	public void update(Graphics g) {

	    paint(g);

	}
	
	
	
	

	public static void main(String[] args) throws IOException
	{
		
		
		mainClass tc = new mainClass();
		
		TuioClient client = new TuioClient();
		
		client.addTuioListener(tc);
		client.connect();
		
		
		

	}
	
	
	
	//---TUIO part
	@Override
	public void addTuioCursor(TuioCursor tcur)
	{
		//System.out.println("add "+tcur.getCursorID()+" "+tcur.getX()+" "+tcur.getY());
		if(tcur.getCursorID()==0){
			srtX0 = tcur.getScreenX(ScreenX);
			srtY0 = tcur.getScreenY(ScreenY);
			reX0 = tcur.getScreenX(ScreenX);
			reY0 = tcur.getScreenY(ScreenY);
			
		}
		
	}
	
	@Override
	public void updateTuioCursor(TuioCursor tcur)
	{
		
		if(tcur.getCursorID()==0){
			//jtextfiled.setText("(" + tcur.getX() + ", " + tcur.getY() + ")");
			reX0 = tcur.getScreenX(ScreenX);
			reY0 = tcur.getScreenY(ScreenY);
			//System.out.println("ID "+tcur.getSessionID());
			//System.out.println("acc "+tcur.getMotionAccel()+" speed "+tcur.getMotionSpeed());
			TuioTime t = tcur.getTuioTime();
			//System.out.println("frame "+t.getSeconds());
			System.out.println(""+reX0+" "+reY0);
			
			if(time0 < t.getSeconds()){
			
			Ang0 = (int)tcur.getAngleDegrees((float)angX0, (float)angY0) + 100;
			speed0 = (int)tcur.getMotionSpeed();
			//dataSave0.add((int)time0,Ang0,speed0);
			//commu0.sandmessege(out);
			
			//System.out.println(getAngle(angX0,angY0,tcur.getScreenX(ScreenX),tcur.getScreenY(ScreenY)));
			angX0 = tcur.getScreenX(ScreenX);
			angY0 = tcur.getScreenX(ScreenY);
			//System.out.println("===========cur 0");
			
			//System.out.println("speed "+tcur.getMotionSpeed());
			saveData = true;
			
			}
			time0 = t.getSeconds();
			
			//System.out.println("sec "+t.getSeconds()+"mil "+t.getMicroseconds());
			}
		if(tcur.getCursorID()==1){
			reX1 = tcur.getScreenX(ScreenX);
			reY1 = tcur.getScreenY(ScreenY);
			TuioTime t = tcur.getTuioTime();
			//System.out.println("frame "+t.getMicroseconds());
			if(time1 < t.getMicroseconds()){
			Ang1 = (int)tcur.getAngleDegrees((float)angX1, (float)angY1) + 100;
			speed1 = (int)tcur.getMotionSpeed();
			out = ""+speed1+Ang1;
			//commu1.sandmessege(out);
			angX1 = tcur.getX();
			angY1 = tcur.getY();
			//System.out.println("===========cur 1");
			//System.out.println("ang "+Ang1);
			//System.out.println("speed "+tcur.getMotionSpeed());
			}
			time1 = t.getMicroseconds();
			}
		if(tcur.getCursorID()==2){
			reX2 = tcur.getScreenX(ScreenX);
			reY2 = tcur.getScreenY(ScreenY);
			}
		if(tcur.getCursorID()==3){
			reX3 = tcur.getScreenX(ScreenX);
			reY3 = tcur.getScreenY(ScreenY);
			}
		if(tcur.getCursorID()==4){
			reX4 = tcur.getScreenX(ScreenX);
			reY4 = tcur.getScreenY(ScreenY);
			}
		/*
		//System.out.println("update "+tcur.getCursorID()+" "+tcur.getX()+" "+tcur.getY());
		ArrayList<TuioPoint> TuioCurList = tcur.getPath();
		for(int i=0;TuioCurList.size() > i; i++){
		TuioPoint t = TuioCurList.get(i);
		System.out.println("test "+t.getScreenX(ScreenX)+" "+t.getScreenY(480)+" "+i);
		}
		*/
		
	}
	
	@Override
	public void removeTuioCursor(TuioCursor tcur)
	{
		if(tcur.getCursorID()==0){
			endX0 = tcur.getScreenX(ScreenX);
			endY0 = tcur.getScreenY(ScreenY);
			speed0 = 0;
			strEnd = true;
			//commu0.sandmessege("2222");
			System.out.println(""+((float)srtX0-(float)endX0)+"   "+((float)srtY0-(float)endY0)+"  "+Math.abs(srtX0-endX0)+"  "+Math.abs(srtY0-endY0));
			
			if(((srtY0-endY0)>0)&&(Math.abs(srtX0-endX0)<Math.abs(srtY0-endY0))) 
			{
				go = true;
				back = false;
				right = false;
				left = false;
				commu0.sandmessege("1");
				System.out.println("..1"); //go
			}
			if(((srtY0-endY0)<0)&&(Math.abs(srtX0-endX0)<Math.abs(srtY0-endY0))) 
			{
				go = false;
				back = true;
				right = false;
				left = false;
				commu0.sandmessege("2");
				System.out.println("..2"); //back
			}
			if(((srtX0-endX0)>0)&&(Math.abs(srtX0-endX0)>Math.abs(srtY0-endY0))) 
			{
				go = false;
				back = false;
				right = true;
				left = false;
				commu0.sandmessege("4");
				System.out.println("..3"); //right
			}
			if(((srtX0-endX0)<0)&&(Math.abs(srtX0-endX0)>Math.abs(srtY0-endY0))) 
			{
				go = false;
				back = false;
				right = false;
				left = true;
				commu0.sandmessege("3");
				System.out.println("..4"); //left
			}
			
			
		}
		//System.out.println("remove "+tcur.getCursorID()+" "+tcur.getX()+" "+tcur.getY());
	}
	
	@Override
	public void addTuioObject(TuioObject tobj)
	{
		
	}
	
	@Override
	public void updateTuioObject(TuioObject tobj)
	{
		
	}
	
	@Override
	public void removeTuioObject(TuioObject tobj)
	{
		
	}
	
	@Override
	public void addTuioBlob(TuioBlob tblb)
	{
		
	}
	
	@Override
	public void updateTuioBlob(TuioBlob tblb)
	{
		
	}
	
	@Override
	public void removeTuioBlob(TuioBlob tblb)
	{
		
	}
	
	@Override
	public void refresh(TuioTime ftime)
	{
		repaint();

		//System.out.println("ang "+getAngle(reX0,reY0,reX1,reX1));
		
	}
	//---TUIO part end
	
	


	
	
	
	public mainClass()
	{
		
		
		this.add(jtextfiled, "South");
		
		this.setSize(ScreenX, ScreenY);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		System.out.println("update "+Cursor0.getCursorID()+" "+Cursor0.getX()+" "+Cursor0.getY());
		System.out.println("update "+Cursor1.getCursorID()+" "+Cursor1.getX()+" "+Cursor1.getY());
		System.out.println("update "+Cursor2.getCursorID()+" "+Cursor2.getX()+" "+Cursor2.getY());
		System.out.println("update "+Cursor3.getCursorID()+" "+Cursor3.getX()+" "+Cursor3.getY());
		System.out.println("update "+Cursor4.getCursorID()+" "+Cursor4.getX()+" "+Cursor4.getY());
		*/
	}
	 private static double getAngle(int x1,int y1, int x2,int y2){
		   int dx = x2 - x1;
		   int dy = y2 - y1;
		   
		   double rad= Math.atan2(dx, dy);
		   double degree = (rad*180)/Math.PI ;
		 
		   return degree;
		  }
	
	
	
	public void paint(Graphics g)
	{
		if(strEnd){
		g.clearRect(0, 0, ScreenX, ScreenY);
		strEnd = false;
		}
		g.setColor(null);
		
		g.fillRect(reX0, reY0, 20, 20);
		g.fillRect(reX1, reY1, 20, 20);
		g.fillRect(reX2, reY2, 20, 20);
		g.fillRect(reX3, reY3, 20, 20);
		g.fillRect(reX4, reY4, 20, 20);
		
		
	}
	
	
	
}
