package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[36];
    FloatControl fc;
    int volumeScale = 3;
    float volume;



    public Sound(){
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/sound/levelup.wav");
        soundURL[9] = getClass().getResource("/sound/cursor.wav");
        soundURL[10] = getClass().getResource("/sound/burning.wav");
        soundURL[11] = getClass().getResource("/sound/cuttree.wav");
        soundURL[12] = getClass().getResource("/sound/gameover.wav");
        soundURL[13] = getClass().getResource("/sound/stairs.wav");
        soundURL[14] = getClass().getResource("/sound/sleep.wav");
        soundURL[15] = getClass().getResource("/sound/blocked.wav");
        soundURL[16] = getClass().getResource("/sound/parry.wav");
        soundURL[17] = getClass().getResource("/sound/speak.wav");
        soundURL[18] = getClass().getResource("/sound/Merchant.wav");
        soundURL[19] = getClass().getResource("/sound/Dungeon.wav");
        soundURL[20] = getClass().getResource("/sound/chipwall.wav");
        soundURL[21] = getClass().getResource("/sound/dooropen.wav");
        soundURL[22] = getClass().getResource("/sound/FinalBattle.wav");
        soundURL[23] = getClass().getResource("/sound/raygun_shoot.wav");
        soundURL[24] = getClass().getResource("/sound/gun_shot.wav");
        soundURL[25] = getClass().getResource("/sound/gun_shot_empty.wav");
        soundURL[26] = getClass().getResource("/sound/background_music.wav");
        soundURL[27] = getClass().getResource("/sound/boss_battle.wav");
        soundURL[28] = getClass().getResource("/sound/boss_battle_2.wav");
        soundURL[29] = getClass().getResource("/sound/victory.wav");
        soundURL[30] = getClass().getResource("/sound/vlad_attack_sound.wav");
        soundURL[31] = getClass().getResource("/sound/proud_creation.wav");
        soundURL[32] = getClass().getResource("/sound/killed_creation.wav");
        soundURL[33] = getClass().getResource("/sound/evil_laugh.wav");
        soundURL[34] = getClass().getResource("/sound/check_noise.wav");
        soundURL[35] = getClass().getResource("/sound/1_over_0.wav");



    }

    public void setFile(int i){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    public void checkVolume(){
        switch (volumeScale){
            case 0 : volume = -80f; break;
            case 1 : volume = -20f; break;
            case 2 : volume = -12f; break;
            case 3 : volume = -5f;  break;
            case 4 : volume = 1f;   break;
            case 5 : volume = 6f;   break;
        }
        fc.setValue(volume);
    }
}
