package org.example.transfferingapp.user;

import java.math.BigDecimal;

public interface UserInterface {
    public BigDecimal deposit(BigDecimal amount);
    public BigDecimal withdraw(BigDecimal amount);
}
