package com.cse364.infra.dtos;

import java.util.List;

public class OccupationDto {
    public int id;
    public String name;
    public List<String> aliases;

    public OccupationDto(int id, String name, List<String> aliases) {
        this.id = id;
        this.name = name;
        this.aliases = aliases;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OccupationDto)) { return false; }
        OccupationDto occupationDto = (OccupationDto) o;
        return id == occupationDto.id &&
                name.equals(occupationDto.name) &&
                aliases.equals(occupationDto.aliases);
    }
}