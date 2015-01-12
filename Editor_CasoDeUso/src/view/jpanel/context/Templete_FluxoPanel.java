package view.jpanel.context;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Templete_FluxoPanel extends JPanel{

	private boolean edit;
	private int minDimX, maxDimX, minDimY, maxDimY, idCasoDeUso;
	private Color cor;
	
	public abstract JPanel north(boolean edit);
	public abstract JPanel center(boolean edit);
	public abstract JPanel south(boolean edit);
	
	public Templete_FluxoPanel(boolean edit,  int idCasoDeUso) {		
		this.edit = edit;
		this.idCasoDeUso = idCasoDeUso;
		
		setPanelSize(500,800,350,400);
		this.setPreferredSize(new Dimension(minDimX, maxDimY));
		this.setMinimumSize(new Dimension(minDimX, minDimY));
		this.setMaximumSize(new Dimension(maxDimX, maxDimY));
		setCorFundo(Color.white);
		this.setBackground(cor);
		
		this.setLayout(new BorderLayout());
		if(north(edit) != null)
			this.add( north(edit), BorderLayout.NORTH);
		if(center(edit) != null)
			this.add( center(edit), BorderLayout.CENTER);
		if(south(edit) != null)
			this.add( south(edit), BorderLayout.SOUTH);
	}
	
	//SET
	public void setPanelSize(int minDimX, int maxDimX, int minDimY, int maxDimY) {
		this.minDimX = minDimX;
		this.minDimY = minDimY;
		this.maxDimX = maxDimX;
		this.maxDimY = maxDimY;
	}
	public void setMinDimX(int minDimX) {
		this.minDimX = minDimX;
	}
	public void setMaxDimX(int maxDimX) {
		this.maxDimX = maxDimX;
	}
	public void setMinDimY(int minDimY) {
		this.minDimY = minDimY;
	}
	public void setMaxDimY(int maxDimY) {
		this.maxDimY = maxDimY;
	}
	private void setCorFundo(Color Color) {
		this.cor = Color;
	}
	public void reloadView() {
		removeAll();
		if(north(edit) != null)
			this.add( north(edit), BorderLayout.NORTH);
		if(center(edit) != null)
			this.add( center(edit), BorderLayout.CENTER);
		if(south(edit) != null)
			this.add( south(edit), BorderLayout.SOUTH);
		revalidate();
		repaint();
	}
	
	//GET
	
	public boolean getEdit() {
		return this.edit;
	}
	public int getIdCasoDeUso() {
		return idCasoDeUso;
	}
	
	public int getMinDimX() {
		return minDimX;
	}
	public int getMaxDimX() {
		return maxDimX;
	}
	public int getMinDimY() {
		return minDimY;
	}
	public int getMaxDimY() {
		return maxDimY;
	}
	public Color getCorFundo() {
		return this.cor;
	}
}
