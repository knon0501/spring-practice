package hello.hellospring.users;

import hello.hellospring.Photos.PhotoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAllUser(){
        return userRepository.findAll();
    }

    public UserDto findUserbyid(Long id){
        UserEntity userEntity=userRepository.findById(id).orElseThrow(()->new NoSuchElementException());
        return userEntity.toDto();
    }

    public UserDto createUser(UserDto userDto)
    {
        UserEntity userEntity = userDto.toEntity();
        UserEntity returnEntity = userRepository.save(userEntity);
        return returnEntity.toDto();
    }

    public UserDto deletebyId(Long id){
        UserDto userDto=findUserbyid(id);
        userRepository.deleteById(id);
        return userDto;
    }

    public UserDto updatebyId(Long id,UserDto userDto)
    {
        UserEntity pastEntity=userRepository.findById(id).orElseThrow(()->new NoSuchElementException());

        for(PhotoEntity photo: pastEntity.getPhotos())
            photo.setUser(null);

        UserEntity userEntity=userDto.toEntity(id);
        UserEntity returnEntity = userRepository.save(userEntity);
        return returnEntity.toDto();

    }

    public UserDto addPhoto(Long id, PhotoEntity photoEntity)
    {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->new NoSuchElementException());
        userEntity.addPhoto(photoEntity);
        UserEntity returnEntity = userRepository.save(userEntity);
        System.out.println(returnEntity.getPhotos());
        return returnEntity.toDto();
    }


    public UserDto deletePhoto(Long user_id,Long photo_id)
    {
        UserEntity userEntity=userRepository.findById(user_id).orElseThrow(()->new NoSuchElementException());
        if(userEntity.deletePhoto(photo_id)){
            UserEntity returnEntity=userRepository.save(userEntity);
            return returnEntity.toDto();
        }
        else{
            return userEntity.toDto();
        }

    }


}
