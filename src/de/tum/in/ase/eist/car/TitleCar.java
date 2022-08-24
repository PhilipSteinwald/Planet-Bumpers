package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class TitleCar extends Car {

	private static final String FAST_CAR_IMAGE_FILE = "Title.png";

	private static final int MIN_SPEED_FAST_CAR = 0;
	private static final int MAX_SPEED_FAST_CAR = 0;

	public TitleCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'T');
		this.setSize(new Dimension2D(700,250));
		this.setPosition(150,220);
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
