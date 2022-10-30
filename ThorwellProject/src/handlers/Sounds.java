/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import city.cs.engine.SoundClip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * NOTE
 * Music and SFX here were downloaded from:
 * ZapSplat | www.zapspalt.com
 * freesfx | www.freesfx.co.uk
 * freedsound | www.freesound.org
 */
/**
 * Handles the retrieval of sounds from files and allows it to be played,
 * stopped, manipulated.
 *
 * @author jly09
 */
public class Sounds {

    private SoundClip bowFiring;
    private SoundClip arrowHitting;
    private SoundClip shieldHit;
    private SoundClip arrowAgainstArrowHit;
    private SoundClip arrowHittingBody;

    /**
     *
     */
    public Sounds() {
        
        try {
            bowFiring = new SoundClip("./data/sounds/sfx/arrow_impact.wav");
            arrowHitting = new SoundClip("./data/sounds/sfx/bow_release.wav");
            shieldHit = new SoundClip("./data/sounds/sfx/shield_hit.wav");
            arrowAgainstArrowHit = new SoundClip("./data/sounds/sfx/arrow_against_arrow.wav");
            arrowHittingBody = new SoundClip("./data/sounds/sfx/arrow_impact_body.wav");

            bowFiring.setVolume(0.3f);
            arrowHitting.setVolume(0.3f);
            shieldHit.setVolume(0.3f);
            arrowAgainstArrowHit.setVolume(0.3f);
            arrowHittingBody.setVolume(0.2f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

    }

    /**
     *
     * @return
     */
    public SoundClip playBowFiring() {
        return bowFiring;
    }

    /**
     *
     * @return
     */
    public SoundClip playArrowHitting() {
        return arrowHitting;
    }

    /**
     *
     * @return
     */
    public SoundClip playShieldHit() {
        return shieldHit;
    }
    
    public SoundClip playArrowHittingBody() {
        return arrowHittingBody;
    }

    /*public SoundClip playArrowAgainstArrowHit() {
        return arrowAgainstArrowHit;
    }*/
}
