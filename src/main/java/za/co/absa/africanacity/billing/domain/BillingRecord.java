package za.co.absa.africanacity.billing.domain;

import com.github.ffpojo.metadata.positional.annotation.PositionalField;
import com.github.ffpojo.metadata.positional.annotation.PositionalRecord;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@PositionalRecord
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "billing_extract")
public class BillingRecord implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "client_swift_address")
    private String clientSwiftAddress;

    @NonNull
    @Column(name = "service_name")
    private String serviceName;

    @NonNull
    @Column(name = "sub_service_name")
    private String subServiceName;

    @NonNull
    @Column(name = "date")
    private String date;

    @NonNull
    @Column(name = "usage_status")
    private String usageStatus;

    @PositionalField(initialPosition = 1, finalPosition = 12)
    public String getClientSwiftAddress() {
        return clientSwiftAddress;
    }

    @PositionalField(initialPosition = 13, finalPosition = 20)
    public String getServiceName() {
        return serviceName;
    }

    @PositionalField(initialPosition = 21, finalPosition = 25)
    public String getSubServiceName() {
        return subServiceName;
    }

    @PositionalField(initialPosition = 26, finalPosition = 33)
    public String getDate() {
        return date;
    }

    @PositionalField(initialPosition = 34, finalPosition = 40)
    public String getUsageStatus() {
        return usageStatus;
    }
}
