package cz.kpmg.java.app.sample.controller;

import ch.qos.logback.classic.LoggerContext;
import cz.kpmg.java.app.sample.api.AssetsApi;
import cz.kpmg.java.app.sample.config.MaskLogFilter;
import cz.kpmg.java.app.sample.model.Asset;
import cz.kpmg.java.app.sample.service.AssetsService;
import cz.kpmg.java.app.sample.utils.ResponseEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AssetsController implements AssetsApi {

    @Value(value = "${custom.prop}")
    private String customProperty;

    private final AssetsService assetsService;

    public AssetsController(AssetsService assetsService, @Value(value = "${logging.mask}") String mask) {
        this.assetsService = assetsService;
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.addTurboFilter(new MaskLogFilter(mask));
    }

    @ReadOperation
    @Override
    public ResponseEntity<List<Asset>> getAssetsList() {
        log.info("Custom property result: {}", customProperty);
        List<Asset> result = assetsService.getUserList();
        return ResponseEntityUtils.createResponseEntity(result, HttpStatus.OK);
    }
//
//    @ReadOperation
//    @Override
//    public ResponseEntity<User> getUserById(String id) {
//        try {
//            User result = usersService.getUserById(id);
//            return ResponseEntityUtils.createResponseEntity(result, HttpStatus.OK);
//        } catch (AppException e) {
//            return ResponseEntityUtils.fromException(e);
//        }
//    }
//
//    @WriteOperation
//    @Override
//    public ResponseEntity<User> createUser(User user) {
//        try {
//            User result = usersService.createUser(user);
//            return ResponseEntityUtils.createResponseEntity(result, HttpStatus.CREATED);
//        } catch (AppException e) {
//            return ResponseEntityUtils.fromException(e);
//        }
//    }
//
//    @WriteOperation
//    @Override
//    public ResponseEntity<User> updateUser(String id, User user) {
//        try {
//            User result = usersService.updateUser(user, id);
//            return ResponseEntityUtils.createResponseEntity(result, HttpStatus.OK);
//        } catch (AppException e) {
//            return ResponseEntityUtils.fromException(e);
//        }
//    }
//
//    @DeleteOperation
//    @Override
//    public ResponseEntity<Void> deleteUser(String id) {
//        try {
//            usersService.deleteUser(id);
//            return ResponseEntityUtils.createEmptyResponseEntity(HttpStatus.OK);
//        } catch (AppException e) {
//            return ResponseEntityUtils.fromException(e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<User> updateUserCV(String id, String extension, MultipartFile fileName) {
//        log.debug("id={}", id);
//        log.debug("extension={}", extension);
//        log.debug("file={}, contentType={}, size={}", LogUtils.reflectionToString(fileName), fileName.getContentType(), fileName.getSize());
//        return ResponseEntityUtils.createEmptyResponseEntity(HttpStatus.NOT_IMPLEMENTED);
//    }
}
