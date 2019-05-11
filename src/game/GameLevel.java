package game;

import level.LevelInformation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import gameobject.Collidable;
import gameobject.LivesIndicator;
import gameobject.ScoreIndicator;
import gameobject.Sprite;
import gameobject.LevelIndicator;
import gameobject.Paddle;
import gameobject.Ball;
import gameobject.Block;
import geometry.Point;
import observer.BallRemover;

import observer.BlockRemover;
import observer.ScoreTrackingListener;
import utilts.Counter;

import java.awt.Color;


/**
 * A class to create a new game.
 * The class is in charge of initializing and running the game.
 */
public class GameLevel implements Animation {
    // Members of the class.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation information;
    private KeyboardSensor keyboardSensor;

    /**
     * A constructor, create a new game.
     *
     * @param information     the level information
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     * @param score           the score
     * @param lives           the lives
     */
    public GameLevel(LevelInformation information, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score, Counter lives) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blocksCounter = new Counter();
        blocksCounter.increase(information.numberOfBlocksToRemove());
        ballCounter = new Counter();
        this.lives = lives;
        this.score = score;
        this.information = information;
        this.keyboardSensor = keyboardSensor;
        this.runner = animationRunner;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * Add a new collidable to the the list
     * of the collidables.
     *
     * @param c - a new collidable to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a new sprite to the sprute list.
     *
     * @param s - a new sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c a collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s a sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the blocks,
     * gameobject,Ball and gameobject, Paddle
     * and add them to the game.
     */
    public void initialize() {
        //information.getBackground().addToGame(this);
        addSprite(information.getBackground());
        ScoreIndicator sc = new ScoreIndicator(score);
        sc.addToGame(this);
        LivesIndicator live = new LivesIndicator(lives);
        live.addToGame(this);
        LevelIndicator level = new LevelIndicator(information.levelName());
        level.addToGame(this);
        createBlock();
        createBorders();
    }

    /**
     * Run.
     */
    public void run() {
        while (lives.getValue() > 0 && blocksCounter.getValue() > 0) {
            playOneTurn();
        }
        runner.getGui().close();
    }

    /**
     * Run the game - start the animation loop.
     */
    public void playOneTurn() {
        // Create a new paddle.
        Paddle p = new Paddle(keyboardSensor, information.paddleWidth(), information.paddleSpeed());
        // Add the paddle to the game.
        p.addToGame(this);
        environment.setPaddle(p);
        // Find the center of the paddle
        Point centerPaddle = new Point(environment.getPaddle().getCollisionRectangle().getUpperLeft().getX()
                + environment.getPaddle().getCollisionRectangle().getWidth() / 2,
                environment.getPaddle().getCollisionRectangle().getUpperLeft().getY() - 5);
        // Create a new balls according to the level information.
        for (int i = 0; i < information.numberOfBalls(); i++) {
            Ball ball = new Ball(centerPaddle, 5, Color.white, environment);
            ball.setVelocity(information.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);
        p.removeFromGame(this);
    }

    /**
     * Create the screen borders (blocks).
     */
    public void createBorders() {
        // Set the left block.
        Block block = new Block(new Point(0, Constants.BLOCK_HIGTH),
                Constants.BLOCK_HIGTH, (double) Constants.SCREEN_HEIGHT);
        // Set the color of the block to gray.
        block.setColor(Color.decode("#BDBDBD"));
        // Add the block to the game.
        block.addToGame(this);
        // Set the right block.
        Block block2 = new Block(new Point(Constants.SCREEN_WIDTH
                - Constants.BLOCK_HIGTH, Constants.BLOCK_HIGTH),
                Constants.BLOCK_HIGTH, (double) Constants.SCREEN_HEIGHT);
        // Set the color of the block to gray.
        block2.setColor(Color.decode("#BDBDBD"));
        // Add the block to the game.
        block2.addToGame(this);
        BallRemover ball = new BallRemover(this, ballCounter);
        // Set the bottom block.
        Block block3 = new Block(new Point(0, Constants.SCREEN_HEIGHT),
                (double) Constants.SCREEN_WIDTH, Constants.BLOCK_HIGTH);
        // Set the color of the block to gray.
        block3.setColor(Color.decode("#BDBDBD"));
        // Add the block to the game.
        block3.addToGame(this);
        block3.addHitListener(ball);
        // Set the upper block.
        Block block4 = new Block(new Point(Constants.BLOCK_HIGTH, Constants.BLOCK_HIGTH),
                (double) Constants.SCREEN_WIDTH, Constants.BLOCK_HIGTH);
        // Set the color of the block to gray.
        block4.setColor(Color.decode("#BDBDBD"));
        // Add the block to the game.
        block4.addToGame(this);
    }

    /**
     * Create block.
     */
    public void createBlock() {
        BlockRemover b = new BlockRemover(this, blocksCounter);
        ScoreTrackingListener s = new ScoreTrackingListener(score);
        // Create the number of blocks according to the level information.
        for (int i = 0; i < information.blocks().size(); i++) {
            // add yhe blocks to the game.
            information.blocks().get(i).addToGame(this);
            information.blocks().get(i).addHitListener(b);
            information.blocks().get(i).addHitListener(s);
        }
    }

    /**
     * Do one frame in the animation.
     *
     * @param d  the surface
     * @param dt the dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //draws all the sprites on the surface.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // Check if there is no more blocks in the game.
        if (blocksCounter.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        // Check if there is no more balls in the game.
        if (ballCounter.getValue() == 0) {
            lives.decrease(1);
            this.running = false;
        }
        // Check if "p" is pressed
        if (keyboardSensor.isPressed("p")) {
            this.runner.run(new PauseScreen(keyboardSensor));
        }
    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop,
     * else return false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void stop() {
        this.running = true;
    }

    /**
     * Gets blocks counter.
     *
     * @return the blocks counter
     */
    public int getBlocksCounter() {
        return this.blocksCounter.getValue();
    }
}

