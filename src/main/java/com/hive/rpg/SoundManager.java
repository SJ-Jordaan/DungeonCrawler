package com.hive.rpg;

import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.net.URL;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private Map<String, AudioInputStream> soundMap;
    public SoundManager() {
        soundMap = new HashMap<String, AudioInputStream> ();
        try {
            URL url = new URL("http://codeskulptor-demos.commondatastorage.googleapis.com/GalaxyInvaders/bonus.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            soundMap.put("bonus", ais);
            clip.open(soundMap.get("bonus"));
            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException m) {
            System.out.println(m.toString());
        }
    }

    public void playSound () {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(soundMap.get("bonus"));
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException e ) {
            System.out.println(e.toString());
        }
    }
}
