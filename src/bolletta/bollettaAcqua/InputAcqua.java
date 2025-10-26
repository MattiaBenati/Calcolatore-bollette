package bolletta.bollettaAcqua;

import bolletta.Main;

import java.util.Scanner;

public class InputAcqua
{
    // VARIABILI
    private double importoTotale;
    private int personePianoTerra;
    private int personePianoPrimo;
    
    private final Scanner scanner;
    
    public InputAcqua()
    {
        scanner = new Scanner(System.in);
    }
    
    // STEP
    public void inserisciDati()
    {
        inserisciValori();
        while (!riepilogoEDecisione())
        {
            modificaDati();
        }
    }
    
    private void inserisciValori()
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("INSERIMENTO DATI BOLLETTA ACQUA"));
        
        while (true)
        {
            System.out.print("Importo totale bolletta acqua (€): ");
            String input = scanner.nextLine().replace(",", ".");
            try
            {
                importoTotale = Double.parseDouble(input);
                if (importoTotale > 0) break;
            } catch (Exception ignored) {}
        }
        
        while (true)
        {
            System.out.print("Numero persone piano terra: ");
            String input = scanner.nextLine();
            try
            {
                personePianoTerra = Integer.parseInt(input);
                if (personePianoTerra >= 0) break;
            } catch (Exception ignored) {}
        }
        
        while (true)
        {
            System.out.print("Numero persone primo piano: ");
            String input = scanner.nextLine();
            try
            {
                personePianoPrimo = Integer.parseInt(input);
                if (personePianoPrimo >= 0) break;
            } catch (Exception ignored) {}
        }
    }
    
    private boolean riepilogoEDecisione()
    {
        while (true)
        {
            stampaRiepilogo(false);
            
            System.out.println("0 = Modifica i dati");
            System.out.println("1 = Conferma e prosegui");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            
            if (scelta == 1) return true;
            if (scelta == 0) return false;
        }
    }
    
    private void modificaDati()
    {
        scanner.nextLine(); // pulizia buffer iniziale
        
        while (true)
        {
            stampaRiepilogo(true);
            
            System.out.println("Digita un numero da 1 a 3 per modificare, oppure 0 per tornare alla schermata precedente");
            System.out.print("Scelta: ");
            String sceltaInput = scanner.nextLine();
            
            System.out.println(); // riga vuota prima dell'inserimento
            
            switch (sceltaInput)
            {
                case "1" ->
                {
                    while (true)
                    {
                        System.out.print("Importo totale bolletta acqua (€): ");
                        String input = scanner.nextLine().replace(",", ".");
                        try
                        {
                            double valore = Double.parseDouble(input);
                            if (valore > 0)
                            {
                                importoTotale = valore;
                                break;
                            }
                        } catch (Exception ignored) {}
                    }
                }
                case "2" ->
                {
                    while (true)
                    {
                        System.out.print("Numero persone piano terra: ");
                        String input = scanner.nextLine();
                        try
                        {
                            int valore = Integer.parseInt(input);
                            if (valore >= 0)
                            {
                                personePianoTerra = valore;
                                break;
                            }
                        } catch (Exception ignored) {}
                    }
                }
                case "3" ->
                {
                    while (true)
                    {
                        System.out.print("Numero persone primo piano: ");
                        String input = scanner.nextLine();
                        try
                        {
                            int valore = Integer.parseInt(input);
                            if (valore >= 0)
                            {
                                personePianoPrimo = valore;
                                break;
                            }
                        } catch (Exception ignored) {}
                    }
                }
                case "0" ->
                {
                    System.out.println();
                    return;
                }
            }
        }
    }
    
    private void stampaRiepilogo(boolean conNumeri)
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("RIEPILOGO DATI INSERITI"));
        
        if (conNumeri)
        {
            System.out.println("1 = Importo totale bolletta acqua: " + importoTotale + " €");
            System.out.println("2 = Numero persone piano terra: " + personePianoTerra);
            System.out.println("3 = Numero persone primo piano: " + personePianoPrimo);
        }
        else
        {
            System.out.println("Importo totale bolletta acqua: " + importoTotale + " €");
            System.out.println("Numero persone piano terra: " + personePianoTerra);
            System.out.println("Numero persone primo piano: " + personePianoPrimo);
        }
        System.out.println();
    }
    
    // GETTER
    public double getImportoTotale()
    {
        return importoTotale;
    }
    
    public int getPersonePianoTerra()
    {
        return personePianoTerra;
    }
    
    public int getPersonePianoPrimo()
    {
        return personePianoPrimo;
    }
}