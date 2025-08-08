package Tetris;

import processing.core.PApplet;

public class Colors {

    int colorBUIT;
    int colorI, colorO, colorS, colorSI, colorT, colorL, colorLI;
    int[] colors;

    public Colors(PApplet p5){
        colorBUIT   = p5.color(255);
        colorI      = p5.color(0,255,255);
        colorO      = p5.color(0,0,255);
        colorS      = p5.color(255,0,255);
        colorSI     = p5.color(155,0,155);
        colorT      = p5.color(0,255,0);
        colorL      = p5.color(255,255,0);
        colorLI     = p5.color(155,155,0);

        colors = new int[8];

        colors[0] = colorBUIT;
        colors[1] = colorI;
        colors[2] = colorO;
        colors[3] = colorS;
        colors[4] = colorSI;
        colors[5] = colorT;
        colors[6] = colorL;
        colors[7] = colorLI;
    }
}
