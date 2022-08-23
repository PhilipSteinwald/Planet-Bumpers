package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;

public class ACar extends Car {

	private static final String FAST_CAR_IMAGE_FILE = "ACarN1.png";

	private static final int MIN_SPEED_FAST_CAR = 3;
	private static final int MAX_SPEED_FAST_CAR = 3;

	public ACar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'A');
		this.setSize(new Dimension2D(70,70));
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}

	public ACar(Point2D position, int direction) {
		super(position,direction,'A');
		this.setSize(new Dimension2D(70,70));
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
