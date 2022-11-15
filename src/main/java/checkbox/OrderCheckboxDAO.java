package mipl.icmodule.checkbox;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCheckboxDAO {

public Long ibid;
public Long investigationId;
public String billNo;
public BigDecimal icAmount;
public BigDecimal preparedAmount;
public BigDecimal allocatedAmount;
public Long clientId;
public Long proId;
public Long icId;
public BigDecimal calculatedAmount;
public String checkBox;

}
