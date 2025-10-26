package bolletta;

import bolletta.bollettaAcqua.CalcoloAcqua;
import bolletta.bollettaAcqua.InputAcqua;
import bolletta.bollettaAcqua.StampaAcqua;
import bolletta.bollettaElettricita.CalcoloElettricita;
import bolletta.bollettaElettricita.InputElettricita;
import bolletta.bollettaElettricita.StampaElettricita;
import bolletta.bollettaGas.CalcoloGas;
import bolletta.bollettaGas.InputGas;
import bolletta.bollettaGas.StampaGas;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        try {
            new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            System.out.println("⚠️ errore nella configurazione UTF-8");
        }
        
        Scanner scanner = new Scanner(System.in);
        
        Main.clearConsole();
        System.out.println(Main.creaTitolo("CALCOLO BOLLETTA CASA"));
        
        System.out.println("1 = Calcola bolletta energia elettrica");
        System.out.println("2 = Calcola bolletta gas");
        System.out.println("3 = Calcola bolletta acqua");
        System.out.print("Scelta: ");
        int scelta = scanner.nextInt();
        
        switch (scelta)
        {
            case 1 ->
            {
                InputElettricita input = new InputElettricita();
                input.inserisciDatiStep1();  // STEP 1: importo + consumo + persone
                input.inserisciDatiStep2();  // STEP 2: letture caldaia
                input.inserisciDatiStep3();  // STEP 3: modalità + letture kCal
                
                CalcoloElettricita calcolo = new CalcoloElettricita(
                        input.getImportoTotale(),
                        input.getConsumoTotale(),
                        input.getPersonePianoTerra(),
                        input.getPersonePianoPrimo(),
                        input.getLetturaInizio(),
                        input.getLetturaFine(),
                        input.getModalitaCentralina(),
                        input.getKcalInizialiPianoTerra(),
                        input.getKcalFinaliPianoTerra(),
                        input.getKcalInizialiPianoPrimo(),
                        input.getKcalFinaliPianoPrimo()
                );
                
                StampaElettricita stampa = new StampaElettricita(calcolo);
                try
                {
                    StampaElettricita.esportaSuFileTxt(calcolo);
                    System.out.println("\nFile esportato correttamente sul Desktop.");
                } catch (Exception e)
                {
                    System.out.println("\nErrore durante l'esportazione del file: " + e.getMessage());
                }
            }
            case 2 ->
            {
                InputGas input = new InputGas();
                input.inserisciDati();
                
                CalcoloGas calcolo = new CalcoloGas(
                        input.getImportoTotale(),
                        input.getPersonePianoTerra(),
                        input.getPersonePianoPrimo()
                );
                
                StampaGas stampa = new StampaGas(calcolo);
                try {
                    StampaGas.esportaSuFileTxt(calcolo);
                    System.out.println("\nFile esportato correttamente sul Desktop.");
                } catch (Exception e) {
                    System.out.println("\nErrore durante l'esportazione del file: " + e.getMessage());
                }
            }
            case 3 -> {
                InputAcqua input = new InputAcqua();
                input.inserisciDati();
                
                CalcoloAcqua calcolo = new CalcoloAcqua(
                        input.getImportoTotale(),
                        input.getPersonePianoTerra(),
                        input.getPersonePianoPrimo()
                );
                
                StampaAcqua stampa = new StampaAcqua(calcolo);
                try {
                    StampaAcqua.esportaSuFileTxt(calcolo);
                    System.out.println("\nFile esportato correttamente sul Desktop.");
                } catch (Exception e) {
                    System.out.println("\nErrore durante l'esportazione del file: " + e.getMessage());
                }
            }
            case 0 ->
            {
                System.out.println("\nChiusura del software in corso...");
                System.exit(0);
            }
            default -> System.out.println("\nScelta non valida.");
        }
    }
    
    public static void clearConsole()
    {
        try
        {
            if (System.getProperty("os.name").toLowerCase().contains("windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e)
        {
            System.out.println("Impossibile pulire la console.");
        }
    }
    
    public static String creaTitolo(String testo)
    {
        String contenuto = "  " + testo.toUpperCase() + "  ";
        String linea = "═".repeat(contenuto.length());
        return "╔" + linea + "╗\n" +
                "║" + contenuto + "║\n" +
                "╚" + linea + "╝\n";
    }
    
    public static String creaTitoloFileTxt(String testo)
    {
        String linea = "=".repeat(testo.length() + 8);
        return linea + "\n=== " + testo.toUpperCase() + " ===\n" + linea + "\n";
    }
}