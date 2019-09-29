import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import java.awt.Color;
import java.awt.Font;

public class mainActivity {

    private JFrame frame;
    private JLabel txtStylo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainActivity window = new mainActivity();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public String sendRequest(String content, String style) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("python "+System.getProperty("user.dir")+"\\src\\resources\\api.py " + content + " " + style);
        //Comment out above line and uncomment below line when converting to jar
        //Process p = Runtime.getRuntime().exec("python api.py");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        System.out.println(System.getProperty("user.dir"));
        String s;
        String url = null;
        TimeUnit.SECONDS.sleep(10);
        while ((s = stdInput.readLine()) != null) {
        	//            System.out.println(s);
            url = "" + s;
        }
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            //            System.out.println(s);
        }

        //        System.exit(0);
        //		s = stdInput.readLine();
        System.out.println(url);
        return url;
    }

    /**
     * Create the application.
     */
    public mainActivity() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 0, 51));
        frame.getContentPane().setForeground(new Color(255, 255, 204));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel m = new JLabel("no file selected");
        JButton btnNewButton_1 = new JButton("Choose Style");
        JLabel l = new JLabel("no file selected");
        JButton btnChooseContentImage = new JButton("Choose Content");

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String com = evt.getActionCommand();

                if (com.equals("save")) {
                    // create an object of JFileChooser class 
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                    // invoke the showsSaveDialog function to show the save dialog 
                    int r = j.showSaveDialog(null);

                    // if the user selects a file 
                    if (r == JFileChooser.APPROVE_OPTION) {
                        // set the label to the path of the selected file 
                        m.setText(j.getSelectedFile().getAbsolutePath());
                    }
                    // if the user cancelled the operation 
                    else
                        m.setText("the user cancelled the operation");
                }

                // if the user presses the open dialog show the open dialog 
                else {
                    // create an object of JFileChooser class 
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                    // invoke the showsOpenDialog function to show the save dialog 
                    int r = j.showOpenDialog(null);

                    // if the user selects a file 
                    if (r == JFileChooser.APPROVE_OPTION) {
                        // set the label to the path of the selected file 
                        m.setText(j.getSelectedFile().getAbsolutePath());
                    }
                    // if the user cancelled the operation 
                    else {
                        m.setText("the user cancelled the operation");
                    }
                }
                if (m.getText() != "the user cancelled the operation" && m.getText() != "no file selected" && l.getText() != "the user cancelled the operation" && l.getText() != "no file selected") {
                    String path = null;
                    try {
                        path = sendRequest(l.getText(), m.getText());
                    } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    URL url = null;
                    BufferedImage image = null;
                    try {
                        url = new URL(path);
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        image = ImageIO.read(url);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    JDialog dialog = new JDialog(new javax.swing.JFrame(), true);
                    JLabel label = new JLabel(new ImageIcon(image));
                    dialog.add(label);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        btnNewButton_1.setBounds(150, 184, 143, 30);
        frame.getContentPane().add(btnNewButton_1);

        //		JPanel p = new JPanel(); 

        // add buttons to the frame 
        //        p.add(btnNewButton_1); 
        //        p.add(btnNewButton_1); 

        // set the label to its initial value 

        btnChooseContentImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // if the user presses the save button show the save dialog 
                String com = evt.getActionCommand();

                if (com.equals("save")) {
                    // create an object of JFileChooser class 
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                    // invoke the showsSaveDialog function to show the save dialog 
                    int r = j.showSaveDialog(null);

                    // if the user selects a file 
                    if (r == JFileChooser.APPROVE_OPTION) {
                        // set the label to the path of the selected file 
                        l.setText(j.getSelectedFile().getAbsolutePath());
                    }
                    // if the user cancelled the operation 
                    else
                        l.setText("the user cancelled the operation");
                }

                // if the user presses the open dialog show the open dialog 
                else {
                    // create an object of JFileChooser class 
                    JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                    // invoke the showsOpenDialog function to show the save dialog 
                    int r = j.showOpenDialog(null);

                    // if the user selects a file 
                    if (r == JFileChooser.APPROVE_OPTION) {
                        // set the label to the path of the selected file 
                        l.setText(j.getSelectedFile().getAbsolutePath());
                    }
                    // if the user cancelled the operation 
                    else {
                        l.setText("the user cancelled the operation");
                    }
                }
                if (m.getText() != "the user cancelled the operation" && m.getText() != "no file selected" && l.getText() != "the user cancelled the operation" && l.getText() != "no file selected") {
                    String path = null;
                    try {
                        path = sendRequest(l.getText(), m.getText());
                    } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    URL url = null;
                    BufferedImage image = null;
                    try {
                        url = new URL(path);
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        image = ImageIO.read(url);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    JDialog dialog = new JDialog(new javax.swing.JFrame(), true);
                    JLabel label = new JLabel(new ImageIcon(image));
                    dialog.add(label);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        btnChooseContentImage.setBounds(148, 142, 145, 30);
        frame.getContentPane().add(btnChooseContentImage);

        txtStylo = new JLabel();
        txtStylo.setBackground(new Color(0, 0, 51));
        txtStylo.setFont(new Font("Tahoma", Font.PLAIN, 26));
        txtStylo.setForeground(new Color(255, 255, 204));
        txtStylo.setHorizontalAlignment(SwingConstants.CENTER);
        txtStylo.setText("Stylo");
        txtStylo.setBounds(150, 33, 143, 63);
        frame.getContentPane().add(txtStylo);
        //		txtStylo.setColumns(10);
    }
}