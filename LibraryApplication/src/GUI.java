import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    JPanel panelMain;
    private JButton button_display;
    private JButton button_remove;
    private JButton button_add;

    public GUI() {
        button_add.setText("Add");
        button_display.setText("Display");
        button_remove.setText("Remove");

        button_display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.displayContents();
            }
        });
    }

}
