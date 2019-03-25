package com.example.musicplayer;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp=MediaPlayer.create(this,R.raw.marveltheme);
        final Button playButton=(Button)findViewById(R.id.button1);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,playButton.getText(),Toast.LENGTH_SHORT).show();
                mp.start();
                //use of asynchronous callback method which toasts a message when song is completed
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(MainActivity.this,"I'm done",Toast.LENGTH_SHORT).show();
                        releaseMediaPlayer();
                    }
                });
            }
        });
        final Button pauseButton=(Button)findViewById(R.id.button2);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,pauseButton.getText(),Toast.LENGTH_SHORT).show();
                mp.stop();
            }
        });
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
        }
    }
}