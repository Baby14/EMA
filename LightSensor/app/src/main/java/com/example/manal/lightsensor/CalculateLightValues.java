package com.example.manal.lightsensor;

/**
 * Created by Manal on 06.11.2015.
 */
public class CalculateLightValues {

    //empfohlenen Werte für C bei 320 bis 540, wobei meist Werte bei 340 üblich sind
    private static final int  C_CONSTANT = 340;
    private double lx_of_illuminance, k, t, ev;
    private int iso;

    //Extra return int, damit eine Gleitzahl ohne Komma raus kommt
    public int calculateEV(double lx, int iso){
        return (int) calculateEVRatio(lx, iso);
    }

    // EV als rationale Zahl
    public double calculateEVRatio(double lx, int iso){
        this.iso = iso;
        this.lx_of_illuminance = lx;
        double log = (this.lx_of_illuminance*this.iso)/C_CONSTANT;
        return log2(log);
    }


    //Ld --> log base 2
    private double log2(double n){
        return (Math.log(n) / Math.log(2));
    }

    //BlendenZahl k aus EV berechnen
    public double calculateApertureEV(double shutterSpeed, double ev){
        this.t = shutterSpeed;
        this.ev= ev;
        //aus der Formel 2^EV=k^2/t --> k=Wurzel(2^EV * t)
        return this.k = Math.sqrt(t*Math.pow(2,ev));

    }

    //Verschlusszeit t aus EV berechnen
    public double calculateShutterSpeedEV(double aperture, double ev){
        this.ev= ev;
        this.k = aperture;
        // aus der Gleichung EV= ld(k^2/t)
        //return this.t = log2(k*k)-ev;
        //aus der Formel 2^EV=k^2/t--> t=2^EV/k^2
        return this.t = (k*k)/Math.pow(2,ev);

    }

    //BlendenZahl k aus Lux berechnen
    public double calculateAperture(int isoValue, double shutterSpeed, double lx){
        this.iso = isoValue;
        this.t = shutterSpeed;
        this.lx_of_illuminance = lx;
        return this.k = Math.sqrt((lx_of_illuminance*iso*t)/C_CONSTANT);

    }

    //Verschlusszeit t berechnen
    public double calculateShutterSpeed(int isoValue, double aperture, double lx){
        this.iso = isoValue;
        this.k = aperture;
        this.lx_of_illuminance=lx;
        return this.t = ((Math.pow(k,2)*C_CONSTANT)/(lx_of_illuminance*iso));

    }

    //ISO berechnen --> Macht kein Sinn
    public int calculateISO(double aperture, double shutterSpeed){
        this.k = aperture;
        this.t = shutterSpeed;
        return this.iso = (int) ((k*k*C_CONSTANT)/(t*lx_of_illuminance));

    }
}
