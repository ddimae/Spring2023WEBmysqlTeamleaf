package ntukhpi.semit.dde.CommonSpring2023.service;

import ntukhpi.semit.dde.CommonSpring2023.entity.INN;

import java.util.List;

public interface INNService {
    List<INN> getAllINNs();

    INN insert(INN inn);

    INN getINNById(Long id);

    INN updateINN(INN inn);

    void deleteINNById(Long id);

}
