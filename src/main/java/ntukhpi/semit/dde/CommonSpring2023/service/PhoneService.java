package ntukhpi.semit.dde.CommonSpring2023.service;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.entity.Phone;

import java.util.List;

public interface PhoneService {
    List<Phone> getAllPhones();

    Phone insert(Phone phone);

    Phone getPhoneById(Long id);

    Phone updatePhone(Phone phone);

    void deletePhoneNById(Long id);

    List<Phone> getPhonesByOwner(Employee owner);

}
