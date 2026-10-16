package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import modelos.TipoEnvio;

public class EnviosVista extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtCodigo, txtCliente, txtPeso, txtDistancia;
    private JComboBox cmbTipoEnvio;

    private JButton btnAgregarEnvio, btnRetirarEnvio, btnGuardarEnvio;

    public EnviosVista() {
        setTitle("Operador Logístico");
        setIconImage(new ImageIcon(getClass().getResource("/iconos/Icono.png")).getImage());
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tbEnvios = new JToolBar();

        btnAgregarEnvio = new JButton();
        btnAgregarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/AgregarEnvio.png")));
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.addActionListener(evt -> btnAgregarEnvioClick());
        tbEnvios.add(btnAgregarEnvio);

        btnRetirarEnvio = new JButton();
        btnRetirarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/RetirarEnvio.png")));
        btnRetirarEnvio.setToolTipText("Retirar Envío");
        tbEnvios.add(btnRetirarEnvio);

        // Contenedor principal con BoxLayout (vertical)
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));

        // Panel de edición (oculto por defecto)
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 100));
        pnlEditarEnvio.setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtCodigo);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox();
        cmbTipoEnvio.setBounds(320, 10, 100, 25);
        DefaultComboBoxModel mdlTipoEnvio = new DefaultComboBoxModel(TipoEnvio.values());
        cmbTipoEnvio.setModel(mdlTipoEnvio);
        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(220, 40, 110, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(330, 40, 90, 25);
        pnlEditarEnvio.add(txtDistancia);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(330, 70, 100, 25);
        btnCancelarEnvio.addActionListener(evt -> btnCancelarEnvioClick());
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false); // Se oculta al inicio

        // Tabla de envíos (siempre visible)
        tblEnvios = new JTable();
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        pnlEnvios.add(pnlEditarEnvio);
        pnlEnvios.add(spListaEnvios);

        JScrollPane spEnvios = new JScrollPane(pnlEnvios);
        spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(tbEnvios, BorderLayout.NORTH);
        add(spEnvios, BorderLayout.CENTER);
    }

    // getters (datos ingresados por el usuario)
    public TipoEnvio getTipoSeleccionado() {
        return (TipoEnvio) cmbTipoEnvio.getSelectedItem();
    }

    public String getCodigo() {
        return txtCodigo.getText();
    }

    public String getCliente() {
        return txtCliente.getText();
    }

    public double getPeso() {
        try {
            return Double.parseDouble(txtPeso.getText());
        } catch (Exception ex) {
            return 0;
        }
    }

    public double getDistancia() {
        try {
            return Double.parseDouble(txtDistancia.getText());
        } catch (Exception ex) {
            return 0;
        }
    }

    // setters de eventos (el controlador los conecta)
    public void setGuardarEnvioClick(ActionListener evento) {
        btnGuardarEnvio.addActionListener(evento);
    }

    public void setRetirarEnvioClick(ActionListener evento) {
        btnRetirarEnvio.addActionListener(evento);
    }

    // metodos publicos (el controlador los invoca)
    public void mostrarEnvios(String[][] datos, String[] encabezados) {
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblEnvios.setModel(dtm);
    }

    public void ocultarEdicionEnvio() {
        limpiarFormulario();
        pnlEditarEnvio.setVisible(false);
    }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipoEnvio.setSelectedIndex(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public String pedirCodigoRetiro() {
        return JOptionPane.showInputDialog(this,
                "Ingrese el código del envío a retirar:", "Retirar envío",
                JOptionPane.QUESTION_MESSAGE);
    }

    // eventos privados (solo interfaz, sin logica de negocio)
    private void btnAgregarEnvioClick() {
        pnlEditarEnvio.setVisible(true);
    }

    private void btnCancelarEnvioClick() {
        ocultarEdicionEnvio();
    }

}