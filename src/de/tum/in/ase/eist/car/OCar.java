package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;

public class OCar extends Car {

	private static final String CAR_IMAGE_FILE = "OCar.png";

	private static final int MIN_SPEED_CAR = 2;
	private static final int MAX_SPEED_CAR = 2;

	public OCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'O');
		this.setSize(new Dimension2D(130,130));
		setMinSpeed(MIN_SPEED_CAR);
		setMaxSpeed(MAX_SPEED_CAR);
		setRandomSpeed();
		setIconLocation(CAR_IMAGE_FILE);
	}

	public OCar(Point2D position, int direction) {
		super(position,direction,'O');
		this.setSize(new Dimension2D(130,130));
		setMinSpeed(MIN_SPEED_CAR);
		setMaxSpeed(MAX_SPEED_CAR);
		setRandomSpeed();
		setIconLocation(CAR_IMAGE_FILE);
	}
}
