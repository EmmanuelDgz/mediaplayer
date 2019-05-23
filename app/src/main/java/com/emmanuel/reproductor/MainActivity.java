package com.emmanuel.reproductor;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnPlay;
    private Button btnStop;
    private Button btnPrevius;
    private Button btnNext;
    private TextView labelSong;
    private TextView labelAuthor;
    private ImageView portada;

    private ArrayList<Song> songs = new ArrayList<>();
    private MediaPlayer reproductor;
    private boolean paused = false;

    private int position = 0;
    private int listSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnPrevius = findViewById(R.id.btnPrevius);
        btnNext = findViewById(R.id.btnNext);
        labelSong = findViewById(R.id.labelSong);
        labelAuthor = findViewById(R.id.labelAuthor);
        portada = findViewById(R.id.imageView);

        songs.add(new Song("c1", "Hang On To Your IQ", "Placebo", "p1", this));
        songs.add(new Song("c2", "Campos Verdes", "Almendra", "p2", this));
        songs.add(new Song("c3", "Verde Llano", "Almendra", "p3", this));
        songs.add(new Song("c4", "Elementales Leches", "Invisible", "p4", this));
        songs.add(new Song("c5", "Algo Flota En La Laguna", "Pescado Rabioso", "p5", this));
        songs.add(new Song("c6", "Special Needs", "Placebo", "p6", this));

        listSize = songs.size();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproductor.stop();
            }
        });

        btnPrevius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = position - 1;
                if (position == -1)
                    position = songs.size() - 1;
                reproductor.stop();
                play();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = (position + 1) % listSize;
                reproductor.stop();
                play();
            }
        });
    }

    private void play() {
        if (reproductor != null && reproductor.isPlaying()) {
            reproductor.pause();
            paused = true;
        }
        else {
            labelSong.setText(songs.get(position).getName());
            labelAuthor.setText(songs.get(position).getAuthor());
            portada.setImageResource(songs.get(position).getAlbumID());
            if (!paused) {
                reproductor = songs.get(position).getMediaPlayer();
            }
            reproductor.start();
            paused = false;
        }
    }
}
