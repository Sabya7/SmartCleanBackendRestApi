package com.sabya.SmartCleanBackendRestApi.repository;

import com.sabya.SmartCleanBackendRestApi.model.StepTimer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepTimerRepository extends JpaRepository<StepTimer,Integer> {
}
