package mbm.genai_service.web;

import mbm.genai_service.caption.CaptionService;
import mbm.genai_service.image_analysis.ImageAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/genai")
public class ImageController {

    private final ImageAnalysisService imageAnalysisService;
    private final CaptionService captionService;

    public ImageController(final ImageAnalysisService imageAnalysisService,
                           final CaptionService captionService) {
        this.imageAnalysisService = imageAnalysisService;
        this.captionService = captionService;
    }

    @PostMapping(value = "/describe-image", consumes = "multipart/form-data")
    public String describeImage(@RequestParam("image") final MultipartFile image) {
        return imageAnalysisService.getImageDescription(image);
    }

    @GetMapping("/get-caption")
    public String getCaption(@RequestParam final String description) {
        return captionService.getCaption(description);
    }
}
