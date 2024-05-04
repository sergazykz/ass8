import java.util.ArrayList;
import java.util.List;

interface Iterator {
    boolean hasNext();
    Song next();
}

interface Playlist {
    Iterator createIterator();
    void addSong(Song song);
}

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + artist;
    }
}

class PlaylistImpl implements Playlist {
    private List<Song> songs;

    public PlaylistImpl() {
        this.songs = new ArrayList<>();
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public Iterator createIterator() {
        return new SongIterator(songs);
    }

    private class SongIterator implements Iterator {
        private int currentIndex = 0;
        private List<Song> songs;

        public SongIterator(List<Song> songs) {
            this.songs = songs;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < songs.size();
        }

        @Override
        public Song next() {
            return songs.get(currentIndex++);
        }
    }
}

public class Ass8b {
    public static void main(String[] args) {
        Playlist playlist = new PlaylistImpl();
        playlist.addSong(new Song("Almatynyn tunderi", "Kairat Nurtas"));
        playlist.addSong(new Song("Ana", "Sadraddin"));
        playlist.addSong(new Song("Auylymdy Sagyndym", "Raim"));

        Iterator iterator = playlist.createIterator();
        System.out.println("Playlist:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}