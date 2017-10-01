import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import processing.core.PApplet;

enum Direction {
  NORTH,
  SOUTH,
  EAST,
  WEST
}

public class Snake {
  private ArrayDeque<GridPosition> body;
  private boolean shouldGrow;
  private Direction currentDirection = Direction.EAST;

  private double cellsPerSecond;
  private long lastTimeMoved;
  private int cellsPerGrow;
  private int cellsGrown;

  public Snake(int row, int col) {
    body = new ArrayDeque<>();
    shouldGrow = true;
    body.add(new GridPosition(row, col));
    lastTimeMoved = System.currentTimeMillis();
    cellsPerSecond = 14;
    cellsPerGrow = 5;
    cellsGrown = 0;
  }

  public void setDirection(Direction direction) {
    this.currentDirection = direction;
  }

  public void move() {
    double delayTime = 1000.0 / cellsPerSecond;

    if (System.currentTimeMillis() - lastTimeMoved < delayTime) {
      return;
    }

    lastTimeMoved = System.currentTimeMillis();

    GridPosition newHead = new GridPosition(body.peekFirst().row, body.peekFirst().col);
    switch (currentDirection) {
      case NORTH:
        newHead.row--;
        break;
      case SOUTH:
        newHead.row++;
        break;
      case EAST:
        newHead.col++;
        break;
      case WEST:
        newHead.col--;
        break;
    }
    body.addFirst(newHead);

    if (!shouldGrow) {
      body.removeLast();
    } else {
      cellsGrown++;
      if (cellsGrown >= cellsPerGrow) {
        shouldGrow = false;
      }
    }
  }

  public boolean isCollidingWithSelf() {
    Set<GridPosition> visited = new HashSet<>();
    for (GridPosition position : body) {
      if (visited.contains(position)) {
        return true;
      }
      visited.add(position);
    }
    return false;
  }

  public void render(PApplet pApplet) {
    pApplet.colorMode(PApplet.RGB);
    int color = pApplet.color(0x40, 0xF9, 0x9B);
    pApplet.stroke(color);
    pApplet.fill(color);

    for (GridPosition position : body) {
      pApplet.rect(
          position.col * Main.SCALE_FACTOR,
          position.row * Main.SCALE_FACTOR,
          Main.SCALE_FACTOR,
          Main.SCALE_FACTOR);
    }
  }

  public boolean isCollidingWith(GridPosition appleLocation) {
    return body.contains(appleLocation);
  }

  public void grow() {
    cellsPerSecond += 1;
    shouldGrow = true;
    cellsGrown = 0;
  }

  public GridPosition getHead() {
    return body.peekFirst();
  }
}
