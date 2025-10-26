package bolletta.bollettaAcqua;

public class CalcoloAcqua
{
    private double importoTotale;
    private int personePianoTerra;
    private int personePianoPrimo;
    
    private int personeTotali;
    private double costoIndividualeAcqua;
    private double costoAcquaPianoTerra;
    private double costoAcquaPrimoPiano;
    
    public CalcoloAcqua(double importoTotale, int personePianoTerra, int personePianoPrimo)
    {
        this.importoTotale = importoTotale;
        this.personePianoTerra = personePianoTerra;
        this.personePianoPrimo = personePianoPrimo;
        
        eseguiCalcoli();
    }
    
    private void eseguiCalcoli()
    {
        personeTotali = personePianoTerra + personePianoPrimo;
        costoIndividualeAcqua = importoTotale / personeTotali;
        costoAcquaPianoTerra = costoIndividualeAcqua * personePianoTerra;
        costoAcquaPrimoPiano = costoIndividualeAcqua * personePianoPrimo;
    }
    
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
    
    public int getPersoneTotali()
    {
        return personeTotali;
    }
    
    public double getCostoIndividualeAcqua()
    {
        return costoIndividualeAcqua;
    }
    
    public double getCostoAcquaPianoTerra()
    {
        return costoAcquaPianoTerra;
    }
    
    public double getCostoAcquaPrimoPiano()
    {
        return costoAcquaPrimoPiano;
    }
}