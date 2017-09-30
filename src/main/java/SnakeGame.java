import java.util.Random;
import processing.core.PApplet;

public class SnakeGame {
  // width and height based on nokia
  public static final int GRID_HEIGHT = 100;
  public static final int GRID_WIDTH = 140;

  private enum GridCell {
    APPLE,
    WALL,
    EMPTY
  }

  private GridCell[][] grid;
  private Random random;
  private GridPosition appleLocation;
  private Snake snake;

  public SnakeGame() {
    random = new Random();
    snake = new Snake(GRID_HEIGHT / 2, GRID_WIDTH / 2);
    initializeGrid();
    placeApple();
  }

  private void placeApple() {
    appleLocation = new GridPosition(random.nextInt(GRID_HEIGHT), random.nextInt(GRID_WIDTH));
    grid[appleLocation.row][appleLocation.col] = GridCell.APPLE;
  }

  private void initializeGrid() {
    grid = new GridCell[GRID_HEIGHT][GRID_WIDTH];

    for (int row = 0; row < GRID_HEIGHT; row++) {
      for (int col = 0; col < GRID_WIDTH; col++) {
        grid[row][col] = GridCell.EMPTY;

        // place walls
        if (row == 0 || col == 0 || row == GRID_HEIGHT - 1 || col == GRID_WIDTH - 1) {
          grid[row][col] = GridCell.WALL;
        }
      }
    }
  }

  // color for snake so i don't forget 40F99B
  public void render(PApplet pApplet) {
    pApplet.colorMode(PApplet.RGB);

    for (int row = 0; row < GRID_HEIGHT; row++) {
      for (int col = 0; col < GRID_WIDTH; col++) {
        int color;
        switch (grid[row][col]) {
          case APPLE:
            color = pApplet.color(0xED, 0x6B, 0x86); // light red
            break;
          case WALL:
            color = pApplet.color(0x61, 0x70, 0x7D); // blackish
            break;
          default:
          case EMPTY:
            color = pApplet.color(0x6C, 0xD4, 0xFF); // light blue
            break;
        }

        pApplet.stroke(color);
        pApplet.fill(color);

        pApplet.rect(
            col * Main.SCALE_FACTOR, row * Main.SCALE_FACTOR, Main.SCALE_FACTOR, Main.SCALE_FACTOR);
      }
    }

    snake.move();
    if (snake.isCollidingWithSelf()) {
      //      System.exit(0);
      System.out.println("Colliding");
    }

    snake.render(pApplet);
  }
}
