package org.mrtxee.bnksrs.accountservice.service;
/*
* номер счета accountNumber генерируется автоматически: “4003”+<8 рандомных цифр>
* */
public interface AccountNumberGenerator {
    public long generate();
}
