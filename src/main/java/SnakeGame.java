import java.util.Random;
import processing.core.PApplet;

public class SnakeGame {
  // width and height based on nokia
  public static final int GRID_HEIGHT = 50;
  public static final int GRID_WIDTH = 70;

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
    do {
      appleLocation = new GridPosition(random.nextInt(GRID_HEIGHT), random.nextInt(GRID_WIDTH));
    } while (grid[appleLocation.row][appleLocation.col] == GridCell.WALL);
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

  public void renderGrid(PApplet pApplet) {
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
  }

  // color for snake so i don't forget 40F99B
  public void render(PApplet pApplet) {
    renderGrid(pApplet);

    snake.move();

    if (snake.isCollidingWith(appleLocation)) {
      snake.grow();
      removeApple();
      placeApple();
    }

    if (snake.isCollidingWithSelf() || isWall(snake.getHead())) {
      Main.lose();
    }

    snake.render(pApplet);
  }

  private boolean isWall(GridPosition head) {
    return grid[head.row][head.col] == GridCell.WALL;
  }

  private void removeApple() {
    grid[appleLocation.row][appleLocation.col] = GridCell.EMPTY;
    appleLocation = null;
  }

  public void keyPressed(int key) {
    switch (key) {
      case PApplet.UP:
        snake.setDirection(Direction.NORTH);
        break;
      case PApplet.DOWN:
        snake.setDirection(Direction.SOUTH);
        break;
      case PApplet.LEFT:
        snake.setDirection(Direction.WEST);
        break;
      case PApplet.RIGHT:
        snake.setDirection(Direction.EAST);
        break;
      default:
        System.out.println("Unused keycode: " + key);
    }
  }
}
