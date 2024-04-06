package tacos.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table
public class PancakeOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private LocalDateTime placedAt;
    @NotBlank(message = "必填项")
    private String name;
    @NotBlank(message = "必填项")
    private String street;
    @NotBlank(message = "必填项")
    private String city;
    @NotBlank(message = "必填项")
    private String province;
    @NotBlank(message = "必填项")
    private String country;
    @NotBlank(message = "必填项")
    private String postCode;

    //@CreditCardNumber(message = "卡号不合法")
    private String ccNumber;    // 信用卡
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([2-9][0-9])$", message = "输入格式为 MM/YY")  // 日期格式校验
    private String ccExpiration;    // 信用卡过期时间
    private String ccCVV;   // 信用卡验证码

    private List<Pancake> pancakes = new ArrayList<>();

    public void addPancake(Pancake pancake){
        pancakes.add(pancake);
    }
}
