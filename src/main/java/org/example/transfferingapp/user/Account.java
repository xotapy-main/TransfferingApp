package org.example.transfferingapp.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account implements UserInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Setter
    @Getter
    @Email
    private String email;
    @Setter
    @Getter
    @Column(nullable = false, unique = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String accountName;
    @NonNull
    private BigDecimal balance = BigDecimal.ZERO;
    @Setter
    @Getter
    @NonNull
    private BigDecimal withdrawAmount = BigDecimal.ZERO;
    @Setter
    @Getter
    @NonNull
    private BigDecimal depositAmount = BigDecimal.ZERO;

    public Account() {}

    @Override
    public BigDecimal withdraw(@NotNull @Positive BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException(amount.toString());
        }
        this.balance = this.balance.subtract(amount);
        this.withdrawAmount = this.withdrawAmount.add(BigDecimal.ONE);
        return this.balance;
    }

    @Override
    public BigDecimal deposit(@NotNull @Positive BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be strictly positive");
        }
        this.balance = this.balance.add(amount);
        this.depositAmount = this.depositAmount.add(amount);
        return this.balance;
    }
}
