package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;

public class GCar extends Car {

	private static final String FAST_CAR_IMAGE_FILE = "GCar.png";

	private static final int MIN_SPEED_FAST_CAR = 4;
	private static final int MAX_SPEED_FAST_CAR = 4;

	public GCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'G');
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
	public GCar(Point2D position, int direction) {
		super(position,direction,'G');
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
