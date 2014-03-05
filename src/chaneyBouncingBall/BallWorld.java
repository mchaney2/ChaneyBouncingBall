package chaneyBouncingBall;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class BallWorld extends JPanel {
   private static final int UPDATE_RATE = 50;  // Frames per second (fps)
   

   private Ball ball; // A single bouncing Ball's instance
   private Ball ball2;
   private ContainerBox box;  // The container rectangular box
  
   private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
  
   /**
    * Constructor to create the UI components and init the game objects.
    * Set the drawing canvas to fill the screen (given its width and height).
    * 
    * @param width : screen width
    * @param height : screen height
    */
   public BallWorld(int width, int height) {
  
      this.canvasWidth = width;
      this.canvasHeight = height;
      
      // Init the ball at a random location (inside the box) and moveAngle
//      Random rand = new Random();
//      double radius = 25;
////      int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
////      int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
//      
//      double x = 300;
//      double y = 250;
//      double x2 = rand.nextInt(500);
//      double y2 = rand.nextInt(500);
//      double speed = 0;
//      double accel = 5;
//      double accelAngle;
//      if (x2 < x)
//      {
//    	  accelAngle = Math.atan2(y2 - 250, x2 - 300);
//      }
//      else
//      {
//    	accelAngle = Math.atan2(y2 - 250, x2 - 300) + Math.PI;  
//      }
//    	  
//      
      
//      ball = new Ball(x, y, radius, speed, double 0, double 0, angleInDegree, Color.BLUE);
//      ball2 = new Ball(x2, y2, 15, speed, accel, accelAngle, angleInDegree, Color.yellow);
      
      boolean stationary = true;
      Random rand = new Random();
      int angleInDegree = rand.nextInt(360);
      int radius1 = 100;
      int radius2 = 25;
      double accelAngle;
      float x1 = rand.nextInt(canvasWidth - radius1 * 2 - 20) + radius1 + 10;
      float y1 = rand.nextInt(canvasHeight - radius1 * 2 - 20) + radius1 + 10;
      float x2 = rand.nextInt(canvasWidth - radius2 * 2 - 20) + radius2 + 10;
      float y2 = rand.nextInt(canvasHeight - radius2 * 2 - 20) + radius2 + 10;
//      float x1 = 200;
//      float y1 = 400;
//      float x2 = 300;
//      float y2 = 500;
      
      
      
//      float x = 100;
//      float y = 100;
      float velX1 = 0;
      float velY1 = 0;
      float accelX1 = 0;
      float accelY1 = 0;
      float velX2 = 0;
      float velY2 = 0;
      float accelX2 = 0;
      float accelY2 = 0;
  

//      int angleInDegree = rand.nextInt(360);
//      if (x < 300)
//      {
//    	  double accelAngle = Math.atan2(y - 200, x - 300);
//      }
//      else
//      {
//    	  double accelAngle = Math.atan2(y - 200, x - 300) + 180;
//      }
      

      ball = new Ball(x1, y1, velX1, velY1, accelX1, accelY1, 
    		  radius1, Color.BLUE, (float)25);
      ball2 = new Ball(x2, y2, velX2, velY2, accelX2, accelY2, radius2, 
    		  Color.YELLOW, (float)5);
      // Init the Container Box to fill the screen
      box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.BLACK, Color.WHITE);
     
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
            Dimension dim = c.getSize();
            canvasWidth = dim.width;
            canvasHeight = dim.height;
            // Adjust the bounds of the container to fill the window
            box.set(0, 0, canvasWidth, canvasHeight);
         }
      });
  
      // Start the ball bouncing
      gameStart();
   }
   
   /** Start the ball bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
      Thread gameThread = new Thread() {
         public void run() {
            while (true) {
               // Execute one time-step for the game 
               gameUpdate();
               // Refresh the display
               repaint();
               // Delay and give other thread a chance
               try {
                  Thread.sleep(1000 / UPDATE_RATE);
               } catch (InterruptedException ex) {}
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
   }
   

   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
      ball.moveOneStepWithCollisionDetection(box, ball2);
      ball2.moveOneStepWithCollisionDetection(box, ball);
   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);    // Paint background
         // Draw the box and the ball
         box.draw(g);
         ball.draw(g);
         ball2.draw(g);
         // Display ball's information
         g.setColor(Color.WHITE);
         g.setFont(new Font("Courier New", Font.PLAIN, 12));
//         g.drawString("Ball " + ball.toString(), 20, 30);
      }
  
      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }
}