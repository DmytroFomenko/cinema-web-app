package fdv.service;


import fdv.entity.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getAllServices();

    List<Service> getAllAvailableServices();

    Service findServiceById(Long serviceId);
}
