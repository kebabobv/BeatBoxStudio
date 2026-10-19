package com.miniphone.beatboxstudio;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Oggetto che gestisce i suoni
    private SoundPool soundPool;

    // Identificatori dei suoni caricati
    private int kickSound;
    private int snareSound;
    private int closedHatSound;
    private int openHatSound;
    private int clapSound;
    private int tomSound;
    private int crashSound;
    private int rimSound;

    // Indicano se i suoni sono pronti per essere riprodotti
    private boolean kickLoaded = false;
    private boolean snareLoaded = false;
    private boolean closedHatLoaded = false;
    private boolean openHatLoaded = false;
    private boolean clapLoaded = false;
    private boolean tomLoaded = false;
    private boolean crashLoaded = false;
    private boolean rimLoaded = false;

    // Parametri usati per riprodurre i suoni
    private float leftVolume = 1.0f;
    private float rightVolume = 1.0f;
    private int playPriority = 1;
    private int loopCount = 0;
    private float playRate = 1.0f;

    // Priorita' usata durante il caricamento dei suoni
    private int loadPriority = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creaSoundPool();
        caricaSuoni();
        collegaPulsanti();
    }

    // Crea il sistema che gestisce la riproduzione audio
    private void creaSoundPool() {

        AudioAttributes attributi = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(
                        AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(8)
                .setAudioAttributes(attributi)
                .build();

        // Controlla quando un suono termina il caricamento
        soundPool.setOnLoadCompleteListener(
                new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(
                            SoundPool pool, int sampleId, int status) {

                        if (status == 0) {

                            if (sampleId == kickSound) {
                                kickLoaded = true;
                            } else if (sampleId == snareSound) {
                                snareLoaded = true;
                            } else if (sampleId == closedHatSound) {
                                closedHatLoaded = true;
                            } else if (sampleId == openHatSound) {
                                openHatLoaded = true;
                            } else if (sampleId == clapSound) {
                                clapLoaded = true;
                            } else if (sampleId == tomSound) {
                                tomLoaded = true;
                            } else if (sampleId == crashSound) {
                                crashLoaded = true;
                            } else if (sampleId == rimSound) {
                                rimLoaded = true;
                            }
                        }
                    }
                });
    }

    // Carica gli otto file audio dalla cartella res/raw
    private void caricaSuoni() {

        kickSound = soundPool.load(
                this, R.raw.kick, loadPriority);

        snareSound = soundPool.load(
                this, R.raw.snare, loadPriority);

        closedHatSound = soundPool.load(
                this, R.raw.hihat_closed, loadPriority);

        openHatSound = soundPool.load(
                this, R.raw.hihat_open, loadPriority);

        clapSound = soundPool.load(
                this, R.raw.clap, loadPriority);

        tomSound = soundPool.load(
                this, R.raw.tom, loadPriority);

        crashSound = soundPool.load(
                this, R.raw.crash, loadPriority);

        rimSound = soundPool.load(
                this, R.raw.rim, loadPriority);
    }

    // Collega ogni pad al metodo che riproduce il suo suono
    private void collegaPulsanti() {

        LinearLayout padKick = findViewById(R.id.padKick);
        LinearLayout padSnare = findViewById(R.id.padSnare);
        LinearLayout padClosedHat =
                findViewById(R.id.padClosedHat);
        LinearLayout padOpenHat =
                findViewById(R.id.padOpenHat);
        LinearLayout padClap = findViewById(R.id.padClap);
        LinearLayout padTom = findViewById(R.id.padTom);
        LinearLayout padCrash = findViewById(R.id.padCrash);
        LinearLayout padRim = findViewById(R.id.padRim);

        padKick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playKick();
            }
        });

        padSnare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSnare();
            }
        });

        padClosedHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClosedHat();
            }
        });

        padOpenHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playOpenHat();
            }
        });

        padClap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClap();
            }
        });

        padTom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTom();
            }
        });

        padCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playCrash();
            }
        });

        padRim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRim();
            }
        });
    }

    // Riproduce il suono della cassa
    private void playKick() {
        if (kickLoaded) {
            soundPool.play(kickSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }

    // Riproduce il suono del rullante
    private void playSnare() {
        if (snareLoaded) {
            soundPool.play(snareSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }

    // Riproduce il suono del piatto chiuso
    private void playClosedHat() {
        if (closedHatLoaded) {
            soundPool.play(closedHatSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }

    // Riproduce il suono del piatto aperto
    private void playOpenHat() {
        if (openHatLoaded) {
            soundPool.play(openHatSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }

    // Riproduce il suono del clap
    private void playClap() {
        if (clapLoaded) {
            soundPool.play(clapSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }

    // Riproduce il suono del tom
    private void playTom() {
        if (tomLoaded) {
            soundPool.play(tomSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }

    // Riproduce il suono del crash
    private void playCrash() {
        if (crashLoaded) {
            soundPool.play(crashSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }

    // Riproduce il suono del rimshot
    private void playRim() {
        if (rimLoaded) {
            soundPool.play(rimSound, leftVolume, rightVolume,
                    playPriority, loopCount, playRate);
        }
    }
}