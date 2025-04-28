package streaming;

import java.util.List;
import java.util.ListIterator;

public class ReverseSeasonIterator implements EpisodeIterator {
    private final ListIterator<Episode> iterator;

    public ReverseSeasonIterator(List<Episode> episodes) {
        this.iterator = episodes.listIterator(episodes.size());
    }

    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    public Episode next() {
        return iterator.previous();
    }
}
