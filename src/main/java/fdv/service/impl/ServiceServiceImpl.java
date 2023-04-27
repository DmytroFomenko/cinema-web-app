package fdv.service.impl;

import fdv.repository.ServiceRepository;
import fdv.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    @Override
    public List<fdv.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public List<fdv.entity.Service> getAllAvailableServices() {
        return serviceRepository.findAll().stream().filter(fdv.entity.Service::getAvailable).toList();
    }

    @Override
    public fdv.entity.Service findServiceById(Long serviceId) {
        return serviceRepository.findServiceById(serviceId);
    }
}
