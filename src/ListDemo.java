import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import java.sql.SQLException;
import java.util.Map;

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
    private JButton btnHtml;
    private JButton btnUpdate;
    DefaultListModel model;

    public ListDemo(){
        add(panel1);
        model = new DefaultListModel();
        lstLinks.setModel(model);
        setSize(600,600);
        setTitle("Swing List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );

        //IT'S FOR THE LIST ELEMENTS IN GUI TO MAKE THEM CLICKABLE
/*        lstLinks.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String data = txtName.getText();
                try {
                    URI uri = new URI(data);
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
        });*/

        btnHtml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = txtName.getText();
                String urlTemplate = "<a href=\"X\" class = \"links\"> X </a><br><br>";
                File file = new File("links.html");
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file, true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                BufferedWriter br = new BufferedWriter(fr);
                try {
                    br.write((urlTemplate.replace("X", data)));
                    br.write("\n");
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
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String data = txtName.getText();
                try {
                    AddDb.sqlAdd(data);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                int size = lstLinks.getModel().getSize();
                if (size < 10){
                    //THE REASON THAT I SET LIMIT 20 IS I WANTED TO MAKE IT EASIER FOR MY TESTS
                    String cutString = data.substring(0, 20);
                    model.addElement(cutString);
                    lblMessage.setText(cutString+ " " + "linki listeye ve veritabanına eklendi.");
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = txtName.getText();
                model.removeElement(data);
                lblMessage.setText(data + " " + "linki listeden silindi.");
            }
        });

        btnRemoveSelected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lstLinks.getSelectedIndex();
                String data = txtName.getText();
                if (index != -1){
                    try {
                        DeleteDb.deleteSql(data);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    lblMessage.setText(data + " " + "linki veritabanından silindi.");
                }
                else{
                    lblMessage.setText("Seçili link yok!");
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldData = txtName.getText();
                int index = lstLinks.getSelectedIndex();
                int id;
                if (index != -1){
                    try {
                        id = GetSQLId.getId(oldData);
                        UpdateWindow updateWindow = new UpdateWindow(id);
                        updateWindow.setVisible(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    lblMessage.setText(oldData + " " + "linki veritabanında güncellenecek.");
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

