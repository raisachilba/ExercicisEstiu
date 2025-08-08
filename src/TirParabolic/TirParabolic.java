package TirParabolic;

import processing.core.PApplet;

public class TirParabolic extends PApplet {

    Target[] targets;
    Projectil p;
    float f = 100;
    float h = 400;
    boolean disparat = false;
    float t = 0;
    float g = 9.8f;
    int numShots = 0, numPoints = 0, numTargets = 0;

    public static void main(String[] args) {
        PApplet.main("TirParabolic.TirParabolic");
    }

    public void settings(){
        size(1460, 800);
    }

    public void setup(){
        p = new Projectil(100, h, 50);
        setTargets(3, 9);
    }

    public void draw(){
        background(220);

        for(int i=0; i<targets.length; i++) {
            targets[i].display(this);
        }
        p.display(this);
        displayInfo();

        if(!disparat) {
            float a = map(mouseY, this.h - 100, this.h + 100, 0, -PI);
            p.setProperties(a, f, h);
        }
        else if(disparat && p.x <= width && p.y <= height){
            p.update(t, g);
            t += 0.1;
            for(int i=0; i<targets.length; i++){
                if(targets[i].estat != Target.ESTAT.EXPLOTAT &&
                        targets[i].esImpactatPer(this, p)){
                    targets[i].setEstat(Target.ESTAT.EXPLOTAT);
                    numPoints++;
                }
            }
        }
        else if (disparat && (p.x > width || p.y > height)) {
            for(int i=0; i<targets.length; i++){
                if(targets[i].estat == Target.ESTAT.PENDENT){
                    targets[i].setEstat(Target.ESTAT.FALLAT);
                }
            }
            textAlign(CENTER); textSize(36); fill(255, 0, 0);
            text("Press R key to set up the next scenario", width/2, height/2);
        }
    }

    public void keyPressed(){
        if (keyCode == RIGHT) {
            f += 10;
            f = constrain(f, 10, 300);
        }
        else if (keyCode == LEFT) {
            f -= 10;
            f = constrain(f, 10, 300);
        }
        else if (keyCode == UP) {
            h -= 10;
        }
        else if (keyCode == DOWN) {
            h += 10;
        }
        if(key == 's' || key == 'S'){
            if(!disparat){
                numShots++;
            }
            disparat = true;
        }
        else if (key == 'r') {
            disparat = false;
            t = 0;
            setTargets(3, 12);
        }
    }

    void setTargets(int n1, int n2){

        int nt = (int) random(n1, n2);
        targets = new Target[nt];
        for(int i=0; i<nt; i++){
            float x = random(width/2, width);
            float y = random(height/5, 4*height/5);
            float r = random(20, 60);
            targets[i] = new Target(x, y, r);
        }
        numTargets += nt;
    }

    void displayInfo(){
        fill(0); textAlign(LEFT); textSize(34);
        text("Tir Parabòlic", 50, 50);

        fill(0); textAlign(RIGHT);
        text("Score", width - 50, 50);
        textSize(14);
        String percentatge = nf(100*(numPoints/(float)numTargets), 2, 2);
        text("Rate: "+ percentatge+"%", width - 50, 80);
        text("Hits: "+ numPoints + " / " + numTargets, width - 50, 100);
        text("Shots: "+ numShots, width - 50, 120);

        fill(0); textSize(14); textAlign(LEFT);
        text("Press S key to shot your cannon.", 50, height-90);
        text("Use MOUSE to set your cannon direction.", 50, height-70);
        text("Press ARROW KEYS to set up your cannon.", 50, height-50);
        text("UP: move up, DOWN: move down, LEFT: decrease force, RIGHT: increase force.", 50, height-30);

    }
}
