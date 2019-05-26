package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import objetoUsuario.ObjetoReparacion;
import objetoUsuario.ObjetoTipoarreglo;
import dataConnection.DataReparacion;
import dataConnection.DataTipoarreglo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewTipoAreglo extends JFrame {

	private JPanel contentPane;
	private JTextField txt_codigo;
	private JTextField txt_descripcion;
	private JTextField txt_id;
	private ArrayList<ObjetoTipoarreglo> objetoTipoarregloLis;
	private static DataTipoarreglo conexion = new DataTipoarreglo();
	private JTable table_tipoarreglo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTipoAreglo frame = new ViewTipoAreglo();
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
	public ViewTipoAreglo() {
		setTitle("Tipo de Arreglo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 416, 36);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setToolTipText("");
		panel.setBackground(SystemColor.textHighlight);
		
		JLabel label_2 = new JLabel("Solucionalo SAS");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(label_2);
		contentPane.setLayout(null);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(5, 52, 416, 76);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 11, 91, 14);
		panel_1.add(lblCdigo);
		
		txt_codigo = new JTextField();
		txt_codigo.setBounds(10, 31, 116, 20);
		panel_1.add(txt_codigo);
		txt_codigo.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(156, 11, 91, 14);
		panel_1.add(lblDescripcin);
		
		txt_descripcion = new JTextField();
		txt_descripcion.setBounds(156, 31, 116, 20);
		panel_1.add(txt_descripcion);
		txt_descripcion.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(330, 11, 46, 14);
		panel_1.add(lblId);
		
		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setBounds(314, 31, 61, 20);
		panel_1.add(txt_id);
		txt_id.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 173, 416, 69);
		contentPane.add(scrollPane);
		
		table_tipoarreglo = new JTable();
		table_tipoarreglo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiar();
				int col = table_tipoarreglo.getSelectedRow();
				txt_id.setText(table_tipoarreglo.getModel().getValueAt(col, 0).toString());
				txt_codigo.setText(table_tipoarreglo.getModel().getValueAt(col, 1).toString());
				txt_descripcion.setText(table_tipoarreglo.getModel().getValueAt(col, 2).toString());
			}
		});
		/**
	     * campos de la tabla 
	     *
	     * @return
	     */
		table_tipoarreglo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Código", "Descripción", "Objecte"
			}
		));
		scrollPane.setViewportView(table_tipoarreglo);
		table_tipoarreglo.removeColumn(table_tipoarreglo.getColumn("Objecte"));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar(); 
			}
		});
		btnCancelar.setBounds(312, 139, 109, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codigo = String.valueOf(txt_codigo.getText());
				String descripcion = String.valueOf(txt_descripcion.getText());
				
				
					try {
						validarCampos();
						conexion = new DataTipoarreglo();
						conexion.insertarTipoarreglo( codigo, descripcion);
						JOptionPane.showMessageDialog(null,"El tipo de Arreglo fue insertado con exito");
						actualizalista();
						limpiar();
						
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"El tipo de Arreglo no fue insertado");
						e1.printStackTrace();
					}
				
				
				conexion.closeConexion();
			}
		});
		btnGuardar.setBounds(193, 139, 109, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblListaDeArreglos = new JLabel("Lista de Arreglos");
		lblListaDeArreglos.setBounds(5, 158, 150, 14);
		contentPane.add(lblListaDeArreglos);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar","Eliminar",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_NO_OPTION){
					ObjetoTipoarreglo user = (ObjetoTipoarreglo)table_tipoarreglo.getModel().getValueAt(table_tipoarreglo.getSelectedRow(),3);
					try {
						conexion.eliminarTipoarreglo(user.getIdtipoarreglo());
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
		btnEliminar.setBounds(312, 253, 109, 23);
		contentPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idtipoarreglo = String.valueOf(txt_id.getText());
				String codigo = String.valueOf(txt_codigo.getText());
				String descripcion = String.valueOf(txt_descripcion.getText());
				
				
					try {
						//validarcampos();
						conexion = new DataTipoarreglo();
						conexion.editarTipoarreglo(Integer.parseInt(idtipoarreglo), codigo, descripcion);
						JOptionPane.showMessageDialog(null,"El tipo de Arreglo fue actualizado con exito");
						actualizalista();
						limpiar();
						
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"El tipo de Arreglo no fue actualizado");
						e1.printStackTrace();
					}
				
				
				conexion.closeConexion();
			}
		});
		btnModificar.setBounds(193, 253, 109, 23);
		contentPane.add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Desea salir de esta ventana de Tipoarreglo?","Salir",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_NO_OPTION){
					
					dispose();
					conexion.closeConexion();
				}
			}
		});
		btnSalir.setBounds(94, 253, 89, 23);
		contentPane.add(btnSalir);
		
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
			objetoTipoarregloLis = conexion.retornaTipoarreglo();
			
			DefaultTableModel modelo = (DefaultTableModel)table_tipoarreglo.getModel();
			//Recorre cada uno de los registros obtenidos en la consulta retornausuario
			while (modelo.getRowCount()>0)modelo.removeRow(0); 
			int numcols = modelo.getColumnCount();
			for(ObjetoTipoarreglo usr:objetoTipoarregloLis){
				Object[]fila = new Object[numcols];
				fila[0]=usr.getIdtipoarreglo();
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
