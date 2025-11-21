
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatBotPanel extends JPanel{
    

    //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
    private GUI app;

    public ChatBotPanel(GUI app) {
        this.app = app;

        setLayout(null);
        setBackground(new Color(245, 245, 245));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Input for chatbot



        JTextField userInputText = new JTextField("Enter Input here:");
        userInputText.setBounds(200, 740, 1000, 40);   // x, y, width, height
        userInputText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(userInputText);


 

   
}
}



