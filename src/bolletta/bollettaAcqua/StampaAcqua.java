package bolletta.bollettaAcqua;

import bolletta.Main;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class StampaAcqua
{
    private CalcoloAcqua calcoli;
    
    public StampaAcqua(CalcoloAcqua calcoli)
    {
        this.calcoli = calcoli;
    }
    
    public static void esportaSuFileTxt(CalcoloAcqua calcoli)
    {
        try
        {
            String desktopPath = System.getProperty("user.home") + "\\Desktop\\";
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"));
            String nomeFile = "bolletta acqua " + timestamp + ".txt";
            File file = new File(desktopPath + nomeFile);
            PrintWriter writer = new PrintWriter(file);
            
            Function<Double, String> format = val -> String.format(Locale.ITALY, "%.2f", val).replace('.', ',');
            
            List<String> output = new ArrayList<>();
            List<String> riepilogoFinale = new ArrayList<>();
            
            // RIPARTIZIONE FINALE BOLLETTA ACQUA
            Main.clearConsole();
            String titoloCmd = Main.creaTitolo("RIPARTIZIONE FINALE BOLLETTA ACQUA");
            System.out.println(titoloCmd); // stampa solo a schermo

            riepilogoFinale.add(Main.creaTitoloFileTxt("RIPARTIZIONE FINALE BOLLETTA ACQUA"));
            riepilogoFinale.add("Quota acqua per ogni persona: " + format.apply(calcoli.getCostoIndividualeAcqua()) + " €");
            riepilogoFinale.add("");
            riepilogoFinale.add("Totale da pagare piano TERRA: " + format.apply(calcoli.getCostoAcquaPianoTerra()) + " €");
            riepilogoFinale.add("Totale da pagare piano PRIMO: " + format.apply(calcoli.getCostoAcquaPrimoPiano()) + " €\n");

            for (int i = 1; i < riepilogoFinale.size(); i++)
                System.out.println(riepilogoFinale.get(i));

            output.addAll(riepilogoFinale);
            
            // CALCOLI ESEGUITI
            output.add(Main.creaTitoloFileTxt("CALCOLI ESEGUITI"));
            output.add("personeTotali = personePianoTerra + personePrimoPiano");
            output.add("              = " + calcoli.getPersonePianoTerra() + " + " + calcoli.getPersonePianoPrimo());
            output.add("              = " + calcoli.getPersoneTotali());
            
            output.add("");
            
            output.add("costoIndividualeAcqua = importoTotale / personeTotali");
            output.add("                      = " + format.apply(calcoli.getImportoTotale()) + " / " + calcoli.getPersoneTotali());
            output.add("                      = " + format.apply(calcoli.getCostoIndividualeAcqua()) + " €");
            
            output.add("");
            
            output.add("costoAcquaPianoTerra = costoIndividualeAcqua * personePianoTerra");
            output.add("                     = " + format.apply(calcoli.getCostoIndividualeAcqua()) + " * " + calcoli.getPersonePianoTerra());
            output.add("                     = " + format.apply(calcoli.getCostoAcquaPianoTerra()) + " €");
            
            output.add("");
            
            output.add("costoAcquaPrimoPiano = costoIndividualeAcqua * personePianoPrimo");
            output.add("                     = " + format.apply(calcoli.getCostoIndividualeAcqua()) + " * " + calcoli.getPersonePianoPrimo());
            output.add("                     = " + format.apply(calcoli.getCostoAcquaPrimoPiano()) + " €");
            
            for (String riga : output)
                writer.println(riga);
            
            writer.close();
            
            System.out.println("File esportato correttamente sul Desktop.");
            System.out.print("Premi INVIO per tornare al menu principale...");
            System.in.read();
            Main.main(new String[0]);
            
        } catch (Exception e)
        {
            System.out.println("Errore durante l'esportazione del file: " + e.getMessage());
        }
    }
}