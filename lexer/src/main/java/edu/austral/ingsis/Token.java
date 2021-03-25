package edu.austral.ingsis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    private String value;
    private TokenType tokenType;
    private Integer line;
    private Integer index;

}
