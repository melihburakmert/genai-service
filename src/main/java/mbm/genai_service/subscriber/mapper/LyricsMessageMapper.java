package mbm.genai_service.subscriber.mapper;

import mbm.genai_service.domain.LyricsDataDto;
import mbm.genai_service.domain.LyricsDto;
import mbm.genai_service.domain.TrackDto;
import mbm.genai_service.subscriber.message.Lyrics;
import mbm.genai_service.subscriber.message.LyricsMessage;
import mbm.genai_service.subscriber.message.Track;
import org.springframework.stereotype.Component;

@Component
public class LyricsMessageMapper {

    public LyricsDataDto map(final LyricsMessage lyricsMessage) {
        return new LyricsDataDto(lyricsMessage.lyrics().stream().map(this::mapLyrics).toList());
    }

    private LyricsDto mapLyrics(final Lyrics lyrics) {
        return new LyricsDto(mapTrack(lyrics.track()), lyrics.lyrics());
    }

    private TrackDto mapTrack(final Track track) {
        return TrackDto.builder().artist(track.artist()).title(track.title()).build();
    }
}
