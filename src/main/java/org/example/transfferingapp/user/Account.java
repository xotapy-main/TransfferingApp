package org.example.transfferingapp.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import org.example.transfferingapp.user.UserInterface.UserInterface;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Entity
@Table(name = "User Account")
public class Account implements UserInterface {
    @Id
    @NonNull
    private final String id = generateId();
    @NonNull
    private String name;
    @NonNull
    private String userName;
    @Email
    private String email;
    @NonNull
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal withdrawAmount = BigDecimal.ZERO;
    private BigDecimal depositAmount = BigDecimal.ZERO;


    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    //Функция генерации UUID с префиксом
    private String generateId() {
        String hash = sha256(name + "UnitTest").substring(0, 12);
        return userName + "_" + hash;
    }
    private String generateUserName() {
        return sha256(name + "UnitTest").substring(0, 12);
    }

    public @NonNull String getId() {
        return id;
    }

    public @NonNull String getName() {
        return name;
    }

    public @NonNull BigDecimal getBalance() {
        return balance;
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        return balance.add(amount);
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        return null;
    }

    public BigDecimal getDepositCount() {
        return depositAmount;
    }

    public BigDecimal getWithdrawCount() {
        return withdrawAmount;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setBalance(@NonNull BigDecimal balance) {
        this.balance = balance;
    }

    public void setWithdrawCount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public void setDepositCount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

}
