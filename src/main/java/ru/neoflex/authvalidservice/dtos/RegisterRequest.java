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
public class RegisterRequest {
    @Schema(
            description = "firstName",
            name = "firstName",
            example = "ivan"
    )
    private String firstName;
    @Schema(
            description = "lastName",
            name = "lastName",
            example = "ivanov"
    )
    private String lastName;
    @Schema(
            description = "username",
            name = "username",
            example = "ivan80"
    )
    private String username;
    @Schema(
            description = "password",
            name = "password",
            example = "1234"
    )
    private String password;
}
