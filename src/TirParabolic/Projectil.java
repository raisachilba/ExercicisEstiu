package TirParabolic;

import processing.core.PApplet;

import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;

public class Projectil {

    public int r;
    float x0, y0;
    float x, y;
    float f, a;
    float v0x, v0y, vy;

    Projectil(float x, float h, float f){

        this.x0 = x;
        this.y0 = h;

        this.x = x;
        this.y = h;

        this.f = f;
        this.a = 0;

        this.v0x = + f * cos(this.a);
        this.v0y = - f * sin(this.a);
        this.vy = this.v0y;
    }

    void display(PApplet p5){
        float dx = this.x0 + this.f *cos(this.a);
        float dy = this.y0 - this.f *sin(this.a);

        p5.pushStyle();

        p5.strokeWeight(10); p5.stroke(0);
        p5.line(this.x0, this.y0, dx, dy);
        p5.stroke(0); p5.fill(0);
        p5.rect(this.x0 - 20, this.y0, 40, 20);
        p5.noStroke(); p5.fill(255, 0, 0);
        p5.circle(this.x, this.y, 10);

        p5.fill(0); p5.textSize(14); p5.textAlign(p5.LEFT);
        p5.text("Sx:"+ p5.nf(this.x,3,2)+", Sy: "+ p5.nf(this.y,3,2),55,80);
        p5.text("Vx:"+ p5.nf(this.v0x,2,2)+",Vy:"+ p5.nf(this.vy,2,2),55,100);
        p5.text("F: " + p5.nf(this.f, 2, 2), 55, 120);

        p5.popStyle();
    }

    void setProperties(float a, float f, float h){
        this.y = h;
        this.y0 = h;

        this.a = a;
        this.f = f;

        this.v0x = + this.f * cos(this.a);
        this.v0y = - this.f * sin(this.a);
        this.vy = this.v0y;
    }

    void update(float t, float g){
        this.x = this.x0 + this.v0x * t;
        this.y = this.y0 + this.vy*t - 0.5f*g*t*t;

        this.vy = this.v0y + g*t;
    }
}

