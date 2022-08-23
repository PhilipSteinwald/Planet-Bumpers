package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.Car;

public class StarCollision extends Collision {
    public StarCollision(Car car1, Car car2) {
        super(car1, car2);
    }

	public boolean detectCollision() {
		Point2D p1 = getCar1().getPosition();
		Dimension2D d1 = getCar1().getSize();

		Point2D p2 = getCar2().getPosition();
		Dimension2D d2 = getCar2().getSize();

		double x = Math.abs((p1.getX() + d1.getWidth()/2)-(p2.getX() + d2.getWidth()/2));
		double y = Math.abs((p1.getY() + d1.getWidth()/2)-(p2.getY() + d2.getWidth()/2));
		double distance = Math.sqrt(x*x+y*y) - (d1.getWidth()-d2.getWidth())/10;

		return distance < d1.getWidth()/2+d2.getWidth()/2;
	}

	public Car evaluate() {
		Car winnerCar = null;
		if (this.compareTypes()) {
			winnerCar = this.getCar1();
		} else {
			winnerCar = this.getCar2();
		}
		return winnerCar;
	}

	private boolean compareTypes(){
		char t1 = this.getCar1().getType();
		char t2 = this.getCar2().getType();
		if(t1 == 'H'){ return true;}
		else if(t2 == 'H'){ return false;}
		else if(t1 == 'O'){ return true;}
		else if(t2 == 'O'){ return false;}
		else if(t1 == 'A'){ return true;}
		else if(t2 == 'A'){ return false;}
		else if(t1 == 'G'){ return true;}
		else if(t2 == 'G'){ return false;}
		else if(t1 == 'M'){ return true;}
		else{ return false;}
	}
}
