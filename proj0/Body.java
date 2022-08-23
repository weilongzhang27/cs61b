public class Body{
	private static final double G=6.67e-11;
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Body(Body b){
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}

	public double calcDistance(Body b){
		double dX=b.xxPos-this.xxPos;
		double dY=b.yyPos-this.yyPos;
		double r=Math.sqrt(dX*dX+dY*dY);
		return r;
	}

	public double calcForceExertedBy(Body b){
		double r=this.calcDistance(b);
		double force=G*this.mass*b.mass/(r*r);
		return force;
	}

	public double calcForceExertedByX(Body b){
		double dX=b.xxPos-this.xxPos;
		double r=this.calcDistance(b);
		//double dY=b.yyPos-this.yyPos;
		double force=this.calcForceExertedBy(b);
		double fX=force*dX/r;
		return fX;
	}

	public double calcForceExertedByY(Body b){
		double dY=b.yyPos-this.yyPos;
		double r=this.calcDistance(b);
		double force=this.calcForceExertedBy(b);
		double fY=force*dY/r;
		return fY;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double netfX=0;
		for(Body b:allBodys){
			if(this.equals(b)){
				continue;
			}else{
				netfX+=this.calcForceExertedByX(b);
			}
		}
		return netfX;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double netfY=0;
		for(Body b:allBodys){
			if(this.equals(b)){
				continue;
			}else{
				netfY+=this.calcForceExertedByY(b);
			}
		}
		return netfY;
	}

	public void update(double dt, double fX, double fY){
		this.xxVel+=dt*fX/this.mass;
		this.yyVel+=dt*fY/this.mass;
		this.xxPos+=this.xxVel*dt;
		this.yyPos+=this.yyVel*dt;
	}

	public void Draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "./image/"+this.imgFileName);
	}

}
