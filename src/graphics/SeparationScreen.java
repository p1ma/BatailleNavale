package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import game.Configuration;
import game.Game;
import graphics.listener.BoardController;

public class SeparationScreen extends JPanel {

	public SeparationScreen(final int unit) {
		this.setBackground(Color.WHITE);
		
		// SIZE
		this.setPreferredSize(new Dimension(unit, unit * Configuration.HEIGHT));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
}
