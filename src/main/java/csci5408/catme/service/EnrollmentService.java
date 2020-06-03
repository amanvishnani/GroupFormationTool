package csci5408.catme.service;

import csci5408.catme.dto.CourseSummary;
import csci5408.catme.dto.UserSummary;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnrollmentService {
    boolean enrollStudent(CourseSummary c, UserSummary u);
    boolean enrollStudents(CourseSummary c, List<UserSummary> u);
    boolean enrollStudents(CourseSummary c, MultipartFile file);
}
