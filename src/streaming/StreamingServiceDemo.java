package streaming;

public class StreamingServiceDemo {
    public static void main(String[] args) {
        Season season1 = new Season();
        season1.addEpisode(new Episode("Pilot", 1800));
        season1.addEpisode(new Episode("Episode 2", 1900));

        Season season2 = new Season();
        season2.addEpisode(new Episode("Season 2 Premiere", 2000));
        season2.addEpisode(new Episode("Episode 2", 2100));

        Series series = new Series();
        series.addSeason(season1);
        series.addSeason(season2);

        System.out.println("Binge Watching:");
        EpisodeIterator binge = series.createBingeIterator();
        while (binge.hasNext()) {
            System.out.println(binge.next().getTitle());
        }
    }
}
