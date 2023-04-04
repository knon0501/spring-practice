package hello.hellospring.users;

import hello.hellospring.Photos.PhotoEntity;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
   private String sex;
   private  Long age;

   private List<PhotoEntity> photos;
   public UserEntity toEntity(){

       UserEntity userEntity=UserEntity.builder()
               .name(name)
               .sex(sex)
               .age(age)
               .photos(photos)
               .build();
       for(PhotoEntity photo: photos)
            photo.setUser(userEntity);
       return userEntity;
   }
   public UserEntity toEntity(long id){
        UserEntity userEntity=UserEntity.builder()
                .id(id)
                .name(name)
                .sex(sex)
                .age(age)
                .photos(photos)
                .build();
       for(PhotoEntity photo: photos)
           photo.setUser(userEntity);
       return userEntity;
    }
}
