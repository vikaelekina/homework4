package model;

import lombok.*;
import org.junit.jupiter.api.Assertions;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Data {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public boolean isIncomplete() {
        return this.getId()==null ||
                this.getFirst_name() == null || this.getFirst_name().isEmpty() ||
                this.getLast_name() == null || this.getLast_name().isEmpty() ||
                this.getAvatar() == null || this.getAvatar().isEmpty();
    }
}
