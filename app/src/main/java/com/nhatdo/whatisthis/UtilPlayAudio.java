package com.nhatdo.whatisthis;

import android.content.Context;
import android.media.MediaPlayer;

import com.nhatdo.whatisthis.bean.FlashCardDetails;

import java.util.List;

public class UtilPlayAudio {

    public static MediaPlayer audio;

    public static void playAudio(int position, List<FlashCardDetails> list, Context context) {
        stop();
        int getPronounceAudioId = 0;
        getPronounceAudioId = list.get(position).getPronounceAudioId();
        if (getPronounceAudioId != 0) {
            audio = MediaPlayer.create(context, getPronounceAudioId);
            if (audio.isPlaying()) {
                stop();
            }
            audio.start();
            audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer audio) {
                    stop();
                }
            });
        }
    }

    public static void playAudioForLong(int position, List<FlashCardDetails> list, Context context) {
        stop();
        int audioId = 0;
        audioId = list.get(position).getAudioId();
        if (audioId != 0) {
            audio = MediaPlayer.create(context, audioId);
            if (audio.isPlaying()) {
                stop();
            }
            audio.start();
            audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer audio) {
                    stop();
                }
            });
        }
    }

    static void stop() {
        if (audio != null) {
            audio.reset();
            audio.release();
            audio = null;
        }
    }

}
