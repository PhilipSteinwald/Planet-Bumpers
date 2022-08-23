package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.Car;

public class StarCollision2 extends Collision {
    public StarCollision2(Car car1, Car car2) {
        super(car1, car2);
    }

	public boolean detectCollision() {
		Point2D p1 = getCar1().getPosition();
		Dimension2D d1 = getCar1().getSize();

		Point2D p2 = getCar2().getPosition();
		Dimension2D d2 = getCar2().getSize();

		double x = Math.abs((p1.getX() + d1.getWidth()/2)-(p2.getX() + d2.getWidth()/2));
		double y = Math.abs((p1.getY() + d1.getWidth()/2)-(p2.getY() + d2.getWidth()/2));
		double distance = Math.sqrt(x*x+y*y);

		return distance < d1.getWidth()/2+d2.getWidth()/2;
	}

	public Car evaluate() {

		Point2D p1 = getCar1().getPosition();
		Point2D p2 = getCar2().getPosition();

		Car winnerCar = null;
		if (p1.getX() < p2.getX()) {
			winnerCar = this.getCar2();
		} else {
			winnerCar = this.getCar1();
		}
		return winnerCar;
	}
}
