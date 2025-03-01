package mbm.genai_service.web;

import mbm.genai_service.caption.CaptionService;
import mbm.genai_service.image_analysis.ImageAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/genai")
public class GenAIController {

    private final ImageAnalysisService imageAnalysisService;
    private final CaptionService captionService;

    public GenAIController(final ImageAnalysisService imageAnalysisService,
                           final CaptionService captionService) {
        this.imageAnalysisService = imageAnalysisService;
        this.captionService = captionService;
    }

    @PostMapping(value = "/image-description", consumes = "multipart/form-data")
    public ResponseEntity<String> describeImage(
            @RequestParam("image") final MultipartFile image,
            @RequestHeader(value = "X-Session-Id", defaultValue = "test") final String sessionId) {
        final String description = imageAnalysisService.getImageDescription(image, sessionId);
        return ResponseEntity.ok().body(description);
    }

    // To be polled by the client
    @GetMapping(value = "/caption")
    public ResponseEntity<String> getCaption(@RequestHeader(value = "X-Session-Id", defaultValue = "test") final String sessionId) {
        return Optional.ofNullable(captionService.getCaption(sessionId))
                .map(caption -> ResponseEntity.ok().body(caption))
                .orElse(ResponseEntity.accepted().build());
    }
}
