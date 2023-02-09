package github.lionfish.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String shortLink;

    @Column
    private String fullLink;

    @Column
    private String timestamp;

    @Column
    private Date createTime;

    @Column
    private Integer expired;
}
