package mbm.genai_service.domain;

import lombok.Builder;

@Builder
public record TrackDto(String artist, String title) {
}
