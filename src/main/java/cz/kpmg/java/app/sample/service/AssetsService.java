package cz.kpmg.java.app.sample.service;

import cz.kpmg.java.app.sample.model.Asset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AssetsService {

    private static final String ASSET_NOT_FOUND = "Asset not found.";

//    private final UsersRepository usersRepository;

    public AssetsService(Object usersRepository) {
//        this.usersRepository = usersRepository;
    }

    public List<Asset> getUserList() {
//        List<AssetEntity> entityList = IterableUtils.toList(usersRepository.findAll());
        List<Asset> entityList = new ArrayList<>();
        log.debug("Found entity list: {}", entityList);
//        return entityList.stream().map(UserMapper.MAPPER::mapToUser).toList();
        return entityList;
    }

//    public User getUserById(String id) throws NotFoundException {
//        Optional<AssetEntity> entityOpt = usersRepository.findById(id);
//        if (entityOpt.isEmpty()) {
//            log.warn(USER_NOT_FOUND + " id={}", id);
//            throw new NotFoundException(USER_NOT_FOUND);
//        }
//        log.debug("Found user entity: {}", entityOpt.get());
//        return UserMapper.MAPPER.mapToUser(entityOpt.get());
//    }
//
//    public User createUser(User user) throws BadRequestException {
//        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getSurname())) {
//            throw new BadRequestException("Bad input.");
//        }
//        user.setId(UUID.randomUUID().toString());
//        usersRepository.save(UserMapper.MAPPER.mapToUserEntity(user));
//        return user;
//    }
//
//    public User updateUser(User user, String id) throws BadRequestException, NotFoundException {
//        Optional<AssetEntity> entityOpt = usersRepository.findById(id);
//        if (entityOpt.isEmpty()) {
//            log.warn("User not found. [id={}]", id);
//            throw new NotFoundException(USER_NOT_FOUND);
//        }
//        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getSurname())) {
//            throw new BadRequestException("Bad input.");
//        }
//        user.setId(id);
//        usersRepository.save(UserMapper.MAPPER.mapToUserEntity(user));
//        return user;
//    }
//
//    public void deleteUser(String id) throws NotFoundException {
//        Optional<AssetEntity> entityOpt = usersRepository.findById(id);
//        if (entityOpt.isEmpty()) {
//            log.warn("User not found. [id={}]", id);
//            throw new NotFoundException(USER_NOT_FOUND);
//        }
//        log.debug("Found user entity: {}", entityOpt.get());
//        usersRepository.delete(entityOpt.get());
//    }

}
