import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import java.io.FileWriter;
public class ListDemo extends javax.swing.JFrame {
    private JList lstLinks;
    private JPanel panel1;
    private JTextField txtName;
    private JButton btnAdd;
    private JButton btnRemove;
    private JLabel lblName;
    private JButton btnRemoveSelected;
    private JLabel lblMessage;
    private JScrollPane scroll1;
    private JScrollBar scrollBar1;

    DefaultListModel model;
    public ListDemo(){
        add(panel1);
        model = new DefaultListModel();
        lstLinks.setModel(model);
        setSize(600,600);
        setTitle("Swing List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );


        lstLinks.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    URI uri = new URI(txtName.getText());
                    System.out.println(txtName.getText());
                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    if (desktop != null)
                        desktop.browse(uri);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (URISyntaxException use) {
                    use.printStackTrace();
                }
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addElement(txtName.getText());
                lblMessage.setText(txtName.getText() + " " + "linki listeye eklendi.");

                String data = txtName.getText();
                File file = new File("output.txt");
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file, true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                BufferedWriter br = new BufferedWriter(fr);
                try {
                    br.write(data + "\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    br.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fr.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeElement(txtName.getText());
                lblMessage.setText(txtName.getText() + " " + "linki listeden silindi.");
            }
        });

        btnRemoveSelected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lstLinks.getSelectedIndex();
                if (index != -1){
                    model.removeElementAt(index);
                    lblMessage.setText(" ");
                }
                else{
                    lblMessage.setText("Seçili link yok!");
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ListDemo list = new ListDemo();
                list.setVisible(true);
            }
        });
    }
}

