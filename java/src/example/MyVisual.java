package example;

import C17368666.*;
import processing.core.PImage;

public class MyVisual extends Visual
{    
    WaveForm wf;
    AudioBandsVisual abv;
    float amp;
    PImage img;
    float angle = 0;
    Bubbles[] bubbles = new Bubbles[300];

    public void settings()
    {
        //size(1024, 500);
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D); 
    }

    public void setup()
    {
        colorMode(HSB);
        surface.setResizable(true);
        startMinim();

        img = loadImage("MusicVisuals/data/light background.jpeg");
        img.resize(width, height);

        // Call loadAudio to load an audio file to process 
        loadAudio("MusicVisuals/data/business.mp3");   

        
        // Call this instead to read audio from the microphone
        //startListening(); 
        
        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);

        for(int i = 0; i < bubbles.length; i++)
        {
            bubbles[i] = new Bubbles(this);
        }
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw()
    {
        background(img);
        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();
        amp = getSmoothedAmplitude();
        fill(0);
        noStroke();
        circle(width / 2, height / 2, 500);
        fill(255);
        circle(width / 2,height / 2, 150);
        fill(0);
        circle(width / 2, height / 2, 35);
        fill(0);
        textSize(40);
        textAlign(CENTER);
        text("Tiesto - Business (Official Audio)", width / 2 , 150);

        pushMatrix();
        lights();
        stroke(100);
        fill(map(getAmplitude(), 0, 1 , 0, 360));
        translate(width * 0.82f, height / 2);
        rotateY(angle);
        angle += 0.01f;
        sphere(100 );
        popMatrix();

        pushMatrix();
        lights();
        stroke(100);
        fill(map(getAmplitude(), 0, 1 , 0, 360));
        translate(width * 0.18f, height / 2);
        rotateY(angle);
        angle += 0.01f;
        sphere(100);
        popMatrix();
        

        
        wf.render();
        //abv.render();
        for(int i = 0; i < bubbles.length; i++)
        {
            bubbles[i].draw();
            bubbles[i].drop();
        }
    }
}
