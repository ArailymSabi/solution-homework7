package streaming;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BingeIterator implements EpisodeIterator {
    private final Iterator<Season> seasonIterator;
    private EpisodeIterator currentEpisodeIterator;

    public BingeIterator(List<Season> seasons) {
        this.seasonIterator = seasons.iterator();
        if (seasonIterator.hasNext()) {
            currentEpisodeIterator = seasonIterator.next().createIterator();
        }
    }

    public boolean hasNext() {
        if (currentEpisodeIterator == null) return false;
        if (currentEpisodeIterator.hasNext()) return true;
        while (seasonIterator.hasNext()) {
            currentEpisodeIterator = seasonIterator.next().createIterator();
            if (currentEpisodeIterator.hasNext()) return true;
        }
        return false;
    }

    public Episode next() {
        if (hasNext()) {
            return currentEpisodeIterator.next();
        }
        throw new NoSuchElementException();
    }
}
