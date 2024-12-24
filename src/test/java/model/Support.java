package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Support {
    private  String url;
    private  String text;

    public boolean isIncomplete() {
        return this.getUrl() == null || this.getUrl().isEmpty() ||
                this.getText() == null || this.getText().isEmpty();
    }
}
