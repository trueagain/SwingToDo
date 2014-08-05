package com.trueagain.swingtodolist;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

class MainPanel extends JPanel {
	private final Persistence persistence;
	private final JTextField inputTextField = new JTextField();
	private final JButton addButton = new JButton("add");
	private final JList<ToDoItem> list;
	private final JPopupMenu popup = new JPopupMenu();
	private boolean isInEditMode = false;
	private int itemToEdit = -1;

	public MainPanel(Persistence _persistence) {
		this.persistence = _persistence;
		this.list = new JList<ToDoItem>(persistence.getListModel());
		setLayout(new GridBagLayout());

		inputTextField.setColumns(12);
		add(inputTextField,
				createGDConstrains(0, 0, 1, 0, GridBagConstraints.BOTH,
						GridBagConstraints.WEST));
		
		inputTextField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					if(isInEditMode){
						saveEdited();
					} else {
						addItem();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

		add(addButton,
				createGDConstrains(1, 0, 0, 0, GridBagConstraints.NONE,
						GridBagConstraints.WEST));

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isInEditMode){
					saveEdited();
				} else {
					addItem();
				}
			}
		});
		
		JMenuItem edit = new JMenuItem("edit");
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				startEdit();
			}

		});
		popup.add(edit);

		JMenuItem removeMenuItem = new JMenuItem("remove");
		removeMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				persistence.removeElementFromListModel(list.getSelectedIndex());
			}

		});
		popup.add(removeMenuItem);
		
		JMenuItem markAsDoneMenuItem = new JMenuItem("mark as done");
		markAsDoneMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				markSelectedAsDone();
			}

		});
		popup.add(markAsDoneMenuItem);
		
		JMenuItem markAsNotDoneYet = new JMenuItem("mark as not done");
		markAsNotDoneYet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				markSelectedAsNotDoneYet();
			}

		});
		popup.add(markAsNotDoneYet);
		
		
		
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent event) {
				if (event.isPopupTrigger()) {
					list.setSelectedIndex(list.locationToIndex(event.getPoint()));
					popup.show(event.getComponent(), event.getX(), event.getY());
				}
			}

		});

		add(list,
				createGDConstrains(0, 1, 1, 0, GridBagConstraints.BOTH,
						GridBagConstraints.WEST));

		JLabel emptyLabelToFillSpace = new JLabel();
		add(emptyLabelToFillSpace,
				createGDConstrains(0, 2, 1, 1, GridBagConstraints.BOTH,
						GridBagConstraints.WEST));
	}
	
	private void markSelectedAsDone(){
		persistence.markAsDone(list.getSelectedIndex());
		list.validate();
		list.repaint();
	}
	
	private void markSelectedAsNotDoneYet(){
		persistence.markAsNotDoneYet(list.getSelectedIndex());
		list.validate();
		list.repaint();
	}
	
	private void addItem(){
		persistence.addElementToListModel(new ToDoItem(inputTextField.getText()));
		inputTextField.setText("");
	}
	
	private void startEdit(){
		inputTextField.setText(persistence.getListModel().getElementAt(list.getSelectedIndex()).toClearString());
		isInEditMode = true;
		itemToEdit = list.getSelectedIndex();
		addButton.setText("save");
		list.validate();
		list.repaint();
	}
	
	private void saveEdited(){
		persistence.updateListModelElementName(itemToEdit, inputTextField.getText());
		isInEditMode = false;
		itemToEdit = -1;
		addButton.setText("add");
		inputTextField.setText("");
		list.validate();
		list.repaint();
	}

	private GridBagConstraints createGDConstrains(int gridx, int gridy,
			double weightx, double weighty, int fill, int anchor) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.fill = fill;
		constraints.anchor = anchor;
		return constraints;
	}
}
