package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GetAnswer {
    private Data data;
    private Support support;

    public boolean isIncomplete() {
        return this.data.isIncomplete()||
                this.support.isIncomplete();
    }
}
