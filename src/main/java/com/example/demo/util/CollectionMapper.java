package com.example.demo.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CollectionMapper {
    private final ModelMapper mapper;

    public <S, C extends Collection<S>, D> Set<D> map(C source, Class<D> destination) {
        return source.stream().map(s -> mapper.map(s, destination)).collect(Collectors.toSet());
    }
}