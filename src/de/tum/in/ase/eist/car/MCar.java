package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;

public class MCar extends Car {

	private static final String FAST_CAR_IMAGE_FILE = "MCar.png";

	private static final int MIN_SPEED_FAST_CAR = 5;
	private static final int MAX_SPEED_FAST_CAR = 5;

	public MCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'M');
		this.setSize(new Dimension2D(40,40));
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
