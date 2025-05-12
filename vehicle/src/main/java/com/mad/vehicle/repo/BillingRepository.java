package com.mad.vehicle.repo;

import com.mad.vehicle.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {}
