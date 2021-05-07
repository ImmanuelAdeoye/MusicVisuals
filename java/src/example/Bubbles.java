package example;
import java.util.Random;

import processing.core.*;

public class Bubbles {
    MyVisual mv;
    float x;
    float y;
    float Speed;

    public Bubbles(MyVisual mv)
    {
        this.mv = mv;
        this.x = mv.random(mv.width);
        this.y = mv.random(-250, -150);
        this.Speed = mv.random(1, 6);
    }
    //291.1
    public void drop()
    {
        y = y+Speed;
        if(y > mv.height)
        {
            y = mv.random(-250, -150);
        }
    }

    public void draw()
    {
        mv.noFill();
        mv.stroke(255);
        mv.circle(x, y, 20);
    }
}