/*
 * class runCMD 
 * Die vom Server geschickten CMD-Befhele können am Client ausgeführt werden.
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

class runCMD
{
    private String basedir;

    public runCMD(String basedir)
    {
        this.basedir = basedir;
    }

    public void execCMD(String command) throws InterruptedException
    {
    	command += "> cmdOutput.txt"; // Output des CMD-Befehls wird in der Datei cmdOutput.txt gespeichert
        System.out.println("executing command: " + command);
        Process p = null;
        try
        {
            p = Runtime.getRuntime().exec(
                    "cmd /c " //Nur unter Windows notwendig!
                    + command, null, new File(basedir));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        // Ausgabe in der CMD des Clients
        /*
        System.out.println("OUTPUT");
        printStream(p.getInputStream());
        System.out.println("ERROR-OUTPUT");
        printStream(p.getErrorStream());
        */
    }

    // Ausgabe in der CMD des Clients
    public void printStream(InputStream stream)
    {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(stream));
        String line = "";
        try
        {
           while ((line = in.readLine()) != null)
           {
                System.out.println(line);
           }
           in.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}