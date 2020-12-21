import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterationPanel extends JFrame implements ButtonActions_RegisterationPanel {

    ///////////////  When user press register now button, create a new frame for registeration.

    private final JTextField NameTextField;
    private final JPasswordField PasswordTextField;
    private final JTextField SurnameTextField;
    private final JTextField MailAddressTextField;
    private final JTextField TelNoTextLabel;
    private final JRadioButton GenderButtonMale;
    private final JRadioButton GenderButtonFemale;
    private final JTextField AgeTextField;
    private final JComboBox<String> ProfessionComboBox;
    private final JTextField OtherProfessionTextField;
    private final ArrayList<String> UserInfoArrayList = new ArrayList<>();

    public RegisterationPanel(){
        super("REGISTERATION");

        String [] ProfessionOptions = {"Engineer","Doctor","Lawyer","Nurse","Pilot",
                "Teacher","Photographer","Scientist","Judge","Architect","Other"};

        JPanel registerPanel = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        NameTextField = new JTextField(10);
        JLabel surnameLabel = new JLabel("Surname:");
        SurnameTextField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        PasswordTextField = new JPasswordField(10);
        JLabel mailAddressLabel = new JLabel("Mail Address:");
        MailAddressTextField = new JTextField(10);
        JLabel telNoLabel = new JLabel("Phone Number:");
        TelNoTextLabel = new JTextField(10);
        JLabel genderLabel = new JLabel("Gender:");
        GenderButtonMale = new JRadioButton("Male");
        GenderButtonFemale = new JRadioButton("Female");
        JLabel ageLabel = new JLabel("Age:");
        AgeTextField = new JTextField(3);
        JLabel professionLabel = new JLabel("Profession:");
        ProfessionComboBox = new JComboBox<>(ProfessionOptions);
        JLabel otherProfessionLabel = new JLabel("Enter Profession:");
        OtherProfessionTextField = new JTextField(10);
        JButton okButton = new JButton("Register");

        OtherProfessionTextField.setEditable(false);
        ProfessionComboBox.setSelectedIndex(7);

        ProfessionComboBox.addActionListener(this::actionPerformedComboBox);
        GenderButtonFemale.addActionListener(this::actionPerformedGender);
        GenderButtonMale.addActionListener(this::actionPerformedGender);
        okButton.addActionListener(this::actionPerformedRegisterationCompateButton);

        Dimension dimension = getPreferredSize();
        dimension.width=50;
        setPreferredSize(dimension);

        Border innerBorder = BorderFactory.createTitledBorder("Welcome To The System");
        Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        registerPanel.setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        ///////////////// Row 1: Name
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(NameTextField, gridBagConstraints);

        ////////////////// Row 2: Surname
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(surnameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(SurnameTextField, gridBagConstraints);

        ////////////////// Row 3: Password
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(PasswordTextField, gridBagConstraints);

        ////////////////// Row 4: Mail Address
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(mailAddressLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(MailAddressTextField, gridBagConstraints);

        ////////////////// Row 5: Tel No
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(telNoLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(TelNoTextLabel, gridBagConstraints);

        ////////////////// Row 6: Gender Label
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(genderLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(GenderButtonMale, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(GenderButtonFemale, gridBagConstraints);

        ////////////////// Row 7: Age
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(ageLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(AgeTextField, gridBagConstraints);

        ////////////////// Row 8: Profession
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(professionLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(ProfessionComboBox, gridBagConstraints);

        ////////////////// Row 9: Other part of profession
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(otherProfessionLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(OtherProfessionTextField, gridBagConstraints);

        ////////////////// Row 10: Register Button
        gridBagConstraints.weighty = 0.2 ;

        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        add(okButton, gridBagConstraints);

        ////////////////// SETTINGS //////////////////////
        setVisible(true);
        setSize(350,400);
    }

    //////// ActionListener for profession ComboBox
    int OtherIsEditible = 0;
    private String Profession;

    /////////// ActionListener for Gender RadioBox
    private String Gender;
    
    @Override
    public void actionPerformedGender(ActionEvent Event) {
        JRadioButton Clicked = (JRadioButton) Event.getSource();
        if(Clicked == GenderButtonFemale){
            GenderButtonMale.setEnabled(false);
            Gender = GenderButtonFemale.getText();
        }
        else if(Clicked == GenderButtonMale){
            GenderButtonFemale.setEnabled(false);
            Gender = GenderButtonMale.getText();
        }
    }

    ////////////// ActionListener for registeration panel
    @Override
    public void actionPerformedRegisterationCompateButton(ActionEvent actionEvent) {

        int Confirmation = JOptionPane.showConfirmDialog(super.rootPane, "Do you confirm the information?",
                "Question", JOptionPane.YES_NO_OPTION);
        if(Confirmation == 0){
            String Name = NameTextField.getText();
            String Surname = SurnameTextField.getText();
            String Password = new String(PasswordTextField.getPassword());
            String MailAddress = MailAddressTextField.getText();
            String TelNo = TelNoTextLabel.getText();
            String Age = AgeTextField.getText();
            if(OtherIsEditible > 0){
                Profession = OtherProfessionTextField.getText();
            }

            ///////////////////  Taking User's ınfo ,adding them into an array list finally put them into database
            UserInfoArrayList.add(Name);
            UserInfoArrayList.add(Surname);
            UserInfoArrayList.add(Password);
            UserInfoArrayList.add(MailAddress);
            UserInfoArrayList.add(TelNo);
            UserInfoArrayList.add(Gender);
            UserInfoArrayList.add(Age);
            UserInfoArrayList.add(Profession);

            try {
                String myUrl = "jdbc:mysql://localhost/UNE_Registeration";
                Class.forName("com.mysql.jdbc.Driver");
                Connection UserInfo = DriverManager.getConnection(myUrl, "root", "root");
                String query = " insert into UserInfo (name,surname,password,mail_address,tel_no,gender,age,profession)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement NewUserRegisteration = UserInfo.prepareStatement(query);
                NewUserRegisteration.setString(1, UserInfoArrayList.get(0));
                NewUserRegisteration.setString(2, UserInfoArrayList.get(1));
                NewUserRegisteration.setString(3, UserInfoArrayList.get(2));
                NewUserRegisteration.setString(4, UserInfoArrayList.get(3));
                NewUserRegisteration.setString(5, UserInfoArrayList.get(4));
                NewUserRegisteration.setString(6, UserInfoArrayList.get(5));
                NewUserRegisteration.setString(7, UserInfoArrayList.get(6));
                NewUserRegisteration.setString(8, UserInfoArrayList.get(7));
                NewUserRegisteration.execute();
                NewUserRegisteration.close();
            } catch (Exception e) {
                System.err.println("There is an error\nPlease try later ...");
                System.err.println(e.getMessage());
            }

            setVisible(false);
        }
    }

    //////// Setting editible if the user choose Other option in Profession ComboBox
    public boolean ClickedOtherInComboBox(int getSelected){
        return getSelected == 10;
    }

    @Override
    public void actionPerformedComboBox(ActionEvent e) {
        if(ClickedOtherInComboBox(ProfessionComboBox.getSelectedIndex())){
            OtherProfessionTextField.setEditable(true);
            OtherIsEditible++;
        }
        else{
            OtherProfessionTextField.setEditable(false);
            Profession = (String) ProfessionComboBox.getSelectedItem();
        }
    }
}

