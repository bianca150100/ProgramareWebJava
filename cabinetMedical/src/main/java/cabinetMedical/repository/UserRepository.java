package cabinetMedical.repository;

import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "select u FROM User u where u.username= :username and u.password= :password")
    List<User> checkExistingUser(String username, String password);

    @Modifying
    @Query(value = "update User u set loggedStatus = 1 where u.username= :username and u.password= :password")
    void loginUser(String username, String password);

    @Modifying
    @Query(value = "update User u set loggedStatus = 0 where u.loggedStatus= 1")
    void logoutAllUsers();

    @Modifying
    @Query(value = "update User u set loggedStatus = 0 where u.username= :username")
    void logoutUser(String username);

    @Query(value = "SELECT u FROM User u WHERE u.userType = :userType")
    List<User> filter(UserType userType);

    public default List<User> getAll() {
        return this.findAll();
    }
}