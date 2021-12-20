package view;

import java.io.File;
import javafx.scene.media.AudioClip;

public class Music {
    public static void playMusic()
    {
        AudioClip ac;
        ac = new AudioClip(new File("src/背景音1.wav").toURI().toString());
        ac.setCycleCount(10000); //设置循环次数
        ac.play();   //开始播放
    }

    public static void playMusic1()
    {
        AudioClip ac;
        ac = new AudioClip(new File("src/背景音2.wav").toURI().toString());
        ac.setCycleCount(10000); //设置循环次数
        ac.play();   //开始播放
    }

    public static void endMusic()
    {
        AudioClip ac;
        ac = new AudioClip(new File("src/背景音1.wav").toURI().toString());
        ac.stop();
    }

    public static void endMusic1()
    {
        AudioClip ac;
        ac = new AudioClip(new File("src/背景音2.wav").toURI().toString());
        ac.stop();
    }
}
