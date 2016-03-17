package cn.by.eform.ui.trigger;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;


public class SoundPlay {
	private static String lastfileName;
	private static AudioStream m_as=null;
	
	
	public void playOnce(String fileName) {
		String pathName = System.getProperty("user.dir") + "/SoundFiles/" + fileName + ".wav";
		try {
			InputStream in = new FileInputStream(pathName);
			AudioStream as = new AudioStream(in);
			if(m_as!=null)
			{
				AudioPlayer.player.stop(m_as);
			}
			m_as = as;
			AudioPlayer.player.start(m_as);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void playCyc(String fileName) {
		String pathName = System.getProperty("user.dir") + "/SoundFiles/" + fileName+ ".wav";
		try {
			InputStream in = new FileInputStream(pathName);
			AudioStream as = new AudioStream(in);
			AudioData ad = as.getData();
			ContinuousAudioDataStream cas = new ContinuousAudioDataStream (ad);
			AudioPlayer.player.start(cas);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
