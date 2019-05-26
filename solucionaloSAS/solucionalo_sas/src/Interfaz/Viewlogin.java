package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import objetoUsuario.ObjetoUsuario;
import dataConnection.DataConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.Normalizer.Form;

public class Viewlogin extends JFrame {

	private JPanel contentPane;
	private JTextField txt_usuario;
	private JTextField txt_passware;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewlogin frame = new Viewlogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Viewlogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setToolTipText("");
		panel.setBackground(SystemColor.textHighlight);
		
		JLabel label = new JLabel("Solucionalo SAS");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(label);
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		txt_usuario = new JTextField();
		txt_usuario.setColumns(10);
		
		JLabel lblPassware = new JLabel("Passware");
		
		txt_passware = new JTextField();
		txt_passware.setColumns(10);
		
		/*
		 * se utiliza el boton para ingresar 
		 */
		
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			
			private DataConnection datosdeconexion;
			public void actionPerformed(ActionEvent e) {
				String usuario = String.valueOf(txt_usuario.getText());
				String passwre = String.valueOf(txt_passware.getText());
				
				
				try {
				
					datosdeconexion = new DataConnection();
					boolean existe = datosdeconexion.conectarUsuario(usuario, passwre);
					if(!existe){
						JOptionPane.showMessageDialog(null,"El usuario ingresado No existe");
						return; // para que no se ejecute mas codigo para abajo
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.err.println("error ");
				}
				if(validarCampos()){
					Menu men = new Menu();
					men.setVisible(true);
				}
				//se hace visible el otro formulario
				
				
			}
			
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 566, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txt_passware, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(btnIngresar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalir))
						.addComponent(lblPassware, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(lblPassware))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_passware, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIngresar)
						.addComponent(btnSalir))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	/**
     * valida los campos obligatorios para la vista
     *
     * @return
     */
    private boolean validarCampos() {
        boolean flag = true;

        if (this.txt_usuario.getText() == null || "".equals(this.txt_usuario.getText().trim()) && flag) {
        	JOptionPane.showMessageDialog(null,"El campo usuario es obligatorio");
        	txt_usuario.requestFocus();
            flag = false;
        }

        if (this.txt_passware.getText() == null || ("".equals(this.txt_passware.getText().trim())) && flag) {
        	JOptionPane.showMessageDialog(null,"El campo passware es obligatorio");
        	txt_passware.requestFocus();
            flag = false;
        }
        
        return flag;
    }
}

