package com.ftn.MyHousebackend.service;

import com.ftn.MyHousebackend.controller.AuthenticationController;
import com.ftn.MyHousebackend.dto.ObjectDTO;
import com.ftn.MyHousebackend.dto.ObjectMessageDTO;
import com.ftn.MyHousebackend.dto.ReportDTO;
import com.ftn.MyHousebackend.dto.UserDTO;
import com.ftn.MyHousebackend.exception.InvalidArgumentException;
import com.ftn.MyHousebackend.exception.ObjectNotFound;
import com.ftn.MyHousebackend.exception.UserNotFoundException;
import com.ftn.MyHousebackend.model.Object;
import com.ftn.MyHousebackend.model.ObjectMessage;
import com.ftn.MyHousebackend.model.User;
import com.ftn.MyHousebackend.model.enums.MessageType;
import com.ftn.MyHousebackend.model.enums.ObjectType;
import com.ftn.MyHousebackend.model.enums.UserRole;
import com.ftn.MyHousebackend.repository.ObjectMessageRepository;
import com.ftn.MyHousebackend.repository.ObjectRepository;
import com.ftn.MyHousebackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMessageRepository objectMessageRepository;

    public List<ObjectDTO> getOwnerObjects(String username) {
        //provera da li postoji user sa username
        User user = userService.findByUsername(username);

        List<ObjectDTO> objectsList = new ArrayList<>();
        objectRepository.findAllByOwnerId(user.getId()).forEach(object -> {
            objectsList.add(new ObjectDTO(object));
        });
        objectRepository.findAllByOwnerIdNot(user.getId()).forEach(object -> {
            if(object.getTenants().contains(user)){
                objectsList.add(new ObjectDTO(object));
            }
        });

        return objectsList;
    }

    public UserDTO addTennatToObject(long objectId, String tenantUsername) {
        Optional<Object> optObject = objectRepository.findById(objectId);
        if (optObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Optional<User> optUser = userRepository.findByUsername(tenantUsername);
        if(optUser.isEmpty()) throw new UserNotFoundException("User not found!");

        Object object = optObject.get();
        User user = optUser.get();

        if (object.getOwner().getUsername().equals(user.getUsername())) throw new InvalidArgumentException("Object owner can't be tenant!");

        if(user.getRole() == UserRole.ADMIN)  throw new InvalidArgumentException("Admin can't be tenant!");

        if(object.getTenants().contains(user)) throw new InvalidArgumentException("User is already tenant!");

        user.addObject(object);
        userRepository.saveAndFlush(user);

        object.addTenant(user);
        objectRepository.saveAndFlush(object);

        return new UserDTO(user);
    }

    public List<UserDTO> getPotentialTenants(long objectId) {

        Optional<Object> optObject = objectRepository.findById(objectId);
        if (optObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Object object = optObject.get();

        List<User> potentialTenants = userRepository.findByRoleNotAndDeletedIsFalse(UserRole.ADMIN);

        List<UserDTO> userDTOS = new ArrayList<>();
        potentialTenants.forEach(user -> {
            if (!object.getTenants().contains(user) && object.getOwner()!=user){
                userDTOS.add(new UserDTO(user));
            }
        });

        return userDTOS;
    }

    public ReportDTO getReport (long id, String dateFrom, String dateTo){

        Optional<Object> optObject = objectRepository.findById(id);
        if (optObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Object object = optObject.get();

        LocalDate startDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        LocalDate endDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MMM dd yyyy"));

        List<ObjectMessage> messages = objectMessageRepository.findAllByObject_IdAndDateIsAfterAndDateIsBeforeAndMessageTypeIsNotOrderByDateAsc(id, startDate, endDate, MessageType.MESSAGE);

        ReportDTO report = new ReportDTO();
        report.setObject(new ObjectDTO(object));
        report.setDateFrom(startDate.toString());
        report.setDateTo(endDate.toString());
        List<ObjectMessageDTO> messageDTOs = new ArrayList<>();
        messages.forEach(message -> {
            messageDTOs.add(new ObjectMessageDTO(message));
        });
        report.setMessages(messageDTOs);

        return report;
    }

    public ObjectDTO changeObjectOwner(long objectId, long userId){
        Optional<Object> optObject = objectRepository.findById(objectId);
        if (optObject.isEmpty()) throw new ObjectNotFound("Object not found!");
        Object object = optObject.get();

        Optional<User> optionalUser = userRepository.findByIdAndDeletedIsFalse(userId);
        if(optionalUser.isEmpty()) throw new UserNotFoundException("User not found!");
        User user = optionalUser.get();


        if (user.getRole() != UserRole.OWNER) throw new InvalidArgumentException("User is not OWNER!");

        object.setOwner(user);
        objectRepository.saveAndFlush(object);

        return new ObjectDTO(object);
    }

    public List<ObjectDTO> getAllObjects(){
        List<ObjectDTO> objects = new ArrayList<>();
        objectRepository.findAll().forEach(object -> {
            objects.add(new ObjectDTO(object));
        });
        return objects;
    }

    public ObjectDTO addObject(ObjectDTO newObjectDTO){
        Object newObject = new Object();
        newObject.setName(newObjectDTO.getName());

        Optional<User> optionalUser = userRepository.findByIdAndDeletedIsFalse(newObjectDTO.getOwner().getId());
        if(optionalUser.isEmpty()) throw new UserNotFoundException("User not found!");
        User user = optionalUser.get();
        if (user.getRole() != UserRole.OWNER) throw new InvalidArgumentException("User is not OWNER!");

        newObject.setOwner(user);
        newObject.setObjectType(ObjectType.valueOf(newObjectDTO.getType()));
        newObject.setTenants(new ArrayList<>());
        objectRepository.saveAndFlush(newObject);
        return new ObjectDTO(newObject);
    }
}
