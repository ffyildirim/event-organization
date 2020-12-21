import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddEvent extends JFrame{

    private static JTextField eventNameTextField;
    private final JComboBox<String> eventTypeComboBox;
    private final JTextField othersForEventTypeTextField;
    private final JTextField eventStartDateTextField;
    private final JTextField eventDueDateTextField;
    private final JTextField eventCapacityTextField;
    private final JTextArea eventExplanation;
   // private final ArrayList<String> arrayList;

    public static JTextField getEventNameTextField() {
        return eventNameTextField;
    }

    public AddEvent(/*ArrayList<String> arrayList*/){
        super("Event Registeration");
        //this.arrayList = arrayList;

        String [] TypeOptions = {"Social Responsibility","Music","Sport","Dance","Entertainment",
                "Studying","Other"};

        JLabel eventName = new JLabel("Event Name :");
        eventNameTextField = new JTextField(10);
        JLabel eventType = new JLabel("Event Type :");
        eventTypeComboBox = new JComboBox<>(TypeOptions);
        JLabel othersForEventType = new JLabel("Other :");
        othersForEventTypeTextField = new JTextField(10);
        JLabel eventStartDate = new JLabel("Start Date :");
        eventStartDateTextField = new JTextField(10);
        JLabel eventDueDate = new JLabel("Due Date :");
        eventDueDateTextField = new JTextField(10);
        JLabel eventCapacity = new JLabel("Event Capacity :");
        eventCapacityTextField = new JTextField(4);
        eventExplanation = new JTextArea(5,25);
        JLayeredPane panel = new JLayeredPane();
        JLabel eventExplanationLabel = new JLabel("Event Explanation");
        JLayeredPane panelExplanation = new JLayeredPane();
        JButton registerEventButton = new JButton("Register Event");

        othersForEventTypeTextField.setEditable(false);
        eventTypeComboBox.setSelectedIndex(0);

        eventTypeComboBox.addActionListener(this::actionPerformedForOtherComboBox);

        panel.setPreferredSize(new Dimension(350,220));
        add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        /////////////////  Row 1: Event Name
        //gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        panel.add(eventName, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        panel.add(eventNameTextField, gridBagConstraints);

        /////////////////  Row 2: Event Type
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        panel.add(eventType, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        panel.add(eventTypeComboBox, gridBagConstraints);

        /////////////////  Row 3: other event type
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        panel.add(othersForEventType, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        panel.add(othersForEventTypeTextField, gridBagConstraints);

        /////////////////  Row 4: Start date
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        panel.add(eventStartDate, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        panel.add(eventStartDateTextField, gridBagConstraints);

        /////////////////  Row 5: Due date
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        panel.add(eventDueDate, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        panel.add(eventDueDateTextField, gridBagConstraints);

        /////////////////  Row 6: event capacity
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END ;
        panel.add(eventCapacity, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START ;
        panel.add(eventCapacityTextField, gridBagConstraints);

        ///////////////// Row 7: event explanation
        panelExplanation.setPreferredSize(new Dimension(350,150));
        add(panelExplanation, BorderLayout.SOUTH);
        panelExplanation.setLayout(new FlowLayout());
        panelExplanation.add(eventExplanationLabel);
        panelExplanation.add(eventExplanation);

        ///////////////// Row 8: register event button
        panelExplanation.add(registerEventButton);
        registerEventButton.addActionListener(this::actionPerformedForRegisterEventButton);

        ////////////////////// settings
        setVisible(true);
        setSize(350,400);
    }
    int OtherIsEditible = 0;

    public void actionPerformedForOtherComboBox(ActionEvent actionEvent) {
        if(ClickedOtherInComboBox(eventTypeComboBox.getSelectedIndex())){
            othersForEventTypeTextField.setEditable(true);
            OtherIsEditible++;
        }
        else{
            othersForEventTypeTextField.setEditable(false);
        }
    }
    public boolean ClickedOtherInComboBox(int getSelected){
        return getSelected == 6;
    }
    public static int counter = 0;
    public void actionPerformedForRegisterEventButton(ActionEvent e) {

        counter++;
        ////////////////// ADDING THE DATA TO THE FILE BY USING FILE I/O
        try{
            File eventData = new File(eventNameTextField.getText()+".txt");

            FileWriter eventDataFileWrite = new FileWriter(eventData, true);
            BufferedWriter eventDataBufferedWriter = new BufferedWriter(eventDataFileWrite);

            eventDataBufferedWriter.write("Event owner :"+getInfo(0)+" "
                    +getInfo(1)+"\n");
            eventDataBufferedWriter.write("Mail address :"+getInfo(2)+"\n");
            eventDataBufferedWriter.write("Event Name :"+eventNameTextField.getText()+"\n");
            if(eventTypeComboBox.getSelectedIndex() == 6){
                eventDataBufferedWriter.write("Event Type :"+othersForEventTypeTextField.getText()+"\n");
            }
            else{
                eventDataBufferedWriter.write("Event Type :"+eventTypeComboBox.getSelectedItem()+"\n");
            }
            eventDataBufferedWriter.write("Event Start Date :"+eventStartDateTextField.getText()+"\n");
            eventDataBufferedWriter.write("Event Due Date :"+eventDueDateTextField.getText()+"\n");
            eventDataBufferedWriter.write("Event Capacity :"+eventCapacityTextField.getText()+"\n");
            eventDataBufferedWriter.write("Event Explanation :"+eventExplanation.getText()+"\n");
            eventDataBufferedWriter.close();
        }catch (Exception exception){
            System.out.println("No such file...");
        }
        new MainFrame();
        setVisible(false);
    }
    public String getInfo(int index){
        ArrayList<String> getUserInfo = new ArrayList<>();

        try {
            String myUrl = "jdbc:mysql://localhost/UNE_Registeration";
            Class.forName("com.mysql.jdbc.Driver");
            Connection Verification = DriverManager.getConnection(myUrl, "root", "root");
            PreparedStatement ps=Verification.prepareStatement("Select name,surname,mail_address" +
                    " from UserInfo where password =? and mail_address =?");
            ps.setString(1, SignInPanel.getPasswordForDataVerification());
            ps.setString(2, SignInPanel.getMailAddressForDataVerification());
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                for(int i=0;i<3;i++){
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
}
