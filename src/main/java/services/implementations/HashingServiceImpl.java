package services.implementations;

import services.HashingService;

public class HashingServiceImpl implements HashingService {

    @Override
    public String hash(String value) {
        return "@" + value + "!";
    }
}
