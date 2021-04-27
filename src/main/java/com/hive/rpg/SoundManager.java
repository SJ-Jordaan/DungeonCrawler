package com.hive.rpg;

import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static Map<String, URL> soundMap = new HashMap<String, URL> ();
    private static Clip clip;
    public static float volume = 0.1f;
    public SoundManager() {
        try {
            URL url = new URL("http://codeskulptor-demos.commondatastorage.googleapis.com/GalaxyInvaders/bonus.wav");
            soundMap.put("bonus", url);
        } catch (IOException m) {
            System.out.println(m.toString());
        }
    }

    public void playSound (String sound) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundMap.get(sound));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e ) {
            System.out.println(e.toString());
        }
    }
}
