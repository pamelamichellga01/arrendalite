package com.arrendalite.service;

import com.arrendalite.model.Property;
import com.arrendalite.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public List<Property> propertyList(){
        return propertyRepository.findAll();
    }

    public Property findById(Long id){
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
    }
}
