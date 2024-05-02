package com.example.asadmin.mapper;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.model.DiningSession;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class DiningSessionMapper {

    private EstablishmentMapper establishmentMapper;

    public DiningSessionMapper(EstablishmentMapper establishmentMapper){
        this.establishmentMapper = establishmentMapper;
    };

    public DiningSessionDTO toDTO(DiningSession diningSession){
        if(diningSession == null){
            return null;
        }

        DiningSessionDTO diningSessionDTO = new DiningSessionDTO();

        diningSessionDTO.setId(diningSession.getId());
        diningSessionDTO.setEstablishmentDTO(establishmentMapper.toDTO(diningSession.getEstablishment()));
        diningSessionDTO.setStartDateTime(diningSession.getStartDateTime().toString());
        diningSessionDTO.setClose(diningSession.getClose());

        return diningSessionDTO;
    }

    public DiningSession toEntity(DiningSessionDTO diningSessionDTO){
        if (diningSessionDTO == null){
            return null;
        }

        DiningSession diningSession = new DiningSession();

        diningSession.setId(diningSessionDTO.getId());
        if(diningSessionDTO.getEstablishmentDTO() != null && diningSessionDTO.getEstablishmentDTO().getId() != null) {
            diningSession.setEstablishment(establishmentMapper.toEntity(diningSessionDTO.getEstablishmentDTO()));
        }
        diningSession.setStartDateTime(ZonedDateTime.parse(diningSessionDTO.getStartDateTime()));
        diningSession.setClose(diningSessionDTO.getClose());

        return diningSession;
    }
}
