package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class BackgroundCar extends Car {

	private static final String FAST_CAR_IMAGE_FILE = "backgroundCar.png";

	private static final int MIN_SPEED_FAST_CAR = 0;
	private static final int MAX_SPEED_FAST_CAR = 0;

	public BackgroundCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'B');
		this.setSize(new Dimension2D(1000,800));
		this.setPosition(0,0);
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
