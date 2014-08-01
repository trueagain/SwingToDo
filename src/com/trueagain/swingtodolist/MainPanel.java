package com.trueagain.swingtodolist;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

class MainPanel extends JPanel {
	private final JTextField inputTextField = new JTextField();
	final DefaultListModel<ToDoItem> listModel = new DefaultListModel<ToDoItem>();
	final JList<ToDoItem> list = new JList<ToDoItem>(listModel);
	final JPopupMenu popup = new JPopupMenu();
	
	public MainPanel() {
		setLayout(new GridBagLayout());
		
		inputTextField.setColumns(12);
		add(inputTextField, createGDConstrains(0, 0, 1, 0, GridBagConstraints.BOTH, 
				GridBagConstraints.WEST));

		JButton addButton = new JButton("add");
		add(addButton, createGDConstrains(1, 0, 0, 0, GridBagConstraints.NONE, 
				GridBagConstraints.WEST));
		
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel.addElement(new ToDoItem(inputTextField.getText()));
				inputTextField.setText("");
			}
		});
		
		JMenuItem item = new JMenuItem("remove");
		item.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel.remove(list.getSelectedIndex());
			}
			
		});
		popup.add(item);
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}

			public void mouseReleased(MouseEvent event) {
				if (event.isPopupTrigger()){
					list.setSelectedIndex(list.locationToIndex(event.getPoint()));
					popup.show(event.getComponent(), event.getX(), event.getY());
				}
			}

		});

		add(list, createGDConstrains(0, 1, 1, 0, GridBagConstraints.BOTH, 
				GridBagConstraints.WEST));

		JLabel emptyLabelToFillSpace = new JLabel();
		add(emptyLabelToFillSpace, createGDConstrains(1, 2, 1, 1, GridBagConstraints.BOTH, 
				GridBagConstraints.WEST));
	}
	
	private GridBagConstraints createGDConstrains(int gridx, int gridy, 
			double weightx, double weighty,
			int fill, int anchor){
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

