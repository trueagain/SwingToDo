package com.trueagain.swingtodolist;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private final JList<ToDoItem> list;
	private final JPopupMenu popup = new JPopupMenu();
	

	public MainPanel(Persistence _persistence) {
		this.persistence = _persistence;
		this.list = new JList<ToDoItem>(persistence.getListModel());
		setLayout(new GridBagLayout());

		inputTextField.setColumns(12);
		add(inputTextField,
				createGDConstrains(0, 0, 1, 0, GridBagConstraints.BOTH,
						GridBagConstraints.WEST));

		JButton addButton = new JButton("add");
		add(addButton,
				createGDConstrains(1, 0, 0, 0, GridBagConstraints.NONE,
						GridBagConstraints.WEST));

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				persistence.addElementToListModel(new ToDoItem(inputTextField.getText()));
				inputTextField.setText("");
			}
		});

		JMenuItem item = new JMenuItem("remove");
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				persistence.removeElementFromListModel(list.getSelectedIndex());
			}

		});
		popup.add(item);
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
				createGDConstrains(1, 2, 1, 1, GridBagConstraints.BOTH,
						GridBagConstraints.WEST));
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
