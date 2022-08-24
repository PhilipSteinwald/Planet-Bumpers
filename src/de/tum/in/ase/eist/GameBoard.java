package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.ase.eist.audio.AudioPlayerInterface;
import de.tum.in.ase.eist.car.*;
import de.tum.in.ase.eist.collision.Collision;
import de.tum.in.ase.eist.collision.StarCollision;
import de.tum.in.ase.eist.collision.StarCollision2;

/**
 * Creates all car objects, detects collisions, updates car positions, notifies
 * player about victory or defeat.
 */
public class GameBoard {

	private static final int NUMBER_OF_O_CARS = 2;
	private static final int NUMBER_OF_A_CARS = 2;
	private static final int NUMBER_OF_G_CARS = 2;
	private static final int NUMBER_OF_M_CARS = 2;
	private static final int NUMBER_OF_BLACKHOLES = 2;

	private boolean paused;
	private int ticks;

	/**
	 * List of all active cars, does not contain player car.
	 */
	private final List<Car> cars = new ArrayList<>();

	/**
	 * The player object with player's car.
	 */
	private final Player player;

	private final ExplosionCar explosion;

	/**
	 * AudioPlayer responsible for handling music and game sounds.
	 */
	private AudioPlayerInterface audioPlayer;

	/**
	 * Dimension of the GameBoard.
	 */
	private final Dimension2D size;

	/**
	 * true if game is running, false if game is stopped.
	 */
	private boolean running;

	/**
	 * List of all loser cars (needed for testing, DO NOT DELETE THIS)
	 */
	private final List<Car> loserCars = new ArrayList<>();

	/**
	 * The outcome of this game from the players perspective. The game's outcome is open at the beginning.
	 */
	private GameOutcome gameOutcome = GameOutcome.OPEN;

	/**
	 * Creates the game board based on the given size.
	 *
	 * @param size of the game board
	 */
	public GameBoard(Dimension2D size) {
		this.size = size;
		MCar playerCar = new MCar(size);
		this.player = new Player(playerCar);
		this.player.setup();
		this.explosion = new ExplosionCar(size);
		createCars();
	}

	/**
	 * Creates as many cars as specified by {@link #NUMBER_OF_O_CARS} and adds
	 * them to the cars list.
	 */
	private void createCars() {
		Car background = new BackgroundCar(this.size);
		this.cars.add(background);
		loserCars.add(background);
		background.crunch();
		for (int i = 0; i < NUMBER_OF_O_CARS; i++) {
			this.cars.add(new OCar(this.size));
		}
		for (int i = 0; i < NUMBER_OF_A_CARS; i++) {
			this.cars.add(new ACar(this.size));
		}
		for (int i = 0; i < NUMBER_OF_G_CARS; i++) {
			this.cars.add(new GCar(this.size));
		}
		for (int i = 0; i < NUMBER_OF_M_CARS; i++) {
			this.cars.add(new MCar(this.size));
		}
		for (int i = 0; i < NUMBER_OF_BLACKHOLES; i++) {
			this.cars.add(new BlackholeCar(this.size));
		}
		//wichtig: zuletzt, da Vordergrund
		Car title = new TitleCar(this.size);
		this.cars.add(title);
		loserCars.add(title);
		title.crunch();
		Car title2 = new LittleTitleCar(this.size);
		this.cars.add(title2);
		loserCars.add(title2);
		title2.crunch();
		this.cars.add(explosion);
		loserCars.add(explosion);
		explosion.crunch();
	}

	public Dimension2D getSize() {
		return size;
	}

	/**
	 * Returns if game is currently running.
	 *
	 * @return true if the game is currently running, false otherwise
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * Sets whether the game should be currently running.
	 * <p>
	 * Also used for testing on Artemis.
	 *
	 * @param running true if the game should be running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	public GameOutcome getGameOutcome() {
		return gameOutcome;
	}

	/**
	 * Returns all cars on the game board except the player's car as a list.
	 *
	 * @return the list of all non-player cars
	 */
	public List<Car> getCars() {
		return this.cars;
	}

	public Car getPlayerCar() {
		return this.player.getCar();
	}

	public AudioPlayerInterface getAudioPlayer() {
		return this.audioPlayer;
	}

	public void setAudioPlayer(AudioPlayerInterface audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	/**
	 * Updates the position of each car.
	 */
	public void update() {
		if(!paused) {
			moveCars();
		}else{
			ticks++;
			if(ticks > 10){
				this.explosion.updateExplosion();
			}
			if(ticks == 30){
				this.paused = false;
				ticks = 0;
			}
		}
	}

	/**
	 * Starts the game. Cars start to move and background music starts to play.
	 */
	public void startGame() {
		playMusic();
		this.running = true;
		for (Car car : this.cars) {
			if(car.getType() == 'T'){
				car.setPosition(-500,-300);
			}
		}
	}

	/**
	 * Stops the game. Cars stop moving and background music stops playing.
	 */
	public void stopGame() {
		stopMusic();
		this.running = false;
	}

	/**
	 * Starts the background music.
	 */
	public void playMusic() {
		this.audioPlayer.playBackgroundMusic();
	}

	/**
	 * Stops the background music.
	 */
	public void stopMusic() {
		this.audioPlayer.stopBackgroundMusic();
	}

	/**
	 * @return list of loser cars
	 */
	public List<Car> getLoserCars() {
		return this.loserCars;
	}

	/**
	 * Moves all cars on this game board one step further.
	 */
	public void moveCars() {
		// update the positions of the player car and the autonomous cars
		for (Car car : this.cars) {
			car.checkMove();
			if(car instanceof BlackholeCar){
				((BlackholeCar) car).updateHole();
			}
			car.drive(size);
		}
		this.player.getCar().drive(size);

		// iterate through all cars (except player car) and check if it is crunched
		for (Car car : cars) {
			if (car.isCrunched() || car.getType() == 'B') {
				// because there is no need to check for a collision
				continue;
			}
			Collision collision2 = new StarCollision(player.getCar(), car);
			Collision collision3 = new StarCollision2(player.getCar(), car);
			if(collision2.isCrash() && player.getCar().getType() != car.getType()){
				Car winner = collision2.evaluate();
				Car loser = collision2.evaluateLoser();
				double tempX = loser.getPosition().getX();
				double tempY = loser.getPosition().getY();
				printWinner(winner);
				loserCars.add(loser);

				this.audioPlayer.playCrashSound();

				loser.crunch();

				if(player.getCar() == loser){
					gameOutcome = GameOutcome.LOST;
				}
				if(isWinner()){
					gameOutcome = GameOutcome.WON;
				}else {
					this.paused = true;
					this.explosion.setSize(loser.getSize());
					this.explosion.setPosition(tempX,tempY);
				}
			} else if(collision3.isCrash()  && player.getCar().getType() == car.getType()){
				Car winner = collision3.evaluate();
				Car loser = collision3.evaluateLoser();
				double tempX = loser.getPosition().getX();
				double tempY = loser.getPosition().getY();
				printWinner(winner);
				loserCars.add(loser);

				this.audioPlayer.playCrashSound();

				loser.crunch();

				if(player.getCar() == loser){
					gameOutcome = GameOutcome.LOST;
				} else{
					player.upgradeCar();
				}
				if(isWinner()){
					gameOutcome = GameOutcome.WON;
				}else {
					this.paused = true;
					this.explosion.setSize(loser.getSize());
					this.explosion.setPosition(tempX,tempY);
				}
			}
		}
	}

	/**
	 * If all other cars are crunched, the player wins.
	 *
	 * @return true if the game is over and the player won, false otherwise
	 */
	private boolean isWinner() {
		for (Car car : getCars()) {
			if(car.getType() == 'H'){
				return true;
			}
			if (!car.isCrunched()) {
				return false;
			}
		}
		return true;
	}

	private void printWinner(Car winner) {
		if (winner == this.player.getCar()) {
			System.out.println("The player's car won the collision!");
		} else if (winner != null) {
			System.out.println(winner.getClass().getSimpleName() + " won the collision!");
		} else {
			System.err.println("Winner car was null!");
		}
	}
}
