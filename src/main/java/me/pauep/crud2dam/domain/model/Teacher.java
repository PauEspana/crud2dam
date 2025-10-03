package me.pauep.crud2dam.domain.model;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Teacher {
    private int id;
    
    @NonNull
    private String name;
    
    @NonNull
    private String surname;
}
