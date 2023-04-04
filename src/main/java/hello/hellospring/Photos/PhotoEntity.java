package hello.hellospring.Photos;

import hello.hellospring.users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String url;

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    private UserEntity user;

    public String getUrl() {
        return url;
    }

    public Long getId() {
        return id;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
