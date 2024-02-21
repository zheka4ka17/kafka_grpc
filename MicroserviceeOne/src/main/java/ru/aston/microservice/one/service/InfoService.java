package ru.aston.microservice.one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.microservice.one.entity.Info;
import ru.aston.microservice.one.repository.InfoRepository;


@Service
public class InfoService{

private InfoRepository infoRepository;
    @Autowired
    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public void save(Info info) {
        infoRepository.save(info);

    }
}
