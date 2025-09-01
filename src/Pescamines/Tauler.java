package Pescamines;

import processing.core.PApplet;
import processing.core.PImage;

import static java.lang.Math.max;
import static processing.core.PApplet.floor;

public class Tauler {

    float x, y;
    float w, h;
    int num;
    boolean descobrirTotes;
    int numBombes, numPoints, numClicks;
    int numCasellesVisibles, numCasellesPerObrir;
    Casella[][] tauler;

    Tauler(int m, int nb, float x, float y, float w, float h){

        this.num = m;
        this.numBombes = nb;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.numCasellesPerObrir = (m*m) - nb;
        this.descobrirTotes = false;
        this.numPoints = 0;
        this.numClicks = 0;
        this.numCasellesVisibles = 0;

    }

    void setCaselles(){
        this.tauler = new Casella[this.num][this.num];

        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                this.tauler[f][c] = new Casella(f, c, false);
            }
        }
    }

    void setDescobrirTotes(boolean b){
        this.descobrirTotes = b;
    }

    void setBombes(PApplet p5){
        int num = 0;
        do{
            int rf = floor(p5.random(0, this.num));
            int rc = floor(p5.random(0, this.num));

            if(this.tauler[rf][rc].esBomba == false){
                this.tauler[rf][rc].setEsBomba(true);
                num++;
            }
        }while(num<this.numBombes);
    }

    void display(PApplet p5){
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                float xc = this.x + this.w*c;
                float yc = this.y + this.h*f;
                this.tauler[f][c].display(p5, xc, yc, this.w, this.h, this.descobrirTotes);
            }
        }
        p5.fill(184, 147, 196);
        p5.rect(0, 0, p5.width, this.y);
        p5. fill(0); p5.textSize(18); p5.textAlign(p5.CENTER, p5.CENTER);
        p5.text("POINTS: "+ this.numPoints+ " / CLICKS: "+ this.numClicks, p5.width/2, 30);
    }

    void calculaNumBombes(){
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                if(!this.tauler[f][c].esBomba){
                    int n = 0;
                    for(int fc = max(0, f-1);
                        fc >= 0 && fc<this.num && fc <= f+1;
                        fc++){
                        for(int rc = max(0, c-1);
                            rc>=0 && rc<this.num && rc <=c+1;
                            rc++){
                            if((fc!=f || rc!=c) &&
                                    this.tauler[fc][rc].esBomba){
                                n++;
                            }
                        }
                    }
                    this.tauler[f][c].setNumBombes(n);
                }
            }
        }
    }

    int[] casellaClicada(float mx, float my){
        int f = floor((my - this.y)/this.h);
        int c = floor((mx - this.x)/this.w);
        int[] indexos = {f, c};
        return indexos;
    }

    void updateClicks(){
        this.numClicks++;
    }

    void descobreix(int f, int c){
        this.tauler[f][c].setDescobert(true);
        this.numPoints += this.tauler[f][c].numBombes;
        this.numCasellesVisibles++;

        int minF = max(0, f-1);
        int maxF = this.num;

        for(int fc = minF; fc >= 0 && fc<maxF && fc <= f+1; fc++){
            int minC = max(0, c-1);
            int maxC = this.num;
            for(int rc= minC; rc>=0 && rc< maxC && rc <=c+1; rc++){
                if((fc!=f || rc!=c) && this.esUnZeroOcult(fc, rc)){
                    this.descobreix(fc, rc);
                }
            }
        }
    }

    void updateTauler(int f, int c){
        if(!this.tauler[f][c].esBomba){
            this.descobreix(f, c);
        }
    }

    boolean esUnZeroOcult(int f, int c){
        return !this.tauler[f][c].descobert &&
                this.tauler[f][c].numBombes == 0 &&
                !this.tauler[f][c].esBomba;
    }

}
