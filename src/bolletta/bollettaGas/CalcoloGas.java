package bolletta.bollettaGas;

public class CalcoloGas
{
    private double importoTotale;
    private int personePianoTerra;
    private int personePianoPrimo;
    
    private int personeTotali;
    private double costoIndividualeGas;
    private double costoGasPianoTerra;
    private double costoGasPrimoPiano;
    
    public CalcoloGas(double importoTotale, int personePianoTerra, int personePianoPrimo)
    {
        this.importoTotale = importoTotale;
        this.personePianoTerra = personePianoTerra;
        this.personePianoPrimo = personePianoPrimo;
        
        eseguiCalcoli();
    }
    
    private void eseguiCalcoli()
    {
        personeTotali = personePianoTerra + personePianoPrimo;
        costoIndividualeGas = importoTotale / personeTotali;
        costoGasPianoTerra = costoIndividualeGas * personePianoTerra;
        costoGasPrimoPiano = costoIndividualeGas * personePianoPrimo;
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
    
    public double getCostoIndividualeGas()
    {
        return costoIndividualeGas;
    }
    
    public double getCostoGasPianoTerra()
    {
        return costoGasPianoTerra;
    }
    
    public double getCostoGasPrimoPiano()
    {
        return costoGasPrimoPiano;
    }
}