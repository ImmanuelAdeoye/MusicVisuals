package example;
import java.util.Random;

import processing.core.*;

public class Bubbles {
    MyVisual mv;
    float x;
    float y;
    float ySpeed;

    public Bubbles(MyVisual mv)
    {
        this.mv = mv;
        this.x = mv.random(mv.width);
        this.y = mv.random(-200, -100);
        this.ySpeed = mv.random(4, 10);
    }
    //291.1
    public void drop()
    {
        y = y+ySpeed;
        if(y > mv.height)
        {
            y = mv.random(-200, -100);
        }
    }

    public void draw()
    {
        mv.noFill();
        mv.stroke(255);
        mv.circle(x, y, 20);
    }
}