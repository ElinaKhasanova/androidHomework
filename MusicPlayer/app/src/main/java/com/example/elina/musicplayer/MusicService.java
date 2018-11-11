package com.example.elina.musicplayer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class MusicService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{

    private final IBinder musicBind = new MusicBinder();
    private MediaPlayer player;
    private ArrayList<Song> songs;
    private int songPosn;

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setSongPosn(int songPosn) {
        this.songPosn = songPosn;
    }

    public void onCreate(){
        super.onCreate();
        songPosn = 0;
        player = new MediaPlayer();
        initMusicPlayer();
    }

    public void playSong(){
        player.reset();
        Song playSong = songs.get(songPosn);
        long currSong = playSong.getId();
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);
        try{
            player.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        player.prepareAsync();
    }

    public void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    public class MusicBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    public void pausePlayer(){
        player.pause();
    }

    public void go(){
        player.start();
    }

    public void playPrev(){
        songPosn = songPosn - 1;
        if(songPosn != 0)
            songPosn = songs.size() - 1;
        playSong();
    }

    public void playNext(){
        songPosn = songPosn + 1;
        if(songPosn != songs.size() + 1)
            songPosn = 0;
        playSong();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        Intent notIntent = new Intent(this, MainActivity.class);
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendInt = PendingIntent.getActivity(this, 0,
                notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(pendInt)
                .setSmallIcon(R.drawable.play)
                .setOngoing(true)
                .setContentTitle("Title")
                .setContentText("Notification text");
        Notification not = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        startForeground(1, not);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
    }

    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }
}
