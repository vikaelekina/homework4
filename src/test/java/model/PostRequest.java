package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class PostRequest {
    private String email;
    private String password;
}
