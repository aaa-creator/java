import objectdraw.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
 * This program allows users to create text on the screen, change the font and size of the text and remove the text
 * Author: Daniel Cogan
 */
public class TextPlay extends WindowController implements ActionListener, ChangeListener{
	
	//Declare all the GUI components
	JPanel outPanel, inPanel;
	JTextField textField;
	JComboBox fontMenu;
	JButton removeButton;
	JSlider fontSizeSlider;
	JLabel fontSizeLabel;
	
	//Declare the last text variable and it's properties (size and font)
	Text lastText;
	String currentFont;
	int fontSize = 14;

	public void begin(){resize(340, 420);

		//Create all GUI components
		textField = new JTextField("Enter text to be displayed here");
			textField.addActionListener(this);
		removeButton = new JButton("Remove last text");
			removeButton.addActionListener (this); 
		fontMenu = new JComboBox();
			fontMenu.addItem("Courier");
			fontMenu.addItem("Stencil");
			fontMenu.addItem("Zapfino");
			fontMenu.addItem("Times");
			fontMenu.addActionListener (this);
		inPanel = new JPanel(new GridLayout(0, 2));
			inPanel.add(removeButton);
			inPanel.add(fontMenu);
		fontSizeSlider = new JSlider(10, 48, fontSize);
			fontSizeSlider.addChangeListener(this);
		fontSizeLabel = new JLabel("Current font size: " + fontSize, JLabel.CENTER);
		outPanel = new JPanel(new GridLayout(5, 0));
			outPanel.add(textField);
			outPanel.add(inPanel);
			outPanel.add(fontSizeSlider);
			outPanel.add(fontSizeLabel);
		
		//Add the outside panel to the window controller
		getContentPane().add (outPanel, BorderLayout.SOUTH);
		validate();
		
	}
	
	public void setFont(){
		
		//Set the font of the last text to whatever in selected in the font menu
		if (fontMenu.getSelectedItem() == "Courier"){
			currentFont = "Courier";
		}else if (fontMenu.getSelectedItem() == "Stencil"){
			currentFont = "Stencil";
		}else if (fontMenu.getSelectedItem() == "Zapfino"){
			currentFont = "Zapfino";
		}else if (fontMenu.getSelectedItem() == "Times"){
			currentFont = "Times";
		}lastText.setFont(currentFont);
	}
	
	public void onMouseClick(Location point){
		
		//Create new text on a mouse click and set it's font and size
		lastText = new Text(textField.getText(), point, canvas);
		setFont();
		lastText.setFontSize(fontSize);
	}
	
	public void actionPerformed(ActionEvent evt) {
		
		//Remove the last text if the source s from the remove button
		//Set the font of last text if the source is from the font menu
		if (evt.getSource() == removeButton){
			lastText.removeFromCanvas();
		}else if (evt.getSource() == fontMenu){
			setFont();
		}
		
	}

	public void stateChanged(ChangeEvent event) {
		
		//Set the font size and change the font label text if the slider is moved
		fontSize = fontSizeSlider.getValue();
		fontSizeLabel.setText("Current font size: " + fontSize);
		lastText.setFontSize(fontSize);
		
	}

}
