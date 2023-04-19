package idusw.springboot.boradthymleaf.domain;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
@EqualsAndHashCode
public class Member {
    private  Long seq;
    private  String email;
    private  String name;
    private String pw;
}
