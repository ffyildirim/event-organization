import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private final JTextArea chatTextArea;
    private final JTextField chatTextField;
    private ArrayList<String> myArraylist;
    private JLayeredPane events;


    public MainFrame() {
        super("Home Page");

        JLayeredPane userInfoPanel = new JLayeredPane();
        events = new JLayeredPane();
        JButton addEventButton = new JButton("+Add Event");
        JLabel userName = new JLabel(getInfo(0));
        JLabel userSurname = new JLabel(getInfo(1));
        JLabel userAge = new JLabel(getInfo(2));
        JLabel userGender = new JLabel(getInfo(3));
        JLabel userProfession = new JLabel(getInfo(4));
        JLabel getUserName = new JLabel("Name :");
        JLabel getUserSurname = new JLabel("Surname :");
        JLabel getUserAge = new JLabel("Age :");
        JLabel getUserGender = new JLabel("Gender :");
        JLabel getUserProfession = new JLabel("Profession :");
        chatTextArea = new JTextArea(7,40);
        chatTextField = new JTextField(40);
        JScrollPane chatScrollPane = new JScrollPane(chatTextArea);
        JButton sendButton = new JButton("Send");
        JPanel chatPanel = new JPanel();



        /////////////////////// UserInfo panel ////////////////////////
        userInfoPanel.setPreferredSize(new Dimension(170,190));
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("User Info"));
        add(userInfoPanel,BorderLayout.WEST);

        userInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        /////////////////  Row 1: UserName
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        userInfoPanel.add(userName, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        userInfoPanel.add(getUserName, gridBagConstraints);

        ///////////////// Row 2: UserSurname
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        userInfoPanel.add(userSurname, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        userInfoPanel.add(getUserSurname, gridBagConstraints);

        ///////////////// Row 3: UserAge
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        userInfoPanel.add(userAge, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        userInfoPanel.add(getUserAge, gridBagConstraints);

        ///////////////// Row 4: UserGender
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        userInfoPanel.add(userGender, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        userInfoPanel.add(getUserGender, gridBagConstraints);

        ///////////////// Row 5: USerProfession
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        userInfoPanel.add(userProfession, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        userInfoPanel.add(getUserProfession, gridBagConstraints);

        ////////////////////// Chat panel ////////////////////
        chatPanel.setPreferredSize(new Dimension(200,190));
        chatPanel.setBorder(BorderFactory.createTitledBorder("Chat Panel"));
        add(chatPanel,BorderLayout.SOUTH);
        chatPanel.setLayout(new FlowLayout());
        chatPanel.add(chatScrollPane);
        chatPanel.add(chatTextField);
        chatPanel.add(sendButton);

        sendButton.addActionListener(this::actionPerformedForSendButton);
        chatTextArea.setEditable(false);

        //////////////////////// Events panel ///////////////////////////
        events.setPreferredSize(new Dimension(200,190));
        events.setBorder(BorderFactory.createTitledBorder("Events"));
        add(events,BorderLayout.CENTER);
        events.setLayout((new FlowLayout()));
        addEventButton.addActionListener(this::actionPerformedForAddEventButton);
        events.add(addEventButton);

        if(AddEvent.counter != 0){
            addEventToMainFrame();
            JPanel panel22 = new JPanel();
            panel22.setLayout(new GridBagLayout());
            JLabel[] labels = new JLabel[myArraylist.size()];
            int k=0;
            for(int i=0;i<myArraylist.size();i++){
                labels[i] = new JLabel(myArraylist.get(i));

                /////////////////  Row 1: UserName
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = k;
                gridBagConstraints.fill = GridBagConstraints.NONE;
                gridBagConstraints.insets = new Insets(0,0,0,5);
                gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
                panel22.add(labels[i], gridBagConstraints);
                k++;
            }
            JButton button = new JButton("Join");
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 8;
            gridBagConstraints.fill = GridBagConstraints.NONE;
            gridBagConstraints.insets = new Insets(0,0,0,5);
            gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
            panel22.add(button, gridBagConstraints);
            events.add(panel22);
        }

        /////// Settings
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
    }

    public String getInfo(int index){
        ArrayList<String> getUserInfo = new ArrayList<>();

        try {
            String myUrl = "jdbc:mysql://localhost/UNE_Registeration";
            Class.forName("com.mysql.jdbc.Driver");
            Connection Verification = DriverManager.getConnection(myUrl, "root", "root");
            PreparedStatement ps=Verification.prepareStatement("Select name,surname,age,gender,profession" +
                    " from UserInfo where password =? and mail_address =?");
            ps.setString(1, SignInPanel.getPasswordForDataVerification());
            ps.setString(2, SignInPanel.getMailAddressForDataVerification());
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                for(int i=0;i<5;i++){
                    getUserInfo.add(rs.getString(i+1));
                }
            }
            Verification.close();
        } catch (Exception e) {
            System.err.println("There is an error\nPlease try later ...");
            System.err.println(e.getMessage());
        }
        return getUserInfo.get(index);
    }

    public void actionPerformedForSendButton(ActionEvent e) {
        String userMessage = chatTextField.getText();
        chatTextArea.append(userMessage+"\n");
    }

    public void actionPerformedForAddEventButton(ActionEvent e) {
        //new AddEvent(myArraylist);
        new AddEvent();
    }

    public void addEventToMainFrame(){
        myArraylist = new ArrayList<>();
        int eventLinesInFile=0;
        try{
            File eventDataFile = new File(AddEvent.getEventNameTextField().getText()+".txt");

            FileReader eventDataFileReader = new FileReader(eventDataFile);
            BufferedReader eventDataBufferedReader = new BufferedReader(eventDataFileReader);
            while (eventLinesInFile<8){
                myArraylist.add(eventDataBufferedReader.readLine());
                eventLinesInFile++;
            }
            eventDataBufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
