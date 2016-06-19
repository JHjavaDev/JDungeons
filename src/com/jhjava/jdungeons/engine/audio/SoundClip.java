package com.jhjava.jdungeons.engine.audio;

import com.jhjava.jdungeons.engine.Input;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundClip {
	private Clip clip;
	private FloatControl gainControll;

	public SoundClip(String path) {
		try {
			InputStream audioSource = SoundClip.class.getResourceAsStream("/com/jhjava/jdungeons/res/audio/" + path);
			BufferedInputStream bufferedIn = new BufferedInputStream(audioSource);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
			AudioFormat baseFormat = audioInputStream.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

			AudioInputStream decodedAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);

			clip = AudioSystem.getClip();
			clip.open(decodedAudioInputStream);

			gainControll = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {
			System.err.println("Error: Could not load sound " + path);
			e.printStackTrace();
		}
	}

	public void play() {
		if(clip == null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
		while(!clip.isRunning()) {
			clip.start();
		}
	}

	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}

	public void close() {
		stop();
		clip.drain();
		clip.close();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		while(!clip.isRunning()) {
			clip.start();
		}
	}

	public void setVolume(float value) {
		gainControll.setValue(value);
	}

	public boolean isRunning() {
		return clip.isRunning();
	}
}
