package mbm.genai_service.image_analysis;

import org.springframework.web.multipart.MultipartFile;

public interface ImageAnalysisService {
    String getImageDescription(MultipartFile image, String sessionId);
}
