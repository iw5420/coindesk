package com.currency.coindesk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "CURRENCY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column
    @NotEmpty(message = "幣別code為空")
    @Length(max = 20, message = "長度超過20")
    String code;

    @Column
    @NotEmpty(message = "幣別code為空")
    @Length(max = 20, message = "長度超過20")
    String name;

    @Column
    LocalDateTime update_date;

}
