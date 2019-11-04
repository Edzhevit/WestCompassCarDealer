package services.implementations;

import models.entities.User;
import models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import services.HashingService;
import services.UserValidationService;
import services.UsersService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private final EntityManager entityManager;
    private final HashingService hashingService;
    private final ModelMapper modelMapper;
    private final UserValidationService userValidationService;

    @Inject
    public UsersServiceImpl(EntityManager entityManager, HashingService hashingService, ModelMapper modelMapper, UserValidationService userValidationService) {
        this.entityManager = entityManager;
        this.hashingService = hashingService;
        this.modelMapper = modelMapper;
        this.userValidationService = userValidationService;
    }

    @Override
    public void register(String username, String email, String password, String confirmPassword) throws Exception {

        if (!userValidationService.canCreateUser(username, email, password, confirmPassword)){
            throw new Exception("User cannot be created!");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashingService.hash(password));

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public UserServiceModel login(String username, String password) {
        List<User> users = entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        if (users.isEmpty()){
            return null;
        }

        User user = users.get(0);

        if (!user.getPassword().equals(hashingService.hash(password))){
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }
}
