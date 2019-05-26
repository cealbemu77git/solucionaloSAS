package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

import objetoUsuario.ObjetoReparacion;
import objetoUsuario.ObjetoRol;
import objetoUsuario.ObjetoTipoarreglo;
import objetoUsuario.ObjetoUsuario;
import dataConnection.DataConnection;
import dataConnection.DataReparacion;
import dataConnection.DataRol;
import dataConnection.DataTipoarreglo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewReparacion extends JFrame {

	private JPanel contentPane;
	private JTextField txt_valor;
	private JTextField txt_garantia;
	private JTextField txt_observaciones;
	private JTable table_reparacion;
	private JTextField txt_id;
	private ArrayList<ObjetoReparacion> objetoReparacionLis;
	private static DataReparacion conexion = new DataReparacion();
	private JComboBox comboBox_tipoarreglo;
	private static DataTipoarreglo conoexionTipoArreglo = new DataTipoarreglo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewReparacion frame = new ViewReparacion();
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
	public ViewReparacion() {
		setTitle("Reparaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 557, 36);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setToolTipText("");
		panel.setBackground(SystemColor.textHighlight);
		
		JLabel label = new JLabel("Solucionalo SAS");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(label);
		contentPane.setLayout(null);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(5, 43, 557, 123);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTipoArreglo = new JLabel("Tipo arreglo");
		lblTipoArreglo.setBounds(10, 11, 91, 14);
		panel_1.add(lblTipoArreglo);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(144, 11, 91, 14);
		panel_1.add(lblValor);
		
		txt_valor = new JTextField();
		txt_valor.setBounds(144, 31, 116, 20);
		panel_1.add(txt_valor);
		txt_valor.setColumns(10);
		
		JLabel lblGarantia = new JLabel("Garantia");
		lblGarantia.setBounds(278, 11, 91, 14);
		panel_1.add(lblGarantia);
		
		txt_garantia = new JTextField();
		txt_garantia.setBounds(278, 31, 116, 20);
		panel_1.add(txt_garantia);
		txt_garantia.setColumns(10);
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(412, 11, 91, 14);
		panel_1.add(lblObservaciones);
		
		txt_observaciones = new JTextField();
		txt_observaciones.setBounds(412, 31, 116, 20);
		panel_1.add(txt_observaciones);
		txt_observaciones.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(31, 65, 25, 14);
		panel_1.add(lblId);
		
		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setBounds(66, 62, 60, 20);
		panel_1.add(txt_id);
		txt_id.setColumns(10);
		
		comboBox_tipoarreglo = new JComboBox();
		comboBox_tipoarreglo.setBounds(10, 31, 116, 20);
		panel_1.add(comboBox_tipoarreglo);
		try{
			listaCombobox();
			}catch(SQLException ex){
				System.err.println(ex.getMessage());
			}
		
		JScrollPane scrollPane_reparacion = new JScrollPane();
		scrollPane_reparacion.setBounds(5, 211, 557, 115);
		contentPane.add(scrollPane_reparacion);
		
		table_reparacion = new JTable();
		table_reparacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiar();
				int col = table_reparacion.getSelectedRow();
				txt_id.setText(table_reparacion.getModel().getValueAt(col, 0).toString());
				txt_valor.setText(table_reparacion.getModel().getValueAt(col, 2).toString());
				txt_garantia.setText(table_reparacion.getModel().getValueAt(col, 3).toString());
				txt_observaciones.setText(table_reparacion.getModel().getValueAt(col, 4).toString());
				
			}
		});
		/**
	     * campos de la tabla 
	     *
	     * @return
	     */
		table_reparacion.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Tipo de arreglo", "Valor", "Garantia", "Observaciones", "Objecte"
			}
		));
		scrollPane_reparacion.setViewportView(table_reparacion);
		table_reparacion.removeColumn(table_reparacion.getColumn("Objecte"));
		
		
		/**
	     * boton para cancelar
	     *
	     * @return
	     */
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar(); 
			}
		});
		btnCancelar.setBounds(449, 177, 113, 23);
		contentPane.add(btnCancelar);
		
		/**
	     * boton para guardar registros 
	     *
	     * @return
	     */
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String valor = String.valueOf(txt_valor.getText());
				String garantia = String.valueOf(txt_garantia.getText());
				String observacion = String.valueOf(txt_observaciones.getText());
				ObjetoTipoarreglo obj = (ObjetoTipoarreglo)comboBox_tipoarreglo.getSelectedItem();
				
				
					try {
						validarCampos();
						conexion = new DataReparacion();
						conexion.insertarReparacion( obj.getIdtipoarreglo(),Integer.parseInt(valor) , garantia, observacion);
						JOptionPane.showMessageDialog(null,"La reparacion fue guardada con exito");
						actualizalista();
						limpiar();
						
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"La reparacion no fue guardada");
						e1.printStackTrace();
					}
				
				
				conexion.closeConexion();
			}
		});
		btnGuardar.setBounds(331, 177, 108, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblListaDeReparaciones = new JLabel("Lista de Reparaciones");
		lblListaDeReparaciones.setBounds(10, 196, 151, 14);
		contentPane.add(lblListaDeReparaciones);
		/**
	     * blton para eliminar registros 
	     *
	     * @return
	     */
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar","Eliminar",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_NO_OPTION){
					ObjetoReparacion user = (ObjetoReparacion)table_reparacion.getModel().getValueAt(table_reparacion.getSelectedRow(),5);
					try {
						conexion.eliminarReparacion(user.getIdreparaciones());
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
		btnEliminar.setBounds(449, 337, 113, 23);
		contentPane.add(btnEliminar);
		
		/**
	     * boton para modificar registros 
	     *
	     * @return
	     */
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String idreparaciones = String.valueOf(txt_id.getText());
				ObjetoTipoarreglo obj = (ObjetoTipoarreglo)comboBox_tipoarreglo.getSelectedItem();
				String valor = String.valueOf(txt_valor.getText());
				String garantia = String.valueOf(txt_garantia.getText());
				String observacion = String.valueOf(txt_observaciones.getText());
				
				
					try {
						//validarcampos();
						conexion = new DataReparacion();
						conexion.editarReparacion(Integer.parseInt(idreparaciones), obj.getIdtipoarreglo(),Integer.parseInt(valor) , garantia, observacion);
						JOptionPane.showMessageDialog(null,"La reparacion fue actualizada con exito");
						actualizalista();
						limpiar();
						
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"La reparacion no fue actualizada");
						e1.printStackTrace();
					}
				
				
				conexion.closeConexion();
			}
		});
		btnModificar.setBounds(331, 337, 108, 23);
		contentPane.add(btnModificar);
		
		/**
	     * boton para cerrar la ventana
	     *
	     * @return
	     */
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int respuesta = JOptionPane.showConfirmDialog(null, "Desea salir de esta ventana de Reparaciones?","Salir",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_NO_OPTION){
					
					dispose();
					conexion.closeConexion();
				}
			}
		});
		btnSalir.setBounds(232, 337, 89, 23);
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
		private void listaCombobox()throws SQLException{
			//Con el objeto Arraylis creado de la clase dataconeccion le envia los valores optenidos el la consulta a usuarios 
			comboBox_tipoarreglo.setModel(getComboboxModel(conoexionTipoArreglo.retornaTipoarreglo()));
		}
		
		private DefaultComboBoxModel<ObjetoTipoarreglo> getComboboxModel(List<ObjetoTipoarreglo> tipoArreglo){
			ObjetoTipoarreglo[] comboboxModel = tipoArreglo.toArray(new ObjetoTipoarreglo[0]);
			return new DefaultComboBoxModel<ObjetoTipoarreglo>(comboboxModel);
		}
	
	/**
     * Metodo para visualizar por medio de filas los valores que hay como resultado de la consulta a la DB
     *
     * @return
     */
		void actualizalista()throws SQLException{
			//Con el objeto Arraylis creado de la clase dataconeccion le envia los valores optenidos el la consulta a usuarios 
			objetoReparacionLis = conexion.retornaReparacion();
			
			DefaultTableModel modelo = (DefaultTableModel)table_reparacion.getModel();
			//Recorre cada uno de los registros obtenidos en la consulta retornausuario
			while (modelo.getRowCount()>0)modelo.removeRow(0); 
			int numcols = modelo.getColumnCount();
			for(ObjetoReparacion usr:objetoReparacionLis){
				Object[]fila = new Object[numcols];
				fila[0]=usr.getIdreparaciones();
				fila[1]=usr.getIdtipoarreglo();
				fila[2]=usr.getValor();
				fila[3]=usr.getGarantia();
				fila[4]=usr.getObservacion();
				fila[5]=usr;
				modelo.addRow(fila);
				
				
			}
		
		}
		/**
	     * metodo para limpiar los campos
	     *
	     * @return
	     */
		public void limpiar(){
					txt_valor.setText("");
					txt_garantia.setText("");
					txt_observaciones.setText("");
					txt_id.setText("");
					txt_valor.requestFocus();
				}
		
		/**
	     * valida los campos obligatorios para la vista
	     *
	     * @return
	     */
	    private boolean validarCampos() {
	        boolean flag = true;

	        if (this.txt_valor.getText() == null || "".equals(this.txt_valor.getText().trim()) && flag) {
	        	JOptionPane.showMessageDialog(null,"El campo valor es obligatorio");
	        	txt_valor.requestFocus();
	            flag = false;
	        }

	        if (this.txt_garantia.getText() == null || ("".equals(this.txt_garantia.getText().trim())) && flag) {
	        	JOptionPane.showMessageDialog(null,"El campo garantia es obligatorio");
	        	txt_garantia.requestFocus();
	            flag = false;
	        }
	        if (this.txt_observaciones.getText() == null || ("".equals(this.txt_observaciones.getText().trim())) && flag) {
	        	JOptionPane.showMessageDialog(null,"El campo observaciones es obligatorio");
	        	txt_observaciones.requestFocus();
	            flag = false;
	        }
	        
	        return flag;
	    }
}
