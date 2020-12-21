import javax.swing.*;

public abstract class DATA_SignInPanel extends JFrame {

    /////////////////////////// SIGN IN PANEL //////////////////////////////////
    public JButton RegisterButton;
    public JButton clicked;
    public JButton clickedSignInButton;
    public JLabel UserMailAddress;
    public JLabel Password;
    public JTextField UserMailAddressTextField;
    public JPasswordField PasswordTextField;
    public JButton SignInButton;
    public boolean DataVerified;
    public JPanel panel;

    public DATA_SignInPanel(){
        panel = new JPanel();
        RegisterButton = new JButton("Register now");
        UserMailAddress = new JLabel("Mail Address:");
        Password = new JLabel("Password:");
        UserMailAddressTextField = new JTextField(10);
        PasswordTextField = new JPasswordField(10);
        SignInButton = new JButton("Sign in");
    }
}
