package com.school.forum.dto;

import com.school.forum.validation.NotBlankUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForumRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;

    private String description;

    @NotNull(message = "Effective date must not be null")
    @Future(message = "Effective date must be in the future")
    private Date effectiveDate;

    @Future(message = "Expiry date must be in the future")
    private Date expiryDate;

    @NotBlankUUID(message = "Course ID must not be null")
    private UUID courseId;
}
