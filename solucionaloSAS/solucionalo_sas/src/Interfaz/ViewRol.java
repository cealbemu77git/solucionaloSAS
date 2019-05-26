package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import objetoUsuario.ObjetoRol;
import objetoUsuario.ObjetoTipoarreglo;
import dataConnection.DataRol;
import dataConnection.DataTipoarreglo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewRol extends JFrame {
	private JTextField txt_codigo;
	private JTextField txt_descripcion;
	private JTextField txt_id;
	private JTable table;
	private ArrayList<ObjetoRol> objetoRolLis;
	private static DataRol conexion = new DataRol();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRol frame = new ViewRol();
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
	public ViewRol() {
		setBounds(100, 100, 450, 363);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel_rol = new JPanel();
		panel_rol.setToolTipText("");
		panel_rol.setBackground(SystemColor.textHighlight);
		panel_rol.setBounds(10, 0, 414, 36);
		getContentPane().add(panel_rol);
		
		JLabel label = new JLabel("Solucionalo SAS");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_rol.add(label);
		
		JPanel panel_rol_dos = new JPanel();
		panel_rol_dos.setBackground(SystemColor.activeCaption);
		panel_rol_dos.setBounds(10, 39, 414, 70);
		getContentPane().add(panel_rol_dos);
		panel_rol_dos.setLayout(null);
		
		JLabel label_1 = new JLabel("C\u00F3digo");
		label_1.setBounds(10, 11, 91, 14);
		panel_rol_dos.add(label_1);
		
		txt_codigo = new JTextField();
		txt_codigo.setColumns(10);
		txt_codigo.setBounds(10, 31, 116, 20);
		panel_rol_dos.add(txt_codigo);
		
		JLabel label_2 = new JLabel("Descripci\u00F3n");
		label_2.setBounds(156, 11, 91, 14);
		panel_rol_dos.add(label_2);
		
		txt_descripcion = new JTextField();
		txt_descripcion.setColumns(10);
		txt_descripcion.setBounds(156, 31, 116, 20);
		panel_rol_dos.add(txt_descripcion);
		
		JLabel label_3 = new JLabel("ID");
		label_3.setBounds(330, 11, 46, 14);
		panel_rol_dos.add(label_3);
		
		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setColumns(10);
		txt_id.setBounds(314, 31, 61, 20);
		panel_rol_dos.add(txt_id);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 414, 91);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiar();
				int col = table.getSelectedRow();
				txt_id.setText(table.getModel().getValueAt(col, 0).toString());
				txt_codigo.setText(table.getModel().getValueAt(col, 1).toString());
				txt_descripcion.setText(table.getModel().getValueAt(col, 2).toString());
			}
		});
		/**
	     * campos de la tabla 
	     *
	     * @return
	     */
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Código", "Descripción", "Objecte"
			}
		));
		scrollPane.setViewportView(table);
		table.removeColumn(table.getColumn("Objecte"));
		
		JLabel lblListaDeRol = new JLabel("Lista de Rol");
		lblListaDeRol.setBounds(10, 163, 93, 14);
		getContentPane().add(lblListaDeRol);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar","Eliminar",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_NO_OPTION){
					ObjetoRol user = (ObjetoRol)table.getModel().getValueAt(table.getSelectedRow(),3);
					try {
						conexion.eliminarRol(user.getIdrol());
						actualizalista();
						limpiar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					conexion.closeConexion();
				}
			}
		});
		btnEliminar.setBounds(319, 282, 105, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idrol = String.valueOf(txt_id.getText());
				String codigo = String.valueOf(txt_codigo.getText());
				String descripcion = String.valueOf(txt_descripcion.getText());
				
				
					try {
						//validarcampos();
						conexion = new DataRol();
						conexion.editarRol(Integer.parseInt(idrol), codigo, descripcion);
						JOptionPane.showMessageDialog(null,"El Rol fue actualizado con exito");
						actualizalista();
						limpiar();
						
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"El Rolno fue actualizado");
						e1.printStackTrace();
					}
				
				
				conexion.closeConexion();
			}
		});
		btnModificar.setBounds(204, 282, 105, 23);
		getContentPane().add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Desea salir de esta ventana de Rol?","Salir",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_NO_OPTION){
					
					dispose();
					conexion.closeConexion();
				}
			}
		});
		btnSalir.setBounds(105, 282, 89, 23);
		getContentPane().add(btnSalir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar(); 
			}
		});
		btnCancelar.setBounds(319, 146, 105, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codigo = String.valueOf(txt_codigo.getText());
				String descripcion = String.valueOf(txt_descripcion.getText());
				
					try {
						validarCampos();
						conexion = new DataRol();
						conexion.insertarRol( codigo, descripcion);
						JOptionPane.showMessageDialog(null,"El Rol fue Ingresado con exito");
						actualizalista();
						limpiar();
						
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"El Rolno fue Ingresado");
						e1.printStackTrace();
					}
				
				
				conexion.closeConexion();
			}
		});
		btnGuardar.setBounds(204, 146, 105, 23);
		getContentPane().add(btnGuardar);
		
		/**
	     * metodo para actualizar los campos de la tabla
	     *
	     * @return
	     */
		try {
			
			actualizalista();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
	/**
     * Metodo para visualizar por medio de filas los valores que hay como resultado de la consulta a la DB
     *
     * @return
     */
		void actualizalista()throws SQLException{
			//Con el objeto Arraylis creado de la clase dataconeccion le envia los valores optenidos el la consulta a usuarios 
			objetoRolLis = conexion.retornaRol();
			
			DefaultTableModel modelo = (DefaultTableModel)table.getModel();
			//Recorre cada uno de los registros obtenidos en la consulta retornausuario
			while (modelo.getRowCount()>0)modelo.removeRow(0); 
			int numcols = modelo.getColumnCount();
			for(ObjetoRol usr:objetoRolLis){
				Object[]fila = new Object[numcols];
				fila[0]=usr.getIdrol();
				fila[1]=usr.getCodigo();
				fila[2]=usr.getDescripcion();
				fila[3]=usr;
				modelo.addRow(fila);
				
				
			}
		
		}
		/**
	     * metodo para limpiar los campos
	     *
	     * @return
	     */
		public void limpiar(){
					txt_codigo.setText("");
					txt_descripcion.setText("");
					txt_id.setText("");
					txt_codigo.requestFocus();
				}
		
		/**
	     * valida los campos obligatorios para la vista
	     *
	     * @return
	     */
	    private boolean validarCampos() {
	        boolean flag = true;

	        if (this.txt_codigo.getText() == null || "".equals(this.txt_codigo.getText().trim()) && flag) {
	        	JOptionPane.showMessageDialog(null,"El campo valor es obligatorio");
	        	txt_codigo.requestFocus();
	            flag = false;
	        }

	        if (this.txt_descripcion.getText() == null || ("".equals(this.txt_descripcion.getText().trim())) && flag) {
	        	JOptionPane.showMessageDialog(null,"El campo garantia es obligatorio");
	        	txt_descripcion.requestFocus();
	            flag = false;
	        }
	        
	        return flag;
	    }

}
