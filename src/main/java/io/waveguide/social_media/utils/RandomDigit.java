package io.waveguide.social_media.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomDigit {

    public int getFourDigitNumber(){
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }
}
