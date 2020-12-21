import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignInPanel extends DATA_SignInPanel implements ButtonActions_SignInPanel{

    public SignInPanel() {
        super();

        //////// Option Pane at the beginning
        JOptionPane.showMessageDialog(super.rootPane, "We are so happy to see you between us" +
                "\nLet's find some activities to do...");

        SignInButton.addActionListener(this::actionPerformedForSignInButton);
        RegisterButton.addActionListener(this::actionPerformed);

        Border innerBorder = BorderFactory.createTitledBorder("Welcome To The System");
        Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        panel.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

        panel.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        ///////////////// First Row: Mail Address
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        panel.add(UserMailAddress, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(UserMailAddressTextField, gridBagConstraints);

        ////////////////// Second Row: Password
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        panel.add(Password, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(PasswordTextField, gridBagConstraints);

        ////////////////// Third Row: Sign in button
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(SignInButton, gridBagConstraints);

        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);
        add(RegisterButton, BorderLayout.SOUTH);

        //////// Settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 300);
    }

    private static String PasswordForDataVerification;
    private static String MailAddressForDataVerification;

    public static String getPasswordForDataVerification() {
        return PasswordForDataVerification;
    }

    public static String getMailAddressForDataVerification() {
        return MailAddressForDataVerification;
    }
    @Override
    public void actionPerformedForSignInButton(ActionEvent actionEvent) {
        DataVerified = false;
        PasswordForDataVerification = new String(PasswordTextField.getPassword());
        MailAddressForDataVerification = UserMailAddressTextField.getText();
        try {
            String myUrl = "jdbc:mysql://localhost/UNE_Registeration";
            Class.forName("com.mysql.jdbc.Driver");
            Connection Verification = DriverManager.getConnection(myUrl, "root", "root");
            PreparedStatement ps=Verification.prepareStatement("Select password,mail_address " +
                    "from UserInfo where password =? and mail_address =?");
            ps.setString(1, PasswordForDataVerification);
            ps.setString(2,MailAddressForDataVerification);
            ResultSet rs=ps.executeQuery();
            DataVerified = rs.next();
            Verification.close();
        } catch (Exception e) {
            System.err.println("There is an error\nPlease try later ...");
            System.err.println(e.getMessage());
        }
        if(DataVerified){
            System.out.println("Connecting main page");
            clickedSignInButton = (JButton) actionEvent.getSource();
            new MainFrame();
            setVisible(false);
        }
        else{
            JFrame NotConfirmationMessage = new JFrame();
            JOptionPane.showMessageDialog(NotConfirmationMessage,
                    "Please check your information!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        clicked = (JButton) actionEvent.getSource();
        new RegisterationPanel();
    }
}
