import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objectdraw.*;

// A very simple drawing program.
public class Scribbler extends WindowController
                       implements ActionListener {

   // user modes
   // using ints rather than boolean to allow for extension to
   // other modes
   private static final int DRAWING = 1;
   private static final int MOVING = 2;
   private static final int COLORING = 3;

   // empty scribble as placeholder when nothing there.
   private static final EmptyScribble empty = new EmptyScribble();

   // the current scribble
   private ScribbleIfc currentScribble;

   // the collection of scribbles
   private ScribbleCollectionIfc scribbles = new EmptyScribbleCollection();

   // stores last point for drawing or dragging
   private Location lastPoint;

   // whether the most recent scribble has been selected for moving
   private boolean draggingScribble;

   // buttons that allow user to select modes
   private JButton setDraw, setMove, setErase, setColor;

   // Choice button to select color
   private JComboBox chooseColor;

   // new color for scribble
   private Color newColor;

   // label indicating current mode
   private JLabel modeLabel;

   // the current mode -- drawing mode by default
   private int chosenAction = DRAWING;

   // create and hook up the user interface components
   public void begin() {

      setDraw = new JButton("Draw");
      setMove = new JButton("Move");
      setColor = new JButton("Color");
      setErase = new JButton("Erase last");

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(setDraw);
      buttonPanel.add(setMove);
      buttonPanel.add(setColor);
      buttonPanel.add(setErase);

      chooseColor = new JComboBox();
      chooseColor.addItem("red");
      chooseColor.addItem("blue");
      chooseColor.addItem("green");
      chooseColor.addItem("yellow");
      Panel choicePanel = new Panel();
      choicePanel.add(chooseColor);

      Panel controlPanel = new Panel(new GridLayout(3,1));
      modeLabel = new JLabel("  Current mode: drawing");
      controlPanel.add(modeLabel);
      controlPanel.add(buttonPanel);
      controlPanel.add(choicePanel);

      add(controlPanel, BorderLayout.SOUTH);

      // add listeners
      setDraw.addActionListener(this);
      setMove.addActionListener(this);
      setErase.addActionListener(this);
      setColor.addActionListener(this);
      
      // make the current scribble empty
      currentScribble = empty;
      
      validate();
   }

   // if in drawing mode then start with empty scribble
   // if in moving mode then prepare to move
   public void onMousePress(Location point) {
      if (chosenAction == DRAWING) {

             // start with an empty scribble for drawing
         currentScribble = empty;
      } else if (chosenAction == MOVING) {
             // check if user clicked on current scribble
          currentScribble = scribbles.getScribble(point);
          draggingScribble = currentScribble.contains(point);
          } else if (chosenAction == COLORING) {
        currentScribble = scribbles.getScribble(point);
    	  if (chooseColor.getSelectedItem() == "red"){
    	  currentScribble.setColor(Color.RED);
    	  } else if (chooseColor.getSelectedItem() == "blue"){
        	  currentScribble.setColor(Color.BLUE);
    	  } else if (chooseColor.getSelectedItem() == "green"){
        	  currentScribble.setColor(Color.GREEN);
    	  } else if (chooseColor.getSelectedItem() == "yellow"){
        	  currentScribble.setColor(Color.YELLOW);
    	  }
    	  
   }

      // remember point of press for drawing or moving
      lastPoint = point;
   }

   // if in drawing mode, add a new segment to scribble
   // if in moving mode then move it
   public void onMouseDrag(Location point) {
      if (chosenAction == DRAWING) {
         // add new line segment to current scribble
         Line newSegment = new Line(lastPoint, point, canvas);

         currentScribble =
               new Scribble(newSegment, currentScribble);
         
      } else if (chosenAction == MOVING) {
    	  // if dragging, move current scribble
         if (draggingScribble) {
            currentScribble.move(point.getX() - lastPoint.getX(),
                                 point.getY() - lastPoint.getY());
         }
      }
      
      // update for next move or draw
      lastPoint = point;
   }

   public void onMouseRelease(Location point) {
      scribbles = new ScribbleCollection(currentScribble, scribbles);
   }

   // Set mode according to button pressed.
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == setDraw) {
         chosenAction = DRAWING;
         modeLabel.setText(" Current mode: drawing");
      } else if (e.getSource() == setMove) {
         chosenAction = MOVING;
         modeLabel.setText("  Current mode: moving");
      } else if (e.getSource() == setColor) {
          chosenAction = COLORING;
          modeLabel.setText("  Current mode: coloring");
      } else if (e.getSource() == setErase) {
    	  scribbles.getFirst().erase();
          scribbles = scribbles.getRest();
      } 
   }
}
