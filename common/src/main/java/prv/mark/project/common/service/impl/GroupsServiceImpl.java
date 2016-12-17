package prv.mark.project.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import prv.mark.project.common.entity.GroupsEntity;
import prv.mark.project.common.repository.GroupsRepository;
import prv.mark.project.common.service.GroupsService;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of the {@link GroupsService} interface.
 *
 * @author mlglenn.
 */
@Service
public class GroupsServiceImpl implements GroupsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupsServiceImpl.class);

    @Value("#{systemProperties['ENVIRONMENT']}")
    private String env;

    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    public Optional<GroupsEntity> findByGroupId(String groupId) {
        LOGGER.debug("GroupsServiceImpl.findById({})", groupId);
        return Optional.ofNullable(groupsRepository.findByGroupId(groupId).get());
    }

    @Override
    public List<GroupsEntity> findAll() {
        LOGGER.debug("GroupsServiceImpl.findAll()");
        return groupsRepository.findAll();
    }


    @Override
    public GroupsEntity save(GroupsEntity entity) {
        LOGGER.debug("GroupsServiceImpl.save({})", entity.toString());
        return groupsRepository.saveAndFlush(entity);
    }
}
