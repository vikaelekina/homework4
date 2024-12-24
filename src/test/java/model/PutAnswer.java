package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class PutAnswer {
    private String name;
    private String job;
    private String updatedAt;
}
