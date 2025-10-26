package bolletta.bollettaElettricita;

import bolletta.Main;

import java.util.Scanner;

public class InputElettricita
{
    // === VARIABILI STEP1 ===
    private double importoTotale;
    private double consumoTotale;
    private int personePianoTerra;
    private int personePianoPrimo;
    
    // === VARIABILI STEP2 ===
    private double letturaInizioCaldaia;
    private double letturaFineCaldaia;
    
    // === VARIABILI STEP3 ===
    private String modalitaCentralina;
    private int kCalInizioTerra;
    private int kCalFineTerra;
    private int kCalInizioPrimo;
    private int kCalFinePrimo;
    
    private final Scanner scanner;
    
    public InputElettricita()
    {
        scanner = new Scanner(System.in);
    }
    
    // === STEP1 ===
    public void inserisciDatiStep1()
    {
        inserisciValoriStep1();
        while (!riepilogoEDecisioneStep1())
        {
            modificaDatiStep1();
        }
    }
    
    private void inserisciValoriStep1()
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("INSERIMENTO DATI BOLLETTA ENERGIA ELETTRICA"));
        
        while (true)
        {
            System.out.print("Importo totale bolletta energia (€): ");
            String input = scanner.nextLine().replace(",", ".");
            try
            {
                importoTotale = Double.parseDouble(input);
                if (importoTotale > 0) break;
            } catch (Exception ignored)
            {
            }
        }
        
        while (true)
        {
            System.out.print("Consumo totale energia elettrica (kWh): ");
            String input = scanner.nextLine().replace(",", ".");
            try
            {
                consumoTotale = Double.parseDouble(input);
                if (consumoTotale > 0) break;
            } catch (Exception ignored)
            {
            }
        }
        
        while (true)
        {
            System.out.print("Numero persone piano terra: ");
            String input = scanner.nextLine();
            try
            {
                personePianoTerra = Integer.parseInt(input);
                if (personePianoTerra >= 0) break;
            } catch (Exception ignored)
            {
            }
        }
        
        while (true)
        {
            System.out.print("Numero persone primo piano: ");
            String input = scanner.nextLine();
            try
            {
                personePianoPrimo = Integer.parseInt(input);
                if (personePianoPrimo >= 0) break;
            } catch (Exception ignored)
            {
            }
        }
    }
    
    private boolean riepilogoEDecisioneStep1()
    {
        while (true)
        {
            stampaRiepilogoStep1(false);
            
            System.out.println("0 = Modifica i dati");
            System.out.println("1 = Conferma e prosegui");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            
            if (scelta == 1) return true;
            if (scelta == 0) return false;
        }
    }
    
    private void modificaDatiStep1()
    {
        scanner.nextLine(); // pulizia buffer iniziale
        
        while (true)
        {
            stampaRiepilogoStep1(true);
            
            System.out.println("Digita un numero da 1 a 4 per modificare, oppure 0 per tornare alla schermata precedente");
            System.out.print("Scelta: ");
            String sceltaInput = scanner.nextLine();
            
            System.out.println(); // riga vuota prima dell'inserimento
            
            switch (sceltaInput)
            {
                case "1" ->
                {
                    while (true)
                    {
                        System.out.print("Importo totale bolletta energia (€): ");
                        String input = scanner.nextLine().replace(",", ".");
                        try
                        {
                            double valore = Double.parseDouble(input);
                            if (valore > 0)
                            {
                                importoTotale = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
                    }
                }
                case "2" ->
                {
                    while (true)
                    {
                        System.out.print("Consumo totale energia elettrica (kWh): ");
                        String input = scanner.nextLine().replace(",", ".");
                        try
                        {
                            double valore = Double.parseDouble(input);
                            if (valore > 0)
                            {
                                consumoTotale = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
                    }
                }
                case "3" ->
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
                        } catch (Exception ignored)
                        {
                        }
                    }
                }
                case "4" ->
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
                        } catch (Exception ignored)
                        {
                        }
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
    
    private void stampaRiepilogoStep1(boolean conNumeri)
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("RIEPILOGO DATI INSERITI"));
        
        if (conNumeri)
        {
            System.out.println("1 = Importo totale bolletta energia: " + importoTotale + " €");
            System.out.println("2 = Consumo totale energia elettrica: " + consumoTotale + " kWh");
            System.out.println("3 = Numero persone piano terra: " + personePianoTerra);
            System.out.println("4 = Numero persone primo piano: " + personePianoPrimo);
        }
        else
        {
            System.out.println("Importo totale bolletta energia: " + importoTotale + " €");
            System.out.println("Consumo totale energia elettrica: " + consumoTotale + " kWh");
            System.out.println("Numero persone piano terra: " + personePianoTerra);
            System.out.println("Numero persone primo piano: " + personePianoPrimo);
        }
        System.out.println();
    }
    
    // === STEP2 ===
    public void inserisciDatiStep2()
    {
        inserisciLettureStep2();
        while (!riepilogoEDecisioneStep2())
        {
            modificaLettureStep2();
        }
    }
    
    private void inserisciLettureStep2()
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("INSERIMENTO LETTURE CONTATORE CALDAIA"));
        
        scanner.nextLine(); // pulizia buffer per evitare doppio prompt
        
        while (true)
        {
            System.out.print("Lettura caldaia INIZIO PRIMO mese (kWh): ");
            String input = scanner.nextLine().replace(",", ".");
            try
            {
                letturaInizioCaldaia = Double.parseDouble(input);
                if (letturaInizioCaldaia >= 0) break;
            } catch (Exception ignored)
            {
            }
        }
        
        while (true)
        {
            System.out.print("Lettura caldaia FINE SECONDO mese (kWh): ");
            String input = scanner.nextLine().replace(",", ".");
            try
            {
                letturaFineCaldaia = Double.parseDouble(input);
                if (letturaFineCaldaia >= letturaInizioCaldaia) break;
            } catch (Exception ignored)
            {
            }
        }
    }
    
    private boolean riepilogoEDecisioneStep2()
    {
        while (true)
        {
            stampaRiepilogoStep2(false);
            
            System.out.println("0 = Modifica i dati");
            System.out.println("1 = Conferma e prosegui");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            
            if (scelta == 1) return true;
            if (scelta == 0) return false;
        }
    }
    
    private void modificaLettureStep2()
    {
        scanner.nextLine(); // pulizia buffer iniziale
        
        while (true)
        {
            stampaRiepilogoStep2(true);
            
            System.out.println("Digita un numero da 1 a 2 per modificare, oppure 0 per tornare alla schermata precedente");
            System.out.print("Scelta: ");
            String sceltaInput = scanner.nextLine();
            
            System.out.println(); // riga vuota prima dell’inserimento
            
            switch (sceltaInput)
            {
                case "1" ->
                {
                    while (true)
                    {
                        System.out.print("Lettura caldaia INIZIO PRIMO mese (kWh): ");
                        String input = scanner.nextLine().replace(",", ".");
                        try
                        {
                            double valore = Double.parseDouble(input);
                            if (valore >= 0)
                            {
                                letturaInizioCaldaia = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
                    }
                }
                case "2" ->
                {
                    while (true)
                    {
                        System.out.print("Lettura caldaia FINE SECONDO mese (kWh): ");
                        String input = scanner.nextLine().replace(",", ".");
                        try
                        {
                            double valore = Double.parseDouble(input);
                            if (valore >= letturaInizioCaldaia)
                            {
                                letturaFineCaldaia = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
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
    
    private void stampaRiepilogoStep2(boolean conNumeri)
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("RIEPILOGO DATI INSERITI"));
        
        if (conNumeri)
        {
            System.out.println("1 = Lettura caldaia INIZIO PRIMO mese: " + letturaInizioCaldaia + " kWh");
            System.out.println("2 = Lettura caldaia FINE SECONDO mese: " + letturaFineCaldaia + " kWh");
        }
        else
        {
            System.out.println("Lettura caldaia INIZIO PRIMO mese: " + letturaInizioCaldaia + " kWh");
            System.out.println("Lettura caldaia FINE SECONDO mese: " + letturaFineCaldaia + " kWh");
        }
        System.out.println();
    }
    
    // === STEP3 ===
    public void inserisciDatiStep3()
    {
        inserisciValoriStep3();
        while (!riepilogoEDecisioneStep3())
        {
            modificaDatiStep3();
        }
    }
    
    private void inserisciValoriStep3()
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("INSERIMENTO MODALITÀ CENTRALINA E CONSUMI kCal"));
        
        scanner.nextLine(); // pulizia buffer
        
        while (true)
        {
            System.out.print("Modalità centralina (riscaldamento/raffrescamento): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("riscaldamento") || input.equals("raffrescamento"))
            {
                modalitaCentralina = input;
                break;
            }
        }
        
        while (true)
        {
            System.out.print("Lettura INIZIALE kCal piano terra: ");
            String input = scanner.nextLine();
            try
            {
                kCalInizioTerra = Integer.parseInt(input);
                if (kCalInizioTerra >= 0) break;
            } catch (Exception ignored)
            {
            }
        }
        
        while (true)
        {
            System.out.print("Lettura FINALE kCal piano terra: ");
            String input = scanner.nextLine();
            try
            {
                kCalFineTerra = Integer.parseInt(input);
                if (kCalFineTerra >= kCalInizioTerra) break;
            } catch (Exception ignored)
            {
            }
        }
        
        while (true)
        {
            System.out.print("Lettura INIZIALE kCal primo piano: ");
            String input = scanner.nextLine();
            try
            {
                kCalInizioPrimo = Integer.parseInt(input);
                if (kCalInizioPrimo >= 0) break;
            } catch (Exception ignored)
            {
            }
        }
        
        while (true)
        {
            System.out.print("Lettura FINALE kCal primo piano: ");
            String input = scanner.nextLine();
            try
            {
                kCalFinePrimo = Integer.parseInt(input);
                if (kCalFinePrimo >= kCalInizioPrimo) break;
            } catch (Exception ignored)
            {
            }
        }
    }
    
    
    private boolean riepilogoEDecisioneStep3()
    {
        while (true)
        {
            stampaRiepilogoStep3(false);
            
            System.out.println("0 = Modifica i dati");
            System.out.println("1 = Conferma e prosegui");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            
            if (scelta == 1) return true;
            if (scelta == 0) return false;
        }
    }
    
    private void modificaDatiStep3()
    {
        scanner.nextLine(); // pulizia buffer iniziale
        
        while (true)
        {
            stampaRiepilogoStep3(true);
            
            System.out.println("Digita un numero da 1 a 5 per modificare, oppure 0 per tornare alla schermata precedente");
            System.out.print("Scelta: ");
            String sceltaInput = scanner.nextLine();
            
            System.out.println(); // riga vuota prima dell'inserimento
            
            switch (sceltaInput)
            {
                case "1" ->
                {
                    while (true)
                    {
                        System.out.print("Modalità centralina (riscaldamento/raffrescamento): ");
                        String input = scanner.nextLine().toLowerCase();
                        if (input.equals("riscaldamento") || input.equals("raffrescamento"))
                        {
                            modalitaCentralina = input;
                            break;
                        }
                    }
                }
                case "2" ->
                {
                    while (true)
                    {
                        System.out.print("Lettura INIZIALE kCal piano terra: ");
                        String input = scanner.nextLine();
                        try
                        {
                            int valore = Integer.parseInt(input);
                            if (valore >= 0)
                            {
                                kCalInizioTerra = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
                    }
                }
                case "3" ->
                {
                    while (true)
                    {
                        System.out.print("Lettura FINALE kCal piano terra: ");
                        String input = scanner.nextLine();
                        try
                        {
                            int valore = Integer.parseInt(input);
                            if (valore >= kCalInizioTerra)
                            {
                                kCalFineTerra = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
                    }
                }
                case "4" ->
                {
                    while (true)
                    {
                        System.out.print("Lettura INIZIALE kCal primo piano: ");
                        String input = scanner.nextLine();
                        try
                        {
                            int valore = Integer.parseInt(input);
                            if (valore >= 0)
                            {
                                kCalInizioPrimo = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
                    }
                }
                case "5" ->
                {
                    while (true)
                    {
                        System.out.print("Lettura FINALE kCal primo piano: ");
                        String input = scanner.nextLine();
                        try
                        {
                            int valore = Integer.parseInt(input);
                            if (valore >= kCalInizioPrimo)
                            {
                                kCalFinePrimo = valore;
                                break;
                            }
                        } catch (Exception ignored)
                        {
                        }
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
    
    private void stampaRiepilogoStep3(boolean conNumeri)
    {
        Main.clearConsole();
        System.out.println(Main.creaTitolo("RIEPILOGO DATI INSERITI"));
        
        if (conNumeri)
        {
            System.out.println("1 = Modalità centralina: " + modalitaCentralina);
            System.out.println("2 = Lettura INIZIALE kCal piano terra: " + kCalInizioTerra);
            System.out.println("3 = Lettura FINALE kCal piano terra: " + kCalFineTerra);
            System.out.println("4 = Lettura INIZIALE kCal primo piano: " + kCalInizioPrimo);
            System.out.println("5 = Lettura FINALE kCal primo piano: " + kCalFinePrimo);
        }
        else
        {
            System.out.println("Modalità centralina: " + modalitaCentralina);
            System.out.println("Lettura INIZIALE kCal piano terra: " + kCalInizioTerra);
            System.out.println("Lettura FINALE kCal piano terra: " + kCalFineTerra);
            System.out.println("Lettura INIZIALE kCal primo piano: " + kCalInizioPrimo);
            System.out.println("Lettura FINALE kCal primo piano: " + kCalFinePrimo);
        }
        System.out.println();
    }
    
    // === GETTER STEP1 ===
    public double getImportoTotale()
    {
        return importoTotale;
    }
    
    public double getConsumoTotale()
    {
        return consumoTotale;
    }
    
    public int getPersonePianoTerra()
    {
        return personePianoTerra;
    }
    
    public int getPersonePianoPrimo()
    {
        return personePianoPrimo;
    }
    
    // === GETTER STEP2 ===
    public double getLetturaInizio()
    {
        return letturaInizioCaldaia;
    }
    
    public double getLetturaFine()
    {
        return letturaFineCaldaia;
    }
    
    // === GETTER STEP3 ===
    public String getModalitaCentralina()
    {
        return modalitaCentralina;
    }
    
    public double getKcalInizialiPianoTerra()
    {
        return kCalInizioTerra;
    }
    
    public double getKcalFinaliPianoTerra()
    {
        return kCalFineTerra;
    }
    
    public double getKcalInizialiPianoPrimo()
    {
        return kCalInizioPrimo;
    }
    
    public double getKcalFinaliPianoPrimo()
    {
        return kCalFinePrimo;
    }
}