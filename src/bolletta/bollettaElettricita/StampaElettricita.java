package bolletta.bollettaElettricita;

import bolletta.Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class StampaElettricita
{
    private CalcoloElettricita calc;
    
    public StampaElettricita(CalcoloElettricita calc)
    {
        this.calc = calc;
    }
    
    public static void esportaSuFileTxt(CalcoloElettricita calcoli)
    {
        try
        {
            String desktopPath = System.getProperty("user.home") + "\\Desktop\\";
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss"));
            String nomeFile = "bolletta elettricità " + timestamp + ".txt";
            File file = new File(desktopPath + nomeFile);
            PrintWriter writer = new PrintWriter(file);
            
            Function<Double, String> format = val -> String.format(Locale.ITALY, "%.2f", val).replace('.', ',');
            
            List<String> output = new ArrayList<>();
            List<String> riepilogoFinale = new ArrayList<>();
            
            // RIPARTIZIONE FINALE BOLLETTA ELETTRICITÀ
            Main.clearConsole();
            String titoloCmd = Main.creaTitolo("RIPARTIZIONE FINALE BOLLETTA ELETTRICITÀ");
            System.out.println(titoloCmd);
            
            riepilogoFinale.add(Main.creaTitoloFileTxt("RIPARTIZIONE FINALE BOLLETTA ELETTRICITÀ"));
            riepilogoFinale.add("Costo diretto energia piano TERRA: " + format.apply(calcoli.getCostoPianoTerraDiretto()) + " €");
            riepilogoFinale.add("Quota caldaia piano TERRA: " + format.apply(calcoli.getCostoCaldaiaPianoTerra()) + " €");
            riepilogoFinale.add("Quota caldaia piano PRIMO: " + format.apply(calcoli.getCostoCaldaiaPianoPrimo()) + " €\n");
            riepilogoFinale.add("Totale da pagare piano TERRA: " + format.apply(calcoli.getTotalePianoTerra()) + " €");
            riepilogoFinale.add("Totale da pagare piano PRIMO: " + format.apply(calcoli.getTotalePianoPrimo()) + " €\n");
            
            for (int i = 1; i < riepilogoFinale.size(); i++)
                System.out.println(riepilogoFinale.get(i));
            
            output.addAll(riepilogoFinale);
            
            // EQUAZIONI BASE
            output.add(Main.creaTitoloFileTxt("CALCOLI ESEGUITI"));
            output.add("costoPerKWh = importoTotale / consumoTotale");
            output.add("            = " + format.apply(calcoli.getImportoTotale()) + " / " + format.apply(calcoli.getConsumoTotale()));
            output.add("            = " + format.apply(calcoli.getCostoPerKWh()) + " €/kWh\n");
            
            output.add("consumoCaldaia = letturaFine - letturaInizio");
            output.add("               = " + format.apply(calcoli.getLetturaFine()) + " - " + format.apply(calcoli.getLetturaInizio()));
            output.add("               = " + format.apply(calcoli.getConsumoCaldaia()) + " kWh\n");
            
            output.add("consumoPianoTerraDiretto = consumoTotale - consumoCaldaia");
            output.add("                         = " + format.apply(calcoli.getConsumoTotale()) + " - " + format.apply(calcoli.getConsumoCaldaia()));
            output.add("                         = " + format.apply(calcoli.getConsumoPianoTerraDiretto()) + " kWh\n");
            
            output.add("costoTotaleCaldaia = consumoCaldaia * costoPerKWh");
            output.add("                   = " + format.apply(calcoli.getConsumoCaldaia()) + " * " + format.apply(calcoli.getCostoPerKWh()));
            output.add("                   = " + format.apply(calcoli.getCostoTotaleCaldaia()) + " €\n");
            
            output.add("kcalConsumatePianoTerra = kcalFinaliPianoTerra - kcalInizialiPianoTerra");
            output.add("                        = " + format.apply(calcoli.getKcalFinaliPianoTerra()) + " - " + format.apply(calcoli.getKcalInizialiPianoTerra()));
            output.add("                        = " + format.apply(calcoli.getKcalConsumatePianoTerra()) + " kCal\n");
            
            output.add("kcalConsumatePianoPrimo = kcalFinaliPianoPrimo - kcalInizialiPianoPrimo");
            output.add("                        = " + format.apply(calcoli.getKcalFinaliPianoPrimo()) + " - " + format.apply(calcoli.getKcalInizialiPianoPrimo()));
            output.add("                        = " + format.apply(calcoli.getKcalConsumatePianoPrimo()) + " kCal\n");
            
            // RIPARTIZIONE COSTO CALDAIA
            output.add(Main.creaTitoloFileTxt("RIPARTIZIONE COSTO CALDAIA"));
            
            String modalita = calcoli.getModalitaCentralina();
            
            if (modalita.equals("riscaldamento"))
            {
                double kcalTerra = calcoli.getKcalConsumatePianoTerra();
                double kcalPrimo = calcoli.getKcalConsumatePianoPrimo();
                
                if (kcalTerra != 0 && kcalPrimo != 0)
                {
                    output.add("costoPerKCalCaldaia = costoTotaleCaldaia / (kcalConsumatePianoTerra + kcalConsumatePianoPrimo)");
                    output.add("                    = " + format.apply(calcoli.getCostoTotaleCaldaia()) + " / (" + format.apply(kcalTerra) + " + " + format.apply(kcalPrimo) + ")");
                    output.add("                    = " + format.apply(calcoli.getCostoPerKCalCaldaia()) + " €/kCal\n");
                    
                    output.add("costoCaldaiaPianoTERRA = kcalConsumatePianoTerra * costoPerKCalCaldaia");
                    output.add("                       = " + format.apply(kcalTerra) + " * " + format.apply(calcoli.getCostoPerKCalCaldaia()));
                    output.add("                       = " + format.apply(calcoli.getCostoCaldaiaPianoTerra()) + " €\n");
                    
                    output.add("costoCaldaiaPianoPRIMO = kcalConsumatePianoPrimo * costoPerKCalCaldaia");
                    output.add("                       = " + format.apply(kcalPrimo) + " * " + format.apply(calcoli.getCostoPerKCalCaldaia()));
                    output.add("                       = " + format.apply(calcoli.getCostoCaldaiaPianoPrimo()) + " €\n");
                    
                }
                else if (kcalTerra == 0 && kcalPrimo == 0)
                {
                    output.add("costoPerPersonaCaldaia = costoTotaleCaldaia / (personePianoTerra + personePianoPrimo)");
                    output.add("                       = " + format.apply(calcoli.getCostoTotaleCaldaia()) + " / (" + calcoli.getPersonePianoTerra() + " + " + calcoli.getPersonePianoPrimo() + ")");
                    output.add("                       = " + format.apply(calcoli.getCostoPerPersonaCaldaia()) + " €/persona\n");
                    
                    output.add("costoCaldaiaPianoTERRA = personePianoTerra * costoPerPersonaCaldaia");
                    output.add("                       = " + calcoli.getPersonePianoTerra() + " * " + format.apply(calcoli.getCostoPerPersonaCaldaia()));
                    output.add("                       = " + format.apply(calcoli.getCostoCaldaiaPianoTerra()) + " €\n");
                    
                    output.add("costoCaldaiaPianoPRIMO = personePianoPrimo * costoPerPersonaCaldaia");
                    output.add("                       = " + calcoli.getPersonePianoPrimo() + " * " + format.apply(calcoli.getCostoPerPersonaCaldaia()));
                    output.add("                       = " + format.apply(calcoli.getCostoCaldaiaPianoPrimo()) + " €\n");
                    
                }
                else
                {
                    output.add("costoCaldaiaPianoTERRA = " + format.apply(calcoli.getCostoCaldaiaPianoTerra()) + " €");
                    output.add("costoCaldaiaPianoPRIMO = " + format.apply(calcoli.getCostoCaldaiaPianoPrimo()) + " €\n");
                }
                
            }
            else if (modalita.equals("raffrescamento"))
            {
                output.add("costoCaldaiaPianoTERRA = 0,00 €");
                output.add("costoCaldaiaPianoPRIMO = " + format.apply(calcoli.getCostoCaldaiaPianoPrimo()) + " €\n");
            }
            
            // CALCOLI FINALI
            output.add(Main.creaTitoloFileTxt("CALCOLI FINALI"));
            output.add("costoPianoTerraDiretto = consumoPianoTerraDiretto * costoPerKWh");
            output.add("                       = " + format.apply(calcoli.getConsumoPianoTerraDiretto()) + " * " + format.apply(calcoli.getCostoPerKWh()));
            output.add("                       = " + format.apply(calcoli.getCostoPianoTerraDiretto()) + " €\n");
            
            output.add("totalePianoTERRA = costoPianoTerraDiretto + costoCaldaiaPianoTERRA");
            output.add("                 = " + format.apply(calcoli.getCostoPianoTerraDiretto()) + " + " + format.apply(calcoli.getCostoCaldaiaPianoTerra()));
            output.add("                 = " + format.apply(calcoli.getTotalePianoTerra()) + " €\n");
            
            output.add("totalePianoPRIMO = costoCaldaiaPianoPRIMO");
            output.add("                 = " + format.apply(calcoli.getCostoCaldaiaPianoPrimo()));
            output.add("                 = " + format.apply(calcoli.getTotalePianoPrimo()) + " €");
            
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
    
    private static String format(double value)
    {
        return String.format(Locale.US, "%.2f", value);
    }
    
    private void scrivi(BufferedWriter writer, String riga) throws IOException
    {
        writer.write(riga);
        System.out.print(riga);
    }
}