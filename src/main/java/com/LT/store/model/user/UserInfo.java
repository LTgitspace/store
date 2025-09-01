package com.LT.store.model.user;
import com.LT.store.model.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.dialect.SpannerSqlAstTranslator;
import java.util.UUID;
@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")

public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "role_name", referencedColumnName = "role_id")
    private Role role;
}
