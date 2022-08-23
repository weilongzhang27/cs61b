public class NBody{

	public static String backgroundImg="./images/starfield.jpg";


	public static double readRadius(String s){
		In in = new In(s);
		int num_planet=in.readInt();
		double radius=in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String s){
		In in = new In(s);
		int num_planet=in.readInt();
		Body[] bodies = new Body[num_planet];
		double radius=in.readDouble();
		for(int i = 0;i<num_planet;i++){
			double xP=in.readDouble();
			double yP=in.readDouble();
			double xV=in.readDouble();
			double yV=in.readDouble();
			double m=in.readDouble();
			String img=in.readString();
			bodies[i]= new Body(xP,yP,xV,yV,m,img);
		}
		return bodies;
	}

	public static void main(String[] args){
		Double T=Double.parseDouble(args[0]);
		Double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		Double radius=readRadius(filename);
		Body[] bodies=readBodies(filename);
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();

		double universeTime=0.0;
		int num_planet=bodies.length;
		double[] xForces=new double[num_planet];
		double[] yForces=new double[num_planet];

		while(universeTime<=T){


			for(int i=0;i<num_planet;i++){
				double fX=bodies[i].calcNetForceExertedByX(bodies);
				double fY=bodies[i].calcNetForceExertedByY(bodies);
				xForces[i]=fX;
				yForces[i]=fY;
			}

			for(int i=0;i<num_planet;i++){
				bodies[i].update(dt,xForces[i],yForces[i]);
			}

			StdDraw.picture(0, 0, NBody.backgroundImg);
			
			for(Body b:bodies){
			b.Draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			universeTime+=dt;
		}
	}
}

