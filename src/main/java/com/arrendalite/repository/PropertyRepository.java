package com.arrendalite.repository;

import com.arrendalite.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property , Long> {
}
