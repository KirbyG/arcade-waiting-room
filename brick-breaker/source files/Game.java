/*
MVP:
    3 levels
    last brick too hard to get
    powerups too common or rare
    powerups unbalanced
    bomb too big
    moving paddle bounces sometimes glitch
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The main class that runs the application
 * @author gordo_000
 */
public class Game extends Application {
    private int[][][] levelData = new int[][][] {
        {
            /*{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},*/
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 5, 5, 5, 0, 0},
            {0, 0, 4, 4, 4, 0, 0},
            {0, 0, 3, 3, 3, 0, 0},
            {0, 0, 2, 2, 2, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
            /*{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}*/
        },
        
        {
            {0, 0, 0, 0, 0, 0, 0,},
            {0, 1, 1, 4, 1, 1, 0},
            {0, 1, 5, 0, 5, 1, 0},
            {0, 1, 5, 0, 5, 1, 0},
            {0, 1, 5, 1, 5 ,1, 0},
            {0, 1, 5, 5, 5, 1, 0},
            {0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        },
        
        {
            {0, 0, 0, 0, 0, 0, 0},
            {5, 4, 0, 4, 0, 4, 5},
            {0, 4, 0, 4, 0, 4, 0},
            {0, 4, 0, 4, 0, 4, 0},
            {0, 4, 0, 4, 0, 4, 0},
            {0, 4, 0, 4, 0, 4, 0},
            {0, 4, 0, 4, 0, 4, 0},
            {0, 4, 0, 4, 0, 4, 0},
            {5, 4, 0, 4, 0, 4, 5},
            {0, 0, 0, 0, 0, 0, 0}
        }
    };
    
    private long ghostTime;
    private int counter = 0;
    private int lives = 3;
    private ArrayList<Text> texts = new ArrayList<Text>();

    public static final int GUN = 0;
    public static final int FLIP = 1;
    public static final int MULTI = 2;
    public static final int MUTATE = 3;
    public static final int SLOW = 4;
    public static final int FAST = 5;
    public static final int BOMB = 6;
    public static final int GHOST = 7;
    
    private int numBricks = 0;
    private boolean ghostTag = false;
    private int ammo = 0;
    private int startingAmmo = 12;

    public static final Vector gridSize = new Vector(7, 10);
    private final double bounciness = 0.01;
    public static int numPups = 8;
    public static boolean[] pup = new boolean[numPups];
    private int frameCount = 0;
    private int tickCount;
    private Scene scene;
    private Pane pane;
    public static Vector screen;
    public static final double wallWidth = 10;
    private ArrayList<CollisionHolder> collisionsL;
    private ArrayList<CollisionHolder> collisionsP;

    private Paddle paddle;

    private ArrayList<Ball> balls = new ArrayList<Ball>();

    private Wall top;
    private Wall left;
    private Wall right;
    
    private Text livesText;
    private int level = 1;

    private ArrayList<Powerup> powerups = new ArrayList<Powerup>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    private ArrayList<Wall> walls = new ArrayList<Wall>();
    //private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private Brick[][] bricks;

    private int ticks;

    private boolean firingLock = false;
    
    /**
     * initializes the data fields that comprise a game state
     */
    public Game() {
    
        //System.out.println(gridSize.x);
        

        pane = new Pane();
        screen = new Vector(1000, 600);
        scene = new Scene(pane, screen.x, screen.y);

        paddle = new Paddle();
        balls.add(new Ball());
        
        
        
        top = new Wall(new Vector(screen.x / 2, wallWidth / 2), new Vector(screen.x, wallWidth));
        left = new Wall(new Vector(wallWidth / 2, screen.y / 2), new Vector(wallWidth, screen.y));
        right = new Wall(new Vector(screen.x - wallWidth / 2, screen.y / 2), new Vector(wallWidth, screen.y));
        walls.add(top); //top
        walls.add(left); //left
        walls.add(right); //right
        
        
        
        

        for (Wall w : walls) {
            pane.getChildren().add(w.getGraphics());
        }

        livesText = new Text("lives: " + lives);
        livesText.translateXProperty().set(10);
        livesText.translateYProperty().set(10);
        livesText.setFill(Color.PINK);
        pane.getChildren().add(livesText);
        loadLevel();
        for (Ball b : balls) {
            pane.getChildren().add(b.getGraphics());
        }
        pane.getChildren().add(paddle.getGraphics());

        scene.addEventHandler(KeyEvent.ANY, (KeyEvent ke) -> {
            KeyCode code = ke.getCode();
            EventType type = ke.getEventType();
            paddle.takeInput(type, code);
            if (code.equals(KeyCode.UP)) {
                if (pup[GUN] && type.equals(KeyEvent.KEY_PRESSED) && !firingLock) {
                    firingLock = true;
                    ammo--;
                    if (ammo == 0) {
                        pup[GUN] = false;
                    }
                    Projectile p = new Projectile(paddle.getPosition());
                    projectiles.add(p);
                    pane.getChildren().add(p.getGraphics());
                    p.getGraphics().toBack();
                }
            }
            if (type.equals(KeyEvent.KEY_RELEASED)) {
                firingLock = false;
            }
        });
    }
    
    /**
     * loads the bricks for a new level
     */
    private void loadLevel() {
        bricks = new Brick[(int) gridSize.x][(int) gridSize.y];
        int[][] data = levelData[level - 1];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != 0) {
                    double choice = (Math.random() * 1.5);
                    boolean pup = false;
                    if (choice > 1) {
                        pup = true;
                    }
                    bricks[j][i] = new Brick(new Vector(j, i), data[i][j], pup);
                    if (data[i][j] != 5) {
                        numBricks++;
                    }
                    pane.getChildren().add(bricks[j][i].getGraphics());
                }
            }
            //System.out.println(numBricks);
        }
        
        for (Wall w : walls) {
            w.getGraphics().toFront();
        }
        livesText.toFront();
    }
    
    /**
     * starts the program
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Brick Breaker");
        stage.setScene(scene);

        EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
            update();
        };

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        stage.show();
    }
    
    /**
     * launches the application
     * @param args 
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    /**
     * called on every frame to update the game
     */
    private void update() {
        //System.out.println(numBricks);
        if (pup[GHOST] && System.currentTimeMillis() - ghostTime > 3000) {
            pup[GHOST] = false;
        }
        frameCount++;
        tickCount = 0;
        ticks = (int) (Math.max(paddle.getSpeed(), balls.get(0).getSpeed()) * 2);

        HashSet<Brick> kills = new HashSet<Brick>();

        for (int i = 0; i < ticks; i++) {
            tickCount++;
            collisionsL = new ArrayList<CollisionHolder>();
            collisionsP = new ArrayList<CollisionHolder>();

            for (Projectile p : projectiles) {;
                p.move(ticks);
            }

            for (Powerup p : powerups) {
                p.move(ticks);
            }

            for (Ball b : balls) {
                b.move(ticks);
            }
            paddle.move(ticks);

            ArrayList<Projectile> pKills = new ArrayList<Projectile>();
            for (Wall w : walls) {
                collide(paddle, w);
                for (Projectile p : projectiles) {
                    if (collide(p, w)) {
                        pKills.add(p);
                    }
                }
            }
            for (int j = 0; j < balls.size(); j++) {
                Ball b = balls.get(j);
                if (pup[FLIP]) {
                    if (b.getPosition().y < 0) {
                        removeBall(b);
                    }
                } else {
                    if (b.getPosition().y > screen.y) {
                        removeBall(b);
                    }
                }
            }
            for (Ball b : balls) {
                for (Wall w : walls) {
                    collide(b, w);
                }
                NearbyBricks brickIterator = new NearbyBricks(b);
                if (pup[GHOST]) {
                    ghostTag = true;
                }
                while (brickIterator.hasNext()) {
                    Brick br = brickIterator.next();
                    if (collide(b, br)) {
                        kills.add(br);
                        if (pup[BOMB]) {
                            pup[BOMB] = false;
                            Circle collider = new Circle(b.getPosition().x, b.getPosition().y, 100);
                            for (int k = 0; k < bricks.length; k++) {
                                for (int m = 0; m < bricks[k].length; m++) {
                                    Brick bri = bricks[k][m];
                                    if (bri != null) {
                                        //System.out.println(br.getPosition());
                                        if (collide(collider, bri.getShape()) != -1) {
                                            if (bri.getState() == 5) {
                                                //System.out.println("gray bomber");
                                                numBricks++;
                                            }
                                            bri.kill();
                                            kills.add(bri);
                                            
                                        }
                                    }
                                }
                            }
                            collisionsL.clear();
                            collisionsP.clear();
                        }
                    }
                }
                ghostTag = false;
                collide(b, paddle);
                b.updateGraphics();
                for (int j = 0; j < collisionsL.size(); j++) {
                    escape(collisionsL.get(j), b);


                }
                for (int j = 0; j < collisionsP.size(); j++) {
                    escape(collisionsP.get(j), b);

                }
            }

            /////////////////////////////////
            for (Projectile p : projectiles) {
                NearbyBricks brickIterator = new NearbyBricks(p);
                while (brickIterator.hasNext()) {
                    Brick b = brickIterator.next();
                    if (collide(p, b)) {
                        kills.add(b);
                        pKills.add(p);
                    }
                }
            }

            for (Projectile p : pKills) {
                projectiles.remove(p);
                pane.getChildren().remove(p.getGraphics());
            }

            for (int j = 0; j < powerups.size(); j++) {
                collide(powerups.get(j), paddle);
            }

            paddle.updateGraphics();
            for (Powerup p : powerups) {
                p.updateGraphics();
            }
            for (Projectile p : projectiles) {
                p.updateGraphics();
            }
            ///////////////////////////

        }

        for (Brick b : kills) {
            //System.out.println(frameCount + " " + b.getState());
            b.registerImpact();
            if (b.broken()) {
                bricks[(int) b.getGridPos().x][(int) b.getGridPos().y] = null;
                numBricks--;
                if (numBricks == 0) {
                    //System.out.println("win");
                    if (level == 3) {
                        System.exit(0);
                    }
                    level++;
                    for (int i = 0; i < balls.size(); i++) {
                        Ball bl = balls.get(i);
                        pane.getChildren().remove(bl.getGraphics());
                    }
                    balls.clear();
                    for (int i = 0; i < bricks.length; i++) {
                        for (int j = 0; j < bricks[i].length; j++) {
                            if (bricks[i][j] != null) {
                            pane.getChildren().remove(bricks[i][j].getGraphics());
                            }
                        }
                    }
                    for (Projectile p: projectiles) {
                        pane.getChildren().remove(p.getGraphics());
                    }
                    projectiles.clear();
                    Ball ball = new Ball();
                    balls.add(ball);
                    pane.getChildren().add(ball.getGraphics());                    
                    loadLevel();
                    
                }
                pane.getChildren().remove(b.getGraphics());
                if (b.getPowerup()) {
                    Powerup p = new Powerup(b.getPosition(), MoreMath.random(0, 7));
                    pane.getChildren().add(p.getGraphics());
                    powerups.add(p);
                }
            }
            b.updateGraphics();
        }
    }
    
    /**
     * resolves a collision given a CollisionHolder object and a ball
     * @param ch
     * @param b 
     */
    private void escape(CollisionHolder ch, Ball b) {

        while (ch.active()) {
            b.move(ticks);
            b.updateGraphics();
        }
    }
    
    /**
     * one of many overloaded collide methods, this one for the paddle and a wall
     * @param p
     * @param w 
     */
    private void collide(Paddle p, Wall w) {
        if (collide(p.getShape(), w.getShape())) {
            p.setPosition(p.getPosition().add(p.getVelocity().multiply(-0.5)));
            p.setVelocity(new Vector(0, 0));
        }
    }
    
    /**
     * one of many overloaded collide methods, this one for the ball and a wall
     * @param b
     * @param w 
     */
    private void collide(Ball b, Wall w) {
        collide(b, w.getShape());
    }
    
    /**
     * one of many overloaded collide methods, this one for the ball and a brick
     * @param ba
     * @param br
     * @return 
     */
    private boolean collide(Ball ba, Brick br) {
        if (collide(ba, br.getShape())) {
            return true;
        }
        return false;
    }
    
    /**
     * one of many overloaded collide methods, this one for the ball and the paddle
     * @param b
     * @param p 
     */
    private void collide(Ball b, Paddle p) {
        collide(b, p.getShape());
    }
    
    /**
     * deletes a ball when it falls off of the screen
     * @param b 
     */
    private void removeBall(Ball b) {
        balls.remove(b);
        pane.getChildren().remove(b.getGraphics());
        if (balls.size() == 0) {
            lives--;
            Ball ball = new Ball();
            balls.add(ball);
            pane.getChildren().add(ball.getGraphics());
        }
        if (lives == -1) {
            System.exit(0);
        }
        livesText.setText("lives: " + lives);
        
        
    }
    
    /**
     * one of many overloaded collide methods, this one for the ball and a polygon
     * @param b
     * @param p
     * @return 
     */
    private boolean collide(Ball b, Polygon p) {
        //line

        double wallAngle = collide(b.getShape(), p);
        if (wallAngle != -1) {
            //System.out.println(wallAngle + " "  + frameCount + " " + tickCount);
            collide(b, wallAngle);
            //collisions.add(() -> collide(b.getShape(), p) != -1);
            //escape(b, () -> collide(b.getShape(), p) != -1);
            return true;
        }
        return false;
    }
    
    /**
     * one of many overloaded collide methods, this one for a circle and a polygon
     * @param c
     * @param p
     * @return 
     */
    private double collide(Circle c, Polygon p) {
        //System.out.println(c.getCenterX());
        //c.setCenterX(c.getCenterX() - p.getTranslateX());
        Vector shift = new Vector(p.getTranslateX(), p.getTranslateY());
        //c.setCenterY(c.getCenterY() - p.getTranslateY());
        //System.out.println(c2.getCenterX());
        //Circle c = new Circle(c2.getCenterX() - p.translateXProperty().getValue(), c2.getCenterY() - p.translateYProperty().getValue(), c2.getRadius());
        ObservableList<Double> observablePoints = p.getPoints();
        ArrayList<Double> points = MoreMath.listObservableToNormal(observablePoints);
        points.add(observablePoints.get(0));
        points.add(observablePoints.get(1));

        double wallAngle;

        for (int i = 2; i < points.size(); i += 2) {
            Vector p2 = new Vector(points.get(i), points.get(i + 1));
            Vector p1 = new Vector(points.get(i - 2), points.get(i - 1));

            Line line = new Line(p1.x, p1.y, p2.x, p2.y);

            wallAngle = collide(c, line, shift);
            if (wallAngle != -1) {
                return wallAngle;
            }

            wallAngle = collide(c, p2, shift);
            if (wallAngle != -1) {
                return wallAngle;
            }
        }
        return -1;
    }
    
    /**
     * one of many overloaded collide methods, this one for the ball and a wall
     * at a defined angle
     * @param b
     * @param wallAngle 
     */
    private void collide(Ball b, double wallAngle) {
        Vector newVel = new Vector();

        Vector velocity = b.getVelocity();
        double velAngle = velocity.toAngle();
        double normalAngle = wallAngle + Math.PI / 2;

        double diff = Math.abs(MoreMath.angleToPositive(normalAngle) - MoreMath.angleToPositive(velAngle));
        if (diff > Math.PI) {
            diff = Math.PI * 2 - diff;
        }
        if (diff < Math.PI / 2) {
            //overtaken
            Vector deltaV = new Vector();
            deltaV.setAngleLength(normalAngle, bounciness);
            newVel = velocity.add(deltaV);
        } else {
            double newAngle = 2 * wallAngle - velAngle;
            newVel.setAngleLength(newAngle, velocity.length());
        }
        if (!ghostTag) {
            b.setVelocity(newVel);
        }
    }
    
    /**
     * one of many overloaded collide methods, this one for a circle and a line
     * @param c
     * @param line
     * @param shift
     * @return 
     */
    private double collide(Circle c, Line line, Vector shift) {
        Vector p1 = new Vector(line.getStartX(), line.getStartY());
        Vector p2 = new Vector(line.getEndX(), line.getEndY());

        double wallAngle = p1.angleTo(p2);

        double normalAngle = wallAngle + Math.PI / 2;

        Vector scaledNormal = new Vector();
        scaledNormal.setAngleLength(normalAngle, c.getRadius());

        Vector p2Shift = p2.add(scaledNormal);
        Vector p1Shift = p1.add(scaledNormal);

        Polygon collider = new Polygon(new double[]{p1.x, p1.y, p2.x, p2.y, p2Shift.x, p2Shift.y, p1Shift.x, p1Shift.y});

        if (collider.contains(c.getCenterX() - shift.x, c.getCenterY() - shift.y)) {
            if (!ghostTag) {
                collisionsL.add(() -> collide(c, line, shift) != -1);
            }
            return wallAngle;
        }
        return -1;
    }
    
    /**
     * one of many overloaded collide methods, this one for a circle and a point
     * @param c
     * @param p
     * @param shift
     * @return 
     */
    private double collide(Circle c, Vector p, Vector shift) {
        Vector center = (new Vector(c.getCenterX(), c.getCenterY())).subtract(shift);
        if (p.distanceTo(center) + 1 < c.getRadius()) {
            double connectAngle = center.angleTo(p);
            double flatAngle = connectAngle + Math.PI / 2;
            if (!ghostTag) {
                collisionsP.add(() -> collide(c, p, shift) != -1);
            }
            return flatAngle;
        }
        return -1;
    }
    
    /**
     * one of many overloaded collide methods, this one for a powerup and the paddle
     * @param po
     * @param pa 
     */
    private void collide(Powerup po, Paddle pa) {
        //System.out.println(powerups.size());
        if (pupInRange(po)) {
            if (pupOffScreen(po)) {
                destroyPowerup(po);
            } else if (collide(po.getShape(), pa.getShape())) {
                destroyPowerup(po);
                if (po.getType() == FLIP) {
                    pup[po.getType()] = pup[po.getType()] ^ true;
                } else {
                    pup[po.getType()] = true;
                }
                for (Ball ball: balls) {
                    ball.setVelocity(ball.getVelocity().toUnit().multiply(Ball.startSpeed));
                }
                switch (po.getType()) {
                    case GUN:

                        ammo += startingAmmo;
                        break;
                    case FLIP:
                        top.setPosition(new Vector(top.getPosition().x, screen.y - top.getPosition().y));
                        top.updateGraphics();
                        paddle.getPosition().x = balls.get(0).getPosition().x;
                        freePaddle();
                        break;
                    case MULTI:
                        Ball b = new Ball();
                        balls.add(b);
                        pane.getChildren().add(b.getGraphics());
                        break;
                    case MUTATE:
                        paddle.mutate();
                        freePaddle();
                        break;
                    case SLOW:
                        for (Ball ball: balls) {
                            ball.setVelocity(ball.getVelocity().multiply(3.0/4.0));
                        }
                        break;
                        /*for (int i = 0; i < bricks.length; i++) {
                            for (int j = 0; j < bricks[i].length; j++) {
                                Brick br = bricks[i][j];
                                if (br != null) {
                                    br.rotate();
                                }
                            }
                        }
                        break;*/
                    case FAST:
                        for (Ball ball: balls) {
                            ball.setVelocity(ball.getVelocity().multiply(4.0/3.0));
                        }
                        break;
                    case GHOST:
                        ghostTime = System.currentTimeMillis();
                        break;
                }
            }
        }
    }
    
    /**
     * frees the paddle from being stuck in a wall. This can occur when the
     * paddle gets longer or when the paddle flips to the top of the screen.
     * When the paddle flips to the top of the screen, it moves to the x
     * coordinate of the ball to prevent instantly losing the game. This can
     * place the paddle in one of the walls.
     */
    private void freePaddle() {
        paddle.updateGraphics();

        while (collide(paddle.getShape(), left.getShape())) {
            paddle.getPosition().x += 10;
            paddle.updateGraphics();
        }
        while (collide(paddle.getShape(), right.getShape())) {
            paddle.getPosition().x -= 10;
            paddle.updateGraphics();
        }
    }
    
    /**
     * determines if a powerup is in range of the paddle
     * @param po
     * @return 
     */
    private boolean pupInRange(Powerup po) {
        double shift = paddle.getHeight() + Brick.size.y;
        if (pup[FLIP]) {
            return po.getPosition().y < shift;
        }
        return po.getPosition().y > screen.y - shift;
    }
    
    /**
     * determines whether or not a powerup is off the screen
     * @param po
     * @return 
     */
    private boolean pupOffScreen(Powerup po) {
        if (pup[FLIP]) {
            return po.getPosition().y < -Brick.size.y / 2;
        }
        return po.getPosition().y > screen.y + Brick.size.y / 2;
    }
    
    /**
     * one of many overloaded collide methods, this one for two shapes
     * @param s1
     * @param s2
     * @return 
     */
    private boolean collide(Shape s1, Shape s2) {
        return !Shape.intersect(s1, s2).getBoundsInLocal().isEmpty();
    }
    
    /**
     * holds a reference to one of the collide methods, which will be called to
     * determine if the collision is still active
     */
    private interface CollisionHolder {

        public boolean active();
    }
    
    /**
     * destroys a powerup that has hit the paddle or the bottom of the screen
     * @param p 
     */
    private void destroyPowerup(Powerup p) {
        powerups.remove(p);
        pane.getChildren().remove(p.getGraphics());
    }
    
    
    /**
     * one of many overloaded collide methods, this one for a projectile and
     * a brick
     * @param p
     * @param b
     * @return 
     */
    private boolean collide(Projectile p, Brick b) {
        return collide(p.getShape(), b.getShape());
    }
    
    /**
     * one of many overloaded collide methods, this one for a projectile and a
     * wall
     * @param p
     * @param w
     * @return 
     */
    private boolean collide(Projectile p, Wall w) {
        return collide(p.getShape(), w.getShape());
    }
    
    /**
     * An iterator that returns the bricks within range of a mobile object
     * that need to be collision detected
     */
    private class NearbyBricks implements Iterator {

        private Vector min;
        private Vector max;

        private Vector current;

        private Brick next;

        private boolean inBound = true;
        
        /**
         * constructs a new iterator based on the position and size of the
         * given Mobile
         * @param m 
         */
        public NearbyBricks(Mobile m) {
            Vector position = m.getPosition();
            Vector grid = Brick.posToGrid(position);
            //System.out.println(grid.x + " " + grid.y);
            //calculate shift
            Vector size = new Vector(m.getShape().getBoundsInLocal().getWidth(), m.getShape().getBoundsInLocal().getHeight());
            Vector shift = ((((size.divide(Brick.size)).truncate()).add(new Vector(2, 2))).multiply(0.5)).truncate();
            //System.out.println(shift.x + " " + shift.y);

            min = grid.subtract(shift);
            max = grid.add(shift);

            //verify box in bounds
            if (min.x > gridSize.x - 1 || min.y > gridSize.y - 1) {
                inBound = false;
            }
            if (max.x < 0 || max.y < 0) {
                inBound = false;
            }
            //push box into range
            if (min.x < 0) {
                min.x = 0;
            }
            if (min.y < 0) {
                min.y = 0;
            }

            if (max.x > gridSize.x - 1) {
                max.x = gridSize.x - 1;
            }
            if (max.y > gridSize.y - 1) {
                max.y = gridSize.y - 1;
            }
            //

            current = min.copy().subtract(new Vector(1, 0));
        }
            
        /**
         * increases the pointer vector by 1
         */
        private void increment() {
            if (current.x == max.x) {
                current.x = min.x;
                current.y++;
            } else {
                current.x++;
            }
        }
        
        /**
         * determines the next element in the series
         * @return 
         */
        @Override
        public boolean hasNext() {
            if (current.equals(max) || !inBound) {
                return false;
            }

            while (!current.equals(max)) {
                increment();
                Brick b = bricks[(int) current.x][(int) current.y];
                if (b != null) {
                    next = b;
                    return true;
                }
            }
            return false;
        }
        
        /**
         * returns the next element
         * @return 
         */
        @Override
        public Brick next() {
            return next;
        }

    }
}