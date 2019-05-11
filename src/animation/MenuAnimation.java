package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameobject.Background;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    // Members of the class.
    private List<Option<T>> selections;
    private boolean stop;
    private T currentOption;
    private String title;
    private KeyboardSensor keyboardSensor;
    private Background background;
    private boolean isPressed;
    private List<Option<Menu<T>>> subMenus;
    private AnimationRunner animationRunner;

    /**
     * Instantiates a new Menu animation.
     *
     * @param title           the title
     * @param keyboard        the keyboard
     * @param background      the background
     * @param animationRunner the animation runner
     */
    public MenuAnimation(String title, KeyboardSensor keyboard,
                         Background background, AnimationRunner animationRunner) {
        this.isPressed = true;
        this.currentOption = null;
        this.stop = false;
        this.title = title;
        this.keyboardSensor = keyboard;
        this.selections = new LinkedList<>();
        this.background = background;
        this.subMenus = new LinkedList<>();
        this.animationRunner = animationRunner;
    }

    /**
     * Add a new selection.
     *
     * @param key       the key to press
     * @param s         the message to print
     * @param returnVal the return val
     */
    @Override
    public void addSelection(String key, String s, T returnVal) {
        Option<T> option = new Option<T>(key, s, returnVal);
        this.selections.add(option);
    }

    /**
     * Gets status.
     *
     * @return the current status
     */
    @Override
    public T getStatus() {
        return this.currentOption;
    }


    /**
     * Does one frame.
     *
     * @param d  - the drawsurface
     * @param dt - the speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // Draw the background.
        this.background.drawOn(d);
        // Set and draw the title.
        d.setColor(Color.decode("#F8BBD0"));
        d.drawText(30, 45, this.title, 52);
        d.setColor(Color.decode("#FF80AB"));
        d.drawText(30, 45, this.title, 50);
        int i = 0;
        // Draw all the options of the sub menu.
        for (Option<Menu<T>> option : this.subMenus) {
            d.setColor(Color.decode("#E1BEE7"));
            d.drawText(73, 90 + i * 50,
                    "(" + option.getKey() + ")" + option.getMessage(),
                    35);
            d.setColor(Color.decode("#CE93D8"));
            d.drawText(70, 90 + i * 50,
                    "(" + option.getKey() + ")" + option.getMessage(),
                    35);
            i++;
        }
        // Draw all the options of the menu.
        for (Option<T> option : this.selections) {
            d.setColor(Color.decode("#E1BEE7"));
            d.drawText(73, 90 + i * 50,
                    "(" + option.getKey() + ")" + option.getMessage(),
                    35);
            d.setColor(Color.decode("#CE93D8"));
            d.drawText(70, 90 + i * 50,
                    "(" + option.getKey() + ")" + option.getMessage(),
                    35);
            i++;
        }
        // Goes all the selections of the menu
        for (Option<T> selectedOption : this.selections) {
            // Check if the selection is pressed
            if (this.keyboardSensor.isPressed(selectedOption.getKey()) && !isPressed) {
                this.currentOption = selectedOption.getValue();
                stop();
            } else if (!this.keyboardSensor.isPressed(selectedOption.getKey())) {
                this.isPressed = false;
            }
        }
        // Goes all the selections of the sub menu
        for (Option<Menu<T>> selectedOption : this.subMenus) {
            // Check if the selection is pressed
            if (this.keyboardSensor.isPressed(selectedOption.getKey()) && !isPressed) {
                Menu<T> subMenu = selectedOption.getValue();
                subMenu.doOneFrame(d, dt);
                this.animationRunner.run(subMenu);
                this.currentOption = subMenu.getStatus();
                stop();
            } else if (!this.keyboardSensor.isPressed(selectedOption.getKey())) {
                this.isPressed = false;
            }
        }
    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop, else return false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Stop the animation.
     */
    @Override
    public void stop() {
        this.stop = true;
    }

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.subMenus.add(new Option<>(key, message, subMenu));
    }

}
