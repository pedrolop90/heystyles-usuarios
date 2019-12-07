package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.seguridad.cliente.RolClient;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.CargoService;
import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.dto.CargoExtended;
import domain.RolAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class CargoServiceImpl
        extends ServiceImpl<Cargo, CargoEntity, Long> implements CargoService {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private RolClient rolClient;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<CargoEntity, Long> getDao() {
        return cargoDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insert(CargoExtended request) {
        Long id = super.insert(request.getCargo());
        Optional.ofNullable(createRol(request))
                .ifPresent(s -> {
                    CargoEntity cargoEntity = cargoDao.findOne(id);
                    cargoEntity.setIdSecurity(s);
                    cargoDao.save(cargoEntity);
                });
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(CargoExtended request) {
        update(request.getCargo());
        CargoEntity cargoEntity = cargoDao.findOne(request.getCargo().getId());
        RolAuth0 rolAuth0 = new RolAuth0();
        rolAuth0.setNombre(request.getCargo().getNombre());
        rolAuth0.setDescripcion(request.getCargo().getNombre());
        rolAuth0.setPermisos(request.getPermisos());
        rolClient.update(cargoEntity.getIdSecurity(), rolAuth0);
    }

    private Long createRol(CargoExtended cargoExtended) {
        RolAuth0 rolAuth0 = new RolAuth0();
        rolAuth0.setNombre(cargoExtended.getCargo().getNombre());
        rolAuth0.setDescripcion(cargoExtended.getCargo().getNombre());
        rolAuth0.setPermisos(cargoExtended.getPermisos());
        return rolClient.create(rolAuth0);
    }

    @Override
    public CargoExtended getCargoExtended(Long cargoId) {
        CargoEntity cargoEntity = Optional.ofNullable(cargoDao.findOne(cargoId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(messageSource.getMessage(
                        MessageKeys.CARGO_NOT_FOUND, new String[]{String.valueOf(cargoId)}, getLocale())));
        return getConverterService().convertTo(cargoEntity, CargoExtended.class);
    }

    @Override
    public Cargo getCargo(Long cargoId) {
        CargoEntity cargoEntity = Optional.ofNullable(cargoDao.findOne(cargoId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(messageSource.getMessage(
                        MessageKeys.CARGO_NOT_FOUND, new String[]{String.valueOf(cargoId)}, getLocale())));
        return getConverterService().convertTo(cargoEntity, Cargo.class);
    }

    @Override
    public List<Cargo> getCargos() {
        List<CargoEntity> cargoEntities = cargoDao.findByNivelGreaterThanEqual(1L);
        return getConverterService().convertTo(cargoEntities, Cargo.class);
    }
}
