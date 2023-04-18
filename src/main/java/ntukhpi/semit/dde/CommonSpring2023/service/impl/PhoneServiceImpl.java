package ntukhpi.semit.dde.CommonSpring2023.service.impl;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.entity.Phone;
import ntukhpi.semit.dde.CommonSpring2023.repository.PhoneRepository;
import ntukhpi.semit.dde.CommonSpring2023.service.PhoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        super();
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone insert(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone getPhoneById(Long id) {
        return phoneRepository.findById(id).get();
    }

    @Override
    public Phone updatePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public void deletePhoneNById(Long id) {
        phoneRepository.deleteById(id);
    }

    @Override
    public List<Phone> getPhonesByOwner(Employee owner) {
        return phoneRepository.getPhonesByOwner(owner);
    }
}

