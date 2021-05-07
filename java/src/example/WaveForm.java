package example;

import processing.core.*;

// This is an example of a visual that renders the waveform
public class WaveForm
{
    MyVisual mv;
    float cy = 0;

    public WaveForm(MyVisual mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
    }

    public void render()
    {
        mv.colorMode(PApplet.HSB);
        for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        {
            mv.stroke(
                PApplet.map(i, 0, mv.getAudioBuffer().size(), 0, 255)
                , 255
                , 255
            );
            
            float y1 = mv.height*0.9f;
            float border = mv.width*0.3f;
            float x1 = PApplet.map(i, 0, mv.getAudioBuffer().size(), border, mv.width-border);
            mv.line(x1, y1, x1, y1 + 50 * mv.getAudioBuffer().get(i));
        }
    }
}