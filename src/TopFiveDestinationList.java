

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);

                // Add top border
                JPanel panel = new JPanel();
                panel.setBackground(Color.white);
                topDestinationListFrame.getContentPane().add(panel, BorderLayout.NORTH);

                // Add label
                JLabel label = new JLabel("Jef DeWitt", JLabel.CENTER);
                panel.add(label);
            }
        });
    }
}

class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 1050);

        listModel = new DefaultListModel();

        // Destination titles, descriptions, and image credits
        String dest1 = "1. Greece - See the Acropolis in Athens -- " + "Photo: Puk Patrick, https://unsplash.com/@macpukpro";
        String dest2 = "2. Brazil - Visit Copacabana Beach in Rio -- " + "Photo: Agustín Diaz, https://unsplash.com/@agussdiaz28";
        String dest3 = "3. Thailand - Adventure through Thailand -- " + "Photo: Robin Noguier, https://unsplash.com/@robinnoguier";
        String dest4 = "4. New Zealand - Experience mythical locations -- " + "Photo: Andres Iga, https://unsplash.com/@andresiga";
        String dest5 = "5. Antarctica - Brave the cold of Antarctica -- " + "Photo: James Eades, https://unsplash.com/@eadesstudio";

        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture(dest1, new ImageIcon(getClass().getResource("/resources/puk-patrick-EvMearrxas4-unsplash.jpg")));
        addDestinationNameAndPicture(dest2, new ImageIcon(getClass().getResource("/resources/agustin-diaz-7F65HDP0-E0-unsplash.jpg")));
        addDestinationNameAndPicture(dest3, new ImageIcon(getClass().getResource("/resources/robin-noguier-sydwCr54rf0-unsplash.jpg")));
        addDestinationNameAndPicture(dest4, new ImageIcon(getClass().getResource("/resources/andres-iga-7XKkJVw1d8c-unsplash.jpg")));
        addDestinationNameAndPicture(dest5, new ImageIcon(getClass().getResource("/resources/james-eades-RmOi5f7HfeE-unsplash.jpg")));
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);
        list.setCellRenderer(renderer);
        list.setSelectionBackground(Color.DARK_GRAY);
        list.setSelectionForeground(Color.WHITE);
        list.setFont(new Font("Sans-serif", Font.BOLD, 12));

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}