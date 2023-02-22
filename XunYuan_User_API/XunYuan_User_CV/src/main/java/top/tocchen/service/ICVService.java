package top.tocchen.service;

import top.tocchen.entity.CVEntity;
import top.tocchen.enums.UserCVUpdateType;

/**
 * @author tocchen
 * @date 2023/2/20 14:18
 * @since jdk 1.8
 **/
public interface ICVService {

    String saveCV(CVEntity entity);
    <E> Long  updateCVById(UserCVUpdateType type, E data,String id);
    CVEntity queryCVById(String id);
    Long removeCVById(String id);
}
