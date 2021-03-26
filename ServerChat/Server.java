import java.util.*;
import java.net.*;
import java.io.*;
public class Server

{
    // Variabili globali del server necessarie ad elaborare la risposta
    static ArrayList<String> membri= new ArrayList();
    static ArrayList<Messaggio> bellantuono= new ArrayList();
    static ArrayList<Integer> indice= new ArrayList();
    static int n;
    static int patty,taccone;
    public static void main(String args[])
    {
        ServerSocket ss;
        try
        {
            ss = new ServerSocket(2000);
   
            while(true)
            { 
                try
                {
                    Socket client = ss.accept();
                    System.out.println("Accettata connessione da "+client.getRemoteSocketAddress().toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    
                    // Lettura richiesta dal client
                    String str=in.readLine();  
                    String risposta = elaborazione(str);
                    
                    //trasmissione risposta del server 
                    out.write(risposta);  
                    out.flush();

                    // chiusura connessione
                    client.close(); 
                    in.close();
                    out.close();
                }
                catch(Exception e) 
                { 
                    System.out.println("COMUNICAZIONE FALLITA!\nErrore: " + e.getMessage());
                } 
            }
            
        }
        catch(Exception e) 
        {
            System.out.println("APERTURA ServerSocket FALLITA!\nErrore: " + e.getMessage());
        } 
    }
    static private String elaborazione(String richiesta)
    {
        String comando=richiesta.substring(0,4);
        String s="NICK ACCEPTED";
        n=membri.size();
        switch (comando)
        {
            case "NICK":
            {
                String mimmo=richiesta.substring(5);
                if (membri.contains(mimmo))
                    s="NICK DECLINED";
                
                if (s.equals("NICK ACCEPTED"))
                {
                    int zero=0;
                    membri.add(mimmo);
                    indice.add(zero);
                }
            }break;
            
            case "QUIT":
            {
                String pino=richiesta.substring(5);
                int ind=membri.indexOf(pino);
                if (ind!=-1)
                {
                    membri.remove(ind);
                    indice.remove(ind);
                    s="DISCONNECTED";
                }
                
                if (s.equals("NICK ACCEPTED"))
                    s="NOT FOUND";
            }break;
            
            case "TEXT":
            {
                
                char m=richiesta.charAt(5);
                int k=richiesta.length();
                
                for (int i=6;i<k;i++)
                    if (richiesta.charAt(i)=='§')
                        patty=i;
                        
                for (int j=patty;j<k;j++)
                    if (richiesta.charAt(j)=='$')
                        taccone=j;
                        
                if (m=='U')
                {
                    String text=richiesta.substring(7,patty);
                    String mitt=richiesta.substring(patty+1,taccone);
                    String dest=richiesta.substring(taccone+1);
                    if (membri.contains(mitt) && membri.contains(dest))
                    {
                        bellantuono.add(new Messaggio(text,mitt,dest,m));
                        s="RECEIVED";
                    }
                    
                    if (s.equals("NICK ACCEPTED"))
                        s="NOT FOUND";
                }
                
                if (m=='B')
                {
                    String text=richiesta.substring(7,patty);
                    String mitt=richiesta.substring(patty+1);
                    if (membri.contains(mitt))
                    {
                        bellantuono.add(new Messaggio(text,mitt,"ALL",m));
                        s="RECEIVED";
                    }
                    
                    if (s.equals("NICK ACCEPTED"))
                        s="NOT FOUND";
                }
            }break;
            
            case "USRS":
            {
                s="USERS ";
                for (int i=0;i<n;i++)
                    s=s+"§"+membri.get(i);
            }break;
            
            case "RCVD":
            {
                s="RF";
                String pippo=richiesta.substring(5);
                if (!membri.contains(pippo))
                    s="NOT FOUND";
    
                if (s.equals("RF"))
                {
                    int c=membri.indexOf(pippo);
                    for (int i=indice.get(c);i<bellantuono.size();i++)
                    {
                        if (bellantuono.get(i).getDest().equals(pippo) || bellantuono.get(i).getDest().equals("ALL"))
                        {
                            s=s+"§"+bellantuono.get(i).getMitt()+"§"+bellantuono.get(i).getText()+"§"+bellantuono.get(i).getTime()+"§"+bellantuono.get(i).getMode();
                        }
                    }
                    indice.set(c,bellantuono.size());
                }
                
                if (s.equals("RF"))
                    s="NO MEX";
    
            }break;
            
            default: s="ERROR";break;
        }
        
        return s;
    }
    
}
