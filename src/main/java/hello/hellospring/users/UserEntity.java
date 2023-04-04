package hello.hellospring.users;


import hello.hellospring.Photos.PhotoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sex;
    private Long age;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PhotoEntity> photos=new ArrayList<>();

    public void addPhoto(PhotoEntity photoEntity){
        photos.add(photoEntity);
        photoEntity.setUser(this);

    }

    public boolean deletePhoto(Long photo_id){
        for(PhotoEntity photoEntity: photos){
            if(photoEntity.getId()==photo_id){
                photos.remove(photoEntity);
                photoEntity.setUser(null);
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Long getAge() {
        return age;
    }
    public List<PhotoEntity> getPhotos() {
        return photos;
    }

    public UserDto toDto() {
        return UserDto.builder()
                .name(name)
                .sex(sex)
                .age(age)
                .photos(photos)
                .build();
    }


}
