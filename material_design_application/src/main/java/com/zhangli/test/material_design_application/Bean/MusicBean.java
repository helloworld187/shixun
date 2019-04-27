package com.zhangli.test.material_design_application.Bean;

public class MusicBean {
    private String album;
    private String musicName;
    private String singerName;
    private String musicUrl;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "album='" + album + '\'' +
                ", musicName='" + musicName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                '}';
    }
}
