package org.example.transfferingapp.user.UserInterface;

import java.math.BigDecimal;

public interface UserInterface {
    public BigDecimal getBalance();
    public BigDecimal deposit(BigDecimal amount);
    public BigDecimal withdraw(BigDecimal amount);
}
