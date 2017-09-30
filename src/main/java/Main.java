import processing.core.PApplet;

public class Main extends PApplet {
  public static final int SCALE_FACTOR = 5;

  private SnakeGame snakeGame;

  @Override
  public void settings() {
    setSize(SnakeGame.GRID_WIDTH * SCALE_FACTOR, SnakeGame.GRID_HEIGHT * SCALE_FACTOR);
  }

  @Override
  public void setup() {
    snakeGame = new SnakeGame();
  }

  @Override
  public void draw() {
    snakeGame.render(this);
  }

  public static void main(String[] args) {
    PApplet.main("Main");
  }
}
