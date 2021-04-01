import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import java.awt.MouseInfo.*;
public class Milanfaccia extends JFrame  implements ActionListener, MouseListener
{
    ArrayList<JLabel> nomi= new ArrayList();
    JLabel disc= new JLabel(new ImageIcon("quit.png"));
    JPanel user= new JPanel();
    JPanel sezione= new JPanel();
    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
    Border bord = BorderFactory.createLineBorder(Color.GRAY, 1);
    Border bo = BorderFactory.createLineBorder(Color.BLUE, 3);
    Border b = BorderFactory.createLineBorder(Color.RED, 4);
    JTextField tast= new JTextField();
    
    public Milanfaccia()
    {
        setResizable(false);
        setSize(1280,720);
        setLayout(null);
        
        // TIMER x RICHIESTA USERS
        Timer t=new Timer(30,this);
        t.setActionCommand("TIMER");
        t.start();
        
        // PANEL USERS ONLINE
        user.setLayout(null);
        user.setSize(180,720);
        user.setBackground(new Color(240,230,140));
        user.setBorder(border);
        
        // PANEL INTERFACCIA MESSAGGI
        sezione.setLayout(null);
        sezione.setSize(1100,720);
        sezione.setLocation(180,0);
        sezione.setBackground(Color.WHITE);
        sezione.setBorder(border);
        
        // TEXTFIELD INVIO MESSAGGI
        tast.setSize(1100,100);
        tast.setLocation(184,620);
        tast.setBorder(bo);
        
        // ARRAYLIST LABEL USERS ONLINE
        //nomi.add(new JLabel("Pippo"));
        nomi.get(0).setSize(172,50);
        nomi.get(0).setLocation(4,4);
        nomi.get(0).setBorder(bord);
        //user.add(nomi.get(0));
        
        //LABEL DISCONNESSIONE
        disc.setSize(60,60);
        disc.setLocation(1200,10);
        
        add(disc);
        add(user);
        add(sezione);
        add(tast);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        
    }
    
    public void mouseClicked(MouseEvent e)
    {
        // ANCORA NON PROVATO
        String s="";
        if(e.getButton() == MouseEvent.BUTTON1)
            if (e.getSource()==disc)
                s="QUIT";
    }
    
    public void mouseEntered(MouseEvent e){}
    
    public void mouseExited(MouseEvent e){}
    
    public void mousePressed(MouseEvent e){}
    
    public void mouseReleased(MouseEvent e){}
}
