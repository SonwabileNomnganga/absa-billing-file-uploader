package za.co.absa.africanacity.billing.service.impl;

import com.github.ffpojo.FFPojoHelper;
import com.github.ffpojo.exception.FFPojoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.absa.africanacity.billing.domain.BillingRecord;
import za.co.absa.africanacity.billing.repository.BillingRecordRepository;
import za.co.absa.africanacity.billing.service.BillingUploaderService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BillingUploaderServiceImpl implements BillingUploaderService {

    @Autowired
    private BillingRecordRepository repository;

    @Override
    public void saveData(String data) throws FFPojoException {

        log.info("To save to database, the string " + data);
        if(data != null) {
            FFPojoHelper ffpojo = FFPojoHelper.getInstance();
            repository.saveAndFlush(ffpojo.createFromText(BillingRecord.class, data));
        }
    }
}
