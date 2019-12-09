package za.co.absa.africanacity.billing.service;

import com.github.ffpojo.exception.FFPojoException;

import java.util.List;
import java.util.Optional;

public interface BillingUploaderService {
    void saveData(String data) throws FFPojoException;
}
