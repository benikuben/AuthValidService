package ru.neoflex.authvalidservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    @Schema(
            description = "token",
            name = "token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6IlRlc3QiLCJzdWIiOiJUZXN0IiwiaWF0IjoxNjk4MjU1OTQxLCJleHAiOjE2OTgyNTk1NDF9.QbRsikNdvQFeGRkOGq3tabsxwoRnOG0FtKbIPlLBz4s"
    )
    private String token;
}
