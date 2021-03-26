import java.time.*;
public class Messaggio
{
    String testo;
    String mittente;
    String destinatario;
    char mode;
    LocalDateTime orario;
    
    public Messaggio(String testo, String mittente, String destinatario, char mode)
    {
        this.testo=testo;
        this.mittente=mittente;
        this.destinatario=destinatario;
        this.mode=mode;
        orario=LocalDateTime.now();
    }
    
    public String getText()
    {
        return testo;
    }
    
    public String getMitt()
    {
        return mittente;
    }
    
    public String getDest()
    {
        return destinatario;
    }
    
    public char getMode()
    {
        return mode;
    }
    
    public LocalDateTime getTime()
    {
        return orario;
    }
}
