import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class AppWindow extends JFrame implements ActionListener{
    private JButton button = new JButton();
    private JPanel jPanel = new JPanel();
    private JLabel jLabel = new JLabel();
    private String file_path = "";

    public AppWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        button.addActionListener(this);

        button.setText("Select File");

        this.add(button);

        jLabel.setText("\nExtension : ");
        jPanel.add(jLabel);
        this.add(jPanel);
        this.pack();
        this.setVisible(true);
    }

    public String getPath() {
        return file_path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                file_path = fileChooser.getSelectedFile().getAbsolutePath();
            }else{
                System.out.println("File-Chosing Canceled");
            }
            FileInfo fileInfo = new FileInfo(file_path);
            jLabel.setText("\nExtension : " + fileInfo.getExtension());
        }
    }
}
