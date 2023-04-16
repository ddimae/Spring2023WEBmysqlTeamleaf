package ntukhpi.semit.dde.CommonSpring2023.service.impl;

import ntukhpi.semit.dde.CommonSpring2023.entity.Employee;
import ntukhpi.semit.dde.CommonSpring2023.entity.INN;
import ntukhpi.semit.dde.CommonSpring2023.repository.INNRepository;
import ntukhpi.semit.dde.CommonSpring2023.service.INNService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INNServiceImpl implements INNService {
    private INNRepository innRepository;

    public INNServiceImpl(INNRepository innRepository) {
        super();
        this.innRepository = innRepository;
    }

    @Override
    public List<INN> getAllINNs() {
        return innRepository.findAll();
    }

    @Override
    public INN insert(INN inn) {
        return innRepository.save(inn);
    }

    @Override
    public INN getINNById(Long id) {
        return innRepository.findById(id).get();
    }

    @Override
    public INN updateINN(INN inn) {
        return innRepository.save(inn);
    }

    @Override
    public void deleteINNById(Long id) {
        innRepository.deleteById(id);
    }

    @Override
    public INN getINNByOwner(Employee empl) {
        return innRepository.getINNByOwner(empl);
    }
}
