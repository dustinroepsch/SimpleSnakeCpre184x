import processing.core.PApplet;

public class Main extends PApplet {
  public static final int SCALE_FACTOR = 10;

  private SnakeGame snakeGame;
  private static boolean playing = true;

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
    if (playing) {
      snakeGame.render(this);
    } else {
      // textMode(CENTER);
      textSize(height * .20f);
      stroke(0);
      fill(0);
      text("You Lost! \nHit ENTER \nto play again!", 0, height / 3f);
    }
  }

  @Override
  public void keyPressed() {
    if (!playing && (keyCode == ENTER || keyCode == RETURN)) {
      restart();
      snakeGame = new SnakeGame();
    }
    snakeGame.keyPressed(keyCode);
  }

  public static void lose() {
    playing = false;
  }

  public static void restart() {
    playing = true;
  }

  public static void main(String[] args) {
    PApplet.main("Main");
  }
}
