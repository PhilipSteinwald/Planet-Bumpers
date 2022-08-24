package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class LittleTitleCar extends Car {

	private static final String FAST_CAR_IMAGE_FILE = "LittleTitle.png";

	private static final int MIN_SPEED_FAST_CAR = 0;
	private static final int MAX_SPEED_FAST_CAR = 0;

	public LittleTitleCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'T');
		this.setSize(new Dimension2D(220,50));
		this.setPosition(400,450);
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
