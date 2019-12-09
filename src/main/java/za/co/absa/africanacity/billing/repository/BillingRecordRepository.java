package za.co.absa.africanacity.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.absa.africanacity.billing.domain.BillingRecord;

import java.util.List;

public interface BillingRecordRepository extends JpaRepository<BillingRecord, Long> {
}
