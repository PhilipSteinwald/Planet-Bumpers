package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class ExplosionCar extends Car {

	private String FAST_CAR_IMAGE_FILE;
	private String[] frames;
	private int index;
	private int temp;

	private static final int MIN_SPEED_FAST_CAR = 0;
	private static final int MAX_SPEED_FAST_CAR = 0;

	public ExplosionCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'T');
		this.setSize(new Dimension2D(50,50));
		this.setPosition(-100,-100);
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		frames = new String[10];
		frames[0] = "ExplosionN1.png";
		frames[1] = "ExplosionN2.png";
		frames[2] = "ExplosionN3.png";
		frames[3] = "ExplosionN4.png";
		frames[4] = "ExplosionN5.png";
		frames[5] = "ExplosionN6.png";
		frames[6] = "ExplosionN7.png";
		frames[7] = "ExplosionN8.png";
		frames[8] = "ExplosionN9.png";
		frames[9] = "ExplosionN10.png";
		temp = 0;
		index = 0;
		FAST_CAR_IMAGE_FILE = frames[index];
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}

	public void updateExplosion() {
		temp++;
		temp = temp % 2;
		if (temp == 0) {
			if (index == frames.length - 1) {
				index = 0;
				this.setPosition(-100,-100);
			} else {
				index++;
			}
		}
		FAST_CAR_IMAGE_FILE = frames[index];
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
