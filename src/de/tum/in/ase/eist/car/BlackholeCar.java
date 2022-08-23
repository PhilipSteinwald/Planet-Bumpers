package de.tum.in.ase.eist.car;

import com.sun.javafx.collections.FloatArraySyncer;
import de.tum.in.ase.eist.Dimension2D;

public class BlackholeCar extends Car {

	private String FAST_CAR_IMAGE_FILE;
	private String[] frames;
	private int index;
	private int temp;

	private static final int MIN_SPEED_FAST_CAR = 0;
	private static final int MAX_SPEED_FAST_CAR = 0;

	public BlackholeCar(Dimension2D gameBoardSize) {
		super(gameBoardSize,'H');
		this.setSize(new Dimension2D(150,150));
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		frames = new String[10];
		frames[0] = "BlackHoleCarN1.png";
		frames[1] = "BlackHoleCarN2.png";
		frames[2] = "BlackHoleCarN3.png";
		frames[3] = "BlackHoleCarN4.png";
		frames[4] = "BlackHoleCarN5.png";
		frames[5] = "BlackHoleCarN6.png";
		frames[6] = "BlackHoleCarN7.png";
		frames[7] = "BlackHoleCarN8.png";
		frames[8] = "BlackHoleCarN9.png";
		frames[9] = "BlackHoleCarN10.png";
		temp = 0;
		index = 0;
		FAST_CAR_IMAGE_FILE = frames[index];
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}

	public void updateHole() {
		temp++;
		temp = temp % 2;
		if (temp == 0) {
			if (index == frames.length - 1) {
				index = 0;
			} else {
				index++;
			}
		}
		FAST_CAR_IMAGE_FILE = frames[index];
		setIconLocation(FAST_CAR_IMAGE_FILE);
	}
}
