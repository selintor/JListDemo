import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateWindow extends javax.swing.JFrame{
    private JTextField txtUpdate;
    private JButton btnSubmit;
    private JLabel lblTitle;
    private JPanel panel2;
    private JList lstUpdate;
    private JButton btnUpdate;
    private JLabel lblUpdate;

    DefaultListModel model;
    public UpdateWindow(int id){
        add(panel2);
        model = new DefaultListModel();
        lstUpdate.setModel(model);
        setSize(600,400);
        setTitle("Update List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = txtUpdate.getText();
                model.addElement(data);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newData = txtUpdate.getText();
                int index = lstUpdate.getSelectedIndex();
                if (index != -1){
                    try {
                        UpdateDb.updateSql(id,newData);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    lblUpdate.setText(newData + " " + "linki veritabanına eklendi.");
                }
                else{
                    lblUpdate.setText("Seçili link yok!");
                }
            }
        });
    }
}
