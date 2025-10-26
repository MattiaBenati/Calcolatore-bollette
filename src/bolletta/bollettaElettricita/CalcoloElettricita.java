package bolletta.bollettaElettricita;

public class CalcoloElettricita
{
    
    private double importoTotale, consumoTotale;
    private int personePianoTerra, personePianoPrimo;
    private double letturaInizio, letturaFine;
    private String modalitaCentralina;
    private double kcalInizialiPianoTerra, kcalFinaliPianoTerra;
    private double kcalInizialiPianoPrimo, kcalFinaliPianoPrimo;
    
    private double consumoCaldaia, consumoPianoTerraDiretto;
    private double costoPerKWh, costoTotaleCaldaia;
    private double kcalConsumatePianoTerra, kcalConsumatePianoPrimo;
    private double costoPerKCalCaldaia, costoPerPersonaCaldaia;
    private double costoCaldaiaPianoTerra, costoCaldaiaPianoPrimo;
    private double costoPianoTerraDiretto, totalePianoTerra, totalePianoPrimo;
    
    public CalcoloElettricita(double importoTotale, double consumoTotale, int personePianoTerra, int personePianoPrimo,
                              double letturaInizio, double letturaFine, String modalitaCentralina,
                              double kcalInizialiPianoTerra, double kcalFinaliPianoTerra,
                              double kcalInizialiPianoPrimo, double kcalFinaliPianoPrimo)
    {
        
        this.importoTotale = importoTotale;
        this.consumoTotale = consumoTotale;
        this.personePianoTerra = personePianoTerra;
        this.personePianoPrimo = personePianoPrimo;
        this.letturaInizio = letturaInizio;
        this.letturaFine = letturaFine;
        this.modalitaCentralina = modalitaCentralina;
        this.kcalInizialiPianoTerra = kcalInizialiPianoTerra;
        this.kcalFinaliPianoTerra = kcalFinaliPianoTerra;
        this.kcalInizialiPianoPrimo = kcalInizialiPianoPrimo;
        this.kcalFinaliPianoPrimo = kcalFinaliPianoPrimo;
        
        eseguiCalcoli();
    }
    
    private void eseguiCalcoli()
    {
        consumoCaldaia = letturaFine - letturaInizio;
        consumoPianoTerraDiretto = consumoTotale - consumoCaldaia;
        costoPerKWh = importoTotale / consumoTotale;
        costoTotaleCaldaia = consumoCaldaia * costoPerKWh;
        
        kcalConsumatePianoTerra = kcalFinaliPianoTerra - kcalInizialiPianoTerra;
        kcalConsumatePianoPrimo = kcalFinaliPianoPrimo - kcalInizialiPianoPrimo;
        
        if (modalitaCentralina.equals("riscaldamento"))
        {
            if (kcalConsumatePianoTerra != 0 && kcalConsumatePianoPrimo != 0)
            {
                costoPerKCalCaldaia = costoTotaleCaldaia / (kcalConsumatePianoTerra + kcalConsumatePianoPrimo);
                costoCaldaiaPianoTerra = kcalConsumatePianoTerra * costoPerKCalCaldaia;
                costoCaldaiaPianoPrimo = kcalConsumatePianoPrimo * costoPerKCalCaldaia;
            }
            else if (kcalConsumatePianoTerra == 0 && kcalConsumatePianoPrimo == 0)
            {
                costoPerPersonaCaldaia = costoTotaleCaldaia / (personePianoTerra + personePianoPrimo);
                costoCaldaiaPianoTerra = personePianoTerra * costoPerPersonaCaldaia;
                costoCaldaiaPianoPrimo = personePianoPrimo * costoPerPersonaCaldaia;
            }
            else
            {
                if (kcalConsumatePianoTerra != 0)
                {
                    costoCaldaiaPianoTerra = costoTotaleCaldaia;
                    costoCaldaiaPianoPrimo = 0;
                }
                else
                {
                    costoCaldaiaPianoTerra = 0;
                    costoCaldaiaPianoPrimo = costoTotaleCaldaia;
                }
            }
        }
        else if (modalitaCentralina.equals("raffrescamento"))
        {
            costoCaldaiaPianoTerra = 0;
            costoCaldaiaPianoPrimo = costoTotaleCaldaia;
        }
        
        costoPianoTerraDiretto = consumoPianoTerraDiretto * costoPerKWh;
        totalePianoTerra = costoPianoTerraDiretto + costoCaldaiaPianoTerra;
        totalePianoPrimo = costoCaldaiaPianoPrimo;
    }
    
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
    
    public double getLetturaInizio()
    {
        return letturaInizio;
    }
    
    public double getLetturaFine()
    {
        return letturaFine;
    }
    
    public String getModalitaCentralina()
    {
        return modalitaCentralina;
    }
    
    public double getKcalInizialiPianoTerra()
    {
        return kcalInizialiPianoTerra;
    }
    
    public double getKcalFinaliPianoTerra()
    {
        return kcalFinaliPianoTerra;
    }
    
    public double getKcalInizialiPianoPrimo()
    {
        return kcalInizialiPianoPrimo;
    }
    
    public double getKcalFinaliPianoPrimo()
    {
        return kcalFinaliPianoPrimo;
    }
    
    public double getConsumoCaldaia()
    {
        return consumoCaldaia;
    }
    
    public double getConsumoPianoTerraDiretto()
    {
        return consumoPianoTerraDiretto;
    }
    
    public double getCostoPerKWh()
    {
        return costoPerKWh;
    }
    
    public double getCostoTotaleCaldaia()
    {
        return costoTotaleCaldaia;
    }
    
    public double getKcalConsumatePianoTerra()
    {
        return kcalConsumatePianoTerra;
    }
    
    public double getKcalConsumatePianoPrimo()
    {
        return kcalConsumatePianoPrimo;
    }
    
    public double getCostoPerKCalCaldaia()
    {
        return costoPerKCalCaldaia;
    }
    
    public double getCostoPerPersonaCaldaia()
    {
        return costoPerPersonaCaldaia;
    }
    
    public double getCostoCaldaiaPianoTerra()
    {
        return costoCaldaiaPianoTerra;
    }
    
    public double getCostoCaldaiaPianoPrimo()
    {
        return costoCaldaiaPianoPrimo;
    }
    
    public double getCostoPianoTerraDiretto()
    {
        return costoPianoTerraDiretto;
    }
    
    public double getTotalePianoTerra()
    {
        return totalePianoTerra;
    }
    
    public double getTotalePianoPrimo()
    {
        return totalePianoPrimo;
    }
}