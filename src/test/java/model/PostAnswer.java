package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class PostAnswer {
    private Integer id;
    private String token;
    private String error;

    public boolean NotCompleteRegister() {
        return this.getId()==null ||
                this.getToken() == null || this.getToken().isEmpty() ||
                !(this.getError() == null);
    }
}

