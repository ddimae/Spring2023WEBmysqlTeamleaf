package ntukhpi.semit.dde.CommonSpring2023.repository;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.entity.INN;
import ntukhpi.semit.dde.CommonSpring2023.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone,Long> {

    List<Phone> getPhonesByOwner(Employee owner);

}
